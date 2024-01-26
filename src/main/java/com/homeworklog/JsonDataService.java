package com.homeworklog;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataService {

    public static void saveToJson(List<Student> students, Shell shell) {
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setFilterExtensions(new String[] { "*.json" });
        String selectedFile = fileDialog.open();

        if (selectedFile != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(new File(selectedFile), students);

                MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                messageBox.setMessage("Data saved to file successfully " + selectedFile);
                messageBox.open();
            } catch (IOException e) {
                handleIOException(shell, "Error saving data");
            }
        }
    }

    public static List<Student> loadFromJson(Shell shell) {
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
        fileDialog.setFilterExtensions(new String[] { "*.json" });
        String selectedFile = fileDialog.open();

        if (selectedFile != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(new File(selectedFile), new TypeReference<List<Student>>() {
                });
            } catch (IOException e) {
                handleIOException(shell, "Error loading data");
            }
        }
        return null;
    }

    private static void handleIOException(Shell shell, String errorMessage) {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
        messageBox.setMessage(errorMessage);
        messageBox.open();
    }
}
