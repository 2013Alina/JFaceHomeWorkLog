package com.homeworklog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CompositeImputData extends Composite {
    private GridData gridData; 
    private TableViewer tableViewer;
    private Text textName;
    private Text textGroup;
    private Button checkButton;
    private boolean inputEnabled = true;
    private boolean switchTable = true;
   
    public CompositeImputData(Composite parent, TableViewer tableViewer) {
        super(parent, SWT.NONE);
        this.tableViewer = tableViewer;

        Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
        group.setLayout(new GridLayout(4, true));

        Label labelName = new Label(group, SWT.NONE);
        labelName.setText("Name: ");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        labelName.setLayoutData(gridData);
        labelName.pack();
        
        textName = new Text(group, SWT.BORDER);
        textName.setSize(100, 20);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
        textName.setLayoutData(gridData);
        textName.pack();
        
        Label labelGroup = new Label(group, SWT.NONE);
        labelGroup.setText("Group: ");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        labelGroup.setLayoutData(gridData);
        labelGroup.pack();
        
        textGroup = new Text(group, SWT.BORDER);
        textGroup.setSize(100, 20);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
        textGroup.setLayoutData(gridData);
        textGroup.pack();
        
        Label labelDone = new Label(group, SWT.NONE);
        labelDone.setText("SWT task done: ");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        labelDone.setLayoutData(gridData);
        labelDone.pack();
        
        checkButton = new Button(group, SWT.CHECK);
        gridData = new GridData(SWT.END, SWT.END, true, false, 3, 1);
        gridData.verticalIndent = 20;
        checkButton.setLayoutData(gridData);
        
        Button newButton = new Button(group, SWT.PUSH);
        newButton.setText("New");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        newButton.setLayoutData(gridData);
        newButton.pack();
        
        newButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                textName.setText("");
                textGroup.setText("");
                checkButton.setSelection(false);
            }
        });
        
        Button saveButton = new Button(group, SWT.PUSH);
        saveButton.setText("Save");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        saveButton.setLayoutData(gridData);
        saveButton.pack();

        saveButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (inputEnabled) {
                    String name = textName.getText();
                    String group = textGroup.getText();
                    boolean isDone = checkButton.getSelection();
                    if (name != null && !name.isEmpty() && group != null && !group.isEmpty()) {
                        saveRowToTable(name, group, isDone);
                    } else {
                        MessageDialog.openError(saveButton.getShell(), "Error", "You did not enter a name or group!");
                    }
                }
            }
        });

        Button deleteButton = new Button(group, SWT.PUSH);
        deleteButton.setText("Delete");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        deleteButton.setLayoutData(gridData);
        deleteButton.pack();

        deleteButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                if (!selection.isEmpty()) {
                    List<Student> students = (List<Student>)tableViewer.getInput();
                    students.removeAll(selection.toList());
                    tableViewer.setInput(students);
                } else {
                    MessageDialog.openError(deleteButton.getShell(), "Error", "No row selected for deletion!");
                }
            }
        });

        Button cancelButton = new Button(group, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        cancelButton.setLayoutData(gridData);
        cancelButton.pack();

        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (switchTable) {
                    setVisible(false);
                    Composite sashForm = getParent();
                    Point sashFormSize = sashForm.getSize();
                    Control[] children = sashForm.getChildren();
                    for (int i = 0; i < children.length; i++) {
                        if (children[i] instanceof TableViwerLog) {
                            ((TableViwerLog) children[i]).setSize(sashFormSize);
                            switchTable = false;
                            break;
                        }
                    }
                }
            }
        });

        parent.pack();
        group.pack();
        pack();

    }

    private void saveRowToTable(String name, String group, boolean isDone) {
        List<Student> studentsList = new ArrayList<>((List<Student>) tableViewer.getInput());
        Student student = new Student(name, Integer.parseInt(group), isDone);
        studentsList.add(student);
        tableViewer.setInput(studentsList);
    }

    public boolean isSwitchTable() {
        return switchTable;
    }

    public void setSwitchTable(boolean switchTable) {
        this.switchTable = switchTable;
    }
}

