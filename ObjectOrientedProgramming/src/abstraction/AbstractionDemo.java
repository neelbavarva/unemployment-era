package abstraction;

import abstraction.notification.EmailNotifier;
import abstraction.notification.NotificationService;
import abstraction.notification.SmsNotifier;
import abstraction.notification.WebhookNotifier;
import abstraction.notification.WhatsAppNotifier;

import java.io.IOException;
import java.util.List;

public class AbstractionDemo {
    public static void main(String[] args) throws IOException {
        // Test 1: same NotificationService code, four different channels
        List<NotificationService> services = List.of(
                new NotificationService(new EmailNotifier()),
                new NotificationService(new SmsNotifier()),
                new NotificationService(new WebhookNotifier()),
                new NotificationService(new WhatsAppNotifier())   // added later, service untouched
        );
        for (NotificationService service : services) {
            service.notifyUser("Neel", "Payment of 500 received");
            System.out.println("---");
        }

        // Test 2: invalid recipients must be rejected before delivery
        NotificationService email = new NotificationService(new EmailNotifier());
        String[] badRecipients = {"", "   ", null};
        for (String bad : badRecipients) {
            try {
                email.notifyUser(bad, "Should never be delivered");
                System.out.println("FAIL: delivered to [" + bad + "]");
            } catch (RuntimeException e) {
                System.out.println("Blocked [" + bad + "]: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }

        // Test 3: the flow can't be bypassed from outside
        // new EmailNotifier().deliver("Neel", "skip validation");   // ❌ compile error: deliver() is protected
        // class Sneaky extends BaseNotifier { public void send(...) {...} }   // ❌ compile error: send() is final
    }
}
