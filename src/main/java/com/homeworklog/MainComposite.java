package com.homeworklog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class MainComposite extends Composite {
    
    public MainComposite(Composite parent) {
        super(parent, SWT.NONE);
        parent.getShell().setText("JFace homework log");
        
        
        SashFormLog sashFormLog = new SashFormLog(this);
        sashFormLog.pack();
        
        parent.pack();
        
    }
}
