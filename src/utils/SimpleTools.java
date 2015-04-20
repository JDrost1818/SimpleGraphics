package utils;

import javax.swing.*;

public class SimpleTools {

    public static int centerV(JComponent topComponent, int bottom, JComponent toBeCentered) {
        int top = topComponent.getY() + topComponent.getHeight();
        return centerV(top, bottom, toBeCentered);
    }

    public static int centerV(int top, int bottom, JComponent toBeCentered) {
        return top + ((bottom - top) / 2) - (toBeCentered.getHeight()/2);
    }

    public static int centerH(JComponent leftComponent, int right, JComponent toBeCentered) {
        int left = leftComponent.getX() + leftComponent.getWidth();
        return centerH(left, right, toBeCentered);
    }

    public static int centerH(int left, int right, JComponent toBeCentered) {
        return left + ((right - left) / 2) - (toBeCentered.getWidth()/2);
    }
}
