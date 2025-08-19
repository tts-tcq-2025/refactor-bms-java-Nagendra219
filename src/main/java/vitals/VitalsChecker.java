package vitals;


public abstract class VitalsChecker {

    private enum VitalType {
        TEMPERATURE("Temperature", 95.0f, 102.0f, "°F"),
        PULSE_RATE("Pulse rate", 60.0f, 100.0f, "bpm"),
        SPO2("Oxygen saturation", 90.0f, Float.MAX_VALUE, "%");

        final String name;
        final float min;
        final float max;
        final String unit;

        VitalType(String name, float min, float max, String unit) {
            this.name = name;
            this.min = min;
            this.max = max;
            this.unit = unit;
        }

        boolean isNormal(float value) {
            return value >= min && value <= max;
        }

        String getAlert(float value) {
            return name + " out of range (" + value + " " + unit + ").";
        }
    }

    public static boolean vitalsOk(float temperature, float pulseRate, float spo2) {
        StringBuilder alerts = new StringBuilder();

        checkAndAppendAlert(VitalType.TEMPERATURE, temperature, alerts);
        checkAndAppendAlert(VitalType.PULSE_RATE, pulseRate, alerts);
        checkAndAppendAlert(VitalType.SPO2, spo2, alerts);

        if (alerts.length() > 0) {
            handleAlert(alerts.toString().trim());
            return false;
        }

        return true;
    }

    private static void checkAndAppendAlert(VitalType type, float value, StringBuilder alerts) {
        if (!type.isNormal(value)) {
            alerts.append(type.getAlert(value)).append(" ");
        }
    }

    private static void handleAlert(String message) {
        System.out.println("ALERT: " + message);
        displayAlertAnimation();
    }

    private static void displayAlertAnimation() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.print("\r* ");
                Thread.sleep(1000);
                System.out.print("\r *");
                Thread.sleep(1000);
            }
            System.out.println(); // newline after animation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Alert animation interrupted.");
        }
    }
}
