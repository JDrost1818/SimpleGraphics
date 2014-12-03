import data.DATA;
import data.constants.SimpleConstants;
import simplestructures.SimpleButton;
import simplestructures.SimpleComboBox;
import simplestructures.SimpleFrame;
import simplestructures.SimpleSeparator;
import simplestructures.SimpleLabel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UIManager.put("Label.font", DATA.FONTS.ROBOTO_LIGHT);
        UIManager.put("Button.font", DATA.FONTS.ROBOTO_LIGHT);
        UIManager.put("Label.foreground", new Color(0x404040));
        UIManager.put("TextField.font", DATA.FONTS.ROBOTO_THIN);
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
        SimpleFrame frame = new SimpleFrame(DATA.COLORS.WHITE);
        SimpleComboBox x = new SimpleComboBox(new Dimension(200, 45), new String[] { "CurItem", "SubItem 1", "SubItem 2", "SubItem 3" });
        x.setLocation(10, 10);
        frame.add(x);

        SimpleButton y = new SimpleButton();
        y.setBounds(400,400,160,50);
        y.setColors(DATA.COLORS.GREEN, DATA.COLORS.DARK_GRAY, DATA.COLORS.WHITE);
        frame.add(y);

        SimpleSeparator z = new SimpleSeparator(SimpleConstants.HORIZONTAL);
        z.setBounds(400,100,100,400);
        z.setThickness(1);
        z.setColor(DATA.COLORS.GRAY);
        frame.add(z);

        SimpleLabel label = new SimpleLabel("Hello World");
        label.setLocation(30,400);
        frame.add(label);

    }
}
