package com.carevaluation.prototype.utility;

public final class CarOneHotEncoder {

    private CarOneHotEncoder() {}

    public static double[] encode(
            String buying,
            String maint,
            String doors,
            String persons,
            String lugBoot,
            String safety
    ) {
        double[] buyingVector  = oneHot4Buying(norm(buying));     // vhigh, high, med, low
        double[] maintVector   = oneHot4Maint(norm(maint));       // vhigh, high, med, low
        double[] doorsVector   = oneHot4Doors(norm(doors));       // 2, 3, 4, 5more
        double[] personsVector = oneHot3Persons(norm(persons));   // 2, 4, more
        double[] lugBootVector = oneHot3LugBoot(norm(lugBoot));   // small, med, big
        double[] safetyVector  = oneHot3Safety(norm(safety));     // low, med, high

        double[] x = new double[21];
        int k = 0;

        k = copyInto(x, k, buyingVector);
        k = copyInto(x, k, maintVector);
        k = copyInto(x, k, doorsVector);
        k = copyInto(x, k, personsVector);
        k = copyInto(x, k, lugBootVector);
        k = copyInto(x, k, safetyVector);

        return x;
    }

    private static int copyInto(double[] dest, int offset, double[] src) {
        System.arraycopy(src, 0, dest, offset, src.length);
        return offset + src.length;
    }

    private static String norm(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private static double[] oneHot4Buying(String v) {
        return switch (v) {
            case "vhigh" -> new double[]{1, 0, 0, 0};
            case "high"  -> new double[]{0, 1, 0, 0};
            case "med"   -> new double[]{0, 0, 1, 0};
            case "low"   -> new double[]{0, 0, 0, 1};
            default -> throw invalid("buying", v, "vhigh, high, med, low");
        };
    }

    private static double[] oneHot4Maint(String v) {
        return switch (v) {
            case "vhigh" -> new double[]{1, 0, 0, 0};
            case "high"  -> new double[]{0, 1, 0, 0};
            case "med"   -> new double[]{0, 0, 1, 0};
            case "low"   -> new double[]{0, 0, 0, 1};
            default -> throw invalid("maint", v, "vhigh, high, med, low");
        };
    }

    private static double[] oneHot4Doors(String v) {
        return switch (v) {
            case "2"     -> new double[]{1, 0, 0, 0};
            case "3"     -> new double[]{0, 1, 0, 0};
            case "4"     -> new double[]{0, 0, 1, 0};
            case "5more" -> new double[]{0, 0, 0, 1};
            default -> throw invalid("doors", v, "2, 3, 4, 5more");
        };
    }

    private static double[] oneHot3Persons(String v) {
        return switch (v) {
            case "2"    -> new double[]{1, 0, 0};
            case "4"    -> new double[]{0, 1, 0};
            case "more" -> new double[]{0, 0, 1};
            default -> throw invalid("persons", v, "2, 4, more");
        };
    }

    private static double[] oneHot3LugBoot(String v) {
        return switch (v) {
            case "small" -> new double[]{1, 0, 0};
            case "med"   -> new double[]{0, 1, 0};
            case "big"   -> new double[]{0, 0, 1};
            default -> throw invalid("lugBoot", v, "small, med, big");
        };
    }

    private static double[] oneHot3Safety(String v) {
        return switch (v) {
            case "low"  -> new double[]{1, 0, 0};
            case "med"  -> new double[]{0, 1, 0};
            case "high" -> new double[]{0, 0, 1};
            default -> throw invalid("safety", v, "low, med, high");
        };
    }

    private static IllegalArgumentException invalid(String field, String got, String expected) {
        return new IllegalArgumentException(
                "Invalid '" + field + "' value: '" + got + "'. Expected one of: " + expected
        );
    }
}