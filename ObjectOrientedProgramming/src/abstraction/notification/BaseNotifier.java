package abstraction.notification;

public abstract class BaseNotifier implements Notifier {
    // same for all
    public final void send(String recipient, String message) {
        validate(recipient);
        String formattedMessage = loadTemplate(message);
        log(recipient);
        deliver(recipient, formattedMessage);
    }

    // same for all
    private void validate(String recipient) {
        if (recipient == null || recipient.isBlank()) {
            throw new RuntimeException("Invalid name");
        }
        System.out.println("Validation successful");
    }

    protected abstract String loadTemplate(String message);

    // same for all
    private void log(String recipient) {
        System.out.println("Recipient " + recipient);
    }

    protected abstract void deliver(String recipient, String formattedMessage);
}
