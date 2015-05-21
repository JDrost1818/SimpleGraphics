package utils;

import data.Colors;
import data.DATA;
import simplestructures.SimpleFrame;
import simplestructures.SimpleLabel;

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
        minLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        minLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((JFrame)contentPane.getRootPane().getParent()).setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                minLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((SimpleFrame)((JLabel) e.getSource()).getTopLevelAncestor()).close();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
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
        entryField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        entryField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (entryField.getText().equals(text))
                    entryField.setText("");
            }
        });

        return entryField;
    }
}
