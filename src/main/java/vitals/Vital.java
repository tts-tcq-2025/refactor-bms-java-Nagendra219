package vitals;

private static class Vital {
         private final String messageKey;
        private final float min;
        private final float max;
        private final String unit;

        Vital(String messageKey, float min, float max, String unit) {
            this.messageKey = messageKey;
            this.min = min;
            this.max = max;
            this.unit = unit;
        }

        boolean isNormal(float value) {
            return value >= min && value <= max;
        }
    }
