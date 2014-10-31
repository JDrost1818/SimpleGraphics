package dgraphics.utilities;

import dgraphics.data.Colors;
import dgraphics.data.DATA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleComboBox {

    private final JPanel displayedPanel = new JPanel(null);
    private final JPanel wrapper = new JPanel(null);

    private final JLabel curAction;

    private int actualHeight;
    private final Color outlineColor;
    private final Color optionsHoverColor;
    private String[] options;
    private JLabel[] optionsLabels;

    public SimpleComboBox(Dimension size, Color bg, Color outline, Color hoverColor, String[] items){

        outlineColor = outline;
        optionsHoverColor = hoverColor;
        options = items;
        optionsLabels = new JLabel[items.length-1];

        wrapper.setSize(size);
        wrapper.setBackground(bg);
        wrapper.setBorder(new LineBorder(outline, 1));
        actualHeight = (int) size.getHeight();

        displayedPanel.setSize(size);
        displayedPanel.setBorder(new LineBorder(outline, 1));
        displayedPanel.setBackground(bg);
        wrapper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                wrapper.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                wrapper.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                Point p = e.getPoint().getLocation();
                if (e.getSource() == wrapper) {
                    if (!wrapper.contains(p)) {
                        setHeight(displayedPanel.getHeight());
                    }
                } else if (!wrapper.contains((int) p.getX(), (int) p.getY()) && p.getY() != -1) {
                    setHeight(displayedPanel.getHeight());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (wrapper.getHeight() > displayedPanel.getHeight()) {
                    setHeight(displayedPanel.getHeight());
                } else {
                    setHeight(actualHeight);
                }
            }
        });

        curAction = new JLabel(items[0]);
        curAction.setFont(DATA.FONTS.SMALL);
        curAction.setForeground(Colors.backgroundToText(displayedPanel.getBackground()));
        curAction.setBounds(15, 0, 125, 40);
        displayedPanel.add(curAction);

        for (int i=1; i < options.length; i++) {
            JLabel subItem = buildSubItemPanel(items[i], i);
            optionsLabels[i-1] = subItem;
            wrapper.add(subItem);
            actualHeight += subItem.getHeight();
        }
        actualHeight++;

        JLabel dropDown = new JLabel(DATA.IMAGES.DOWN_CARET);
        dropDown.setBounds(165, 15, 20, 15);
        displayedPanel.add(dropDown);

        wrapper.add(displayedPanel);
    }

    public void setSelectionListener(MouseListener newListener) {
        Component[] components = wrapper.getComponents();
        for (Component curComp : components) {
            if (curComp != displayedPanel) {
                curComp.addMouseListener(newListener);
            }
        }
    }

    public JComponent get() {
        return wrapper;
    }

    public void setBounds(int x_marg, int y_marg, int x, int y){
        wrapper.setBounds(x_marg,y_marg,x,y);
        //Will also need to adjust the components...
    }

    public int getWidth() {
        return displayedPanel.getWidth();
    }

    public int getHeight() {
        return displayedPanel.getHeight();
    }

    private void setHeight(int newHeight) {
        wrapper.setBounds(wrapper.getX(), wrapper.getY(), wrapper.getWidth(), newHeight);
    }

    private JLabel buildSubItemPanel(final String name, int index) {

        final JLabel returnLabel = new JLabel("   " + name);
        returnLabel.setOpaque(true);
        returnLabel.setBorder(new MatteBorder(0,1,0,1, outlineColor));
        returnLabel.setBackground(wrapper.getBackground());
        returnLabel.setFont(DATA.FONTS.SMALL);
        returnLabel.setForeground(Colors.backgroundToText(displayedPanel.getBackground()));
        returnLabel.setBounds(0, actualHeight, wrapper.getWidth(), 40);
        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                returnLabel.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                returnLabel.setBackground(optionsHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnLabel.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                returnLabel.setBackground(wrapper.getBackground());
                for (MouseListener curListener : wrapper.getMouseListeners()) {
                    curListener.mouseExited(e);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                select(returnLabel.getText().trim());
                setHeight(displayedPanel.getHeight());
            }
        });

        return returnLabel;

    }

    private void select(String name) {
        curAction.setText(name);
        int index = 0;
        for (String curOption : options) {
            if (!curOption.equals(name)) {
                optionsLabels[index].setText("   " + curOption);
                index++;
            }
        }
    }

    public void setFont(Font newFont) {
        for (JLabel curLabel : optionsLabels) {
            curLabel.setFont(newFont);
        }
        curAction.setFont(newFont);
    }

    public String getCurSelection() {
        return curAction.getText();
    }

}
