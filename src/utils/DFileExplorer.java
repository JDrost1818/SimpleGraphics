package utils;

import data.DATAR;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class DFileExplorer extends JFrame {

    private final JPanel contentPane = new JPanel(null);
    private final LineBorder defaultBorder2 = new LineBorder(new Color(0, 0, 0, 25), 2, true);
    private final JPanel display = new JPanel(null);
    private String currentDirectory;

    private ArrayList<File> filePath = new ArrayList<File>();
    private JLabel back_tab;
    private JLabel first_tab = new JLabel("");
    private JLabel second_tab = new JLabel("");
    private int curPageOfResults = 0;

    public String finalDestination = "";
    private Frame thisFrame;

    public DFileExplorer() {

        setUndecorated(true);
        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setContentPane(contentPane);


        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
        contentPane.add(get_minimize());
        contentPane.add(get_close());
        contentPane.add(first_tab);
        contentPane.add(second_tab);

        for (Frame frame : getFrames()) {
            try {
                JFrame jframe = (JFrame) frame;
                if (jframe.getContentPane().equals(contentPane)) {
                    thisFrame = frame;
                }
            } catch (Exception e) { // Do Nothing }
            }

            this.currentDirectory = (this.currentDirectory == null) ? "C:\\" : this.currentDirectory;
            back_tab = DButton.get_button("<", DATAR.COLORS.LIGHT_BLUE, getContentPane(), defaultBorder2, new Runnable() {
                @Override
                public void run() {
                    repopulateDisplay(filePath.get(filePath.size() - 2));
                }
            });

            back_tab.setBounds(50, 17, 20, 30);
            back_tab.setBorder(defaultBorder2);
            back_tab.setFont(new Font("Verdana", Font.PLAIN, 14));
            first_tab.setFont(new Font("Verdana", Font.PLAIN, 14));
            second_tab.setFont(new Font("Verdana", Font.PLAIN, 14));

            display.setBounds(50, 60, 400, 185);
            display.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
            display.setBackground(Color.white);
            contentPane.add(display);

            JLabel select = DButton.get_button("Select", DATAR.COLORS.GREEN, getContentPane(), new Runnable() {
                @Override
                public void run() {
                    finalDestination = currentDirectory;
                    thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
                }
            });
            select.setBounds(290, 250, 75, 35);
            select.setFont(new Font("Verdana", Font.PLAIN, 14));
            contentPane.add(select);

            JLabel cancel = DButton.get_button("Cancel", DATAR.COLORS.RED, getContentPane(), new Runnable() {
                @Override
                public void run() {
                    finalDestination = null;
                    thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
                }
            });
            cancel.setBounds(375, 250, 75, 35);
            cancel.setFont(new Font("Verdana", Font.PLAIN, 14));
            contentPane.add(cancel);

            JLabel scrollLeftButton = DButton.get_button("<", DATAR.COLORS.GRAY, getContentPane(), new Runnable() {
                @Override
                public void run() {
                    if (curPageOfResults > 0) {
                        curPageOfResults--;
                        turnPage();
                    }
                }
            });
            scrollLeftButton.setBounds(10, 139, 30, 30);
            scrollLeftButton.setFont(new Font("Verdana", Font.PLAIN, 14));
            contentPane.add(scrollLeftButton);

            JLabel scrollRightButton = DButton.get_button(">", DATAR.COLORS.GRAY, getContentPane(), new Runnable() {
                @Override
                public void run() {
                    if (getFileNames(currentDirectory).length - 21 - curPageOfResults * 7 > 0) {
                        curPageOfResults++;
                        turnPage();
                    }
                }
            });
            scrollRightButton.setBounds(460, 139, 30, 30);
            scrollRightButton.setFont(new Font("Verdana", Font.PLAIN, 14));
            contentPane.add(scrollRightButton);
        }
    }

    public void execute(final String startingDirectory){
        thisFrame.setVisible(true);
        System.out.println("HERE");
        currentDirectory = startingDirectory;
        repopulateDisplay(new File(startingDirectory));

    }

    private void makeTabs(final String name, final String destination){

        currentDirectory = destination;

        String[] dirs = destination.split("\\\\");
        filePath.clear();
        String curDir = "";
        for (String dir : dirs){
            curDir += dir + "\\";
            filePath.add(new File(curDir));
        }

        contentPane.remove(back_tab);
        contentPane.remove(first_tab);
        contentPane.remove(second_tab);

        if (filePath.size() > 1){
            first_tab =  makeTab(filePath.get(filePath.size() - 2).getName(), filePath.get(filePath.size() - 2).getAbsolutePath());
            first_tab.setFont(new Font("Verdana", Font.PLAIN, 14));
            int minWidth = ((int) (first_tab.getFontMetrics(first_tab.getFont()).getStringBounds(first_tab.getText(),first_tab.getGraphics()).getWidth()) + first_tab.getInsets().left + first_tab.getInsets().right) + 12;
            first_tab.setBorder(defaultBorder2);
            first_tab.setBounds(50, 17, minWidth, 30);
            contentPane.add(first_tab);

            second_tab = makeTab(name, destination);
            second_tab.setFont(new Font("Verdana", Font.PLAIN, 14));
            int minWidth2 = ((int) (second_tab.getFontMetrics(second_tab.getFont()).getStringBounds(second_tab.getText(),second_tab.getGraphics()).getWidth()) + second_tab.getInsets().left + second_tab.getInsets().right) + 12;
            second_tab.setBorder(defaultBorder2);
            second_tab.setBounds(50 + first_tab.getWidth(), 17, minWidth2, 30);
            contentPane.add(second_tab);
        } else {
            second_tab = makeTab(name, destination);
            second_tab.setFont(new Font("Verdana", Font.PLAIN, 14));
            int minWidth2 = ((int) (second_tab.getFontMetrics(second_tab.getFont()).getStringBounds(second_tab.getText(),second_tab.getGraphics()).getWidth()) + second_tab.getInsets().left + second_tab.getInsets().right) + 12;
            second_tab.setBorder(defaultBorder2);
            second_tab.setBounds(50, 17, minWidth2, 30);
            contentPane.add(second_tab);
        }

        if (filePath.size() > 2){
            first_tab.setBounds(first_tab.getX() + 20, first_tab.getY(), first_tab.getWidth(), first_tab.getHeight());
            second_tab.setBounds(second_tab.getX() + 20, second_tab.getY(), second_tab.getWidth(), second_tab.getHeight());
            contentPane.add(back_tab);
        }

        repaint();
    }

    private JLabel makeTab(String name, final String destination) {
        name = (name.equals("") ? "C:" : name);
        return DButton.get_button(name, DATAR.COLORS.LIGHT_BLUE, getContentPane(), defaultBorder2, new Runnable() {
            @Override
            public void run() {
                repopulateDisplay(new File(destination));
            }
        });
    }

    private JPanel makeFolder(final File file, int x_marg, int y_marg){
        final JPanel newPanel = new JPanel(null);
        newPanel.setBounds(x_marg,y_marg,125,20);
        newPanel.setBackground(Color.white);

        JLabel icon = new JLabel(new ImageIcon(this.getClass().getResource("Flat_Folder_Icon.png")));
        icon.setBounds(0,0,20,20);
        newPanel.add(icon);

        JLabel folderName = new JLabel(file.getName());
        folderName.setBounds(25,0,103,20);
        folderName.setFont(new Font("Verdana", Font.PLAIN, 14));
        newPanel.add(folderName);

        newPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repopulateDisplay(file.getAbsoluteFile());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                newPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                newPanel.setBackground(DATAR.COLORS.GRAY);
                newPanel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newPanel.setBorder(null);
                newPanel.setBackground(Color.white);
                newPanel.setForeground(DATAR.COLORS.DARK_GRAY);

                getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        return newPanel;
    }

    private String[] getFileNames(String s) {

        File[] files = new File(s).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && !pathname.isHidden() && pathname.canRead();
            }
        });

        String[] strings = new String[files.length];
        for (int i=0; i < files.length; i++){
            strings[i] = files[i].getName();
        }

        return strings;
    }

    private void repopulateDisplay(File file) {
        display.removeAll();
        this.curPageOfResults = 0;
        repaint();
        String[] files = getFileNames(file.getAbsolutePath());
        int x_marg = 5, y_marg = 5, i = 0;
        for (String fileName : files){
            display.add(makeFolder(new File(file.getAbsolutePath() + "\\" + fileName), x_marg, y_marg));
            i = (i > 6) ? 1 : i + 1;
            x_marg = (i > 6) ? x_marg + 133 : x_marg;
            y_marg = (i > 6) ? 5 : y_marg + 25;
        }
        makeTabs(file.getName(), file.getAbsolutePath());
    }

    private File[] fileNamesToFiles(String[] fileNames){
        File[] files = new File[fileNames.length];
        for(int i=0; i < files.length; i++){
            files[i] = new File(fileNames[i]);
        }
        return files;
    }

    private void turnPage(){
        display.removeAll();
        File[] files = fileNamesToFiles(getFileNames(new File(currentDirectory).getAbsolutePath()));
        int x_marg = 5, y_marg = 5, j = 0;
        for (int i=curPageOfResults*7; i < files.length; i++){
            display.add(makeFolder(files[i], x_marg, y_marg));
            j = (j > 6) ? 1 : j + 1;
            x_marg = (j > 6) ? x_marg + 133 : x_marg;
            y_marg = (j > 6) ? 5 : y_marg + 25;
        }
        repaint();
        validate();
    }

    private JLabel get_minimize(){
        final JLabel header_min_label = new JLabel("_", SwingConstants.CENTER);
        header_min_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisFrame.setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                header_min_label.setBackground(DATAR.COLORS.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getContentPane().setCursor(Cursor.getDefaultCursor());
                header_min_label.setBackground(Color.white);
            }
        });
        header_min_label.setBackground(Color.white);
        header_min_label.setBounds(getContentPane().getWidth() - 70, 10, 30, 30);
        header_min_label.setOpaque(true);
        header_min_label.setFont(new Font("Serif", Font.PLAIN, 20));
        header_min_label.setForeground(DATAR.COLORS.DARK_GRAY);
        header_min_label.setVerticalAlignment(SwingConstants.TOP);

        return header_min_label;
    }

    private JLabel get_close() {
        final JLabel header_close_label_mi = new JLabel("X", SwingConstants.CENTER);
        header_close_label_mi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                finalDestination = null;
                thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                header_close_label_mi.setBackground(DATAR.COLORS.GRAY);
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
        header_close_label_mi.setForeground(DATAR.COLORS.DARK_GRAY);
        header_close_label_mi.setVerticalAlignment(SwingConstants.BOTTOM);

        return header_close_label_mi;
    }
}
