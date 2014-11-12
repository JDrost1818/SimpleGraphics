package utilities;

import data.Colors;
import data.DATA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleComboBox extends JPanel {

    private final JPanel displayedPanel = new JPanel(null);

    private final JLabel curAction;

    private int actualHeight;

    // Colors
    private Color bgColor;
    private Color textColor;
    private Color outlineColor;
    private Color optionsHoverColor;

    private String[] options;
    private JLabel[] optionsLabels = new JLabel[0];

    ////////////////////
    //  CONSTRUCTORS  //
    ////////////////////
    public SimpleComboBox(Dimension size, String[] items) {
        this(size, items, DATA.COLORS.WHITE, DATA.COLORS.DARK_GRAY, DATA.COLORS.DARK_GRAY, DATA.COLORS.GRAY);
    }

    public SimpleComboBox(Dimension size, String[] items, Color bg, Color fg, Color outline, Color hoverColor){

        bgColor = bg;
        textColor = fg;
        outlineColor = outline;
        optionsHoverColor = hoverColor;

        setSize(size);
        setBackground(bg);
        setBorder(new LineBorder(outline, 1));
        setLayout(null);
        actualHeight = (int) size.getHeight();

        displayedPanel.setSize(size);
        displayedPanel.setBorder(new LineBorder(outline, 1));
        displayedPanel.setBackground(bg);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                Point p = e.getPoint().getLocation();
                if (e.getSource() == this) {
                    if (!contains(p)) {
                        setHeight(displayedPanel.getHeight());
                    }
                } else if (!contains((int) p.getX(), (int) p.getY()) && p.getY() != -1) {
                    setHeight(displayedPanel.getHeight());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (getHeight() > displayedPanel.getHeight()) {
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

        JLabel dropDown = new JLabel("<html>&or</html>");
        dropDown.setBounds(165, 15, 20, 15);
        displayedPanel.add(dropDown);

        setModel(items);
        add(displayedPanel);
    }



    public void setSelectionListener(MouseListener newListener) {
        Component[] components = getComponents();
        for (Component curComp : components) {
            if (curComp != displayedPanel) {
                curComp.addMouseListener(newListener);
            }
        }
    }

    public JComponent get() {
        return this;
    }

    public int getWidth() {
        return displayedPanel.getWidth();
    }

    public int getHeight() {
        return displayedPanel.getHeight();
    }

    private void setHeight(int newHeight) {
        setBounds(getX(), getY(), getWidth(), newHeight);
    }

    private JLabel buildSubItemPanel(final String name) {

        final JLabel returnLabel = new JLabel("   " + name);
        returnLabel.setOpaque(true);
        returnLabel.setBorder(new MatteBorder(0,1,0,1, outlineColor));
        returnLabel.setBackground(getBackground());
        returnLabel.setFont(DATA.FONTS.SMALL);
        returnLabel.setForeground(Colors.backgroundToText(displayedPanel.getBackground()));
        returnLabel.setBounds(0, actualHeight, getWidth(), 40);
        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel)e.getSource()).getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                ((JLabel)e.getSource()).setBackground(optionsHoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel)e.getSource()).getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                ((JLabel)e.getSource()).setBackground(getBackground());
                for (MouseListener curListener : getMouseListeners()) {
                    curListener.mouseExited(e);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                select(((JLabel)e.getSource()).getText().trim());
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

    public void setNewFont(Font newFont) {
        for (JLabel curLabel : optionsLabels) {
            curLabel.setFont(newFont);
        }
        curAction.setFont(newFont);
    }

    public void setModel(String[] newOptions) {
        MouseListener[] listeners = new MouseListener[0];
        if (optionsLabels.length > 0) {
             listeners = optionsLabels[0].getMouseListeners();
        }
        actualHeight = displayedPanel.getHeight();
        optionsLabels = new JLabel[options.length-1];
        this.options = newOptions;
        curAction.setText(options[0]);
        for (int i=1; i < options.length; i++) {
            JLabel subItem = buildSubItemPanel(options[i]);
            optionsLabels[i-1] = subItem;
            add(subItem);
            actualHeight += subItem.getHeight();
            for (MouseListener curListener : listeners) {
                subItem.addMouseListener(curListener);
            }
        }
        actualHeight++;
    }

    public String getCurSelection() {
        return curAction.getText();
    }

}
