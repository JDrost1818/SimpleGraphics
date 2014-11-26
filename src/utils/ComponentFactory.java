package utils;

import data.Colors;
import data.DATA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComponentFactory {

    public static JLabel getColoredJLabel(String s, Color backgroundColor) {
        JLabel newLabel = new JLabel(s, SwingConstants.CENTER);

        newLabel.setBackground(backgroundColor);
        newLabel.setForeground(Colors.backgroundToText(backgroundColor));
        newLabel.setOpaque(true);

        return newLabel;
    }

    public static JLabel getMinimize(final JPanel contentPane, final Color backgroundColor){
        final JLabel minLabel = getColoredJLabel("_", backgroundColor);
        final Color[] palette = Colors.getColorPalette(backgroundColor);
        minLabel.setBounds(contentPane.getWidth()-70, 10, 30, 30);
        minLabel.setVerticalAlignment(SwingConstants.TOP);
        minLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((JFrame)contentPane.getRootPane().getParent()).setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                minLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contentPane.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                minLabel.setBackground(palette[0]);
            }
        });

        return minLabel;
    }

    public static JLabel getClose(final Container contentPane, final Color backgroundColor){
        final JLabel closeLabel = getColoredJLabel("X", backgroundColor);
        final Color[] palette = Colors.getColorPalette(backgroundColor);
        closeLabel.setBounds(contentPane.getWidth()-40, 10, 30, 30);
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

    public static JTextField getTextField(Color bg, final String text) {
        final JTextField entryField = new JTextField(text);

        entryField.setBorder(null);
        entryField.setBackground(bg);
        entryField.setEditable(true);
        entryField.setFont(DATA.FONTS.SMALL);
        entryField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entryField.getText().equals(text))
                    entryField.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                entryField.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entryField.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        return entryField;
    }
}
