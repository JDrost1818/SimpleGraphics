package dgraphics.utilities;

import dgraphics.data.Colors;
import dgraphics.data.DATA;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class SimpleExplorer extends JFrame {

    // Default L&F Stuff
    private final LineBorder defaultBorder = new LineBorder(new Color(0, 0, 0, 25), 2, true);
    private final Font defaultFont = new Font("Verdana", Font.PLAIN, 14);
    private final ImageIcon folderIcon = new ImageIcon(this.getClass().getResource("Flat_Folder_Icon.png"));

    // Top Level Containers
    private final JPanel contentPane = new JPanel(null);
    private final JPanel display = new JPanel(null);
    private final JPanel tabPanel = new JPanel(null);

    // Components - Tabs
    private JLabel backTab = new JLabel("", SwingConstants.CENTER);
    private JLabel firstTab = new JLabel("", SwingConstants.CENTER);
    private JLabel secondTab = new JLabel("", SwingConstants.CENTER);

    // Variables
    private String currentDirectory;
    private File[] curDirFiles;
    private ArrayList<File> filePath = new ArrayList<File>();
    private FolderPanel[] folderList = new FolderPanel[21];

    public SimpleExplorer() {

        // Decorate Frame
        setUndecorated(true);
        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        // Decorate Content Pane
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
        contentPane.add(ComponentFactory.getMinimize(contentPane, contentPane.getBackground()));
        contentPane.add(ComponentFactory.getClose(contentPane, contentPane.getBackground()));

        // Decorate Display
        display.setBounds(50, 60, 400, 185);
        display.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
        display.setBackground(Color.white);
        contentPane.add(display);

        // Decorate Tab Panel
        tabPanel.setBounds(50, 17, 350, 30);
        tabPanel.setBackground(Color.white);
        contentPane.add(tabPanel);

        // Add Buttons
        contentPane.add(getSelectButton());
        contentPane.add(getCancelButton());
        contentPane.add(getScrollLeftButton());
        contentPane.add(getScrollRightButton());

    }

    public void execute(String startingDirectory) {
        /*
            Populates the display. Everything else hereafter is handled
            by the buttons and the refreshHandler
         */

        setVisible(true);

        // Intitializes needed variables/data
        currentDirectory = startingDirectory;
        curDirFiles = getDirFiles(currentDirectory);
        filePath = getDirFilePath(currentDirectory);

        // Makes 21 panels because that's how many fit on the display
        for (int i=0; i < 21; i++) {
            String name = (curDirFiles.length > i) ? curDirFiles[i].getName() : "";
            folderList[i] = new FolderPanel(this, i, name, folderIcon);
            display.add(folderList[i].get());
        }

        // Makes the tabs
        makeTabs(filePath);

        refresh();
    }

    /*
        These are the main handlers for repopulating the display. They handle
         most of what goes on without doing much of the actual work.
     */

    public void refreshHandler(File curFile, int index) {
        /*
            The refresh handler systematically steps through the processes
            that are necessary after a user clicks on a button that necessitates
            a change to the display. It is merely a manager as other functions
            will wind up doing the grunt work.
         */

        // This means a Folder was clicked, which only returns an index, unlike tabs
        if (curFile == null) {
            curFile = getFile(index);
        }

        currentDirectory = curFile.getAbsolutePath();
        curDirFiles = getDirFiles(currentDirectory);
        filePath = getDirFilePath(currentDirectory);

        // Updates the 21 panels with their new names
        for (int i=0; i < 21; i++) {
            String name = (curDirFiles.length > i) ? curDirFiles[i].getName() : "";
            folderList[i].setName(name);
        }

        // Makes the tabs
        makeTabs(filePath);

        System.out.println(index);
        refresh();
    }

    private void makeTabs(ArrayList<File> filePath) {
        // Cleans up the tabPanel
        tabPanel.removeAll();

        int x_marg = 0, i =0;
        for (File curFile : filePath) {
            if (curFile.getName().equals("<")){
                x_marg += updateTab(backTab, filePath.get(1), x_marg);
                tabPanel.add(backTab);
            } else if (i == 0 || (i == 1 && filePath.size() > 2)) {
                x_marg += updateTab(firstTab, curFile, x_marg);
                tabPanel.add(firstTab);
            } else {
                updateTab(secondTab, curFile, x_marg);
                tabPanel.add(secondTab);
            }
            i++;
        }
    }

    /*
        These are data finders. They take a variable in and extrapolate
        that data to find more important information.
     */
    private File[] getDirFiles(String directory) {
        return new File(directory).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && !pathname.isHidden() && pathname.canRead();
            }
        });
    }

    private File getFile(int index) {
        // Will need to account for current page in future updates, but
        // Currently only working with one page.
        return curDirFiles[index];
    }

    private ArrayList<File> getDirFilePath(String currentDirectory) {
        String[] dirs = currentDirectory.split("\\\\");
        ArrayList<File> path = new ArrayList<File>();
        String curDir = "";

        for (int i=0; i < dirs.length; i++) {
            curDir += dirs[i] + "\\";
            if (i >= dirs.length-2){
                if (i == dirs.length-2 && dirs.length > 2)
                    path.add(new File("<"));
                path.add(new File(curDir));
            }
        }

        return path;
    }

    /*
        These are component makers. They are separated from the rest of
        the code in order to improve readability. They are all the buttons
        that can be found on the file explorer.
     */
    private Component getSelectButton() {
        JLabel selectButton = CustomFactory.buildButton("Select", DATA.COLORS.GREEN, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
            }
        });
        selectButton.setBounds(290, 250, 75, 35);
        selectButton.setFont(defaultFont);

        return selectButton;
    }

    private Component getCancelButton() {
        JLabel cancelButton = CustomFactory.buildButton("Cancel", DATA.COLORS.RED, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                getRootPane().getParent().dispatchEvent(new WindowEvent(((JFrame) getRootPane().getParent()), WindowEvent.WINDOW_CLOSING));
            }
        });
        cancelButton.setBounds(375, 250, 75, 35);
        cancelButton.setFont(defaultFont);

        return cancelButton;
    }

    private Component getScrollLeftButton() {
        JLabel scrollLeftButton = CustomFactory.buildButton("<", DATA.COLORS.GRAY, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                // Nothing Yet
            }
        });
        scrollLeftButton.setBounds(10, 139, 30, 30);
        scrollLeftButton.setFont(defaultFont);

        return scrollLeftButton;
    }

    private Component getScrollRightButton() {
        JLabel scrollRightButton = CustomFactory.buildButton(">", DATA.COLORS.GRAY, getContentPane(), true, new Runnable() {
            @Override
            public void run() {
                // Nothing Yet
            }
        });
        scrollRightButton.setBounds(460, 139, 30, 30);
        scrollRightButton.setFont(defaultFont);

        return scrollRightButton;
    }

    private int updateTab(final JLabel curTab, final File newFile, int margin) {
        /*
            Takes in a tab, edits  it for new properties, then returns its width
         */

        if (curTab == backTab) {
            curTab.setText("<");
        } else {
            curTab.setText(newFile.getName().equals("") ? "C:" : newFile.getName());
        }
        curTab.setForeground(Colors.backgroundToText(Color.white));
        curTab.setBackground(Color.white);
        curTab.setBorder(defaultBorder);
        curTab.setOpaque(true);

        curTab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((Component) e.getSource()).removeMouseListener(this);
                curTab.setBackground(Color.white);
                curTab.setForeground(DATA.COLORS.DARK_GRAY);
                curTab.setBorder(defaultBorder);
                refreshHandler(newFile, -1);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                curTab.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //Nothing
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                curTab.setBackground(DATA.COLORS.LIGHT_BLUE);
                curTab.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                curTab.setBorder(defaultBorder);
                curTab.setBackground(Color.white);
                curTab.setForeground(DATA.COLORS.DARK_GRAY);

                getContentPane().setCursor(Cursor.getDefaultCursor());
            }
        });

        int width = determineWidth(curTab);
        curTab.setBounds(margin, 0, width, 30);

        return width;
    }

    /*
        These are tools. Small functions that do often-repeated tasks
     */
    private void refresh() {
        repaint();
        validate();
    }

    private int determineWidth(JLabel label) {
        return ((int) (label.getFontMetrics(label.getFont()).getStringBounds(label.getText(),label.getGraphics()).getWidth())
                + label.getInsets().left + label.getInsets().right) + 12;
    }

}
