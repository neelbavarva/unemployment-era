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
        // Same NotificationService code, four different behaviours
        List<NotificationService> services = List.of(
                new NotificationService(new EmailNotifier()),
                new NotificationService(new SmsNotifier()),
                new NotificationService(new WebhookNotifier()),
                new NotificationService(new WhatsAppNotifier())   // added later, service untouched
        );

        for (NotificationService service : services) {
            service.notifyUser("Neel", "Payment of 500 received");
        }
    }
}
