package abstraction.notification;

public class NotificationService {
    private final Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void notifyUser(String recipient, String message) {
        notifier.send(recipient, message);
    }

}
