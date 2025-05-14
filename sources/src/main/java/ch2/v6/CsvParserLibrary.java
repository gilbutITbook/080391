package ch2.v6;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvParserLibrary {

    public CsvParserLibrary() {}

    public void setObjectType(Class<?> objectType) {
        this.objectType = objectType;
    }
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<?> parse(String csv) {
        List<Object> result = new ArrayList<>();
        String[] lines = csv.split("\n");

        if (lines.length < 2) {
            return result; // 데이터가 없으면 빈 리스트 반환
        }

        String[] headers = lines[0].split(",");
        for (int i = 1; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            try {
                Object instance = objectType.getDeclaredConstructor().newInstance();
                for (int j = 0; j < headers.length; j++) {
                    String fieldName = headers[j].trim();
                    Field field = objectType.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(instance, convertValue(field.getType(), values[j].trim()));
                }
                result.add(instance);
            } catch (Exception e) {
                if (mode != Mode.IGNORE_ERRORS) {
                    throw new RuntimeException("CSV 파싱 중 오류 발생", e);
                }
            }
        }
        return result;
    }

    private Object convertValue(Class<?> type, String value) throws UnsupportedOperationException {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == java.time.LocalDate.class) {
            return java.time.LocalDate.parse(value);
        } else if (type == java.time.LocalDateTime.class) {
            return java.time.LocalDateTime.parse(value);
        }
        throw new UnsupportedOperationException("Unsupported type: " + type);
    }

    public enum Mode {
        IGNORE_ERRORS,
        THROW_EXCEPTIONS
    }

    private Mode mode = Mode.IGNORE_ERRORS;
    private Class<?> objectType = Object.class;
}

record EmployeeParsedData(
        String name,
        String email,
        LocalDate startingDate,
        String role
) { }