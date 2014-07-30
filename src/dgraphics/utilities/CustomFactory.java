package dgraphics.utilities;

import dgraphics.data.Colors;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomFactory {

    public static JPanel buildContentPane(Frame topFrame, Color backgroundColor, Dimension size){

        /*
            Produces a pretty bare-bones JPanel which fits the style
            that I usually like to incorporate into my applications.
            Since it returns the JPanel, all characteristics can be
            altered after creation and so no parameters are needed.
         */

        // Design layout and colors
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(backgroundColor);
        contentPane.setBounds(new Rectangle(size));

        // Add components
        contentPane.add(ComponentFactory.getMinimize(contentPane, backgroundColor));
        contentPane.add(ComponentFactory.getClose(contentPane, backgroundColor));

        return contentPane;
    }

    private static Timer buildFadeTimer(final Color[] colors, final Container contentPane, final JLabel button, final int direction) {
        return new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int red, green, blue, alpha;

                red     = colors[0].getRed();
                green   = colors[0].getGreen();
                blue    = colors[0].getBlue();
                alpha   = (button.getBackground() == contentPane.getBackground()) ? 0 : colors[4].getAlpha()+(10*direction);

                if (alpha > 255) {
                    button.setBackground(colors[0]);
                    ((Timer)e.getSource()).stop();
                } else if (alpha < 0) {
                    button.setBackground(contentPane.getBackground());
                    ((Timer)e.getSource()).stop();
                } else {
                    colors[4] = new Color(red, green, blue, alpha);
                    button.setBackground(new Color(red, green, blue, alpha));
                }
                contentPane.repaint();
            }
        });
    }

    public static JLabel buildButton(String title, Color background, final Container contentPane, final boolean invisible, final Runnable action){
        // Get default colored labels
        final JLabel newButton = ComponentFactory.getColoredJLabel(title, contentPane.getBackground());

        // Figure out colors
        final Color[] colors = new Color[5]; // { BGActive, BGInactive, TextActive, TextInactive, TimerColor }
        colors[0] = (invisible) ? background : Colors.highlightColor(background);
        colors[1] = (invisible) ? contentPane.getBackground() : background;
        colors[2] = Colors.backgroundToText(colors[0]);
        colors[3] = Colors.backgroundToText(colors[1]);

        newButton.setBackground(colors[1]);
        newButton.setForeground(colors[3]);

        // Add Actions/Event Handlers
        final Timer enterTimer = buildFadeTimer(colors, contentPane, newButton, 1);
        final Timer exitTimer = buildFadeTimer(colors, contentPane, newButton, -1);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                newButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newButton.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                newButton.setForeground(colors[2]);
                if (invisible) {
                    enterTimer.start();
                    exitTimer.stop();
                } else {
                    newButton.setBackground(colors[0]);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (invisible) {
                    enterTimer.stop();
                    exitTimer.start();
                } else {
                    newButton.setBackground(colors[1]);
                }

                newButton.setBorder(null);
                newButton.setForeground(colors[3]);
                newButton.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        return newButton;
    }
}
