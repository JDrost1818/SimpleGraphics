package dgraphics.utilities;

import dgraphics.data.DATA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SimpleComboBox {

    private final JPanel mainPanel = new JPanel(null);

    private final JLabel displayLabel = new JLabel("Home");
    private final JLabel seperator = new JLabel("|", SwingConstants.CENTER);
    private final JLabel displayIcon = new JLabel(">", SwingConstants.CENTER);

    public SimpleComboBox(){
        mainPanel.setBounds(90, 40, 200, 30);
        mainPanel.setBorder(new LineBorder(DATA.COLORS.GRAY,1,true));
        mainPanel.setBackground(Color.white);

        displayLabel.setBounds(10,0,165,30);
        displayLabel.setBackground(DATA.COLORS.DARK_GRAY);
        mainPanel.add(displayLabel);

        seperator.setBounds(165,-2,10,30);
        seperator.setVerticalAlignment(SwingConstants.CENTER);
        seperator.setForeground(DATA.COLORS.GRAY);
        seperator.setFont(new Font("Verdana", Font.PLAIN, 18));
        mainPanel.add(seperator);

        displayIcon.setBounds(175,0,25,30);
        displayIcon.setBackground(DATA.COLORS.DARK_GRAY);
        mainPanel.add(displayIcon);
    }

    public JComponent get() {
        return mainPanel;
    }

    public void setBounds(int x_marg, int y_marg, int x, int y){
        mainPanel.setBounds(x_marg,y_marg,x,y);
        //Will also need to adjust the components...
    }
}
