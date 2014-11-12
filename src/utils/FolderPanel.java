package utils;

import data.DATA;
import simplestructures.SimpleExplorer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FolderPanel {

    private final JPanel mainPanel = new JPanel(null);
    private final int index;
    private JLabel name;
    private JLabel icon;
    private boolean isActive;

    public FolderPanel(final SimpleExplorer main, int number, String name, ImageIcon icon) {

        this.index = number;

        this.icon = new JLabel(icon);
        this.icon.setBounds(5,0,20,25);
        this.mainPanel.add(this.icon);

        this.name = new JLabel(name);
        this.name.setBounds(30,0,98,25);
        this.name.setFont(new Font("Verdana", Font.PLAIN, 14));
        this.mainPanel.add(this.name);

        this.mainPanel.setBounds(getBounds(number));
        this.mainPanel.setBackground(Color.white);
        this.mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.refreshHandler(null, index);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mainPanel.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                mainPanel.setBackground(DATA.COLORS.GRAY);
                mainPanel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainPanel.setBorder(null);
                if (!isActive) {
                    mainPanel.setBackground(Color.white);
                    mainPanel.setForeground(DATA.COLORS.DARK_GRAY);
                }

                mainPanel.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        if (name.equals("")) {
            mainPanel.setVisible(false);
        }
    }

    private Rectangle getBounds(int number) {
        int x = 5 + ((int) number/7) * 133;
        int y = 5 + (DMath.bind(number, 7)) * 25;
        int width = 125;
        int height = 25;
        return new Rectangle(x, y, width, height);
    }

    public void setBounds(int x, int y, int width, int height) {
        mainPanel.setBounds(x,y,width,height);
    }

    public JPanel get() {
        return this.mainPanel;
    }

    public void setName(String title) {
        if (title.equals("")){
            mainPanel.setVisible(false);
        } else {
            name.setText(title);
            mainPanel.setVisible(true);
        }
    }

    public void setIcon(ImageIcon newIcon) {
        this.icon.setIcon(newIcon);
    }

    public void setActive() {
        mainPanel.setBackground(DATA.COLORS.GRAY);
        isActive = true;
    }

    public void setInactive() {
        mainPanel.setBackground(Color.white);
        isActive = false;
    }
}
