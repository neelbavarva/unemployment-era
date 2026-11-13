package abstraction.notification;

public class WhatsAppNotifier extends BaseNotifier {
    @Override
    protected String loadTemplate(String message) {
        return "WhatsApp : " + message;
    }

    @Override
    protected void deliver(String recipient, String formattedMessage) {
        System.out.println(formattedMessage + " delivered to " + recipient + " using Push Notify");
    }
}