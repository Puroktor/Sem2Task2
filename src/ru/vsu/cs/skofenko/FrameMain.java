package ru.vsu.cs.skofenko;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JTable tableIn;
    private JButton loadInputButton;
    private JButton writeInputButton;
    private JButton solveButton;
    private JButton saveButton;
    private JTable tableOut;
    private JTextField shiftQuantityField;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task 8");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableIn, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOut, 40, false, true, false, false);
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserOpen.addChoosableFileFilter(filter);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        loadInputButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    InputData data = InOutClass.readListFromFile(fileChooserOpen.getSelectedFile().getPath());
                    int[] arr = Converter.toPrimitive(data.getList().toArray(new Integer[0]));
                    JTableUtils.writeArrayToJTable(tableIn, arr);
                    shiftQuantityField.setText(String.valueOf(data.getTo()));
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });

        solveButton.addActionListener(e -> {
            try {
                int[] inArray = JTableUtils.readIntArrayFromJTable(tableIn);
                MyLinkedList<Integer> list = new MyLinkedList<>(Converter.toObject(inArray));
                list.shiftLeft(Integer.parseInt(shiftQuantityField.getText()));
                int[] arr = Converter.toPrimitive(list.toArray(new Integer[0]));
                JTableUtils.writeArrayToJTable(tableOut, arr);
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        saveButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    int[] outArray = JTableUtils.readIntArrayFromJTable(tableOut);
                    InOutClass.writeListToFile(file, new MyLinkedList<>(Converter.toObject(outArray)));
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
        writeInputButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    int[] outArray = JTableUtils.readIntArrayFromJTable(tableIn);
                    int to = Integer.parseInt(shiftQuantityField.getText());
                    InOutClass.writeDataToFile(file, new InputData(new MyLinkedList<>(Converter.toObject(outArray)), to));
                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        });
    }
}
