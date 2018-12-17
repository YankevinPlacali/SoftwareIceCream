package com.projekt_saqs.presenter.alertpresenter;

import java.util.Observable;
import java.util.Observer;

import com.projekt_saqs.gui.AlertViewInterface;
import com.projekt_saqs.model.stationBuilder.StationBuilder;

public class AlertPresenter implements Observer {
	
	private AlertViewInterface avi;
	
	public AlertPresenter(AlertViewInterface n_avi){
		this.avi = n_avi;
	}
	@Override
	public void update(Observable o, Object arg) {
		StationBuilder ss = (StationBuilder)o;
		avi.getNeueStationen(ss.getNewStation(), ss.getOldStation());
	}
	
}
