package utils;

import data.DATAR;
import settings.DBUTTON;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DButton {

    private final JLabel d_button = new JLabel();

    public DButton(){
        d_button.setBackground(DBUTTON.BACKGROUND);
        d_button.setForeground(DBUTTON.TEXT);
        d_button.setBorder(DBUTTON.BORDER);
    }

    public DButton(String title){

    }

    public static JLabel get_button(String title, final Color back_color, final Container content_pane, final Runnable new_runnable){

        final JLabel new_button = new JLabel(title);
        new_button.setBackground(Color.white);
        new_button.setOpaque(true);
        new_button.setHorizontalAlignment(SwingConstants.CENTER);
        new_button.setVerticalAlignment(SwingConstants.CENTER);
        new_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new_runnable.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new_button.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                content_pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                new_button.setBackground(back_color);
                new_button.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                new_button.setBorder(null);
                new_button.setBackground(Color.white);
                new_button.setForeground(DATAR.COLORS.DARK_GRAY);

                content_pane.setCursor(Cursor.getDefaultCursor());
            }
        });
        return new_button;
    }

    public static JLabel get_button(String title, final Color back_color, final Container content_pane, final Border border, final Runnable new_runnable){

        final JLabel new_button = new JLabel(title);
        new_button.setBackground(Color.white);
        new_button.setOpaque(true);
        new_button.setHorizontalAlignment(SwingConstants.CENTER);
        new_button.setVerticalAlignment(SwingConstants.CENTER);
        new_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new_runnable.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new_button.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                content_pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                new_button.setBackground(back_color);
                new_button.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                new_button.setBorder(border);
                new_button.setBackground(Color.white);
                new_button.setForeground(DATAR.COLORS.DARK_GRAY);

                content_pane.setCursor(Cursor.getDefaultCursor());
            }
        });
        return new_button;
    }
}
