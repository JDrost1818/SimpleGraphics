package dgraphics.utilities;

import dgraphics.data.Colors;
import dgraphics.data.DATA;

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
    private final Font defaultFont = new Font("Verdana", Font.PLAIN, 14);
    private String currentDirectory;

    private ArrayList<File> filePath = new ArrayList<File>();
    private JPanel tab_panel = new JPanel(null);
    private JLabel back_tab;
    private JLabel first_tab = new JLabel("");
    private JLabel second_tab = new JLabel("");
    private int curPageOfResults = 0;
    private boolean notDone;

    private final ImageIcon folderIcon = new ImageIcon(this.getClass().getResource("Flat_Folder_Icon.png"));

    public String finalDestination = "";
    private Frame thisFrame;
    private String startingDirectory;

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
            } catch (Exception e) { /* Do Nothing */ }
        }

        tab_panel.setBounds(50, 17, 350, 30);
        tab_panel.setBackground(Color.white);
        contentPane.add(tab_panel);

        this.currentDirectory = (this.currentDirectory == null) ? "C:\\" : this.currentDirectory;
        back_tab = makeTab(new File("<"), new Runnable() {
            @Override
            public void run() {
                repopulateDisplay(filePath.get(filePath.size() - 2));
                back_tab.setBackground(Color.white);
                back_tab.setForeground(DATA.COLORS.DARK_GRAY);
                back_tab.setBorder(defaultBorder2);
            }
        });

        back_tab.setBounds(0, 0, 20, 30);
        back_tab.setBorder(defaultBorder2);
        back_tab.setFont(defaultFont);
        first_tab.setFont(defaultFont);
        second_tab.setFont(defaultFont);

        display.setBounds(50, 60, 400, 185);
        display.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
        display.setBackground(Color.white);
        contentPane.add(display);

        JLabel select = CustomFactory.buildButton("Select", DATA.COLORS.GREEN, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                finalDestination = currentDirectory;
                notDone = false;
                thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        select.setBounds(290, 250, 75, 35);
        select.setFont(defaultFont);
        contentPane.add(select);

        JLabel cancel = CustomFactory.buildButton("Cancel", DATA.COLORS.RED, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                finalDestination = startingDirectory;
                notDone = false;
                thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        cancel.setBounds(375, 250, 75, 35);
        cancel.setFont(defaultFont);
        contentPane.add(cancel);

        JLabel scrollLeftButton = CustomFactory.buildButton("<", DATA.COLORS.GRAY, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                if (curPageOfResults > 0) {
                    curPageOfResults--;
                    turnPage();
                }
            }
        });
        scrollLeftButton.setBounds(10, 139, 30, 30);
        scrollLeftButton.setFont(defaultFont);
        contentPane.add(scrollLeftButton);

        JLabel scrollRightButton = CustomFactory.buildButton(">", DATA.COLORS.GRAY, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                if (getFileNames(currentDirectory).length - 21 - curPageOfResults * 7 > 0) {
                    curPageOfResults++;
                    turnPage();
                }
            }
        });
        scrollRightButton.setBounds(460, 139, 30, 30);
        scrollRightButton.setFont(defaultFont);
        contentPane.add(scrollRightButton);

    }

    public String execute(final String startingDirectory){
        notDone = true;
        thisFrame.setVisible(true);
        currentDirectory = startingDirectory;
        this.startingDirectory = startingDirectory;
        repopulateDisplay(new File(startingDirectory));

        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() {
                while (notDone) {
                    // Do nothi
                }
                return null;
            }

            @Override
            protected void done() {

            }
        }.execute();

        return this.currentDirectory;

    }

    private void makeTabs(final String destination){

        currentDirectory = destination;

        String[] dirs = destination.split("\\\\");
        filePath.clear();
        String curDir = "";
        for (int i=0; i < dirs.length; i++) {
            curDir += dirs[i] + "\\";
            if (i >= dirs.length-2){
                if (i == dirs.length-2 && dirs.length > 2)
                    filePath.add(new File("<"));
                filePath.add(new File(curDir));
            }
        }

        // Clear the previous tabs
        tab_panel.removeAll();

        // Make new tabs
        int x_marg = 0, y_marg = 0, i=0;
        for (File curFile : filePath){
            if (curFile.getName().equals("<")){
                tab_panel.add(back_tab);
                x_marg += 20;
            } else if (i == 1 || i ==0) {
                first_tab = makeTab(curFile, null);
                int width = determineWidth(first_tab);
                first_tab.setBounds(x_marg, y_marg, width, 30);
                tab_panel.add(first_tab);
                x_marg += width;
            } else {
                second_tab = makeTab(curFile, null);
                int width = determineWidth(second_tab);
                second_tab.setBounds(x_marg, y_marg, width, 30);
                tab_panel.add(second_tab);
            }
            i++;
        }

        repaint();
        validate();
    }

    private int determineWidth(JLabel label) {
        return ((int) (label.getFontMetrics(label.getFont()).getStringBounds(label.getText(),label.getGraphics()).getWidth())
                + label.getInsets().left + label.getInsets().right) + 12;
    }

    private JLabel makeTab(final File someFile, final Runnable action) {
        String name = (someFile.getName().equals("") ? "C:" : someFile.getName());
        final JLabel newTab = new JLabel(name, SwingConstants.CENTER);
        newTab.setForeground(Colors.backgroundToText(Color.white));
        newTab.setBackground(Color.white);
        newTab.setBorder(defaultBorder2);
        newTab.setOpaque(true);

        newTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (action == null){
                    repopulateDisplay(someFile);
                    newTab.setBackground(Color.white);
                    newTab.setForeground(DATA.COLORS.DARK_GRAY);
                    newTab.setBorder(defaultBorder2);
                } else {
                    action.run();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                newTab.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                newTab.setBackground(DATA.COLORS.LIGHT_BLUE);
                newTab.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newTab.setBorder(defaultBorder2);
                newTab.setBackground(Color.white);
                newTab.setForeground(DATA.COLORS.DARK_GRAY);

                getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        return newTab;
    }

    private JPanel makeFolder(final File file, int x_marg, int y_marg){
        final JPanel newPanel = new JPanel(null);
        newPanel.setBounds(x_marg,y_marg,125,20);
        newPanel.setBackground(Color.white);
        newPanel.setToolTipText(file.getName());

        JLabel icon = new JLabel(folderIcon);
        icon.setBounds(0,0,20,20);
        newPanel.add(icon);

        JLabel folderName = new JLabel(file.getName());
        folderName.setBounds(25,0,103,20);
        folderName.setFont(defaultFont);
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
                newPanel.setBackground(DATA.COLORS.GRAY);
                newPanel.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newPanel.setBorder(null);
                newPanel.setBackground(Color.white);
                newPanel.setForeground(DATA.COLORS.DARK_GRAY);

                getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        return newPanel;
    }

    private File[] getFileNames(String s) {

        return new File(s).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && !pathname.isHidden() && pathname.canRead();
            }
        });
    }

    private void repopulateDisplay(File file) {
        display.removeAll();
        this.curPageOfResults = 0;
        repaint();
        validate();
        File[] files = getFileNames(file.getAbsolutePath());
        int x_marg = 5, y_marg = 5, i = 0;
        for (File curFile : files){
            if (!curFile.getName().equals("")){
                display.add(makeFolder(curFile, x_marg, y_marg));
                i = (i > 6) ? 1 : i + 1;
                x_marg = (i > 6) ? x_marg + 133 : x_marg;
                y_marg = (i > 6) ? 5 : y_marg + 25;
            }
        }
        makeTabs(file.getAbsolutePath());
    }

    private void turnPage(){
        display.removeAll();
        File[] files = getFileNames(new File(currentDirectory).getAbsolutePath());
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
                header_min_label.setBackground(DATA.COLORS.GRAY);
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
        header_min_label.setForeground(DATA.COLORS.DARK_GRAY);
        header_min_label.setVerticalAlignment(SwingConstants.TOP);

        return header_min_label;
    }

    private JLabel get_close() {
        final JLabel header_close_label_mi = new JLabel("X", SwingConstants.CENTER);
        header_close_label_mi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                finalDestination = "";
                thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
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
