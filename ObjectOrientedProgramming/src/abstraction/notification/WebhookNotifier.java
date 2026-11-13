package abstraction.notification;

public class WebhookNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Webhook - " + recipient + " : " + message);
    }
}
