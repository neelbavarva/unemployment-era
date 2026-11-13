package abstraction.notification;

public interface Notifier {
    void send(String recipient, String message);
}
