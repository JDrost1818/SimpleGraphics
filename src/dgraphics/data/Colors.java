package dgraphics.data;

import java.awt.*;

public class Colors {

    public final Color darkColor = new Color(0x2B2B2B);
    public final Color lightColor = new Color(0x3A3D3F);

    public final Color BORDER_COLOR = new Color(0, 0, 0, 25);

    public final Color GREEN = new Color(0x109D58);
    public final Color LIGHT_GREEN = new Color(0x2FA772);

    public final Color RED = new Color(0xc0392b);
    public final Color GRAY = new Color(0xDDDDDD);
    public final Color DARK_GRAY = new Color(0x404040);

    public final Color BLUE = new Color(0x365f9f);
    public final Color LIGHT_BLUE = new Color(0x4167a6);
    public final Color DARK_BLUE = new Color(0x2e5493);

    public static Color[] getAllColors(){
        return new Color[] { new Color(0x2B2B2B),
                             new Color(0x3A3D3F),
                             new Color(0, 0, 0, 25),
                             new Color(0x109D58),
                             new Color(0x2FA772),
                             new Color(0xc0392b),
                             new Color(0xDDDDDD),
                             new Color(0x404040),
                             new Color(0x365f9f),
                             new Color(0x4167a6),
                             new Color(0x2e5493)};
    }

    public static Color highlightColor(Color backgroundColor) {
        int r = backgroundColor.getRed();
        int g = backgroundColor.getGreen();
        int b = backgroundColor.getBlue();
        float[] hsb = new float[3];
        Color.RGBtoHSB(r,g,b,hsb);

        hsb[2] = (hsb[2]+.08f > 1) ? .9f : hsb[2] + .08f;

        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public static Color backgroundToText(Color backgroundColor){
        int r = backgroundColor.getRed();
        int g = backgroundColor.getGreen();
        int b = backgroundColor.getBlue();
        float[] hsb = new float[3];
        Color.RGBtoHSB(r,g,b,hsb);
        if (hsb[2] < .85){
            hsb[2] = (hsb[1] > .07) ? .27f : hsb[2];
        }

        hsb[0] = 0;
        hsb[1] = 0;
        hsb[2] = .25f / ((hsb[2] < .25) ? .25f : hsb[2]);
        if (hsb[2] > 1) { hsb[2] = 1; }

        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public static Color[] getColorPalette(Color backgroundColor){
        Color textColor = backgroundToText(backgroundColor);
        Color highlightColor = highlightColor(backgroundColor);

        return new Color[] {backgroundColor, textColor, highlightColor};
    }
}
