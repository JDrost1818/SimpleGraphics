package simplestructures;

import com.sun.javaws.exceptions.InvalidArgumentException;
import data.DATA;
import data.constants.SimpleConstants;

import javax.swing.*;
import java.awt.*;

public class SimpleSeperator extends JPanel {

    private int direction;

    //////////////////
    // Constructors //
    //////////////////

    SimpleSeperator(int _length) throws InvalidArgumentException {
        this(_length, 1, SimpleConstants.HORIZONTAL);
    }

    SimpleSeperator(int _length, int _thickness) throws InvalidArgumentException {
        this(_length, _thickness, SimpleConstants.VERTICAL, 0, DATA.COLORS.DARK_GRAY);
    }

    SimpleSeperator(int _length, int _thickness, int _direction) throws InvalidArgumentException {
        this(_length, _thickness, _direction, 0, DATA.COLORS.DARK_GRAY);
    }

    SimpleSeperator(int _length, int _thickness, int _direction, int _offset) throws InvalidArgumentException {
        this(_length, _thickness, _direction, _offset, DATA.COLORS.DARK_GRAY);
    }

    SimpleSeperator(int _length, int _thickness, int _direction, int _offset, Color _color) throws InvalidArgumentException {
        if (_direction == SimpleConstants.HORIZONTAL) {
            setBounds(0, _offset, _length, _thickness);
        } else if (_direction == SimpleConstants.VERTICAL) {
            setBounds(_offset, 0, _thickness, _length);
        } else {
            throw new InvalidArgumentException(new String[] {"\"" + _direction + "\" is not a supported direction."});
        }

        setBackground(_color);

        direction = _direction;
    }

    /////////////////////////////////////////////////////
    //  Public Methods                                 //
    //     These are available to the user to provide  //
    //     easy-to-use functions to change the frame   //
    /////////////////////////////////////////////////////

    public void setColor(Color _color) {
       setBackground(_color);
    }

    public void setThickness(int _thickness) {
        if (direction == SimpleConstants.HORIZONTAL) {
            setBounds(getX(), getY(), getWidth(), _thickness);
        } else {
            setBounds(getX(), getY(), _thickness, getHeight());
        }
    }
}
