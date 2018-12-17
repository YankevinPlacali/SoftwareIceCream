package com.projekt_saqs.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.projekt_saqs.gui.*;

@SuppressWarnings("serial")
public class VarianceWriter extends JPanel implements VarianzViewInterface {
	
	JLabel labelVariance = new JLabel("Varianz:");
	JTextField variance = new JTextField();

	public VarianceWriter() {
		this.variance.setEditable(false);
		this.setLayout(new GridLayout(1, 2));
		this.add(labelVariance);
		this.add(variance);
	}

	@Override
	public void updateVariance(float n_varianz, float n_percentage) {
		this.variance.setText("" + n_varianz);
		if(n_percentage < 10)
			this.variance.setForeground(new Color(245, 17, 54));
		else if(n_percentage > 5)
				this.variance.setForeground(new Color(18, 129, 9));
			else
				this.variance.setForeground(new Color(27, 97, 219));
	}
}
