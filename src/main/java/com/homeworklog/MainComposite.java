package com.homeworklog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class MainComposite extends Composite {

    public MainComposite(Composite parent) {
        super(parent, SWT.NONE);
        parent.getShell().setText("JFace homework log");
        
        setLayout(new FillLayout());

        SashFormLog sashFormLog = new SashFormLog(this);

        sashFormLog.pack();
        parent.pack();
        pack();
    }

}
