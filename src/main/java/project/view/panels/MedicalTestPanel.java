package project.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import project.model.MedicalTest;


@SuppressWarnings("serial")
public class MedicalTestPanel extends JPanel {

	private JLabel lDate, lProteinConcentration, lCarbamide, lCreatinine;
	private JDateChooser dcDate;
	private JTextField tProtein, tCarbamide, tCreatinine;
	private JButton bSaveMedicalTest, bCancelMedicalTest;
	
	
	public MedicalTestPanel(int x, int y) {
		
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder("Badanie"));
		this.setPreferredSize(new Dimension(x,y));
		
		
		this.lDate = new JLabel("Data:");
		this.lDate.setBounds(20, 30, 200, 20);
		this.add(this.lDate);
		
		this.dcDate = new JDateChooser();
		this.dcDate.setBounds(240, 30, 150, 20);
		this.add(this.dcDate);
		
		this.lProteinConcentration = new JLabel("Białko w moczu [mg/dl]:");
		this.lProteinConcentration.setBounds(20, 60, 200, 20);
		this.add(this.lProteinConcentration);
		
		this.tProtein = new JTextField();
		this.tProtein.setBounds(240, 60, 150, 20);
		this.add(this.tProtein);
		
		this.lCarbamide = new JLabel("Mocznik [mg/dl]:");
		this.lCarbamide.setBounds(20, 90, 200, 20);
		this.add(this.lCarbamide);
		
		this.tCarbamide = new JTextField();
		this.tCarbamide.setBounds(240, 90, 150, 20);
		this.add(this.tCarbamide);
		
		this.lCreatinine = new JLabel("Kreatynina [mg/dl]:");
		this.lCreatinine.setBounds(20, 120, 200, 20);
		this.add(this.lCreatinine);
		
		this.tCreatinine = new JTextField();
		this.tCreatinine.setBounds(240, 120, 150, 20);
		this.add(this.tCreatinine);
		
		this.bSaveMedicalTest = new JButton("Zapisz"); 
		this.bSaveMedicalTest.setBounds(20, 170, 80, 20);
		this.add(this.bSaveMedicalTest);
		
		this.bCancelMedicalTest = new JButton("Anuluj");
		this.bCancelMedicalTest.setBounds(105, 170, 80, 20);
		this.add(this.bCancelMedicalTest);
		
		this.disableMedicalTestPanel();
	}


	//getters
	public Date getDCDate() {
		
		return this.dcDate.getDate();
	}
	
	
	public Double getTProtein() {

		Double protein;
		try {
			protein = Double.parseDouble(this.tProtein.getText().trim());
		} catch(NumberFormatException e) {
			protein = -1.0;
		}

		return protein;
	}
	
	
	public Double getTCarbamide() {

		Double carbamide;
		try {
			carbamide = Double.parseDouble(this.tCarbamide.getText().trim());
		} catch(NumberFormatException e) {
			carbamide = -1.0;
		}

		return carbamide;
	}
	
	
	public Double getTCreatinine() {

		Double creatinine;
		try {
			creatinine = Double.parseDouble(this.tCreatinine.getText().trim());
		} catch(NumberFormatException e) {
			creatinine = -1.0;
		}

		return creatinine;
	}
	
	
	public JButton getBSaveMedicalTest() {
		
		return this.bSaveMedicalTest;
	}
	
	
	public JButton getBCancelMedicalTest() {
		
		return this.bCancelMedicalTest;
	}

	
	//setters
	public void setDCDate(Date date) {
		
		this.dcDate.setDate(date);
	}
	
	
	public void setTProtein(String text) {
		
		this.tProtein.setText(text);
	}
	
	
	public void setTCarbamide(String text) {
		
		this.tCarbamide.setText(text);
	}
	
	
	public void setTCreatinine(String text) {
		
		this.tCreatinine.setText(text);
	}
	
	
	public void disableMedicalTestPanel() {
		
		this.tProtein.setText(" ");
		this.tCarbamide.setText(" ");
		this.tCreatinine.setText(" ");
		
		this.tProtein.setBackground(Color.WHITE);
		this.tCarbamide.setBackground(Color.WHITE);
		this.tCreatinine.setBackground(Color.WHITE);
		
		this.dcDate.setEnabled(false);
		this.tProtein.setEnabled(false);
		this.tCarbamide.setEnabled(false);
		this.tCreatinine.setEnabled(false);
		this.bSaveMedicalTest.setEnabled(false);
		this.bCancelMedicalTest.setEnabled(false);
	}
	
	
	public void enableMedicalTestPanel() {
		
		this.dcDate.setEnabled(true);
		this.tProtein.setEnabled(true);
		this.tCarbamide.setEnabled(true);
		this.tCreatinine.setEnabled(true);
		this.bSaveMedicalTest.setEnabled(true);
		this.bCancelMedicalTest.setEnabled(true);
	}
	
	
	public void completeMedicalTestPanel(MedicalTest medicalTestInfo) {
	
		this.setDCDate(medicalTestInfo.getDate());
		this.setTProtein(medicalTestInfo.getProtein().toString());
		this.setTCarbamide(medicalTestInfo.getCarbamide().toString());
		this.setTCreatinine(medicalTestInfo.getCreatinine().toString());
		
		if(medicalTestInfo.isProteinOK()) {
			this.tProtein.setBackground(Color.GREEN);
		} else {
			this.tProtein.setBackground(new Color(240,128,128));
		}
			
		if(medicalTestInfo.isCarbamideOK()) {
			this.tCarbamide.setBackground(Color.GREEN);
		} else {
			this.tCarbamide.setBackground(new Color(240,128,128));
		}
		
		if(medicalTestInfo.isCreatinineOK()) {
			this.tCreatinine.setBackground(Color.GREEN);
		} else {
			this.tCreatinine.setBackground(new Color(240,128,128));
		}
	}
	
	
	public void clearMedicalTestPanel() {
		
		this.setDCDate(null);
		this.setTProtein("");
		this.setTCarbamide("");
		this.setTCreatinine("");
		
		this.tProtein.setBackground(Color.WHITE);
		this.tCarbamide.setBackground(Color.WHITE);
		this.tCreatinine.setBackground(Color.WHITE);
	}
}
