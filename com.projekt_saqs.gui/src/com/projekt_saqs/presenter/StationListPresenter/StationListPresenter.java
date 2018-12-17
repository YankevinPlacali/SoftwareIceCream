package com.projekt_saqs.presenter.StationListPresenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.projekt_saqs.gui.StationWriterInterface;
import com.projekt_saqs.model.stationBuilder.StationBuilder;
import com.projekt_saqs.presenter.Station;


public class StationListPresenter implements Observer{
	private StationWriterInterface swi;
	private ArrayList<Station> list;
	
	public StationListPresenter(StationWriterInterface n_swi){
		this.swi = n_swi;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		StationBuilder ss=(StationBuilder) arg0;
        System.out.println("Anzahl von Stationen: " + ss.getList_station().size());
		list=ss.getList_station();
		swi.getAllStationen(list);
	}
}
