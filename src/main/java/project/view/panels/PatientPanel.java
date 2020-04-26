package project.view.panels;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import project.model.Patient;


@SuppressWarnings("serial")
public class PatientPanel extends JPanel {
	
	private JLabel lName, lSurname, lPesel, lGender, lInsurance;
	private JButton bSavePatient, bCancelPatient;
	private JTextField tName, tSurname, tPesel;
	private ButtonGroup bgGenderPanel;
	private JRadioButton rbMale, rbFemale;
	private JComboBox<String> cbInsurance;
	
	
	public PatientPanel(int x, int y) {
		
		super();
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
		this.setPreferredSize(new Dimension(x,y));
		
		
		this.lName = new JLabel("Imię:");
		this.lName.setBounds(20, 30, 120, 20);
		this.add(this.lName);
	
		this.tName = new JTextField();
		this.tName.setBounds(190, 30, 200, 20);
		this.add(this.tName);
		
		this.lSurname = new JLabel("Nazwisko:");
		this.lSurname.setBounds(20, 60, 120, 20);
		this.add(this.lSurname);
		
		this.tSurname = new JTextField();
		this.tSurname.setBounds(190, 60, 200, 20);
		this.add(this.tSurname);
		
		this.lPesel = new JLabel("PESEL:");
		this.lPesel.setBounds(20, 90, 120, 20);
		this.add(this.lPesel);
		
		this.tPesel = new JTextField();
		this.tPesel.setBounds(190, 90, 200, 20);
		this.add(this.tPesel);
		
		this.lGender = new JLabel("Płeć");
		this.lGender.setBounds(20, 120, 120, 20);
		this.add(this.lGender);
		
		this.bgGenderPanel = new ButtonGroup();
		this.rbFemale = new JRadioButton("Kobieta");
		this.rbFemale.setBounds(190, 120, 100, 20);		
		this.bgGenderPanel.add(this.rbFemale);
		this.add(this.rbFemale);
		this.rbMale = new JRadioButton("Mężczyzna");
		this.rbMale.setBounds(290, 120, 100, 20);
		this.bgGenderPanel.add(this.rbMale);
		this.add(this.rbMale);
		
		this.lInsurance = new JLabel("Ubezpieczenie:");
		this.lInsurance.setBounds(20, 150, 120, 20);
		this.add(this.lInsurance);
		
		this.cbInsurance = new JComboBox<String>();
		this.cbInsurance.setBounds(190, 150, 200, 20);
		this.cbInsurance.addItem("NFZ");
		this.cbInsurance.addItem("Prywatne");
		this.cbInsurance.addItem("Brak");
		this.add(this.cbInsurance);
		
		this.bSavePatient = new JButton("Zapisz");
		this.bSavePatient.setBounds(20, 200, 80, 20);
		this.add(this.bSavePatient);
		
		this.bCancelPatient = new JButton("Anuluj");
		this.bCancelPatient.setBounds(105, 200, 80, 20);
		this.add(this.bCancelPatient);
		
		this.disablePatientPanel();
	}

	
	//getters
	public String getTName() {
		
		return this.tName.getText().trim();
	}


	public String getTSurname() {
		
		return this.tSurname.getText().trim();
	}


	public String getTPesel() {
		
		String pesel = this.tPesel.getText().trim();
		
		if (Patient.isPeselCorrect(pesel))
			return pesel;
		return "";
	}

	
	public String getBgGenderPanel() {
		
		if (this.rbFemale.isSelected())
			return "K";
		return "M"; 
	}
	
	
	public String getCBInsurance() {
		
		return cbInsurance.getSelectedItem().toString();
	}

	
	public JButton getBSavePatient() {
		
		return bSavePatient;
	}


	public JButton getBCancelPatient() {
		
		return bCancelPatient;
	}
	

	//setters
	public void setTName(String text) {
		
		this.tName.setText(text);
	}
	
	
	public void setTSurname(String text) {
		
		this.tSurname.setText(text);
	}
	
	
	public void setTPesel(String text) {
		
		this.tPesel.setText(text);
	}
	
	
	public void setCBInsurance(String text) {
		
		this.cbInsurance.setSelectedItem((Object)text);
	}
	
	
	public void disablePatientPanel() {
		
		this.setTName("");
		this.setTSurname("");
		this.setTPesel("");
		this.setCBInsurance("NFZ");
		
		this.tName.setEnabled(false);
		this.tSurname.setEnabled(false);
		this.tPesel.setEnabled(false);
		this.rbFemale.setEnabled(false);
		this.rbMale.setEnabled(false);
		this.cbInsurance.setEnabled(false);
		this.bSavePatient.setEnabled(false);
		this.bCancelPatient.setEnabled(false);
	}
	
	
	public void enablePatientPanel() {
		
		this.tName.setEnabled(true);
		this.tSurname.setEnabled(true);
		this.tPesel.setEnabled(true);
		this.rbFemale.setEnabled(true);
		this.rbMale.setEnabled(true);
		this.cbInsurance.setEnabled(true);
		this.bSavePatient.setEnabled(true);
		this.bCancelPatient.setEnabled(true);
	}
	
	
	public void completePatientPanel(Object[] patientInfo) {
		
		String text = patientInfo[0].toString().trim();
		int index = text.indexOf(" ");
		String name = text.substring(0, index).trim();
		String surname = text.substring(index).trim();
		this.tName.setText(name);
		this.tSurname.setText(surname);
		if (patientInfo[1].toString().equals("K")) {
			this.rbFemale.setSelected(true);
		} else {
			this.rbMale.setSelected(true);
		}
		this.tPesel.setText(patientInfo[2].toString().trim());
		this.cbInsurance.getModel().setSelectedItem(patientInfo[3].toString());
	}
}
