package data;

import java.awt.*;
import java.io.IOException;

public class Fonts {

    public final Font TITLE = new Font("Verdana", Font.PLAIN, 36);
    public final Font DEFAULT = new Font("Verdana", Font.PLAIN, 24);
    public final Font SMALL = new Font("Verdana", Font.PLAIN, 14);

    public final Font TITLE_BOLD = new Font("Verdana", Font.BOLD, 36);
    public final Font DEFAULT_BOLD = new Font("Verdana", Font.BOLD, 24);
    public final Font SMALL_BOLD = new Font("Verdana", Font.BOLD, 14);

    public Font BUTTON_FONT = new Font("Verdana", Font.PLAIN, 24);

    public final Font ROBOTO_LIGHT;
    public final Font ROBOTO_LIGHT_ITALIC;
    public final Font ROBOTO_REGULAR;
    public final Font ROBOTO_THIN;
    public final Font ROBOTO_THIN_ITALIC;

    public Fonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Light.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-LightItalic.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Regular.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Thin.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-ThinItalic.ttf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ROBOTO_LIGHT =          new Font("Roboto Light", 0, 18);
        ROBOTO_LIGHT_ITALIC =   new Font("Roboto Light Italic", 0, 18);
        ROBOTO_REGULAR =        new Font("Roboto Regular", 0, 18);
        ROBOTO_THIN =           new Font("Roboto Thin", 0, 18);
        ROBOTO_THIN_ITALIC =    new Font("Roboto Thin Italic", 0, 18);
    }
}
