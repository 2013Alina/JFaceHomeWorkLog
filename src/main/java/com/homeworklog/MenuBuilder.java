package com.homeworklog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;

public class MenuBuilder {

    private MenuManager menuManager;

    public MenuBuilder(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public void createFileMenu() {
        MenuManager fileMenu = new MenuManager("File");
        menuManager.add(fileMenu);

        Action saveAction = new Action("Save") {
            @Override
            public void run() {

            }
        };
        fileMenu.add(saveAction);

        Action exitAction = new Action("Exit") {
            @Override
            public void run() {

            }
        };
        fileMenu.add(exitAction);
    }

    public void createEditMenu() {
        MenuManager editMenu = new MenuManager("Edit");
        menuManager.add(editMenu);

        Action deleteAction = new Action("Delete") {
            @Override
            public void run() {

            }
        };
        editMenu.add(deleteAction);
    }

    public void createHelpMenu() {
        MenuManager helpMenu = new MenuManager("Help");
        menuManager.add(helpMenu);

        Action aboutAction = new Action("About") {
            @Override
            public void run() {

            }
        };
        helpMenu.add(aboutAction);
    }
}