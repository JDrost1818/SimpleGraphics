package simplestructures;

import data.DATA;
import data.constants.SimpleConstants;

import javax.swing.*;
import java.awt.*;

public class SimpleSeparator extends JSeparator {

    private static final int DEFAULT_THICKNESS = 1;
    private static final int DEFAULT_LENGTH = 50;
    private static final int DEFAULT_OFFSET = 0;

    private int direction;

    /////////////////
    // Constructors
    /////////////////

    /**
     * Constructs a default SimpleSeparator instance.
     * The separator is horizontal, 1px thick, and 50px wide.
     */
    public SimpleSeparator() {
        this(SimpleConstants.HORIZONTAL, DEFAULT_THICKNESS, DEFAULT_LENGTH);
    }

    /**
     * Constructs a SimpleSeparator instance with the specified orientation.
     * The separator is 1px thick
     *
     * @param _direction length of the separator
     * @exception IllegalArgumentException  if <code>_direction</code>
     *          is neither <code>SimpleConstants.HORIZONTAL</code>
     *          nor <code>SimpleConstants.VERTICAL</code>
     */
    public SimpleSeparator(int _direction) {
        this(_direction, DEFAULT_THICKNESS, DEFAULT_LENGTH);
    }

    /**
     * Constructs a SimpleSeparator instance with the specified orientation and thickness.
     *
     * @param _direction length of the separator
     * @param _thickness thickness of the separator
     * @exception IllegalArgumentException  if <code>_direction</code>
     *          is neither <code>SimpleConstants.HORIZONTAL</code>
     *          nor <code>SimpleConstants.VERTICAL</code>
     */
    public SimpleSeparator(int _direction, int _thickness) {
        this(_direction, _thickness, DEFAULT_LENGTH);
    }

    /**
     * Constructs a SimpleSeparator instance with the specified length, thickness
     * of the line, and orientation.
     *
     * @param _direction One of the following constants
     *           defined in <code>SimpleConstants</code>:
     *           <code>HORIZONTAL</code> or
     *           <code>VERTICAL</code>,
     * @param _thickness thickness of the separator
     * @param _length length of the separator
     * @exception IllegalArgumentException  if <code>_direction</code>
     *          is neither <code>SimpleConstants.HORIZONTAL</code>
     *          nor <code>SimpleConstants.VERTICAL</code>
     */
    public SimpleSeparator(int _direction, int _thickness, int _length) {
        this(_direction, _thickness, _length, DEFAULT_OFFSET, DATA.COLORS.DARK_GRAY);
    }

    /**
     * Constructs a SimpleSeparator instance with the specified length, thickness
     * of the line, orientation, and offset of line.
     *
     * @param _direction One of the following constants
     *           defined in <code>SimpleConstants</code>:
     *           <code>HORIZONTAL</code> or
     *           <code>VERTICAL</code>,
     * @param _thickness thickness of the separator
     * @param _length length of the separator
     * @param _offset offset of the separator with respect to its orientation
     * @exception IllegalArgumentException  if <code>_direction</code>
     *          is neither <code>SimpleConstants.HORIZONTAL</code>
     *          nor <code>SimpleConstants.VERTICAL</code>
     */
    public SimpleSeparator(int _direction, int _thickness, int _length, int _offset) {
        this(_direction, _thickness, _length, _offset, DATA.COLORS.DARK_GRAY);
    }

    /**
     * Constructs a SimpleSeparator instance with the specified length, thickness
     * of the line, orientation, offset of line, and color.
     *
     * @param _direction One of the following constants
     *           defined in <code>SimpleConstants</code>:
     *           <code>HORIZONTAL</code> or
     *           <code>VERTICAL</code>,
     * @param _thickness thickness of the separator
     * @param _length length of the separator
     * @param _offset offset of the separator with respect to its orientation
     * @param _color color of the separator
     * @exception IllegalArgumentException  if <code>_direction</code>
     *          is neither <code>SimpleConstants.HORIZONTAL</code>
     *          nor <code>SimpleConstants.VERTICAL</code>
     */
    public SimpleSeparator(int _direction, int _thickness, int _length, int _offset, Color _color) {
        if (_direction == SimpleConstants.HORIZONTAL) {
            setBounds(0, _offset, _length, _thickness);
        } else if (_direction == SimpleConstants.VERTICAL) {
            setBounds(_offset, 0, _thickness, _length);
        } else {
            throw new IllegalArgumentException("\"" + _direction + "\" is not a supported direction.");
        }

        setColor(_color);

        direction = _direction;
    }

    /////////////////
    //  Public Methods
    //     These are available to the user to provide
    //     easy-to-use functions to change the frame
    /////////////////

    /**
     * Sets the color of the separator.
     * The default value of this property is DARK_GRAY.
     *
     * @param _color  new color of separator
     */
    public void setColor(Color _color) {
       setBackground(_color);
       setForeground(_color);
    }

    /**
     * Sets the thickness of the separator.
     * The default value of this property is 1px.
     *
     * @param _thickness  new thickness of separator
     */
    public void setThickness(int _thickness) {
        if (direction == SimpleConstants.HORIZONTAL) {
            setBounds(getX(), getY(), getWidth(), _thickness);
        } else {
            setBounds(getX(), getY(), _thickness, getHeight());
        }
    }
}
