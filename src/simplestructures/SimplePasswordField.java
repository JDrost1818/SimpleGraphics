package simplestructures;

import data.DATA;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SimplePasswordField extends JPasswordField implements SimpleStructure {
    private String holdingText = "Enter Text Here";

    private Color inactiveColor = DATA.COLORS.INACTIVE_TEXT;
    private Color activeColor = DATA.COLORS.DARK_GRAY;

    public SimplePasswordField(String _holdingText) {
        holdingText = _holdingText;
        setText(holdingText);

        setForeground(inactiveColor);
        setBorder(new CompoundBorder(
                new LineBorder(DATA.COLORS.BORDER_COLOR, 1, true),
                new EmptyBorder(5, 10, 5, 10)));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(getPassword()).equals(holdingText)) {
                    setText("");
                    setForeground(activeColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(getPassword()).equals("")) {
                    setText(holdingText);
                    setForeground(inactiveColor);
                }
            }
        });
    }

    @Override
    public void setMargin(int marginLeft, int marginTop) {
        setBounds(marginLeft, marginTop, getWidth(), getHeight());
    }
}
