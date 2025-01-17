package cn.timer.ultra.utils.ultra.render;

import cn.timer.ultra.utils.ultra.color.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static cn.timer.ultra.utils.ultra.color.ColorUtils.glColor;
import static net.minecraft.client.renderer.GlStateManager.disableBlend;
import static net.minecraft.client.renderer.GlStateManager.enableTexture2D;
import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {
    static Minecraft mc = Minecraft.getMinecraft();

    public static void setGlState(final int cap, final boolean state) {
        if (state) {
            GL11.glEnable(cap);
        } else {
            GL11.glDisable(cap);
        }
    }

    public static void rectangleBordered(final double x, final double y, final double x1, final double y1, final double width, final int internalColor, final int borderColor) {
        rectangle(x + width, y + width, x1 - width, y1 - width, internalColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y, x1 - width, y + width, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x, y, x + width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x1 - width, y, x1, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        rectangle(x + width, y1 - width, x1 - width, y1, borderColor);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void rectangle(double left, double top, double right, double bottom, final int color) {
        double var5;
        if (left < right) {
            var5 = left;
            left = right;
            right = var5;
        }
        if (top < bottom) {
            var5 = top;
            top = bottom;
            bottom = var5;
        }
        final float var11 = (color >> 24 & 255) / 255.0f;
        final float var6 = (color >> 16 & 255) / 255.0f;
        final float var7 = (color >> 8 & 255) / 255.0f;
        final float var8 = (color & 255) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var6, var7, var8, var11);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(left, bottom, 0.0).endVertex();
        worldRenderer.pos(right, bottom, 0.0).endVertex();
        worldRenderer.pos(right, top, 0.0).endVertex();
        worldRenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawFastRoundedRect(float x0, float y0, float x1, float y1, float radius, int color) {
        float f2 = (color >> 24 & 0xFF) / 255.0f;
        float f3 = (color >> 16 & 0xFF) / 255.0f;
        float f4 = (color >> 8 & 0xFF) / 255.0f;
        float f5 = (color & 0xFF) / 255.0f;
        glDisable(2884);
        glDisable(3553);
        glEnable(3042);

        glBlendFunc(770, 771);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        glColor4f(f3, f4, f5, f2);
        glBegin(5);
        glVertex2f(x0 + radius, y0);
        glVertex2f(x0 + radius, y1);
        glVertex2f(x1 - radius, y0);
        glVertex2f(x1 - radius, y1);
        glEnd();
        glBegin(5);
        glVertex2f(x0, y0 + radius);
        glVertex2f(x0 + radius, y0 + radius);
        glVertex2f(x0, y1 - radius);
        glVertex2f(x0 + radius, y1 - radius);
        glEnd();
        glBegin(5);
        glVertex2f(x1, y0 + radius);
        glVertex2f(x1 - radius, y0 + radius);
        glVertex2f(x1, y1 - radius);
        glVertex2f(x1 - radius, y1 - radius);
        glEnd();
        glBegin(6);
        float f6 = x1 - radius;
        float f7 = y0 + radius;
        glVertex2f(f6, f7);
        int j;
        for (j = 0; j <= 18; ++j) {
            float f8 = j * 5.0f;
            glVertex2f((float) (f6 + radius * Math.cos(Math.toRadians(f8))), (float) (f7 - radius * Math.sin(Math.toRadians(f8))));
        }
        glEnd();
        glBegin(6);
        f6 = x0 + radius;
        f7 = y0 + radius;
        glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            float f9 = j * 5.0f;
            glVertex2f((float) (f6 - radius * Math.cos(Math.toRadians(f9))), (float) (f7 - radius * Math.sin(Math.toRadians(f9))));
        }
        glEnd();
        glBegin(6);
        f6 = x0 + radius;
        f7 = y1 - radius;
        glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            float f10 = j * 5.0f;
            glVertex2f((float) (f6 - radius * Math.cos(Math.toRadians(f10))), (float) (f7 + radius * Math.sin(Math.toRadians(f10))));
        }
        glEnd();
        glBegin(6);
        f6 = x1 - radius;
        f7 = y1 - radius;
        glVertex2f(f6, f7);
        for (j = 0; j <= 18; ++j) {
            float f11 = j * 5.0f;
            glVertex2f((float) (f6 + radius * Math.cos(Math.toRadians(f11))), (float) (f7 + radius * Math.sin(Math.toRadians(f11))));
        }
        glEnd();
        glEnable(3553);
        glEnable(2884);
        glDisable(3042);
        enableTexture2D();
        disableBlend();
    }


    public static void drawTexturedRect(final float x, final float y, final float width, final float height, final String image) {
        GL11.glPushMatrix();
        final boolean enableBlend = GL11.glIsEnabled(3042);
        final boolean disableAlpha = !GL11.glIsEnabled(3008);
        if (!enableBlend) {
            GL11.glEnable(3042);
        }
        if (!disableAlpha) {
            GL11.glDisable(3008);
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/" + image + ".png"));
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        if (!enableBlend) {
            GL11.glDisable(3042);
        }
        if (!disableAlpha) {
            GL11.glEnable(3008);
        }
        GL11.glPopMatrix();
    }

    public static void drawShadow(final float x, final float y, final float width, final float height) {
        drawTexturedRect(x - 9.0f, y - 9.0f, 9.0f, 9.0f, "shadow/paneltopleft");
        drawTexturedRect(x - 9.0f, y + height, 9.0f, 9.0f, "shadow/panelbottomleft");
        drawTexturedRect(x + width, y + height, 9.0f, 9.0f, "shadow/panelbottomright");
        drawTexturedRect(x + width, y - 9.0f, 9.0f, 9.0f, "shadow/paneltopright");
        drawTexturedRect(x - 9.0f, y, 9.0f, height, "shadow/panelleft");
        drawTexturedRect(x + width, y, 9.0f, height, "shadow/panelright");
        drawTexturedRect(x, y - 9.0f, width, 9.0f, "shadow/paneltop");
        drawTexturedRect(x, y + height, width, 9.0f, "shadow/panelbottom");
    }

    public static void drawShadow2(final float left, final float top, final float right, final float bottom) {
        final float width = right - left;
        final float height = bottom - top;
        drawShadow(left, top, width, height);
    }

    public static void resetCaps() {
        for (int i = 0; i < 40000; i++)
            GL11.glDisable(i);
    }

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.framebufferWidth != mc.displayWidth || framebuffer.framebufferHeight != mc.displayHeight) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(mc.displayWidth, mc.displayHeight, true);
        }
        return framebuffer;
    }

    public static int rainbow(int delay) {
        double rainbow = Math.ceil((System.currentTimeMillis() + delay) / 10.0);
        return Color.getHSBColor((float) (rainbow % 360.0 / 360.0), 0.5f, 1.0f).getRGB();
    }

    public static void drawRect(float left, float top, float right, float bottom, final int color) {
        if (left < right) {
            final float e = left;
            left = right;
            right = e;
        }
        if (top < bottom) {
            final float e = top;
            top = bottom;
            bottom = e;
        }
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float b = (color >> 16 & 0xFF) / 255.0f;
        final float c = (color >> 8 & 0xFF) / 255.0f;
        final float d = (color & 0xFF) / 255.0f;
        final WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(b, c, d, a);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(left, bottom, 0.0).endVertex();
        worldRenderer.pos(right, bottom, 0.0).endVertex();
        worldRenderer.pos(right, top, 0.0).endVertex();
        worldRenderer.pos(left, top, 0.0).endVertex();
        Tessellator.getInstance().draw();
        enableTexture2D();
        disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawRect2(final float x, final float y, final float width, final float height, final int color) {
        final float right = x + width;
        final float bottom = y + height;
        drawRect(x, y, right, bottom, color);
    }

    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double e = left;
            left = right;
            right = e;
        }
        if (top < bottom) {
            final double e = top;
            top = bottom;
            bottom = e;
        }
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float b = (color >> 16 & 0xFF) / 255.0f;
        final float c = (color >> 8 & 0xFF) / 255.0f;
        final float d = (color & 0xFF) / 255.0f;
        final WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(b, c, d, a);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(left, bottom, 0.0).endVertex();
        worldRenderer.pos(right, bottom, 0.0).endVertex();
        worldRenderer.pos(right, top, 0.0).endVertex();
        worldRenderer.pos(left, top, 0.0).endVertex();
        Tessellator.getInstance().draw();
        enableTexture2D();
        disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawRect2(final double x, final double y, final double width, final double height, final int color) {
        final double right = x + width;
        final double bottom = y + height;
        drawRect(x, y, right, bottom, color);
    }


    public static void drawBorderedRect(float x, float y, float width, float height, final float outlineThickness, int rectColor, int outlineColor) {
        drawRect2(x, y, width, height, rectColor);
        glEnable(GL_LINE_SMOOTH);
        GLUtil.setup2DRendering(() -> {
            color(outlineColor);
            GL11.glLineWidth(outlineThickness);
            float cornerValue = (float) (outlineThickness * .19);
            GLUtil.render(GL_LINES, () -> {
                GL11.glVertex2d(x, y - cornerValue);
                GL11.glVertex2d(x, y + height + cornerValue);
                GL11.glVertex2d(x + width, y + height + cornerValue);
                GL11.glVertex2d(x + width, y - cornerValue);
                GL11.glVertex2d(x, y);
                GL11.glVertex2d(x + width, y);
                GL11.glVertex2d(x, y + height);
                GL11.glVertex2d(x + width, y + height);
            });
        });
        GL11.glDisable(GL_LINE_SMOOTH);
    }

    // Bad rounded rect method but the shader one requires scaling that sucks
    public static void renderRoundedRect(float x, float y, float width, float height, float radius, int color) {
        RenderUtil.drawGoodCircle(x + radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + radius, radius, color);
        RenderUtil.drawGoodCircle(x + radius, y + height - radius, radius, color);
        RenderUtil.drawGoodCircle(x + width - radius, y + height - radius, radius, color);

        drawRect2(x + radius, y, width - radius * 2, height, color);
        drawRect2(x, y + radius, width, height - radius * 2, color);
    }

    // Scales the data that you put in the runnable
    public static void scale(float x, float y, float scale, Runnable data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale, scale, 1);
        GL11.glTranslatef(-x, -y, 0);
        data.run();
        GL11.glPopMatrix();
    }

    // Scales the data that you put in the runnable
    public static void scaleStart(float x, float y, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.translate(-x, -y, 0);
    }

    public static void scaleEnd() {
        GlStateManager.popMatrix();
    }


    // TODO: Replace this with a shader as GL_POINTS is not consistent with gui scales
    public static void drawGoodCircle(double x, double y, float radius, int color) {
        color(color);
        GLUtil.setup2DRendering(() -> {
            glEnable(GL_POINT_SMOOTH);
            glHint(GL_POINT_SMOOTH_HINT, GL_NICEST);
            glPointSize(radius * (2 * mc.gameSettings.guiScale));
            GLUtil.render(GL_POINTS, () -> glVertex2d(x, y));
        });
    }

    public static void fakeCircleGlow(float posX, float posY, float radius, Color color, float maxAlpha) {
        setAlphaLimit(0);
        glShadeModel(GL_SMOOTH);
        GLUtil.setup2DRendering(() -> GLUtil.render(GL_TRIANGLE_FAN, () -> {
            color(color.getRGB(), maxAlpha);
            glVertex2d(posX, posY);
            color(color.getRGB(), 0);
            for (int i = 0; i <= 100; i++) {
                double angle = (i * .06283) + 3.1415;
                double x2 = Math.sin(angle) * radius;
                double y2 = Math.cos(angle) * radius;
                glVertex2d(posX + x2, posY + y2);
            }
        }));
        glShadeModel(GL_FLAT);
        setAlphaLimit(1);
    }

    // animation for sliders and stuff
    public static double animate(double endPoint, double current, double speed) {
        boolean shouldContinueAnimation = endPoint > current;
        if (speed < 0.0D) {
            speed = 0.0D;
        } else if (speed > 1.0D) {
            speed = 1.0D;
        }

        double dif = Math.max(endPoint, current) - Math.min(endPoint, current);
        double factor = dif * speed;
        return current + (shouldContinueAnimation ? factor : -factor);
    }

    // Arrow for clickgui

    // Draws a circle using traditional methods of rendering
    public static void drawCircleNotSmooth(double x, double y, double radius, int color) {
        radius /= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_CULL_FACE);
        color(color);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);

        for (double i = 0; i <= 360; i++) {
            double angle = i * .01745;
            GL11.glVertex2d(x + (radius * Math.cos(angle)) + radius, y + (radius * Math.sin(angle)) + radius);
        }

        GL11.glEnd();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void scissor(double x, double y, double width, double height, Runnable data) {
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        scissor(x, y, width, height);
        data.run();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void scissor(double x, double y, double width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        final double scale = sr.getScaleFactor();
        double finalHeight = height * scale;
        double finalY = (sr.getScaledHeight() - y) * scale;
        double finalX = x * scale;
        double finalWidth = width * scale;
        glScissor((int) finalX, (int) (finalY - finalHeight), (int) finalWidth, (int) finalHeight);
    }

    public static void drawRoundedRect3(double x, double y, double x2, double y2, double radius, int color) {
        final double X1 = Math.min(x, x2);
        final double X2 = Math.max(x, x2);
        final double Y1 = Math.min(y, y2);
        final double Y2 = Math.max(y, y2);

        if (radius * 2 > X2 - X1 || radius * 2 > Y2 - Y1) return;
        drawRect(X1, Y1 + radius, X2, Y2 - radius, color);
        drawRect(X1 + radius, Y1, X2 - radius, Y1 + radius, color);
        drawRect(X1 + radius, Y2 - radius, X2 - radius, Y2, color);

        drawSector(X2 - radius, Y2 - radius, 0, 90, radius, color);
        drawSector(X1 + radius, Y2 - radius, 90, 180, radius, color);
        drawSector(X1 + radius, Y1 + radius, 180, 270, radius, color);
        drawSector(X2 - radius, Y1 + radius, 270, 360, radius, color);
    }

    public static void drawSector(final double x, final double y, int angle1, int angle2, final double radius, final int color) {
        boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
        boolean alpha = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
        boolean glTexture2D = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
        if (angle1 > angle2) {
            int temp = angle2;
            angle2 = angle1;
            angle1 = temp;
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        glColor(color);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(x, y);
        for (double i = angle2; i >= angle1; i -= 1) {
            double ldx = Math.cos(i * Math.PI / 180.0) * radius;
            double ldy = Math.sin(i * Math.PI / 180.0) * radius;
            GL11.glVertex2d(x + ldx, y + ldy);
        }
        GL11.glVertex2d(x, y);
        GL11.glEnd();
        if (glTexture2D) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
        if (!blend) {
            GL11.glDisable(GL11.GL_BLEND);
        }
        if (!alpha) {
            GL11.glDisable(GL11.GL_ALPHA_TEST);
        }
    }

    // This will set the alpha limit to a specified value ranging from 0-1
    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL_GREATER, (float) (limit * .01));
    }

    // This method colors the next avalible texture with a specified alpha value ranging from 0-1
    public static void color(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }

    // Colors the next texture without a specified alpha value
    public static void color(int color) {
        color(color, (float) (color >> 24 & 255) / 255.0F);
    }

    /**
     * Bind a texture using the specified integer refrence to the texture.
     *
     * @see org.lwjgl.opengl.GL13 for more information about texture bindings
     */
    public static void bindTexture(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    // Sometimes colors get messed up in for loops, so we use this method to reset it to allow new colors to be used
    public static void resetColor() {
        GlStateManager.color(1, 1, 1, 1);
    }

    public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }

    public static void drawRoundedRect(float x, float y, float right, float bottom, float round, int color) {
        GL11.glPushMatrix();
        GlStateManager.disableAlpha();
        x = (float) ((double) x + ((double) (round / 2.0f) + 0.5));
        y = (float) ((double) y + ((double) (round / 2.0f) + 0.5));
        right = (float) ((double) right - ((double) (round / 2.0f) + 0.5));
        bottom = (float) ((double) bottom - ((double) (round / 2.0f) + 0.5));
        RenderUtil.drawRect(x, y, right, bottom, color);
        RenderUtil.circle(right - round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(right - round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.drawRect(x - round / 2.0f - 0.5f, y + round / 2.0f, right, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x, y + round / 2.0f, right + round / 2.0f + 0.5f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y - round / 2.0f - 0.5f, right - round / 2.0f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y, right - round / 2.0f, bottom + round / 2.0f + 0.5f, color);
        GL11.glPopMatrix();
    }

    public static void drawRoundedRect(double x, double y, double right, double bottom, double round, int color) {
        x = (float) (x + ((round / 2.0f) + 0.5));
        y = (float) (y + ((round / 2.0f) + 0.5));
        right = (float) (right - ((round / 2.0f) + 0.5));
        bottom = (float) (bottom - ((round / 2.0f) + 0.5));
        RenderUtil.drawRect(x, y, right, bottom, color);
        RenderUtil.circle(right - round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(right - round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.drawRect(x - round / 2.0f - 0.5f, y + round / 2.0f, right, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x, y + round / 2.0f, right + round / 2.0f + 0.5f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y - round / 2.0f - 0.5f, right - round / 2.0f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y, right - round / 2.0f, bottom + round / 2.0f + 0.5f, color);
    }

    public static void drawRoundedRect2(float x, float y, float width, float height, final float round, final int color) {
        float right = (float) getRight(x, width);
        float bottom = (float) getBottom(y, height);
        x = (float) (x + ((round / 2.0f) + 0.5));
        y = (float) (y + ((round / 2.0f) + 0.5));
        right = (float) (right - ((round / 2.0f) + 0.5));
        bottom = (float) (bottom - ((round / 2.0f) + 0.5));
        RenderUtil.drawRect(x, y, right, bottom, color);
        RenderUtil.circle(right - round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.circle(x + round / 2.0f, y + round / 2.0f, round, color);
        RenderUtil.circle(right - round / 2.0f, bottom - round / 2.0f, round, color);
        RenderUtil.drawRect(x - round / 2.0f - 0.5f, y + round / 2.0f, right, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x, y + round / 2.0f, right + round / 2.0f + 0.5f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y - round / 2.0f - 0.5f, right - round / 2.0f, bottom - round / 2.0f, color);
        RenderUtil.drawRect(x + round / 2.0f, y, right - round / 2.0f, bottom + round / 2.0f + 0.5f, color);
    }

    public static void drawRoundedRect2(float x, float y, float x1, float y1, int color) {
        float r = (y1 - y) / 2;
        circle(x + r, y + r, r - 0.5f, color);
        circle(x1 - r, y + r, r - 0.5f, color);
        drawRect(x + r, y, x1 - r, y1, color);
    }

    public static double getX(double left) {
        return left;
    }

    public static double getY(double top) {
        return top;
    }

    public static double getWidth(double right, double x) {
        return right - x;
    }

    public static double getHeight(double bottom, double y) {
        return bottom - y;
    }

    public static double getLeft(double x) {
        return x;
    }

    public static double getTop(double y) {
        return y;
    }

    public static double getRight(double x, double width) {
        return x + width;
    }

    public static double getBottom(double y, double height) {
        return y + height;
    }

    public static void doGlScissor(final float x, final float y, final float windowWidth2, final float windowHeight2) {
        int scaleFactor = 1;
        float k = (float) RenderUtil.mc.gameSettings.guiScale;
        if (k == 0.0f) {
            k = 1000.0f;
        }
        while (scaleFactor < k && RenderUtil.mc.displayWidth / (scaleFactor + 1) >= 320 && RenderUtil.mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glScissor((int) (x * scaleFactor), (int) (RenderUtil.mc.displayHeight - (y + windowHeight2) * scaleFactor), (int) (windowWidth2 * scaleFactor), (int) (windowHeight2 * scaleFactor));
    }

    public static void circle(final float x, final float y, final float radius, final int fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void circle(final double x, final double y, final double radius, final int fill) {
        arc(x, y, 0.0f, 360.0f, radius, fill);
    }

    public static void arc(final float x, final float y, final float start, final float end, final float radius, final int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arc(final double x, final double y, final double start, final double end, final double radius, final int color) {
        arcEllipse(x, y, start, end, radius, radius, color);
    }

    public static void arcEllipse(final float x, final float y, float start, float end, final float w, final float h, final int color) {
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        float temp;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        if (var11 > 0.5f) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0f);
            GL11.glBegin(3);
            for (float i = end; i >= start; i -= 4.0f) {
                final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * (w * 1.001f);
                final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * (h * 1.001f);
                GL11.glVertex2f(x + ldx, y + ldy);
            }
            GL11.glEnd();
            GL11.glDisable(2848);
        }
        GL11.glBegin(6);
        for (float i = end; i >= start; i -= 4.0f) {
            final float ldx = (float) Math.cos(i * 3.141592653589793 / 180.0) * w;
            final float ldy = (float) Math.sin(i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f(x + ldx, y + ldy);
        }
        GL11.glEnd();
        enableTexture2D();
        disableBlend();
    }

    public static void arcEllipse(final double x, final double y, double start, double end, final double w, final double h, final int color) {
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        double temp;
        if (start > end) {
            temp = end;
            end = start;
            start = temp;
        }
        final float var11 = (color >> 24 & 0xFF) / 255.0f;
        final float var12 = (color >> 16 & 0xFF) / 255.0f;
        final float var13 = (color >> 8 & 0xFF) / 255.0f;
        final float var14 = (color & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var12, var13, var14, var11);
        if (var11 > 0.5f) {
            GL11.glEnable(2848);
            GL11.glLineWidth(2.0f);
            GL11.glBegin(3);
            for (double i = end; i >= start; i -= 4.0f) {
                final double ldx = Math.cos(i * 3.141592653589793 / 180.0) * (w * 1.001f);
                final double ldy = Math.sin(i * 3.141592653589793 / 180.0) * (h * 1.001f);
                GL11.glVertex2f((float) (x + ldx), (float) (y + ldy));
            }
            GL11.glEnd();
            GL11.glDisable(2848);
        }
        GL11.glBegin(6);
        for (double i = end; i >= start; i -= 4.0f) {
            final double ldx = Math.cos(i * 3.141592653589793 / 180.0) * w;
            final double ldy = Math.sin(i * 3.141592653589793 / 180.0) * h;
            GL11.glVertex2f((float) (x + ldx), (float) (y + ldy));
        }
        GL11.glEnd();
        enableTexture2D();
        disableBlend();
    }

    public static void drawImage(final ResourceLocation image, final float x, final float y, final float width, final float height, final float alpha) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawPicture(ResourceLocation paramResourceLocation, int paramInt1, int paramInt2, int paramInt3) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Minecraft.getMinecraft().getTextureManager().bindTexture(paramResourceLocation);
        Gui.drawScaledCustomSizeModalRect(paramInt1, paramInt2, 0.0F, 0.0F, paramInt3, paramInt3, paramInt3, paramInt3, paramInt3, paramInt3);
        GlStateManager.popMatrix();
    }

    public static void drawImage(final ResourceLocation image, final int x, final int y, final float width, final float height, final float alpha) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((float) x, (float) y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawImage(final ResourceLocation image, final float x, final float y, final float width, final float height) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void drawImage(final ResourceLocation image, final float x, final float y, final float width, final float height, Color c) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, c.getAlpha() / 255f);
        RenderUtil.mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, (int) width, (int) height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static boolean isHovering(float left, float top, float right, float bottom, float mouseX, float mouseY) {
        return mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom;
    }

    public static void drawOutlinedRect(final float x, final float y, final float width, final float height, final float lineSize, final int lineColor) {
        drawRect(x, y, width, y + lineSize, lineColor);
        drawRect(x, height - lineSize, width, height, lineColor);
        drawRect(x, y + lineSize, x + lineSize, height - lineSize, lineColor);
        drawRect(width - lineSize, y + lineSize, width, height - lineSize, lineColor);
    }

    public static void drawArc(float x1, float y1, double r, int color, int startPoint, double arc, int linewidth) {
        r *= 2.0;
        x1 *= 2.0F;
        y1 *= 2.0F;
        float f = (float) (color >> 24 & 255) / 255.0F;
        float f1 = (float) (color >> 16 & 255) / 255.0F;
        float f2 = (float) (color >> 8 & 255) / 255.0F;
        float f3 = (float) (color & 255) / 255.0F;
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glLineWidth((float) linewidth);
        GL11.glEnable(2848);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(3);

        for (int i = startPoint; (double) i <= arc; ++i) {
            double x = Math.sin((double) i * Math.PI / 180.0) * r;
            double y = Math.cos((double) i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double) x1 + x, (double) y1 + y);
        }

        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawArc(float x1, float y1, double r, int color, int startPoint, double arc, int linewidth, boolean rainbow) {
        r *= 2.0;
        x1 *= 2.0F;
        y1 *= 2.0F;
        float f = (float) (color >> 24 & 255) / 255.0F;
        float f1 = (float) (color >> 16 & 255) / 255.0F;
        float f2 = (float) (color >> 8 & 255) / 255.0F;
        float f3 = (float) (color & 255) / 255.0F;
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glLineWidth((float) linewidth);
        GL11.glEnable(2848);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(3);

        for (int i = startPoint; (double) i <= arc; ++i) {
            if (rainbow) glColor(ColorUtils.rainbow(-(long) (1.0E10f / 360 * i)).getRGB());
            double x = Math.sin((double) i * Math.PI / 180.0) * r;
            double y = Math.cos((double) i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double) x1 + x, (double) y1 + y);
        }

        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawRoundedBorder(final double x, final double y, final double x2, final double y2, final double radius, final float width, final int color) {
        final double X1 = Math.min(x, x2);
        final double X2 = Math.max(x, x2);
        final double Y1 = Math.min(y, y2);
        final double Y2 = Math.max(y, y2);

        if (radius * 2 > X2 - X1 || radius * 2 > Y2 - Y1) return;

        /*
             A      1      B
             ↓      ↓      ↓
             /-------------\
         2->|             | <-3
             |             |
             \-------------/
             ↑      ↑      ↑
             C      4      D
        */

        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        color(color);
        drawLine(X1 + radius, Y1, X2 - radius, Y1, width); // 1
        drawLine(X1, Y1 + radius, X1, Y2 - radius, width); // 2
        drawLine(X2, Y1 + radius, X2, Y2 - radius, width); // 3
        drawLine(X1 + radius, Y2, X2 - radius, Y2, width); // 4
        GL11.glDisable(GL11.GL_BLEND);

        arc(X1 + radius, Y1 + radius, 180, 270, radius, width, color); //A
        arc(X2 - radius, Y1 + radius, 270, 360, radius, width, color); //B
        arc(X1 + radius, Y2 - radius, 90, 180, radius, width, color); //C
        arc(X2 - radius, Y2 - radius, 0, 90, radius, width, color); //D
    }

    public static void drawLine(final double x, final double y, final double x1, final double y1, final float width) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void arc(final double x, final double y, int angle1, int angle2, final double radius, final float width, final int color) {
        if (angle1 > angle2) {
            int temp = angle2;
            angle2 = angle1;
            angle1 = temp;
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(width);
        color(color);
        GL11.glBegin(GL11.GL_LINE_STRIP);

        for (double i = angle2; i >= angle1; i -= 1) {
            double ldx = Math.cos(i * Math.PI / 180.0) * radius;
            double ldy = Math.sin(i * Math.PI / 180.0) * radius;
            GL11.glVertex2d(x + ldx, y + ldy);
        }

        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static int width() {
        return new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth();
    }

    public static int height() {
        return new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
    }
}
