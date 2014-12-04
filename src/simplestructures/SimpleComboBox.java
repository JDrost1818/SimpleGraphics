package simplestructures;

import data.DATA;
import data.constants.SimpleConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleComboBox extends JPanel {

    private String[] model;
    private final SimpleLabel curActionLabel;
    private final SimpleLabel expandLabel;
    private final JPanel curActionPanel;
    private final JPanel hiddenOptions = new JPanel(null);

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
        setModel(model);
        setBackground(Color.white);

        setSize(curActionPanel.getWidth(), 1000);
        add(curActionPanel);
        add(hiddenOptions);
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
        int hiddenWidth = (int) (width * .9);
        int hiddenHeight = curActionPanel.getHeight() * (model.length-1) + 10;
        hiddenOptions.setBounds((int)(width*.05), curActionPanel.getHeight(), hiddenWidth, hiddenHeight);
    }

    public void setModel(String[] model) {
        hiddenOptions.removeAll();
        curActionLabel.setText(model[0]);
        int height = curActionPanel.getHeight();
        int width = (int) (curActionPanel.getWidth() * .9);
        for (int i=1; i < model.length; i++) {
            SimpleLabel curLabel = new SimpleLabel(model[i]);
            curLabel.setBounds(0,(i-1)*height, width, height);
            curLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            curLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(((SimpleLabel)e.getSource()).getText());
                    ((SimpleLabel)e.getSource()).setBackground(Color.white);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((SimpleLabel)e.getSource()).setBackground(DATA.COLORS.DARK_GRAY);
                    ((SimpleLabel)e.getSource()).setForeground(DATA.COLORS.WHITE);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    ((SimpleLabel)e.getSource()).setBackground(DATA.COLORS.LIGHT_GRAY);
                    ((SimpleLabel)e.getSource()).setForeground(DATA.COLORS.DARK_GRAY);
                }
            });
            hiddenOptions.add(curLabel);
        }

        hiddenOptions.setBounds((int)(width*.05), curActionPanel.getHeight(), width, height*(model.length-1));
    }

    private void refresh() {
        refreshModel();
    }

    private void refreshModel() {
        curActionLabel.setText(curAction);
    }
}
