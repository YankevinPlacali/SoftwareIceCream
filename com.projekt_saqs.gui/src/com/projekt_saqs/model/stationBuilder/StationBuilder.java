package com.projekt_saqs.model.stationBuilder;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.projekt_saqs.model.InfoUpdateModel;
import com.projekt_saqs.presenter.Info;
import com.projekt_saqs.presenter.Station;

public class StationBuilder extends Observable implements InfoUpdateModel, Runnable{
	private ArrayList<Station> list_station = new ArrayList<Station>();
	private Station newStation;
	private Station oldStation;
	
	public StationBuilder(){
	}

	public ArrayList<Station> getList_station() {
		return list_station;
	}

	public void setList_station(ArrayList<Station> list_station) {
		this.list_station = list_station;
	}

	@Override
	public void run() {
		Random r = new Random();
        int time = 0;
		while(true){
			try {
				Thread.sleep(time * 1000);
				this.newStation = new Station();
				this.list_station.add(this.newStation);
				this.oldStation = null;
				this.setChanged();
				this.notifyObservers();
                System.out.println("\nA Eine Station wurde erstellt");
				System.out.println(this.newStation.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            time = r.nextInt((60 - 10) + 1) + 10;
		}
	}
	
	public Station getNewStation() {
		return newStation;
	}

	public void setNewStation(Station newStation) {
		this.newStation = newStation;
	}

	public void show_list_station(){
		int i = 1;
		for(Station s : this.list_station){
			System.out.println(i + ") Id: " + s.getIdStation() + "\tdate: " + s.getDate().toString() + "\tTarget: " + s.getTarget());
			i++;
		}
	}

	@Override
	public void getActualInfo(Info info) {
		int index = this.getStationIndex(info);
		if(index != -1){
			this.oldStation = new Station(this.list_station.get(index).getIdStation(),
					this.list_station.get(index).getDate(),
					this.list_station.get(index).getTarget());
			if(index != -1){
				this.list_station.get(index).setIdStation(info.getIdStation());
				this.list_station.get(index).setDate(info.getDate());
				this.list_station.get(index).setTarget(info.getActual());
				this.newStation = new Station(this.list_station.get(index).getIdStation(),
						this.list_station.get(index).getDate(),
						this.list_station.get(index).getTarget());
				System.out.println("The station " + this.oldStation.getIdStation() + " has been modified!!!");
				this.setChanged();
				this.notifyObservers();
				return ;
			}else{
				System.out.println("The station " + this.oldStation.getIdStation() + " was not found!!!");
				return ;
			}
		}else{
			System.out.println("The station " + info.getIdStation() + " was not found!!!");
			return ;
		}
	}
	
	public Station getStation(String n_id){
		for(Station s : this.list_station){
			if(s.getIdStation() == n_id)
				return s;
		}
		return null;
	}
	
	public Station getStation(Station s){
		return this.getStation(s.getIdStation());
	}
	
	public int getStationIndex(String n_id){
		int i = 0;
		for(Station s : this.list_station){
			if(s.getIdStation().equals(n_id)){
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getStationIndex(Station s){
		return this.getStationIndex(s.getIdStation());
	}

	public Station getOldStation() {
		return oldStation;
	}

	public void setOldStation(Station oldStation) {
		this.oldStation = oldStation;
	}
	
}
