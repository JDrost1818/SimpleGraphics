package dgraphics.data;

import java.awt.*;

public class Colors {

    public final Color darkColor = new Color(0x2B2B2B);
    public final Color lightColor = new Color(0x3A3D3F);

    public final Color BORDER_COLOR = new Color(0, 0, 0, 25);

    public final Color LIGHT_GREEN = new Color(0x2ECC71);
    public final Color GREEN = new Color(0x26A65B);
    public final Color DARK_GREEN = new Color(0x1E824C);

    public final Color LIGHT_YELLOW = new Color(0xF5D76E);
    public final Color YELLOW = new Color(0xF4D03F);
    public final Color DARK_YELLOW = new Color(0xF7CA18);

    public final Color LIGHT_ORANGE = new Color(0xFEB9532);
    public final Color ORANGE = new Color(0xF89406);
    public final Color DARK_ORANGE = new Color(0xF9690E);

    public final Color LIGHT_RED = new Color(0xe74c3c);
    public final Color RED = new Color(0xc0392b);
    public final Color DARK_RED = new Color(0x96281B);

    public final Color LIGHT_BLUE = new Color(0x4167a6);
    public final Color BLUE = new Color(0x365f9f);
    public final Color DARK_BLUE = new Color(0x2e5493);

    public final Color LIGHT_PURPLE = new Color(0xBE90D4);
    public final Color PURPLE = new Color(0x9B59B6);
    public final Color DARK_PURPLE = new Color(0x8E44AD);

    public final Color LIGHT_BROWN = new Color(0xcaa173);
    public final Color BROWN = new Color(0xa87c4b);
    public final Color DARK_BROWN = new Color(0x865d31);

    public final Color WHITE = new Color(0xFFFFFF);
    public final Color GRAY = new Color(0xDDDDDD);
    public final Color DARK_GRAY = new Color(0x404040);
    public final Color BLACK = new Color(0x000000);

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
