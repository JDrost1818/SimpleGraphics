package simplestructures;

import data.DATA;
import utils.ComponentFactory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SimpleFrame extends JFrame {

    // Components of default frame
    private final JLabel minButton;
    private final JLabel closeButton;
    private final JPanel contentPane = new JPanel(null);

    // Variables to enable dragging
    private int pX, pY;

    //////////////////
    // Constructors //
    //////////////////

    /**
     * Constructs a default SimpleFrame instance.
     * The frame is 800px x 500px and colored WHITE
     */
    public SimpleFrame() {
        this(new Dimension(800,500));
    }

    /**
     * Constructs a SimpleFrame instance of the specified size.
     * The frame is colored WHITE
     * @param _size size of frame
     */
    public SimpleFrame(Dimension _size) {
        this(_size, DATA.COLORS.WHITE);
    }

    /**
     * Constructs a SimpleFrame instance of the specified color.
     * The frame is 800px x 500px
     *
     * @param _background color of frame
     */
    public SimpleFrame(Color _background) {
        this(new Dimension(800, 500), _background);
    }

    /**
     * Constructs a SimpleFrame of specified size and color
     *
     * @param _size size of frame
     * @param _background color of frame
     */
    public SimpleFrame(Dimension _size, Color _background) {

        setContentPane(contentPane);

        // Create Components
        contentPane.setBounds(new Rectangle(_size));
        minButton = ComponentFactory.getMinimize(contentPane, _background);
        closeButton = ComponentFactory.getClose(contentPane, _background);

        add(minButton);
        add(closeButton);

        // Decorate frame
        setSize(_size);
        setBackground(_background);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setFrameColor(_background);
        setBorder(new LineBorder(DATA.COLORS.BORDER_COLOR, 1));
        setDraggable();
        setVisible(true);
    }

    /////////////////////////////////////////////////////
    //  Public Methods                                 //
    //     These are available to the user to provide  //
    //     easy-to-use functions to change the frame   //
    /////////////////////////////////////////////////////

    /**
     * Sets the font of the default components on the frame
     *
     * @param _font font to be set
     */
    public void setFrameFont(Font _font) {
        minButton.setFont(_font);
        closeButton.setFont(_font);
    }

    /**
     * Sets the text color of the default components on the frame
     *
     * @param _color color to be set
     */
    public void setFrameTextColor(Color _color) {
        minButton.setForeground(_color);
        closeButton.setForeground(_color);
    }

    /**
     * Sets the background color of the frame to the specified color
     *
     * @param _color color to be set
     */
    public void setFrameColor(Color _color) {
        setBackground(_color);
        getContentPane().setBackground(_color);
    }

    /**
     * Sets the border of the content pane of the frame
     *
     * @param _border border to be set
     */
    public void setBorder(Border _border) {
        ((JPanel) getContentPane()).setBorder(_border);
    }

    /**
     * Adds a component to the content pane of the frame
     *
     * @param _component component to be added
     */
    public void add(JComponent _component) {
        contentPane.add(_component);
        contentPane.repaint();
    }

    ///////////////////////////////////////////////////////
    //  Private Methods                                  //
    //     These are used in the constructor and simply  //
    //     give easy-to-decipher names to properties     //
    //     automatically given to the frame              //
    ///////////////////////////////////////////////////////

    /**
     * Adds MouseListeners to the frame that allow the frame to be
     * dragged as if it were not set to undecorated
     */
    private void setDraggable() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pX = e.getX();
                pY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                setLocation(getLocation().x + event.getX() - pX,
                        getLocation().y + event.getY() - pY);
            }
        });
    }
}
