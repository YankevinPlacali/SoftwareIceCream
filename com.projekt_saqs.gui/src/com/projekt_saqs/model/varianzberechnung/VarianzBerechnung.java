package com.projekt_saqs.model.varianzberechnung;

import java.util.Observable;

import com.projekt_saqs.model.InfoUpdateModel;
import com.projekt_saqs.presenter.Info;

public class VarianzBerechnung extends Observable implements InfoUpdateModel{
	
	float variance;
	float percentage;
	
	@Override
	public void getActualInfo(Info info) {
		this.variance = info.getActual() - info.getTarget();
		this.percentage = (this.variance / info.getTarget()) * 100;
		this.setChanged();
		this.notifyObservers();
	}

	public float getVariance() {
		return variance;
	}

	public void setVariance(float variance) {
		this.variance = variance;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

}
