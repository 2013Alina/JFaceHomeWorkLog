package com.homeworklog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

public class TableViwerLog extends Composite {

    private TableViewer tableViewer;
    private List<Student> students = new ArrayList<>();
    private boolean inputEnabled = false;
    private Image image;

    public TableViwerLog(Composite parent) {
        super(parent, SWT.NONE);
        
        MenuManager fileMenu = new MenuManager("File");
        Action clearAction = new Action("Clear") {
            @Override
            public void run() {
                tableViewer.setInput(Collections.emptyList());
                inputEnabled = true;
            }
        };
        clearAction.setAccelerator(SWT.MOD1 + 'X');
        fileMenu.add(clearAction);

        Action openAction = new Action("Open") {
            @Override
            public void run() {
                loadFromJson();
            }
        };
        openAction.setAccelerator(SWT.MOD1 + 'O');
        fileMenu.add(openAction);

        Action saveAction = new Action("Save") {
            @Override
            public void run() {
                boolean shouldSave = askForSaveConfirmation();
                if (shouldSave) {
                    saveToJson();
                }
            }
        };
        saveAction.setAccelerator(SWT.MOD1 + 'C');
        fileMenu.add(saveAction);

        Action exitAction = new Action("Exit") {
            @Override
            public void run() {
                getShell().close();
            }
        };
        exitAction.setAccelerator(SWT.ALT + 'Q');
        fileMenu.add(exitAction);

        MenuManager editMenu = new MenuManager("Edit");
        Action addAction = new Action("Add") {
            @Override
            public void run() {
                Composite sashForm = getParent();
                Control[] children = sashForm.getChildren();

                for (int i = 0; i < children.length; i++) {
                    if (children[i] instanceof CompositeImputData) {
                        boolean switchTable = ((CompositeImputData) children[i]).isSwitchTable();
                        if (!switchTable) {
                            this.makeNormalSizeTableViwerLog(sashForm);
                            children[i].setVisible(true);
                            ((CompositeImputData) children[i]).setSwitchTable(true);
                            break;
                        }
                    }
                }
            }

            private Point makeNormalSizeTableViwerLog(Composite parent) {
                for (Control child : parent.getChildren()) {
                    if (child instanceof TableViwerLog) {
                        int width = 488;
                        int height = 224;
                        child.setSize(width, height);
                        return child.getSize();
                    }
                }
                return null;
            }
        };

        addAction.setAccelerator(SWT.MOD1 + 'V');
        editMenu.add(addAction);

        Action deleteAction = new Action("Delete") {
            @Override
            public void run() {
                IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                if (!selection.isEmpty()) {
                    List<Student> students = new ArrayList<>((List<Student>) tableViewer.getInput());
                    students.removeAll(selection.toList());
                    tableViewer.setInput(students);
                } else {
                    MessageDialog.openError(getShell(), "Error", "No row selected for deletion!");
                }
            }
        };
        deleteAction.setAccelerator(SWT.MOD1 + 'X');
        editMenu.add(deleteAction);

        MenuManager helpMenu = new MenuManager("Help");
        Action aboutAction = new Action("About") {
            @Override
            public void run() {
                Shell shell = new Shell(getShell(), SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
                shell.setLayout(new GridLayout(1, true));
                shell.setText("About JFaceHomeWorkLog Application");
                Label label = new Label(shell, SWT.NONE);
                GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
                label.setLayoutData(data);
                label.setText("Author: Muntian Alina");
                label.setSize(200, 200);

                image = null;
                String imagePath = "/image.jpg";

                System.out.println("Resource Path: " + getClass().getResource(imagePath));
                System.out.println("Resource Stream: " + getClass().getResourceAsStream(imagePath));

                try (InputStream imageStream = getClass().getResourceAsStream(imagePath)) {
                    if (imageStream != null) {
                        image = new Image(Display.getCurrent(), imageStream);
                        Label imageLabel = new Label(shell, SWT.NONE);
                        imageLabel.setImage(image);
                    } else {
                        System.err.println("Image not found!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Button closeButton = new Button(shell, SWT.PUSH);
                GridData data1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
                closeButton.setLayoutData(data1);
                closeButton.setText("Close");
                closeButton.setSize(40, 40);
                closeButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        shell.close();
                    }
                });

                shell.pack();
                shell.open();
                shell.setSize(400, 400);

                shell.addDisposeListener(e -> {
                    if (image != null && !image.isDisposed()) {
                        image.dispose();
                    }
                });
            }
        };

        aboutAction.setAccelerator(SWT.MOD1 + 'T');
        helpMenu.add(aboutAction);

        Menu menuBar = new Menu(parent.getShell(), SWT.BAR);
        fileMenu.fill(menuBar, -1);
        editMenu.fill(menuBar, -1);
        helpMenu.fill(menuBar, -1);
        parent.getShell().setMenuBar(menuBar);

        setLayout(new FillLayout());

        tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);

            tableViewer.getTable().addListener(SWT.Resize, event -> {
                Point currentSize = tableViewer.getControl().getSize();
//                System.out.println("TableViwerLog current size: " + currentSize);
            });
     
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(3, 230, true));
        layout.addColumnData(new ColumnWeightData(1, 70, true));
        layout.addColumnData(new ColumnWeightData(2, 130, true));
        tableViewer.getTable().setLayout(layout);
        Color color = new Color(204, 255, 255);
        tableViewer.getTable().setHeaderBackground(color);

