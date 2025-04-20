package ch7.v1;

import java.util.List;

public class Notification {
    final private String message;
    final private List<Medium> supportedMedia;
    final private List<DispatchTime> dispatchTimes;

    public Notification(String message, List<Medium> supportedMedia, List<DispatchTime> times) {
        this.message = message;
        this.supportedMedia = supportedMedia;
        this.dispatchTimes = times;
    }
}
