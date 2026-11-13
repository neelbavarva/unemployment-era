package abstraction.notification;

public class EmailNotifier extends BaseNotifier {
    @Override
    protected String loadTemplate(String message) {
        return "Email : " + message;
    }

    @Override
    protected void deliver(String recipient, String formattedMessage) {
        System.out.println(formattedMessage + " delivered to " + recipient + " using SMTP");
    }
}
