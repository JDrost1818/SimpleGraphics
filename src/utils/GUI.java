package utils;

import data.DATA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

    private final JPanel contentPane = new JPanel(null);
    private final Frame thisFrame;
    private Timer timer;

    public GUI(){

        final Color background = Color.white;

        contentPane.setBounds(0,0,800,500);
        contentPane.setBackground(background);
        setContentPane(contentPane);

        setBounds(0, 0, 800, 500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        //add(ComponentFactory.getMinimize(getFrame(), background));
        //add(ComponentFactory.getClose(getFrame(), background));

        thisFrame = getFrame();

        final JLabel buttonConcept = new JLabel("Concept", SwingConstants.CENTER);
        buttonConcept.setBounds(300, 250, 230, 80);
        buttonConcept.setFont(new Font("Verdana", Font.PLAIN, 24));
        buttonConcept.setOpaque(true);
        buttonConcept.setBackground(Color.white);
        buttonConcept.setForeground(DATA.COLORS.DARK_GRAY);

        timer = new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int red, green, blue, alpha;
                Color background = buttonConcept.getBackground();
                if (background == Color.white){
                    red = DATA.COLORS.BLUE.getRed();
                    green = DATA.COLORS.BLUE.getGreen();
                    blue = DATA.COLORS.BLUE.getBlue();
                    background = new Color(red, green, blue, 0);
                }
                red = background.getRed();
                green = background.getGreen();
                blue = background.getBlue();
                alpha = background.getAlpha();

                buttonConcept.setBackground(new Color(red, green, blue, alpha+5));
                buttonConcept.setForeground(buttonConcept.getForeground().brighter());

                repaint();
                if (alpha == 250) {
                    timer.stop();
                }
            }
        });

        buttonConcept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                timer.stop();
                buttonConcept.setBackground(Color.white);
                buttonConcept.setForeground(DATA.COLORS.DARK_GRAY);
                contentPane.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        });
        //add(buttonConcept);
    }

    private Frame getFrame(){
        for (Frame frame : getFrames()) {
            try {
                JFrame jframe = (JFrame) frame;
                if (jframe.getContentPane().equals(contentPane)) {
                    return frame;
                }
            } catch (Exception e) { /* Do Nothing */ }
        }
        return null;
    }

    public void fadeIn(JComponent component, Color color){

    }

}
