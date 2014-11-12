package simplestructures;

import data.DATA;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleButton extends JButton {

    private final Timer enterTimer;
    private final Timer leaveTimer;

    private Color activeTextColor = DATA.COLORS.DARK_GRAY;
    private Color textColor = DATA.COLORS.DARK_GRAY;

    private Color activeBackgroundColor = DATA.COLORS.GRAY;
    private Color backgroundColor = DATA.COLORS.WHITE;

    public SimpleButton() {
        this("SimpleButton", null);
    }

    public SimpleButton(String _title, Border _border) {
        setText(_title);
        setBorder(_border);
        setFont(DATA.FONTS.BUTTON_FONT);
        setListeners();

        enterTimer = buildEnterTimer();
        leaveTimer = buildLeaveTimer();
    }

    /////////////////////////////////////////////////////
    //  Public Methods                                 //
    //     These are available to the user to provide  //
    //     easy-to-use functions to change the frame   //
    /////////////////////////////////////////////////////


    private void setListeners() {
        // Add Actions/Event Handlers
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                setForeground(activeTextColor);
                enterTimer.start();
                leaveTimer.stop();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                enterTimer.stop();
                leaveTimer.start();
                setForeground(textColor);
                getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private Timer buildEnterTimer() {
        return null;
    }

    private Timer buildLeaveTimer() {
        return null;
    }

}
