package com.projekt_saqs.presenter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class Station {
	private String idStation;
	private Date date;
	private float target;
	
	public Station(){
		this.idStation = this.generateId(3, 3);
		this.date = this.generateDate();
		this.target = this.generateTarget(5, 100);
	}

	public Station(float n_target){
		this.idStation = "AAA777";
		this.date = this.generateDate();
		this.target = n_target;
	}

	public Station(String n_id, Date n_date, float n_target){
		this.idStation = n_id;
		this.date = n_date;
		this.target = n_target;
	}

	public String getIdStation() {
		return idStation;
	}

	public void setIdStation(String idStation) {
		this.idStation = idStation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTarget() {
		return target;
	}

	public void setTarget(float target) {
		this.target = target;
	}

	public String generateId(int lengthChar, int lengthInt){
		int i, randomNum;
		Random r = new Random();
        String newId = "Station";
		int ascii_min_char = 65;
		int ascii_max_char = 90;
		int min_int = 0;
		int max_int = 9;
		
		for(i = 0; i < lengthChar; i++){
			randomNum = r.nextInt((max_int - min_int) + 1) + min_int;
			newId += randomNum;
		}
		return newId;
	}
	
	@SuppressWarnings("deprecation")
	public Date generateDate(){
		Random r = new Random();
		LocalDateTime now = LocalDateTime.now();
		int year = r.nextInt((now.getYear() - 1990) + 1) + 1990;
		int month = r.nextInt((11 - 0) + 1) + 0;
		int day;
		if(month == 2)
			day = r.nextInt((28 - 0) + 1) + 0;
		else{
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				day = r.nextInt((31 - 1) + 1) + 1;
			else
				day = r.nextInt((30 - 1) + 1) + 1;
		}
		System.out.println(day + " " + month + " " + year);
		Date newDate = new Date(year, month, day);
		return newDate;
	}
	
	public float generateTarget(float min, float max){
		Random r = new Random();
		float newTarget = (float) (min + (max - min) * r.nextDouble());
		return newTarget;
	}
	
	public String toString(){
		return "Id: " + this.getIdStation() + "\tdate: " + this.getDate().toString() + "\tTarget: " + this.getTarget();
	}
	
	@SuppressWarnings("deprecation")
	public String dateToString(){
		return this.date.getDate() + "." + (this.date.getMonth() + 1) +
				"." + this.date.getYear();
	}
}
