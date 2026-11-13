package abstraction.notification;

public class EmailNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Email - " + recipient + " : " + message);
    }
}
