import data.DATA;
import utils.SimpleComboBox;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;

public class DFileExplorer extends JFrame {

    private final JPanel contentPane = new JPanel(null);

    public DFileExplorer(){

        setUndecorated(true);
        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new LineBorder(new Color(0,0,0,25), 2));
        contentPane.add(get_minimize());
        contentPane.add(get_maximize());

        final JLabel dirTitle = new JLabel("Dir");
        dirTitle.setBounds(50,50,30,20);
        contentPane.add(dirTitle);

        final JComboBox<String> dirComboBox = new JComboBox<String>(getFileNames("C:\\"));
        dirComboBox.setBounds(90, 50, 200, 20);
        dirComboBox.setBorder(null);
        dirComboBox.setBackground(Color.white);
        for (int i = 0; i < dirComboBox.getComponentCount(); i++)
        {
            System.out.println(dirComboBox.getComponent(i));
            if (dirComboBox.getComponent(i) instanceof JComponent) {
                ((JComponent) dirComboBox.getComponent(i)).setBorder(new LineBorder(DATA.COLORS.GRAY));
            }
        }
        contentPane.add(dirComboBox);

        contentPane.add(new SimpleComboBox().get());
    }

    private String[] getFileNames(String s) {
        File[] files = new File(s).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && !pathname.isHidden();
            }
        });

        String[] strings = new String[files.length];
        for (int i=0; i < files.length; i++){
            strings[i] = files[i].getName();
        }

        return strings;
    }

    private JLabel get_minimize(){
        final JLabel header_min_label = new JLabel("_", SwingConstants.CENTER);
        header_min_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getFrames()[0].setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                header_min_label.setBackground(DATA.COLORS.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getContentPane().setCursor(Cursor.getDefaultCursor());
                header_min_label.setBackground(Color.white);
            }
        });
        header_min_label.setBackground(Color.white);
        header_min_label.setBounds(getContentPane().getWidth()-70, 10, 30, 30);
        header_min_label.setOpaque(true);
        header_min_label.setFont(new Font("Serif", Font.PLAIN, 20));
        header_min_label.setForeground(DATA.COLORS.DARK_GRAY);
        header_min_label.setVerticalAlignment(SwingConstants.TOP);

        return header_min_label;
    }

    private JLabel get_maximize() {
        final JLabel header_close_label_mi = new JLabel("X", SwingConstants.CENTER);
        header_close_label_mi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(-1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                header_close_label_mi.setBackground(DATA.COLORS.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getContentPane().setCursor(Cursor.getDefaultCursor());
                header_close_label_mi.setBackground(Color.white);
            }
        });
        header_close_label_mi.setBackground(Color.white);
        header_close_label_mi.setBounds(getContentPane().getWidth()-40, 10, 30, 30);
        header_close_label_mi.setOpaque(true);
        header_close_label_mi.setFont(new Font("Serif", Font.PLAIN, 20));
        header_close_label_mi.setForeground(DATA.COLORS.DARK_GRAY);
        header_close_label_mi.setVerticalAlignment(SwingConstants.BOTTOM);

        return header_close_label_mi;
    }
}
