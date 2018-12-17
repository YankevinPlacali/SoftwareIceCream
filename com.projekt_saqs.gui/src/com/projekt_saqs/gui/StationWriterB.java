package com.projekt_saqs.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.projekt_saqs.presenter.Station;

@SuppressWarnings("serial")
public class StationWriterB extends JTree implements StationWriterInterface, MouseListener {
	CurrentStationInterface csi;
	private ArrayList<Station> listStation;
	
	
	public StationWriterB(DefaultMutableTreeNode racine, CurrentStationInterface n_csi){
		super(racine);
		this.csi = n_csi;
		this.addMouseListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = this.getLastSelectedPathComponent();
		if(o != null){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)o;
			if(node.isLeaf()){
				node = (DefaultMutableTreeNode)node.getParent();
			}
			String id = node.getUserObject().toString();
			float target = Float.parseFloat(((DefaultMutableTreeNode)node.getChildAt(1)).toString());
			
			String[] textDateArray = ((DefaultMutableTreeNode)node.getChildAt(0)).toString().split("\\.");
			int jour = Integer.parseInt(textDateArray[0]),
					mois = Integer.parseInt(textDateArray[1]),
						annee = Integer.parseInt(textDateArray[2]);
			Date date = new Date(annee, mois - 1, jour);
			Station station = new Station(id, date, target);
			this.csi.getCurrentStation(station);
		}
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
	
	public void updateList(ArrayList<Station> n_listStation){
		this.listStation = n_listStation;
		int nbElement = this.listStation.size();
		int i;
		DefaultMutableTreeNode racine = (DefaultMutableTreeNode) this.getModel().getRoot();
		racine.removeAllChildren();
		for(i = 0; i < nbElement; i++){
			DefaultMutableTreeNode station = new DefaultMutableTreeNode(this.listStation.get(i).getIdStation());
			DefaultMutableTreeNode date = new
					DefaultMutableTreeNode(this.listStation.get(i).dateToString()),
					target = new DefaultMutableTreeNode(this.listStation.get(i).getTarget());
			station.add(date);
			station.add(target);
			racine.add(station);
		}
		this.setModel(new DefaultTreeModel(racine));
	}

	@Override
	public void getAllStationen(ArrayList<Station> n_stationList) {
		this.updateList(n_stationList);
	}

}
