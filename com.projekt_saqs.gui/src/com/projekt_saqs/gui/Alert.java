package com.projekt_saqs.gui;

import javax.swing.JLabel;

import com.projekt_saqs.presenter.Station;

@SuppressWarnings("serial")
public class Alert extends JLabel implements AlertViewInterface {

    public Alert(String text) {
        super(text);
    }

    public Alert() {

    }

    public void getNeueStationen(Station newStation, Station oldStation) {
        if (oldStation == null)
            this.setText("Die Station " + newStation.getIdStation() + " wurde hinzugefügt.");
        else
            this.setText("<html> Die Varianz wurde für folgende Station berechnet.<br>" + " <span color=\"green\">Id: " + newStation.getIdStation() + ", Date: " + newStation.dateToString()
                    + ", Target: " + newStation.getTarget() + "</span><br></html>");
    }

}
