package com.homeworklog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CompositeImputData extends Composite {
    private GridData gridData; 
    
    public CompositeImputData(Composite parent) {
        super(parent, SWT.NONE);
        
        Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
        group.setLayout(new GridLayout(4, true));
        
        Label labelName = new Label(group, SWT.NONE);
        labelName.setText("Name: ");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        labelName.setLayoutData(gridData);
        labelName.pack();
        
        Text textName = new Text(group, SWT.BORDER);
        textName.setSize(100, 20);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
        textName.setLayoutData(gridData);
        textName.pack();
        
        Label labelGroup = new Label(group, SWT.NONE);
        labelGroup.setText("Group: ");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        labelGroup.setLayoutData(gridData);
        labelGroup.pack();
        
        Text textGroup = new Text(group, SWT.BORDER);
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
        
        Button checkButton = new Button(group, SWT.CHECK);
        gridData = new GridData(SWT.END, SWT.END, true, false, 3, 1);
        gridData.verticalIndent = 20;
        checkButton.setLayoutData(gridData);
        
        Button newButton = new Button(group, SWT.PUSH);
        newButton.setText("New");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        newButton.setLayoutData(gridData);
        newButton.pack();
        
        Button saveButton = new Button(group, SWT.PUSH);
        saveButton.setText("Save");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        saveButton.setLayoutData(gridData);
        saveButton.pack();
        
        Button deleteButton = new Button(group, SWT.PUSH);
        deleteButton.setText("Delete");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        deleteButton.setLayoutData(gridData);
        deleteButton.pack();
        
        Button cancelButton = new Button(group, SWT.PUSH);
        cancelButton.setText("Cancel");
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gridData.verticalIndent = 20;
        cancelButton.setLayoutData(gridData);
        cancelButton.pack();
        
        parent.pack();
        group.pack();
        pack();
        
    }

}
