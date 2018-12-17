package com.projekt_saqs.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class View extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fenster = new JMenu("Session");
    private JMenuItem newGui = new JMenuItem("Neues Gui");
    private JTabbedPane onglet = new JTabbedPane();

    public View() {
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);

        this.newGui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String gui = getThis().selectSession();
                getThis().addSession(gui);
            }
        });

        this.fenster.add(this.newGui);
        this.menuBar.add(fenster);
        this.setJMenuBar(menuBar);

        this.add(onglet);
        this.add(onglet);

        this.addWindowListener(new WindowAdapter() {
            @SuppressWarnings("deprecation")
            public void windowClosing(WindowEvent e) {
                for (int i = 0; i < getThis().onglet.getTabCount(); i++) {
                    Component comp = getThis().onglet.getComponentAt(i);
                    if (comp.getName().contains("Main View")) { //gui a
                        MainView a = (MainView)comp;
                        a.getT().stop();
                    } else {
                        AdminView b = (AdminView)comp; //gui b
                        b.getT().stop();
                    }
                }
            }
        });
    }

    public View getThis() {
        return this;
    }

    public void addSession(String gui) {
        String name = gui + (this.onglet.getTabCount() + 1);
        if (this.onglet.getTabCount() == 0) {
            if (gui == "Main View") {
                addTabMainView(name);
            }
            if (gui == "Admin View") {
                addTabAdminView(name);
            }
        } else {
            String nameG = this.onglet.getComponentAt(0).getName();
            if (nameG.contains("Main View")) {
                MainView tmp = (MainView)this.onglet.getComponentAt(0);
                if (gui == "Main View") {
                    MainView mainview = new MainView(tmp.getSs());
                    mainview.setName(name);
                    onglet.addTab(name, mainview);
                }
                if (gui == "Admin View") {
                    AdminView adminview = new AdminView(tmp.getSs());
                    adminview.setName(name);
                    onglet.addTab(name, adminview);
                }
            }
            if (nameG.contains("Admin View")) {
                AdminView tmp = (AdminView)this.onglet.getComponentAt(0);
                if (gui == "Main View") {
                    MainView mainview = new MainView(tmp.getSs());
                    mainview.setName(name);
                    onglet.addTab(name, mainview);
                }
                if (gui == "Admin View") {
                    AdminView adminview = new AdminView(tmp.getSs());
                    adminview.setName(name);
                    onglet.addTab(name, adminview);
                }
            }
        }
    }

    /**
     * @param name
     */
    private void addTabAdminView(String name) {
        AdminView adminview = new AdminView();
        adminview.setName(name);
        onglet.addTab(name, adminview);
    }

    /**
     * @param name
     */
    private void addTabMainView(String name) {
        MainView mainview = new MainView();
        mainview.setName(name);
        onglet.addTab(name, mainview);
    }

    @SuppressWarnings("static-access")
    public String selectSession() {
        String[] guis = { "Admin View", "Main View" };
        JOptionPane jop = new JOptionPane();
        String gui = (String)jop.showInputDialog(null, "Bitte Wählen ein Session", "Session", JOptionPane.QUESTION_MESSAGE, null, guis, guis[0]);
        return gui;
    }

}
