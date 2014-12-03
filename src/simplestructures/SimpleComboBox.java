package simplestructures;

import data.DATA;
import data.constants.SimpleConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SimpleComboBox extends JPanel {

    private String[] model;
    private final SimpleLabel curActionLabel;
    private final SimpleLabel expandLabel;
    private final JPanel curActionPanel;
    private final JPanel hiddenOptions = new JPanel(new GridLayout(0,1));

    private String curAction;

    public SimpleComboBox() {
        model = new String[] { "CurAction", "SubItem1", "SubItem2", "SubItem3" };

        curAction = model[0];
        curActionLabel = new SimpleLabel(curAction);
        expandLabel = new SimpleLabel("<html>&or</html>");
        expandLabel.setFont(new Font("Raleway Light", Font.PLAIN, 20));

        curActionPanel = new JPanel(null);
        curActionPanel.setBackground(DATA.COLORS.WHITE);
        curActionPanel.setBorder(new LineBorder(DATA.COLORS.BORDER_COLOR, 1, true));
        curActionPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        curActionPanel.add(curActionLabel);
        curActionPanel.add(expandLabel);

        sizeComponents();

        setSize(curActionPanel.getSize());
        add(curActionPanel);
        refresh();
    }

    public void sizeComponents() {
        curActionLabel.autoSize();
        expandLabel.autoSize();
        expandLabel.setSize(expandLabel.getHeight(), expandLabel.getHeight());

        // Size CurAction panel - the panel seen when not expanded
        double width = expandLabel.getWidth() + curActionLabel.getWidth() + 20;
        curActionPanel.setBounds(0,0,(int)width,(int) (width / SimpleConstants.GOLDEN_RATIO/2));

        // Puts components in correct spot
        int ymarg = (curActionPanel.getHeight() - curActionLabel.getHeight()) / 2;
        curActionLabel.setLocation(0, ymarg);
        expandLabel.setLocation((int) (width-expandLabel.getWidth()-2), ymarg+1);

        // Will need to size the options panel here
    }

    private void refresh() {
        refreshModel();
    }

    private void refreshModel() {
        curActionLabel.setText(curAction);
    }
}
