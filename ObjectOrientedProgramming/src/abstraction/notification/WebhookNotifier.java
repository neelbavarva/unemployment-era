package abstraction.notification;

public class WebhookNotifier extends BaseNotifier {
    @Override
    protected String loadTemplate(String message) {
        return "Webhook : " + message;
    }

    @Override
    protected void deliver(String recipient, String formattedMessage) {
        System.out.println(formattedMessage + " delivered to " + recipient + " using Webhook");
    }
}
