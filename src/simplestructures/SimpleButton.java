package simplestructures;

import data.DATA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleButton extends JButton {

    private final Timer enterTimer;
    private final Timer leaveTimer;

    private Color activeTextColor = DATA.COLORS.DARK_GRAY;
    private Color textColor = DATA.COLORS.DARK_GRAY;
    private Color activeBackgroundColor = DATA.COLORS.GRAY;

    private boolean isOutlined = true;

    //////////////////
    // Constructors //
    //////////////////

    public SimpleButton() {
        this("SimpleButton", true);
    }

    public SimpleButton(String _title, Color _background, boolean _shouldOutline) {
        this(_title, _background, DATA.COLORS.DARK_GRAY, DATA.COLORS.DARK_GRAY, _shouldOutline);
    }

    public SimpleButton(String _title, Color _background, Color _text, boolean _shouldOutline) {
        this(_title, _background, _text, DATA.COLORS.DARK_GRAY, _shouldOutline);
    }

    public SimpleButton(String _title, Color _background, Color _text, Color _textActive, boolean _shouldOutline) {
        this(_title, _shouldOutline);
        setColors(_background, _text, _textActive);
    }

    public SimpleButton(String _title, boolean shouldOutline) {
        if (shouldOutline) {
            setBorder(new LineBorder(activeBackgroundColor, 1));
        } else {
            setBorder(null);
        }

        setText(_title);

        Color backgroundColor = new Color(0, 0, 0, 0);
        setBackground(backgroundColor, true);

        setListeners();

        isOutlined = shouldOutline;
        enterTimer = buildEnterTimer();
        leaveTimer = buildLeaveTimer();
    }

    /////////////////////////////////////////////////////
    //  Public Methods                                 //
    //     These are available to the user to provide  //
    //     easy-to-use functions to change the frame   //
    /////////////////////////////////////////////////////

    public void setColors(Color _background, Color _text, Color _textActive) {
        activeBackgroundColor = _background;
        textColor = _text;
        activeTextColor = _textActive;
        refreshColors();
    }

    public void setBackground(Color _background) {
        activeBackgroundColor = _background;
        refreshColors();
    }

    public void setForeground(Color _foreground) {
        textColor = _foreground;
        refreshColors();
    }

    public void setActiveForeground(Color _activeForeground) {
        activeTextColor = _activeForeground;
    }

    ///////////////////////////////////////////////////////
    //  Private Methods                                  //
    //     These are used in the constructor and simply  //
    //     give easy-to-decipher names to properties     //
    //     automatically given to the frame              //
    ///////////////////////////////////////////////////////

    private void setBackground(Color _background, boolean forSuper) {
        super.setBackground(_background);
    }

    private void setForeground(Color _foreground, boolean forSuper) {
        super.setForeground(_foreground);
    }

    private void refreshColors() {
        super.setForeground(textColor);
        if (isOutlined) {
            setBorder(new LineBorder(new Color(activeBackgroundColor.getRed(), activeBackgroundColor.getGreen(), activeBackgroundColor.getBlue(), 70), 1));
        }
        repaint();
    }

    private void setListeners() {
        // Add Actions/Event Handlers
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setForeground(activeTextColor, true);
                enterTimer.start();
                leaveTimer.stop();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enterTimer.stop();
                leaveTimer.start();
                setForeground(textColor, true);
                getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private Timer buildEnterTimer() {
        return new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JPanel contentPane = (JPanel) getRootPane().getContentPane();

                int red, green, blue, alpha;

                red     = activeBackgroundColor.getRed();
                green   = activeBackgroundColor.getGreen();
                blue    = activeBackgroundColor.getBlue();
                alpha   = getBackground().getAlpha() + 4;

                if (alpha >= 255) {
                    setBackground(activeBackgroundColor, true);
                    ((Timer)e.getSource()).stop();
                } else {
                    setBackground(new Color(red, green, blue, alpha), true);
                }
                contentPane.repaint();
            }
        });
    }

    private Timer buildLeaveTimer() {
        return new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JPanel contentPane = (JPanel) getRootPane().getContentPane();
                int red, green, blue, alpha;

                red     = activeBackgroundColor.getRed();
                green   = activeBackgroundColor.getGreen();
                blue    = activeBackgroundColor.getBlue();
                alpha   = getBackground().getAlpha() - 4;

                if (alpha < 0) {
                    setBackground(new Color(0,0,0,0), true);
                    ((Timer)e.getSource()).stop();
                } else {
                    setBackground(new Color(red, green, blue, alpha), true);
                }
                contentPane.repaint();
            }
        });
    }

}