        tableViewer.getTable().setHeaderVisible(true); // table header visibility
        tableViewer.getTable().setLinesVisible(true); // horizontal lines between lines will be visible
        
        TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.LEFT);
        TableColumn nameTableColumn = nameColumn.getColumn();
        nameTableColumn.setText("Name");

        nameColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return ((Student) element).getName();
            }
        });

        ViewerComparator comparatorName = new ViewerComparator() {
            @Override
            public int compare(Viewer viewer, Object o1, Object o2) {
                String name1 = ((Student) o1).getName();
                String name2 = ((Student) o2).getName();
                return name1.compareTo(name2);
            }
        };

        nameColumn.setEditingSupport(new EditingSupport(tableViewer) {
            @Override
            protected boolean canEdit(Object element) {
                return false; // only read
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                return null;
            }

            @Override
            protected Object getValue(Object element) {
                return null;
            }

            @Override
            protected void setValue(Object element, Object value) {

            }
        });

        nameColumn.getViewer().setComparator(comparatorName);

        nameTableColumn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                tableViewer.setComparator(comparatorName);
            }
        });

        TableViewerColumn groupColumn = new TableViewerColumn(tableViewer, SWT.RIGHT);
        TableColumn groupTableColumn = groupColumn.getColumn();
        groupTableColumn.setText("Group");
        groupColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return String.valueOf(((Student) element).getGroup());
            }
        });

        ViewerComparator comparatorGroup = new ViewerComparator() {
            @Override
            public int compare(Viewer viewer, Object o1, Object o2) {
                int group1 = ((Student) o1).getGroup();
                int group2 = ((Student) o2).getGroup();
                return Integer.compare(group1, group2);
            }
        };

        groupColumn.setEditingSupport(new EditingSupport(tableViewer) {
            @Override
            protected boolean canEdit(Object element) {
                return false;
            }

            @Override
            protected CellEditor getCellEditor(Object element) {
                return null;
            }

            @Override
            protected Object getValue(Object element) {
                return null;
            }

            @Override
            protected void setValue(Object element, Object value) {

            }
        });

        groupColumn.getViewer().setComparator(comparatorGroup);

        groupTableColumn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                tableViewer.setComparator(comparatorGroup);
            }
        });

        TableViewerColumn swtDoneColumn = new TableViewerColumn(tableViewer, SWT.LEFT | SWT.CHECK);
        TableColumn swtDoneTableColumn = swtDoneColumn.getColumn();
        swtDoneTableColumn.setText("SWT done");
        swtDoneColumn.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return "";
            }

            @Override
            public Image getImage(Object element) {
                Display display = Display.getCurrent();
                Image image = new Image(display, 16, 16);
                GC gc = new GC(image);
                gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
                gc.fillRectangle(0, 0, 16, 16);
                gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                gc.drawRectangle(2, 2, 12, 12);
                if (((Student) element).isDone()) {
                    gc.drawLine(4, 8, 8, 12);
                    gc.drawLine(8, 12, 14, 4);
                }
                gc.dispose();
                return image;
            }
        });

        swtDoneColumn.setEditingSupport(new EditingSupport(tableViewer) {
            @Override
            protected CellEditor getCellEditor(Object element) {
                CheckboxCellEditor cellEditor = new CheckboxCellEditor(tableViewer.getTable());
                return cellEditor;
            }

            @Override
            protected boolean canEdit(Object element) {
                return true;
            }

            @Override
            protected Object getValue(Object element) {
                return ((Student) element).isDone();
            }

            @Override
            protected void setValue(Object element, Object value) {
                ((Student) element).setDone(Boolean.valueOf((boolean) value));
                getViewer().update(element, null);
            }

        });

        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        students.add(new Student("Толик", 1, true));
        students.add(new Student("Петя", 3, false));
        students.add(new Student("Вася", 2, false));
        tableViewer.setInput(students);
        
        nameTableColumn.pack();
        groupTableColumn.pack();
        swtDoneTableColumn.pack();
        parent.pack();
        pack();
        
        tableViewer.getTable().addDisposeListener(e -> {
            fileMenu.dispose();
            editMenu.dispose();
            helpMenu.dispose();
        });

    }

    private void saveToJson() {
        JsonDataService.saveToJson((List<Student>)tableViewer.getInput(), getShell());
    }

    private boolean askForSaveConfirmation() {
        MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage("Wanna save your changes?");
        messageBox.setText("Save Confirmation");
        int response = messageBox.open();
        return response == SWT.YES;
    }

    private void loadFromJson() {
        students = JsonDataService.loadFromJson(getShell());
        if (students != null) {
            tableViewer.setInput(students);
        }
    }
    
    public TableViewer getTableViewer() {
        return tableViewer;
    }
}
