package ch7.v1;

interface NotificationAPI {
    Notification createNotification(
            String message,
            List<Medium> supportedMedium,
            List<DispatchTime> times);
    void addParticipant(int notificationId, String participantEmail);
    void removeParticipant(int notificationId, String participantEmail);
}

