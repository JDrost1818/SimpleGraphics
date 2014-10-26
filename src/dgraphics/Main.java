package dgraphics;

import dgraphics.utilities.SimpleExplorer;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UIManager.put("Label.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("Label.foreground", new Color(0x404040));
        UIManager.put("TextField.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("TextField.foreground", new Color(0x404040));

        String dest = "c:Users";
        SimpleExplorer x = new SimpleExplorer();
        x.setCustomFileFilter("exe");
        System.out.println(x.execute(dest));
        while (dest.equals(x.getReturnValue())){
            Thread.sleep(50);
        }
        System.out.println(x.getReturnValue());
        //GUI gui = new GUI();
        //gui.setVisible(true);
    }
}
