package com.enshahar.peoplegrow.library.csv;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class CsvParserLibrary<T> {
    public enum Mode {
        IGNORE_ERRORS
    }

    private Mode mode;
    private Class<T> objectType;

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setObjectType(Class<T> objectType) {
        this.objectType = objectType;
    }

    public List<T> parse(String csv) {
        List<T> result = new ArrayList<>();

        // Split the CSV into lines
        String[] lines = csv.split("\n");
        if (lines.length < 2) {
            throw new IllegalArgumentException("CSV must have a header and at least one data row.");
        }

        // Extract headers
        String[] headers = parseCsvLine(lines[0]);

        // Process each data row
        for (int i = 1; i < lines.length; i++) {
            String[] values = parseCsvLine(lines[i]);
            if (values.length != headers.length) {
                throw new IllegalArgumentException("Row does not match header column count.");
            }

            // Create an instance of the target type using reflection
            try {
                // Use the canonical constructor for records
                var constructor = objectType.getDeclaredConstructors()[0];
                Object[] args = new Object[headers.length];

                for (int j = 0; j < headers.length; j++) {
                    String fieldValue = values[j].trim();
                    var parameterType = constructor.getParameterTypes()[j];
                    args[j] = convertValue(parameterType, fieldValue);
                }

                @SuppressWarnings("unchecked")
                T object = (T) constructor.newInstance(args);
                result.add(object);
            } catch (Exception e) {
                throw new RuntimeException("Error creating object of type " + objectType.getName(), e);
            }
        }

        return result;
    }


    private String[] parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"' && (i == 0 || line.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes; // Toggle quote state
            } else if (c == ',' && !inQuotes) {
                values.add(current.toString());
                current.setLength(0); // Reset the buffer
            } else {
                current.append(c);
            }
        }

        values.add(current.toString()); // Add the last value
        return values.toArray(new String[0]);
    }


    private Object convertValue(Class<?> fieldType, String value) {
        if (fieldType == String.class) {
            return value;
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (fieldType == java.time.LocalDate.class) {
            return java.time.LocalDate.parse(value);
        }
        throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
    }
}
