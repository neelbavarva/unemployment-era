package abstraction.notification;

public class SmsNotifier extends BaseNotifier {
    @Override
    protected String loadTemplate(String message) {
        return "Sms : " + message;
    }

    @Override
    protected void deliver(String recipient, String formattedMessage) {
        System.out.println(formattedMessage + " delivered to " + recipient + " using ACN");
    }
}
