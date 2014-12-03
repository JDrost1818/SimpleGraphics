package data;

import java.awt.*; 
import java.io.IOException; 

///////////////////////////////////
// GENERATED CODE - DO NOT ALTER //
///////////////////////////////////

public class Fonts {

    public final Font TITLE = new Font("Verdana", Font.PLAIN, 36);
    public final Font DEFAULT = new Font("Verdana", Font.PLAIN, 24);
    public final Font SMALL = new Font("Verdana", Font.PLAIN, 14);

    public final Font TITLE_BOLD = new Font("Verdana", Font.BOLD, 36);
    public final Font DEFAULT_BOLD = new Font("Verdana", Font.BOLD, 24);
    public final Font SMALL_BOLD = new Font("Verdana", Font.BOLD, 14);

    public Font BUTTON_FONT = new Font("Verdana", Font.PLAIN, 24);

	public Fonts() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		// Registers Lato Fonts
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Black.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-BlackItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-BoldItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Hairline.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-HairlineItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-LightItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/lato/Lato-Regular.ttf")));
		} catch (FontFormatException e) {
     	   e.printStackTrace();
    	} catch (IOException e) {
     	   e.printStackTrace();
    	}

		// Registers Raleway Fonts
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-ExtraBold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-ExtraLight.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Heavy.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Medium.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-SemiBold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/raleway/Raleway-Thin.ttf")));
		} catch (FontFormatException e) {
     	   e.printStackTrace();
    	} catch (IOException e) {
     	   e.printStackTrace();
    	}

		// Registers Roboto Fonts
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Black.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-BlackItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-BoldItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-LightItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Medium.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-MediumItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-Thin.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/Roboto-ThinItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-Bold.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-BoldItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-Italic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-Light.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-LightItalic.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/roboto/RobotoCondensed-Regular.ttf")));
		} catch (FontFormatException e) {
     	   e.printStackTrace();
    	} catch (IOException e) {
     	   e.printStackTrace();
    	}
	}
}