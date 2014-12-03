import data.DATA;
import data.constants.SimpleConstants;
import simplestructures.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UIManager.put("Label.font", new Font("Roboto Light", 0, 18));
        UIManager.put("Button.font", new Font("Roboto Light", 0, 18));
        UIManager.put("Label.foreground", new Color(0x404040));
        UIManager.put("TextField.font", new Font("Roboto Thin", 0, 18));
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
        SimpleComboBox x = new SimpleComboBox();
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
        label.setFont(new Font("Lato Light", Font.PLAIN, 20));
        label.autoSize();
        label.setLocation(30,400);
        frame.add(label);
    }
}
