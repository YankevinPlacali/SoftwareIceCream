package com.projekt_saqs.model.stationset;

import com.projekt_saqs.model.UpdateInfoModel;
import com.projekt_saqs.presenter.ComponentPresenter;

public class Test {

	public static void main(String[] args) {
		UpdateInfoModel uim = new StationSet();
		Thread t1 = new Thread(new StationSet());
		Thread t2 = new Thread(new ComponentPresenter(uim));
		t1.start();
		t2.start();
	}

}
