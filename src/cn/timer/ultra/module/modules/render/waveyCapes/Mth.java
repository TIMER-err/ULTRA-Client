package cn.timer.ultra.module.modules.render.waveyCapes;

public class Mth {
    public static float lerp(final float f, final float g, final float h) {
        return g + f * (h - g);
    }

    public static double lerp(final double d, final double e, final double f) {
        return e + d * (f - e);
    }

    public static float fastInvSqrt(float f) {
        final float g = 0.5f * f;
        int i = Float.floatToIntBits(f);
        i = 1597463007 - (i >> 1);
        f = Float.intBitsToFloat(i);
        f *= 1.5f - g * f * f;
        return f;
    }

    public static float fastInvCubeRoot(final float f) {
        int i = Float.floatToIntBits(f);
        i = 1419967116 - i / 3;
        float g = Float.intBitsToFloat(i);
        g = 0.6666667f * g + 0.33333334f * g * g * f;
        g = 0.6666667f * g + 0.33333334f * g * g * f;
        return g;
    }
}
