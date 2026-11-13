package abstraction.notification;

public class WhatsAppNotifier implements Notifier {
    @Override
    public void send(String recipient, String message) {
        System.out.println("WhatsApp - " + recipient + " : " + message);
    }
}