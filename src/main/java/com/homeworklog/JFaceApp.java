package com.homeworklog;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class JFaceApp extends ApplicationWindow {

    public JFaceApp() {
        super(null); // Window allocation(create window of application)
    }

    @Override
    protected Control createContents(Composite parent) {
        MainComposite mainComposite = new MainComposite(parent);
        parent.pack();
        mainComposite.pack();
        parent.setSize(1000, 300);
        return parent; // Window operations and settings
    }

    public static void main(String[] args) {
        JFaceApp awin = new JFaceApp();
        awin.setBlockOnOpen(true);
        awin.open();
        Display.getCurrent().dispose(); // Window presentation(view window)

    }
}
