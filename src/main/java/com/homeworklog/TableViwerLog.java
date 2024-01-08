package com.homeworklog;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TableViwerLog extends Composite {
    
    public TableViwerLog(Composite parent) {
        super(parent, SWT.NONE);
        TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        
        TableViewerColumn columnName = new TableViewerColumn(tableViewer, SWT.NONE);
        columnName.getColumn().setWidth(30);
        columnName.getColumn().setText("Name");
        
        TableViewerColumn columnGroup = new TableViewerColumn(tableViewer, SWT.NONE);
        columnGroup.getColumn().setWidth(30);
        columnGroup.getColumn().setText("Group");
        
        TableViewerColumn columnDone = new TableViewerColumn(tableViewer, SWT.NONE);
        columnDone.getColumn().setWidth(30);
        columnDone.getColumn().setText("SWT done");
    }

}
