package vitals;
import java.util.ArrayList;
import java.util.List;
import Vital;


public abstract class VitalsChecker {

  private static final Vital TEMPERATURE = new Vital("Temperature", 95.0f, 102.0f, "°F");
    private static final Vital PULSE_RATE = new Vital("Pulse rate", 60.0f, 100.0f, "bpm");
    private static final Vital SPO2 = new Vital("Oxygen saturation", 90.0f, Float.MAX_VALUE, "%");

    public static boolean vitalsOk(float temperature, float pulseRate, float spo2) {
        List<String> alerts = checkVitals(temperature, pulseRate, spo2);

        if (!alerts.isEmpty()) {
            alerts.forEach(System.out::println);
            displayAlertAnimation();
            return false;
        }
        return true;
    }

    public static List<String> checkVitals(float temperature, float pulseRate, float spo2) {
        List<String> alerts = new ArrayList<>();

        checkAndAddAlert(TEMPERATURE, temperature, alerts);
        checkAndAddAlert(PULSE_RATE, pulseRate, alerts);
        checkAndAddAlert(SPO2, spo2, alerts);

        return alerts;
    }

    private static void checkAndAddAlert(Vital vital, float value, List<String> alerts) {
        if (!vital.isNormal(value)) {
            alerts.add(vital.getAlert(value));
        }
    }

    private static void displayAlertAnimation() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.print("\r* ");
                Thread.sleep(1000);
                System.out.print("\r *");
                Thread.sleep(1000);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Alert animation interrupted.");
        }
    }
}
