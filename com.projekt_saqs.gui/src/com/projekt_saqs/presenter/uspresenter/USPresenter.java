package com.projekt_saqs.presenter.uspresenter;

import java.util.Observable;
import java.util.Observer;

import com.projekt_saqs.gui.*;
import com.projekt_saqs.model.varianzberechnung.VarianzBerechnung;

public class USPresenter implements Observer{
	VarianzViewInterface vvi;
	
	public USPresenter (VarianzViewInterface n_vvi){
		this.vvi = n_vvi;
	}

	@Override
	public void update(Observable o, Object arg) {
		VarianzBerechnung vb = (VarianzBerechnung)o;
		this.vvi.updateVariance(vb.getVariance(), vb.getPercentage());
	}
}
