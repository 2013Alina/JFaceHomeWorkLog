package com.homeworklog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Sash;

public class SashFormLog extends Composite {

    public SashFormLog(Composite parent) {
        super(parent, SWT.NONE);
        setLayout(new FillLayout());

        SashForm sashForm = new SashForm(this, SWT.HORIZONTAL);

        Composite leftComposite = new Composite(sashForm, SWT.BORDER);
        leftComposite.setLayout(new FillLayout());

        Composite rightComposite = new Composite(sashForm, SWT.BORDER);
        rightComposite.setLayout(new FillLayout());

        Sash sash = new Sash(sashForm, SWT.SMOOTH);
        sashForm.setSashWidth(5);

        sashForm.setLayoutData(new FillLayout());
        sashForm.setWeights(new int[] { 1, 1 });

        sash.addListener(SWT.Selection, event -> {
            sashForm.setWeights(new int[] { sashForm.getClientArea().width / 2, sashForm.getClientArea().width / 2 });
        });

        TableViwerLog tableViwerLog = new TableViwerLog(leftComposite);

        CompositeImputData compositeImputData = new CompositeImputData(rightComposite);
        compositeImputData.setLayout(new FillLayout());

        parent.pack();
        sashForm.pack();
        leftComposite.pack();
        rightComposite.pack();
        compositeImputData.pack();
        tableViwerLog.pack();
        pack();

    }
}
