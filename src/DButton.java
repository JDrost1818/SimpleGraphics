import settings.DBUTTON;

import javax.swing.*;

public class DButton {

    private final JLabel d_button = new JLabel();

    public DButton(){
        d_button.setBackground(DBUTTON.BACKGROUND);
        d_button.setForeground(DBUTTON.TEXT);
        d_button.setBorder(DBUTTON.BORDER);
    }

    public DButton(String title){

    }
}
