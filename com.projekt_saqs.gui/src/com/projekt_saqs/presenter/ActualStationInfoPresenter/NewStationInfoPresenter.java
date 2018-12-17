package com.projekt_saqs.presenter.ActualStationInfoPresenter;

import com.projekt_saqs.model.InfoUpdateModel;
import com.projekt_saqs.presenter.Info;
import com.projekt_saqs.presenter.InfoUpdateInterface;

public class NewStationInfoPresenter implements InfoUpdateInterface{
	private InfoUpdateModel uim_ss;
	private InfoUpdateModel uim_vb;
	

	public NewStationInfoPresenter(InfoUpdateModel n_uim_ss, InfoUpdateModel n_uim_vb){
		this.uim_ss = n_uim_ss;
		this.uim_vb = n_uim_vb;
	}

	@Override
	public void getActualInfo(Info info) {
		this.uim_ss.getActualInfo(info);
		this.uim_vb.getActualInfo(info);
	}
}
