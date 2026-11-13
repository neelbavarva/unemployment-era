package abstraction.notification;

public class SmsNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS - " + recipient + " : " + message);
    }
}
