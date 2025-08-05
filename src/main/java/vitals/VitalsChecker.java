package vitals;


public abstract class VitalsChecker {

    private static final float MIN_TEMPERATURE = 95.0f;
    private static final float MAX_TEMPERATURE = 102.0f;

    private static final float MIN_PULSE_RATE = 60.0f;
    private static final float MAX_PULSE_RATE = 100.0f;

    private static final float MIN_SPO2 = 90.0f;

    static boolean vitalsOk(float temperature, float pulseRate, float spo2) throws InterruptedException {
        String errorMessage = checkVitals(temperature, pulseRate, spo2);
        if (null != errorMessage) {
            return handleError(errorMessage);
        }
        return true;
    }

    public static String checkVitals(float temperature, float pulseRate, float spo2) {
        StringBuilder alerts = new StringBuilder();

        if (!isTemperatureNormal(temperature)) {
            alerts.append("Temperature is out of range (").append(temperature).append(" °F). ");
        }
        if (!isPulseRateNormal(pulseRate)) {
            alerts.append("Pulse rate is out of range (").append(pulseRate).append(" bpm). ");
        }
        if (!isSpo2Normal(spo2)) {
            alerts.append("Oxygen saturation is too low (").append(spo2).append("%). ");
        }

        return alerts.length() > 0 ? alerts.toString().trim() : "All vitals are within normal range.";
    }

    private static boolean isTemperatureNormal(float temperature) {
        return temperature >= MIN_TEMPERATURE && temperature <= MAX_TEMPERATURE;
    }

    private static boolean isPulseRateNormal(float pulseRate) {
        return pulseRate >= MIN_PULSE_RATE && pulseRate <= MAX_PULSE_RATE;
    }

    private static boolean isSpo2Normal(float spo2) {
        return spo2 >= MIN_SPO2;
    }

    private static void displayAlertAnimation() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            System.out.print("\r* ");
            Thread.sleep(1000);
            System.out.print("\r *");
            Thread.sleep(1000);
        }
    }

    private static boolean handleError(String message) throws InterruptedException {
        System.out.println(message);
        displayAlertAnimation();
        return false;
    }
}
