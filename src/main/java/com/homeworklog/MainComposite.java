package com.homeworklog;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;

public class MainComposite extends Composite {

    public MainComposite(Composite parent) {
        super(parent, SWT.NONE);
        parent.getShell().setText("JFace homework log");
        setLayout(new FillLayout());

        MenuManager menuManager = new MenuManager();
        MenuBuilder menuBuilder = new MenuBuilder(menuManager);
        menuBuilder.createFileMenu();
        menuBuilder.createEditMenu();
        menuBuilder.createHelpMenu();

        Menu menuBar = menuManager.createMenuBar((Decorations) parent);
        ((Decorations) parent).setMenuBar(menuBar);

        SashFormLog sashFormLog = new SashFormLog(this);

        sashFormLog.pack();
        parent.pack();
        pack();
    }

}
