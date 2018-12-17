package com.projekt_saqs.presenter;

import java.util.Date;
import java.util.Random;

public class Info extends Station{
	private float actual;

	public Info(){
		super();
		this.actual = this.generateActual(5, 100);
	}
	
	public Info(String id, Date date, float target, float n_actual){
		super(id, date, target);
		this.actual = n_actual;
	}
	
	public Info(float n_target){
		super(n_target);
		this.actual = this.generateActual(5, 100);
	}
	
	public float getActual() {
		return actual;
	}

	public void setActual(float actual) {
		this.actual = actual;
	}
	
	public float generateActual(float min, float max){
		Random r = new Random();
		float newTarget = (float) (min + (max - min) * r.nextDouble());
		return newTarget;
	}
}
