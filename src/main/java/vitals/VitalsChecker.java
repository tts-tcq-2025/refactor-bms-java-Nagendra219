package vitals;
import java.util.ArrayList;
import java.util.List;
import Vital;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class VitalsChecker {

  private static final Vital TEMPERATURE = new Vital("temperature.out_of_range", 95.0f, 102.0f, "°F");
    private static final Vital PULSE_RATE = new Vital("pulse_rate.out_of_range", 60.0f, 100.0f, "bpm");
    private static final Vital SPO2 = new Vital("spo2.out_of_range", 90.0f, Float.MAX_VALUE, "%");

    // Pass Locale here to select language dynamically
    public static boolean vitalsOk(float temperature, float pulseRate, float spo2, Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        List<String> alerts = checkVitals(temperature, pulseRate, spo2, messages);

        if (!alerts.isEmpty()) {
            System.out.println(messages.getString("alert.prefix"));
            alerts.forEach(System.out::println);
            displayAlertAnimation(messages);
            return false;
        }
        return true;
    }

    public static List<String> checkVitals(float temperature, float pulseRate, float spo2, ResourceBundle messages) {
        List<String> alerts = new ArrayList<>();
        checkAndAddAlert(TEMPERATURE, temperature, alerts, messages);
        checkAndAddAlert(PULSE_RATE, pulseRate, alerts, messages);
        checkAndAddAlert(SPO2, spo2, alerts, messages);
        return alerts;
    }

    private static void checkAndAddAlert(Vital vital, float value, List<String> alerts, ResourceBundle messages) {
        if (!vital.isNormal(value)) {
            String pattern = messages.getString(vital.messageKey);
            String alertMessage = MessageFormat.format(pattern, value);
            alerts.add(alertMessage);
        }
    }

    private static void displayAlertAnimation(ResourceBundle messages) {
        try {
            String animationMsg = messages.getString("animation.message");
            for (int i = 0; i < 6; i++) {
                System.out.print("\r" + animationMsg);
                Thread.sleep(1000);
                System.out.print("\r" + " ".repeat(animationMsg.length()));
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Alert animation interrupted.");
        }
}
