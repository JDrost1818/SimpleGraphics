import data.DATA;
import simplestructures.SimpleButton;
import simplestructures.SimpleComboBox;
import simplestructures.SimpleFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UIManager.put("Label.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("Label.foreground", new Color(0x404040));
        UIManager.put("TextField.font", new Font("Verdana", Font.PLAIN, 14));
        UIManager.put("TextField.foreground", new Color(0x404040));
        /*
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
        */
        SimpleFrame frame = new SimpleFrame(DATA.COLORS.darkColor);
        SimpleComboBox x = new SimpleComboBox(new Dimension(200, 45), new String[] { "CurItem", "SubItem 1", "SubItem 2", "SubItem 3" });
        x.setBounds(10, 10, x.getWidth(), x.getHeight());
        frame.add(x);

        SimpleButton y = new SimpleButton();
        y.setBounds(400,400,80,50);
        frame.add(y);
    }
}
