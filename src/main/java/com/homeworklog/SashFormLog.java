package com.homeworklog;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Sash;

public class SashFormLog extends Composite {

    private TableViwerLog tableViwerLog;
    private CompositeImputData compositeImputData;
    
    public SashFormLog(Composite parent) {
        super(parent, SWT.NONE);
        init(parent);
    }

    private void init(Composite parent) {
        setLayout(new FillLayout());
        
        SashForm sashForm = new SashForm(this, SWT.HORIZONTAL);
        
        tableViwerLog = new TableViwerLog(sashForm);
        TableViewer tableViewer = tableViwerLog.getTableViewer();
        
        compositeImputData = new CompositeImputData(sashForm, tableViewer);
        compositeImputData.setLayout(new FillLayout());
        
        Sash sash = new Sash(sashForm, SWT.SMOOTH);
        sashForm.setSashWidth(5);

        sashForm.setLayoutData(new FillLayout());
        sashForm.setWeights(new int[] { 1, 1 });

        sash.addListener(SWT.Selection, event -> {
            sashForm.setWeights(new int[] { sashForm.getClientArea().width / 2, sashForm.getClientArea().width / 2 });
        });

        
        parent.pack();
        sashForm.pack();
        compositeImputData.pack();
        tableViwerLog.pack();
        pack();
    }

    public TableViwerLog getTableViwerLog() {
        return tableViwerLog;
    }

    public CompositeImputData getCompositeImputData() {
        return compositeImputData;
    }
    
    
    
}
