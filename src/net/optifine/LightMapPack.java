package net.optifine;

import net.minecraft.world.World;

public class LightMapPack {
    private final LightMap lightMap;
    private final LightMap lightMapRain;
    private final LightMap lightMapThunder;
    private int[] colorBuffer1 = new int[0];
    private int[] colorBuffer2 = new int[0];

    public LightMapPack(final LightMap lightMap, LightMap lightMapRain, LightMap lightMapThunder) {
        if (lightMapRain != null || lightMapThunder != null) {
            if (lightMapRain == null) lightMapRain = lightMap;
            if (lightMapThunder == null) lightMapThunder = lightMapRain;
        }
        this.lightMap = lightMap;
        this.lightMapRain = lightMapRain;
        this.lightMapThunder = lightMapThunder;
    }

    public boolean updateLightmap(final World world, final float torchFlickerX, final int[] lmColors, final boolean nightvision, final float partialTicks) {
        if (this.lightMapRain == null && this.lightMapThunder == null)
            return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        else {
            final int i = world.provider.getDimensionId();
            if (i != 1 && i != -1) {
                final float f = world.getRainStrength(partialTicks);
                float f1 = world.getThunderStrength(partialTicks);
                final float f2 = 1.0E-4F;
                final boolean flag = f > f2;
                final boolean flag1 = f1 > f2;
                if (!flag && !flag1) return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
                else {
                    if (f > 0.0F) f1 /= f;
                    final float f3 = 1.0F - f;
                    final float f4 = f - f1;
                    if (this.colorBuffer1.length != lmColors.length) {
                        this.colorBuffer1 = new int[lmColors.length];
                        this.colorBuffer2 = new int[lmColors.length];
                    }
                    int j = 0;
                    final int[][] aint = new int[][]{lmColors, this.colorBuffer1, this.colorBuffer2};
                    final float[] afloat = new float[3];
                    if (f3 > f2 && this.lightMap.updateLightmap(world, torchFlickerX, aint[j], nightvision)) {
                        afloat[j] = f3;
                        ++j;
                    }
                    if (f4 > f2 && this.lightMapRain != null && this.lightMapRain.updateLightmap(world, torchFlickerX, aint[j], nightvision)) {
                        afloat[j] = f4;
                        ++j;
                    }
                    if (f1 > f2 && this.lightMapThunder != null && this.lightMapThunder.updateLightmap(world, torchFlickerX, aint[j], nightvision)) {
                        afloat[j] = f1;
                        ++j;
                    }
                    return j == 2 ? this.blend(aint[0], afloat[0], aint[1], afloat[1]) : j == 3 ? this.blend(aint[0], afloat[0], aint[1], afloat[1], aint[2], afloat[2]) : true;
                }
            } else return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        }
    }

    private boolean blend(final int[] cols0, final float br0, final int[] cols1, final float br1) {
        if (cols1.length != cols0.length) return false;
        else {
            for (int i = 0; i < cols0.length; ++i) {
                final int j = cols0[i];
                final int k = j >> 16 & 255;
                final int l = j >> 8 & 255;
                final int i1 = j & 255;
                final int j1 = cols1[i];
                final int k1 = j1 >> 16 & 255;
                final int l1 = j1 >> 8 & 255;
                final int i2 = j1 & 255;
                final int j2 = (int) (k * br0 + k1 * br1);
                final int k2 = (int) (l * br0 + l1 * br1);
                final int l2 = (int) (i1 * br0 + i2 * br1);
                cols0[i] = -16777216 | j2 << 16 | k2 << 8 | l2;
            }
            return true;
        }
    }

    private boolean blend(final int[] cols0, final float br0, final int[] cols1, final float br1, final int[] cols2, final float br2) {
        if (cols1.length == cols0.length && cols2.length == cols0.length) {
            for (int i = 0; i < cols0.length; ++i) {
                final int j = cols0[i];
                final int k = j >> 16 & 255;
                final int l = j >> 8 & 255;
                final int i1 = j & 255;
                final int j1 = cols1[i];
                final int k1 = j1 >> 16 & 255;
                final int l1 = j1 >> 8 & 255;
                final int i2 = j1 & 255;
                final int j2 = cols2[i];
                final int k2 = j2 >> 16 & 255;
                final int l2 = j2 >> 8 & 255;
                final int i3 = j2 & 255;
                final int j3 = (int) (k * br0 + k1 * br1 + k2 * br2);
                final int k3 = (int) (l * br0 + l1 * br1 + l2 * br2);
                final int l3 = (int) (i1 * br0 + i2 * br1 + i3 * br2);
                cols0[i] = -16777216 | j3 << 16 | k3 << 8 | l3;
            }
            return true;
        } else return false;
    }
}
