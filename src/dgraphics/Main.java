package dgraphics;

import dgraphics.utilities.SimpleExplorer;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        UIManager.put("Label.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("Label.foreground", new Color(0x404040));
        UIManager.put("TextField.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("TextField.foreground", new Color(0x404040));

        SimpleExplorer x = new SimpleExplorer();
        System.out.println("Final place = " + x.execute("C:\\Users\\"));
        //GUI gui = new GUI();
        //gui.setVisible(true);
    }
}
