package simplestructures;

import data.DATA;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleButton extends JButton implements SimpleStructure {

    private final Timer enterTimer;
    private final Timer leaveTimer;

    private Color activeTextColor = DATA.COLORS.DARK_GRAY;
    private Color textColor = DATA.COLORS.DARK_GRAY;
    private Color activeBackgroundColor = DATA.COLORS.GRAY;

    private boolean isOutlined = true;

    //////////////////
    // Constructors //
    //////////////////

    /**
     * Constructs a SimpleSeparator instance with the "SimpleButton" text,
     * a visible border with color DARK_GRAY, and a transparent background color
     */
    public SimpleButton() {
        this("SimpleButton", true);
    }

    /**
     * Constructs a SimpleSeparator instance with the the specified text,
     * the specified background color, and, if desired, a visible border
     * with color DARK_GRAY
     *
     * @param _title            text to appear on button
     * @param _background       background color of button on fade-in
     * @param _shouldOutline    if true, a border will be drawn around the button
     */
    public SimpleButton(String _title, Color _background, boolean _shouldOutline) {
        this(_title, _background, DATA.COLORS.DARK_GRAY, DATA.COLORS.DARK_GRAY, _shouldOutline);
    }

    /**
     * Constructs a SimpleSeparator instance with the the specified text,
     * the specified background color, the specified text color,
     * and, if desired, a visible border with color DARK_GRAY
     *
     * @param _title            text to appear on button
     * @param _background       background color of button on fade-in
     * @param _text             color text should appear
     * @param _shouldOutline    if true, a border will be drawn around the button
     */
    public SimpleButton(String _title, Color _background, Color _text, boolean _shouldOutline) {
        this(_title, _background, _text, DATA.COLORS.DARK_GRAY, _shouldOutline);
    }

    /**
     * Constructs a SimpleSeparator instance with the the specified text,
     * the specified background color, the specified text color,
     * the specified text color on mouseOver, and, if desired, a visible
     * border with color DARK_GRAY
     *
     * @param _title            text to appear on button
     * @param _background       background color of button on fade-in
     * @param _text             color text should appear
     * @param _textActive       color text should appear on mouseOver
     * @param _shouldOutline    if true, a border will be drawn around the button
     */
    public SimpleButton(String _title, Color _background, Color _text, Color _textActive, boolean _shouldOutline) {
        this(_title, _shouldOutline);
        setColors(_background, _text, _textActive);
    }

    /**
     * Constructs a SimpleSeparator instance with the the specified text,
     * a transparent background, and, if desired, a visible border with
     * color DARK_GRAY
     *
     * @param _title            text to appear on button
     * @param _shouldOutline    if true, a border will be drawn around the button
     */
    public SimpleButton(String _title, boolean _shouldOutline) {
        if (_shouldOutline) {
            setBorder(new LineBorder(activeBackgroundColor, 1));
        } else {
            setBorder(null);
        }

        setText(_title);

        Color backgroundColor = new Color(0, 0, 0, 0);
        setBackground(backgroundColor, true);
        setContentAreaFilled(false);
        setOpaque(true);
        setFocusPainted(false);

        setListeners();
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        isOutlined = _shouldOutline;
        enterTimer = buildEnterTimer();
        leaveTimer = buildLeaveTimer();
    }

    /////////////////////////////////////////////////////
    //  Public Methods                                 //
    //     These are available to the user to provide  //
    //     easy-to-use functions to change the frame   //
    /////////////////////////////////////////////////////

    /**
     * Sets the background color, text color, and active text color
     *
     * @param _background   background color on fade-in
     * @param _text         color text should appear
     * @param _textActive   color text should appear on mouseOver
     */
    public void setColors(Color _background, Color _text, Color _textActive) {
        activeBackgroundColor = _background;
        textColor = _text;
        activeTextColor = _textActive;
        refreshColors();
    }

    /**
     * Sets the background color
     *
     * @param _background   background color on fade-in
     */
    public void setBackground(Color _background) {
        activeBackgroundColor = _background;
        refreshColors();
    }

    /**
     * Sets the text color
     *
     * @param _foreground   color text should appear
     */
    public void setForeground(Color _foreground) {
        textColor = _foreground;
        refreshColors();
    }

    /**
     * Sets the active text color
     *
     * @param _activeForeground   color text should appear on mouseOver
     */
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
                setForeground(activeTextColor, true);
                enterTimer.start();
                leaveTimer.stop();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enterTimer.stop();
                leaveTimer.start();
                setForeground(textColor, true);
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

    @Override
    public void setMargin(int marginLeft, int marginTop) {
        setBounds(marginLeft, marginTop, getWidth(), getHeight());
    }
}
