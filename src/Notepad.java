import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Notepad implements ActionListener {

    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;
    JMenu file, edit, format, view, help;
    JMenuItem newTab, newWindow, open, save, saveAs, saveAll, closeWindow, closeTab, print, exit;
    JMenuItem undo, redo, cut, copy, paste, delete, selectAll, replace, find;
    JMenuItem statusBar, wordWrap;
    JMenuItem zoomIn, zoomOut, resetZoom;
    JMenuItem background, foreground, textSize, textType;
    JMenuItem helpMessage;

    Notepad() {
        frame = new JFrame("Herve's Notepad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(Color.WHITE);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        view = new JMenu("View");
        help = new JMenu("Help");

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);
        menuBar.add(help);

        frame.setJMenuBar(menuBar);

        newTab = new JMenuItem("New Tab                 CTRL+N");
        newWindow = new JMenuItem("New Window           CTRL+Shift+N");
        open = new JMenuItem("Open                      CTRL+O");
        save = new JMenuItem("Save                      CTRL+S");
        saveAs = new JMenuItem("Save As                 CTRL+Shift+S");
        saveAll = new JMenuItem("Save All               CTRL+Alt+S");
        closeWindow = new JMenuItem("Close Window       CTRL+Shift+W ");
        closeTab = new JMenuItem("Close Tab             CTRL+W");
        print = new JMenuItem("Print                    CTRL+P");
        exit = new JMenuItem("Exit");

        file.add(newTab);
        file.add(newWindow);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(saveAll);
        file.add(closeWindow);
        file.add(closeTab);
        file.add(print);
        file.add(exit);

        undo = new JMenuItem("Undo       CTRL+Z");
        redo = new JMenuItem("Redo         CTRL+Y");
        cut = new JMenuItem("Cut          CTRL+X");
        copy = new JMenuItem("Copy         CTRL+C");
        paste = new JMenuItem("Paste       CTRL+V");
        delete = new JMenuItem("Delete       Del");
        selectAll = new JMenuItem("Select All   CTRL+A");
        replace = new JMenuItem("Replace        CTRL+H");
        find = new JMenuItem("Find           CTRL+F");

        edit.add(undo);
        edit.add(redo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.add(find);
        edit.add(replace);
        edit.add(selectAll);

        statusBar = new JMenuItem("Status Bar");
        wordWrap = new JMenuItem("Word Wrap");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        resetZoom = new JMenuItem("Reset Zoom");

        view.add(zoomIn);
        view.add(zoomOut);
        view.add(resetZoom);
        view.add(statusBar);
        view.add(wordWrap);

        background = new JMenuItem("Set New Background");
        foreground = new JMenuItem("Set New Foreground");
        textSize = new JMenuItem("Set Text Size");
        textType = new JMenuItem("Set Text Style");

        format.add(background);
        format.add(foreground);
        format.add(textSize);
        format.add(textType);

        helpMessage = new JMenuItem("Access the help documentation for tips and instructions on using Notepad");
        help.add(helpMessage);

        // Set action commands for all menu items
        undo.setActionCommand("Undo");
        redo.setActionCommand("Redo");
        cut.setActionCommand("Cut");
        copy.setActionCommand("Copy");
        paste.setActionCommand("Paste");
        delete.setActionCommand("Delete");
        selectAll.setActionCommand("Select All");
        find.setActionCommand("Find");
        replace.setActionCommand("Replace");
        background.setActionCommand("Set New Background");
        foreground.setActionCommand("Set New Foreground");
        textSize.setActionCommand("Set Text Size");
        textType.setActionCommand("Set Text Style");
        newWindow.setActionCommand("New Window");
        open.setActionCommand("Open");
        save.setActionCommand("Save");
        saveAs.setActionCommand("SaveAs");
        saveAll.setActionCommand("SaveAll");
        exit.setActionCommand("Exit");
        newTab.setActionCommand("New Tab");
        closeWindow.setActionCommand("Close");
        zoomOut.setActionCommand("Zoom Out");
        zoomIn.setActionCommand("Zoom In");
        resetZoom.setActionCommand("Reset Zoom");

        // Add action listeners to menu items
        undo.addActionListener(this);
        redo.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        selectAll.addActionListener(this);
        find.addActionListener(this);
        replace.addActionListener(this);
        foreground.addActionListener(this);
        background.addActionListener(this);
        textSize.addActionListener(this);
        textType.addActionListener(this);
        newTab.addActionListener(this);
        newWindow.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        saveAll.addActionListener(this);
        closeWindow.addActionListener(this);
        exit.addActionListener(this);
        zoomOut.addActionListener(this);
        zoomIn.addActionListener(this);
        resetZoom.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Delete":
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                if (start != end) {
                    textArea.replaceRange("", start, end);
                }
                break;
            case "Select All":
                textArea.selectAll();
                break;

            case "Replace":
                String previousText = JOptionPane.showInputDialog(frame, "Enter text to find:");
                String replaceText = JOptionPane.showInputDialog(frame, "Enter text to replace with:");

                if (previousText != null && replaceText != null && !previousText.isEmpty() && !replaceText.isEmpty()) {
                    String newText = textArea.getText();
                    newText = newText.replace(previousText, replaceText);
                    textArea.setText(newText);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please specify both the text to find and the text to replace.");
                }
                break;

            case "Find":
                String searchText = JOptionPane.showInputDialog(frame, "Enter text to find:");
                if (searchText != null && !searchText.isEmpty()) {
                    String text = textArea.getText();
                    int index = text.indexOf(searchText);
                    if (index != -1) {
                        textArea.setSelectionStart(index);
                        textArea.setSelectionEnd(index + searchText.length());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Text not found: " + searchText);
                    }
                }
                break;
            case "Set New Background":
                Color background = JColorChooser.showDialog(frame, "Set New Background Color", Color.WHITE);
                if (background != null) {
                    textArea.setBackground(background);
                }
                break;

            case "Set New Foreground":
                Color foreground = JColorChooser.showDialog(frame, "Set New Foreground Color", Color.BLACK);
                if (foreground != null) {
                    textArea.setForeground(foreground);
                }
                break;

            case "Set Text Size":
                String textSizeStr = JOptionPane.showInputDialog(frame, "Enter Text Size:", "12");
                if (textSizeStr != null && !textSizeStr.isEmpty()) {
                    try {
                        int textSize = Integer.parseInt(textSizeStr);
                        textArea.setFont(new Font(textArea.getFont().getName(), Font.PLAIN, textSize));
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(frame, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "Set Text Style":
                Object[] styles = {"Plain", "Bold", "Italic", "Bold Italic"};
                String styleStr = (String) JOptionPane.showInputDialog(frame, "Select Text Style:", "Text Style",
                        JOptionPane.PLAIN_MESSAGE, null, styles, styles[0]);

                if (styleStr != null) {
                    int fontStyle = switch (styleStr) {
                        case "Bold" -> Font.BOLD;
                        case "Italic" -> Font.ITALIC;
                        case "Bold Italic" -> Font.BOLD | Font.ITALIC;
                        default -> Font.PLAIN;
                    };
                    textArea.setFont(new Font(textArea.getFont().getName(), fontStyle, textArea.getFont().getSize()));
                }
                break;

            case "Exit":
                System.exit(0);
                break;
            case "New Window":
                new Notepad();
                break;
            case "New Tab":
                textArea.setText("");
                textArea.setBackground(Color.WHITE);
                textArea.setForeground(Color.BLACK);
                break;
            case "Close":
                frame.dispose();
                break;

            case "Open":
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (Scanner scanner = new Scanner(file)) {
                        textArea.setText("");  // Clear the text area before loading new content
                        while (scanner.hasNextLine()) {
                            textArea.append(scanner.nextLine() + "\n");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Save As":
                JFileChooser saveAs = new JFileChooser();
                saveAs.setDialogTitle("Save As:");
                int option01 = saveAs.showSaveDialog(frame);
                if (option01 == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file1 = saveAs.getSelectedFile();
                        try (FileWriter fileWriter = new FileWriter(file1)) {
                            fileWriter.write(textArea.getText());
                        }
                        textArea.setText("Saved to " + file1.getName());
                    } catch (IOException ex1) {
                        ex1.printStackTrace();
                    }
                }
                break;

            case "Save":
                JFileChooser save = new JFileChooser();
                save.setDialogTitle("Saving Selected File:");
                int option02 = save.showSaveDialog(frame);
                if (option02 == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file2 = save.getSelectedFile();
                        try (FileWriter fileWriter = new FileWriter(file2)) {
                            fileWriter.write(textArea.getText());
                        }
                    } catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
                break;
            case "Print":
                try {
                    textArea.print();
                }catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(frame, "Printing Failed: " + ex.getMessage(), "Printer", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Zoom In":
                // Get the current font size
                Font currentFont = textArea.getFont();
                int fontSize = currentFont.getSize();

                // Increase font size by a specific increment (e.g., 2 points)
                fontSize += 2;

                // Create a new font with the adjusted size
                Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), fontSize);

                // Set the new font for the text area
                textArea.setFont(newFont);
                break;

            case "Zoom Out":
                // Get the current font size
                currentFont = textArea.getFont();
                fontSize = currentFont.getSize();

                // Ensure font size doesn't become negative (avoid very small fonts)
                if (fontSize > 6) {
                    fontSize -= 2;
                }

                // Create a new font with the adjusted size
                newFont = new Font(currentFont.getName(), currentFont.getStyle(), fontSize);

                // Set the new font for the text area
                textArea.setFont(newFont);
                break;

            case "Reset Zoom":
                // Define a default font size (e.g., 12 points)
                int defaultFontSize = 12;

                // Create a new font with the default size
                Font defaultFont = new Font("Dialog", Font.PLAIN, defaultFontSize);

                // Set the default font for the text area
                textArea.setFont(defaultFont);
                break;


        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Notepad::new);
    }
}
//Thanks For Checking This Out....Herve Was Around