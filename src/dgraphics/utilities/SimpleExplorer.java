package dgraphics.utilities;

import dgraphics.data.Colors;
import dgraphics.data.DATA;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class SimpleExplorer extends JFrame {

    // Default L&F Stuff
    private final Font defaultFont = new Font("Verdana", Font.PLAIN, 14);
    private final LineBorder defaultBorder = new LineBorder(new Color(0, 0, 0, 25), 2, true);

    // Top Level Containers
    private final JPanel display = new JPanel(null);
    private final JPanel tabPanel = new JPanel(null);
    private final JPanel contentPane = new JPanel(null);

    // Components - Tabs
    private JLabel backTab = new JLabel("", SwingConstants.CENTER);
    private JLabel firstTab = new JLabel("", SwingConstants.CENTER);
    private JLabel secondTab = new JLabel("", SwingConstants.CENTER);

    // Variables
    private File currentFile;
    private File[] curDirFiles;
    private int curPageIndex = 0;
    private boolean notDone = true;
    private String currentDirectory;
    private String startingDirectory;
    private String finalDirectory = "";
    private FileFilter currentFileFilter;
    private File backFile = new File("<");
    private MouseListener backTabListener;
    private MouseListener firstTabListener;
    private MouseListener secondTabListener;

    private ArrayList<File> filePath = new ArrayList<File>();
    private FolderPanel[] folderList = new FolderPanel[21];

    public SimpleExplorer() {

        // Decorate Frame
        setUndecorated(true);
        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        // Decorate Content Pane
        contentPane.setBackground(Color.white);
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0, 25), 2));
        contentPane.add(getClose());
        contentPane.add(getMinimize());

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

        setFileFilterToFolders();

    }

    public String execute(String beginDirectory) {
        /*
            Populates the display. Everything else hereafter is handled
            by the buttons and the refreshHandler. This method runs until
            the frame is closed by a button and returns an appropriate
            directory string.
         */

        setVisible(true);

        // Used for return value if canceled
        startingDirectory = beginDirectory;

        // Makes the display's folders only if they aren't made already
        if (folderList[1] == null) {
            System.out.println(startingDirectory);
            curDirFiles = getDirFiles(startingDirectory);
            makeFolders();
        }

        // Calls the refreshHandler to update the UI
        refreshHandler(new File(startingDirectory), -1);

        while (notDone) {
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return finalDirectory;
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

        // This means a Folder was clicked, which only gives an index
        if (curFile == null) {
            curFile = getFile((this.curPageIndex*21) + index);
        }

        currentFile = curFile;
        currentDirectory = curFile.getAbsolutePath();
        curDirFiles = getDirFiles(currentDirectory);
        filePath = getDirFilePath(currentDirectory);

        // Updates the 21 panels with their new names
        updatePanels();

        // Makes the tabs
        makeTabs(filePath);

        refresh();
    }

    private void updatePanels() {
        File curFile;
        for (int i=this.curPageIndex*21, j=0; i < this.curPageIndex*21 + 21; i++, j++) {
             curFile = (curDirFiles != null && curDirFiles.length > i) ? curDirFiles[i] : null;
            if (curFile == null) {
                folderList[j].setName("");
            } else {
                folderList[j].setName(curFile.getName());
                
            }
            folderList[j].setName(name);
        }
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

    private void turnPage(int direction) {
        // direction = -1 for left or 1 for right

        if (curDirFiles.length > this.curPageIndex*21 && direction > 0) {
            this.curPageIndex += direction;
            refreshHandler(currentFile, -1);
        } else if (this.curPageIndex > 0 && direction < 0) {
            this.curPageIndex += direction;
            refreshHandler(currentFile, -1);
        }
    }

    private void exit(String exitDir) {
        finalDirectory = exitDir;
        notDone = false;
        setVisible(false);
        getRootPane().getParent().dispatchEvent(new WindowEvent(((JFrame) getRootPane().getParent()), WindowEvent.WINDOW_CLOSING));
    }

    public String  getReturnValue() {
        return finalDirectory;
    }


    /*
        These are data finders. They take a variable in and extrapolate
        that data to find more important information.
     */
    private File[] getDirFiles(String directory) {
        return new File(directory).listFiles(currentFileFilter);
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
                    path.add(backFile);
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
                exit(currentDirectory);
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
                exit(startingDirectory);
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
                turnPage(-1);
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
                turnPage(1);
            }
        });
        scrollRightButton.setBounds(460, 139, 30, 30);
        scrollRightButton.setFont(defaultFont);

        return scrollRightButton;
    }

    private JLabel getMinimize(){
        final JLabel minLabel = ComponentFactory.getColoredJLabel("_", Color.white);
        final Color[] palette = Colors.getColorPalette(Color.white);
        minLabel.setFont(defaultFont);
        minLabel.setBounds(contentPane.getWidth()-50, 10, 20, 20);
        minLabel.setVerticalAlignment(SwingConstants.TOP);
        minLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((JFrame)contentPane.getRootPane().getParent()).setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.getRootPane().getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                minLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contentPane.getRootPane().getContentPane().setCursor(Cursor.getDefaultCursor());
                minLabel.setBackground(palette[0]);
            }
        });

        return minLabel;
    }

    public JLabel getClose(){
        final JLabel closeLabel = ComponentFactory.getColoredJLabel("X", Color.white);
        final Color[] palette = Colors.getColorPalette(Color.white);
        closeLabel.setFont(defaultFont);
        closeLabel.setBounds(contentPane.getWidth()-30, 10, 20, 20);
        closeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exit(startingDirectory);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                closeLabel.setBackground(palette[2]);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contentPane.setCursor(Cursor.getDefaultCursor());
                closeLabel.setBackground(palette[0]);
            }
        });

        return closeLabel;
    }

    private void makeFolders() {
        // Makes 21 panels because that's how many fit on the display
        System.out.println("Making Folders");
        for (int i=0; i < 21; i++) {
            String name = (curDirFiles != null && curDirFiles.length > i) ? curDirFiles[i].getName() : "";
            folderList[i] = new FolderPanel(this, i, name, DATA.ICONS.FOLDER_ICON);
            display.add(folderList[i].get());
        }
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

        curTab.setOpaque(true);
        curTab.setFont(defaultFont);
        curTab.setBorder(defaultBorder);
        curTab.setBackground(Color.white);
        curTab.setForeground(Colors.backgroundToText(Color.white));


        updateListener(curTab, newFile);

        int width = determineWidth(curTab);
        curTab.setBounds(margin, 0, width, 30);

        return width;
    }

    private void updateListener(final JLabel curTab, final File newFile) {
        // Removes old listener, updates to new listener, and adds back
        if (curTab == backTab) {
            curTab.removeMouseListener(backTabListener);
            backTabListener = getNewMouseListener(curTab, newFile);
            curTab.addMouseListener(backTabListener);
        } else if (curTab == firstTab) {
            curTab.removeMouseListener(firstTabListener);
            firstTabListener = getNewMouseListener(curTab, newFile);
            curTab.addMouseListener(firstTabListener);
        } else {
            curTab.removeMouseListener(secondTabListener);
            secondTabListener = getNewMouseListener(curTab, newFile);
            curTab.addMouseListener(secondTabListener);
        }
    }

    private MouseListener getNewMouseListener(final JLabel curTab, final File newFile) {

        return new MouseListener() {
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
        };
    }

    public void setFileFilterToFolders() {
        currentFileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() && !pathname.isHidden() && pathname.canRead();
            }
        };
    }

    public void setFileFilter(final String[] acceptedFileExtensions) {
        currentFileFilter =  new FileFilter() {
            @Override
            public boolean accept(File curFile) {
                for (String curExtension : acceptedFileExtensions) {
                    if (!curFile.getAbsolutePath().contains(curExtension) && !curFile.isDirectory()){
                        return false;
                    }
                }
                return curFile.isDirectory() && !curFile.isHidden() && curFile.canRead();
            }
        };
    }

    public void setFileFilterToExecutables() {
        currentFileFilter =  new FileFilter() {
            @Override
            public boolean accept(File curFile) {
                return (curFile.isDirectory() && !curFile.isHidden() && curFile.canRead()) ||
                       (!curFile.isDirectory() && curFile.canExecute());
            }
        };
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
