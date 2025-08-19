
private static class Vital {
        private final String name;
        private final float min;
        private final float max;
        private final String unit;

        Vital(String name, float min, float max, String unit) {
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
