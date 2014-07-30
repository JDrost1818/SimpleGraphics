package dgraphics.settings;

import javax.swing.*;

public class SETTINGS {

    private final static JPanel CONTENT_PANE = new JPanel(null);
    private static boolean usedContentPane = false;

    public static JPanel useDefaultContentPane(){
        if (!usedContentPane){
            usedContentPane = true;
            return CONTENT_PANE;
        } else {
            return null;
        }
    }
}
