package com.projekt_saqs.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.projekt_saqs.gui.*;
import com.projekt_saqs.presenter.Station;

@SuppressWarnings("serial")
public class StationWriter extends JTable implements StationWriterInterface, MouseListener {
	
	CurrentStationInterface csi;
	private ArrayList<Station> listStation;
	
	public StationWriter(Object data[][], String title[]){
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(title);
		model.setRowCount(5);
		this.setModel(model);
	}
	
	public StationWriter(){
		
	}
	
	public StationWriter(DefaultTableModel model, CurrentStationInterface n_csi){
		this.csi = n_csi;
		model.setColumnCount(4);
		String [] columns = {"Number", "ID Station", "Date", "Target"};
		model.setColumnIdentifiers(columns);
		this.setModel(model);
		this.addMouseListener(this);
	}
	
	public void updateTable(ArrayList<Station> n_listStation){
		this.listStation = n_listStation;
		int nbElement = this.listStation.size();
		int i;
		DefaultTableModel model = (DefaultTableModel)this.getModel();
		@SuppressWarnings("unused")
		String dateString;
		
		while(this.getRowCount() > 0)
		    model.removeRow(0);
		
		for(i = 0; i < nbElement; i++){
			String row[] = {"" + (i + 1), this.listStation.get(i).getIdStation(), this.listStation.get(i).dateToString(), "" + this.listStation.get(i).getTarget()};
			model.addRow(row);
		}
	}
	
	@Override
	public void getAllStationen(ArrayList<Station> n_stationList) {
		updateTable(n_stationList);
	}

	public CurrentStationInterface getCsi() {
		return csi;
	}

	public void setCsi(CurrentStationInterface csi) {
		this.csi = csi;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String id = this.listStation.get(this.getSelectedRow()).getIdStation();
		Date date = this.listStation.get(this.getSelectedRow()).getDate();
		float target = this.listStation.get(this.getSelectedRow()).getTarget();
		Station station = new Station(id, date, target);
		this.csi.getCurrentStation(station);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
