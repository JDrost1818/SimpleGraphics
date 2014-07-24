package utils;

import data.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComponentFactory {

    public static JLabel getColoredJLabel(String s, Color backgroundColor) {
        JLabel newLabel = new JLabel(s, SwingConstants.CENTER);

        newLabel.setBackground(backgroundColor);
        newLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        newLabel.setForeground(Colors.backgroundToText(backgroundColor));
        newLabel.setOpaque(true);

        return newLabel;
    }

    public static JLabel getMinimize(final Frame frame, final Container contentPane, final Color backgroundColor){
        final JLabel minLabel = getColoredJLabel("_", backgroundColor);
        final Color[] palette = Colors.getColorPalette(backgroundColor);
        minLabel.setBounds(730, 10, 30, 30);
        minLabel.setVerticalAlignment(SwingConstants.TOP);
        minLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                minLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contentPane.setCursor(Cursor.getDefaultCursor());
                minLabel.setBackground(palette[0]);
            }
        });

        return minLabel;
    }

    public static JLabel getClose(final Frame frame, final Container contentPane, final Color backgroundColor){
        final JLabel closeLabel = getColoredJLabel("X", backgroundColor);
        final Color[] palette = Colors.getColorPalette(backgroundColor);
        closeLabel.setBounds(760, 10, 30, 30);
        closeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(-1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                closeLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contentPane.setCursor(Cursor.getDefaultCursor());
                closeLabel.setBackground(palette[0]);
            }
        });

        return closeLabel;
    }
}
