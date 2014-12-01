package simplestructures;

import javax.swing.*;

public class SimpleLabel extends JLabel {

	public SimpleLabel(String text) {
		setText(text);
		setSize(determineWidth(), determineHeight());
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	private int determineWidth() {
        return ((int) (getFontMetrics(getFont()).getStringBounds(getText(), getGraphics()).getWidth())) + 12;
    }

    private int determineHeight() {
        return ((int) (getFontMetrics(getFont()).getStringBounds(getText(), getGraphics()).getHeight())) + 12;
    }
}