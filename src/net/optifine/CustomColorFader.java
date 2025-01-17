package net.optifine;

import net.minecraft.src.Config;
import net.minecraft.util.Vec3;

public class CustomColorFader {
    private Vec3 color = null;
    private long timeUpdate = System.currentTimeMillis();

    public Vec3 getColor(final double x, final double y, final double z) {
        if (this.color == null) {
            this.color = new Vec3(x, y, z);
            return this.color;
        } else {
            final long i = System.currentTimeMillis();
            final long j = i - this.timeUpdate;
            if (j == 0L) {
                return this.color;
            } else {
                this.timeUpdate = i;
                if (Math.abs(x - this.color.xCoord) < 0.004D && Math.abs(y - this.color.yCoord) < 0.004D && Math.abs(z - this.color.zCoord) < 0.004D) {
                    return this.color;
                } else {
                    double d0 = j * 0.001D;
                    d0 = Config.limit(d0, 0.0D, 1.0D);
                    final double d1 = x - this.color.xCoord;
                    final double d2 = y - this.color.yCoord;
                    final double d3 = z - this.color.zCoord;
                    final double d4 = this.color.xCoord + d1 * d0;
                    final double d5 = this.color.yCoord + d2 * d0;
                    final double d6 = this.color.zCoord + d3 * d0;
                    this.color = new Vec3(d4, d5, d6);
                    return this.color;
                }
            }
        }
    }
}
