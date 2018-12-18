package com.projekt_saqs.gui;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projekt_saqs.gui.*;
import com.projekt_saqs.presenter.*;

@SuppressWarnings("serial")
public class UpdateStation extends JPanel implements CurrentStationInterface {
	
	InfoUpdateInterface uii;
	JLabel labelId = new JLabel("ID Station:");
	JTextField id = new JTextField();
	JLabel labelDate = new JLabel("Date:");
	JTextField date = new JTextField();
	JLabel labelTarget = new JLabel("Target:");
	JTextField target = new JTextField();
	JLabel labelActual = new JLabel("Actual:");
	JTextField actual = new JTextField();
	
	public UpdateStation(InfoUpdateInterface n_uii) {
		this.id.setEditable(false);
		this.target.setEditable(false);
		this.uii = n_uii;
		this.setLayout(new GridLayout(4, 2));
		this.add(labelId);
		this.add(id);
		this.add(labelDate);
		this.add(date);
		this.add(labelTarget);
		this.add(target);
		this.add(labelActual);
		this.add(actual);
	}

	public UpdateStation() {
		this.id.setEditable(false);
        this.target.setEditable(false);
		this.setLayout(new GridLayout(4, 2));
		this.add(labelId);
		this.add(id);
		this.add(labelDate);
		this.add(date);
		this.add(labelTarget);
		this.add(target);
		this.add(labelActual);
		this.add(actual);
	}
	
	@Override
	public void getCurrentStation(Station c_station) {
		this.id.setText(c_station.getIdStation());
		this.date.setText(c_station.dateToString());
		this.target.setText("" + c_station.getTarget());
	}

	public InfoUpdateInterface getUii() {
		return uii;
	}

	public void setUii(InfoUpdateInterface uii) {
		this.uii = uii;
	}

	public JLabel getLabelId() {
		return labelId;
	}

	public void setLabelId(JLabel labelId) {
		this.labelId = labelId;
	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JLabel getLabelDate() {
		return labelDate;
	}

	public void setLabelDate(JLabel labelDate) {
		this.labelDate = labelDate;
	}

	public JTextField getDate() {
		return date;
	}

	public void setDate(JTextField date) {
		this.date = date;
	}

	public JLabel getLabelTarget() {
		return labelTarget;
	}

	public void setLabelTarget(JLabel labelTarget) {
		this.labelTarget = labelTarget;
	}

	public JTextField getTarget() {
		return target;
	}

	public void setTarget(JTextField target) {
		this.target = target;
	}

	public JLabel getLabelActual() {
		return labelActual;
	}

	public void setLabelActual(JLabel labelActual) {
		this.labelActual = labelActual;
	}

	public JTextField getActual() {
		return actual;
	}

	public void setActual(JTextField actual) {
		this.actual = actual;
	}
	
	public boolean validateActual(){
		Pattern p = Pattern.compile("[0-9]+(\\.[0-9]+)?");
		Matcher m = p.matcher(this.actual.getText());
		return m.matches();
	}

	public boolean validateDate(){
		Pattern p = Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{4}");
		Matcher m = p.matcher(this.date.getText());
		LocalDateTime now = LocalDateTime.now();
		if(m.matches()){
			String[] textDateArray = this.date.getText().split("\\.");
			int jour = Integer.parseInt(textDateArray[0]),
					mois = Integer.parseInt(textDateArray[1]),
						annee = Integer.parseInt(textDateArray[2]);
			if((jour < 1 || jour > 31) || (mois < 1 || mois > 12) || (annee < 1990 || annee > now.getYear() ) ){
				return false;
			}else{
				if(annee % 4 == 0 && mois == 2 && jour > 28)
					return false;
				if(mois == 2 && jour > 29)
					return false;
				if((mois == 2 || mois == 4 || mois == 6 || mois == 9 || mois == 11) && jour > 30)
					return false;
				return true;
			}
		}
		return m.matches();
	}
}
