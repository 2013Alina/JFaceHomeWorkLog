package com.homeworklog;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

public class TableViwerLog extends Composite {

    private GridData gridData;

    public TableViwerLog(Composite parent) {
        super(parent, SWT.NONE);

        setLayout(new FillLayout());

        TableViewer tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
        GridLayout gridLayout = new GridLayout(3, false);
        tableViewer.getTable().setLayout(gridLayout);

        TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        nameColumn.getColumn().setData(gridData);
        TableColumn nameTableColumn = nameColumn.getColumn();
        nameTableColumn.setText("Name");
        nameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((Student) element).getName();
            }
        });

        TableViewerColumn groupColumn = new TableViewerColumn(tableViewer, SWT.RIGHT);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        groupColumn.getColumn().setData(gridData);
        TableColumn groupTableColumn = groupColumn.getColumn();
        groupTableColumn.setText("Group");
        groupColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return String.valueOf(((Student) element).getGroup());
            }
        });

        TableViewerColumn swtDoneColumn = new TableViewerColumn(tableViewer, SWT.LEFT | SWT.CHECK);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
        swtDoneColumn.getColumn().setData(gridData);
        TableColumn swtDoneTableColumn = swtDoneColumn.getColumn();
        swtDoneTableColumn.setText("SWT done");
        swtDoneColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return "Test";
            }

            @Override
            public Image getImage(Object element) {
                return ((Student) element).isDone() ? Display.getDefault().getSystemImage(SWT.CHECK) : null;
            }
        });

        tableViewer.getTable().setHeaderVisible(true); // table header visibility
        tableViewer.getTable().setLinesVisible(true); // horizontal lines between lines will be visible
        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.setInput(new Student[] { new Student("Вася", 1, true), new Student("Петя", 1, false),
                new Student("Толик", 2, false) });

        nameTableColumn.pack();
        groupTableColumn.pack();
        swtDoneTableColumn.pack();
        parent.pack();
        pack();

    }
}
