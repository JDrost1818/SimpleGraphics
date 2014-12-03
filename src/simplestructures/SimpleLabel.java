package simplestructures;

import data.DATA;

import javax.swing.*;
import java.awt.*;

public class SimpleLabel extends JLabel {

	public SimpleLabel(String text) {
        this(text, DATA.COLORS.DARK_GRAY, null);
	}

    public SimpleLabel(String text, Color fg) {
        this(text, fg, null);
    }

    public SimpleLabel(String text, Color fg, Color bg) {
        setText(text);

        setForeground(fg);
        setBackground(bg);

        autoSize();
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setBackground(Color bg) {
        setOpaque(bg != null); // So if bg is null, sets transparent
        super.setBackground(bg);
    }

    public void autoSize() {
        setSize(determineWidth(), determineHeight());
    }

	private int determineWidth() {
        return ((int) (getFontMetrics(getFont()).getStringBounds(getText(), getGraphics()).getWidth())) + 12;
    }

    private int determineHeight() {
        return ((int) (getFontMetrics(getFont()).getStringBounds(getText(), getGraphics()).getHeight())) + 12;
    }
}