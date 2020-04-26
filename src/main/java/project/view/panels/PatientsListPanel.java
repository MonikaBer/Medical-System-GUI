package project.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.model.MedicalTest;


@SuppressWarnings("serial")
public class PatientsListPanel extends JPanel {

	private DefaultTableModel tableModel; 
	private JTable tabPatientsList;
	private JScrollPane spPatientsList;
	private int rowSelectedNr = -1;
	
	public PatientsListPanel(int x, int y) {
		
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(x,y));
		
		
		this.tableModel = new DefaultTableModel();
		this.tabPatientsList = new JTable(this.tableModel){
		   
			public boolean isCellEditable(int row, int column){
		        return false;
		   }
		   
		   @SuppressWarnings({ "unchecked", "rawtypes" })
		   @Override
		   public Class getColumnClass(int columnIndex) {
			   if (columnIndex == 4)
				   return Boolean.class;
			   return String.class;
		   }
		};	
		
		this.tableModel.addColumn("Imię i Nazwisko");
		this.tableModel.addColumn("Płeć");
		this.tableModel.addColumn("PESEL");
		this.tableModel.addColumn("Ubezpieczenie");
		this.tableModel.addColumn("Badanie");
		this.tabPatientsList.isEditing();
		this.spPatientsList = new JScrollPane(this.tabPatientsList);
		this.add(this.spPatientsList);
		
		this.tabPatientsList.setEnabled(false);
	}
	
	
	//getters
	public JTable getTabPatientsList() {
		
		return this.tabPatientsList;
	}
	
	
	public int getRowSelectedNr() {
		
		return this.rowSelectedNr;
	}

	
	//setters
	public void setRowSelectedNr(int rowNr) {
		
		this.rowSelectedNr = rowNr;
	}
		
	
	public void addToPatientsListPanel(String name, String surname, String pesel, String gender, String insurance, MedicalTest medicalTest) {
		
		if (medicalTest != null) {
			this.tableModel.addRow(new Object[]{name+" "+surname, gender, pesel, insurance, Boolean.TRUE});
		} else {
			this.tableModel.addRow(new Object[]{name+" "+surname, gender, pesel, insurance, Boolean.FALSE});
		}
	}
	
	
	public void deleteFromPatientsList() {

		this.tableModel.removeRow(this.rowSelectedNr);
	}
	
	
	public void editPatient(String name, String surname, String pesel, String gender, String insurance) {
		
		this.tableModel.setValueAt(name+" "+surname, this.rowSelectedNr, 0);
		this.tableModel.setValueAt(gender, this.rowSelectedNr, 1);
		this.tableModel.setValueAt(pesel, this.rowSelectedNr, 2);
		this.tableModel.setValueAt(insurance, this.rowSelectedNr, 3);
	}
	
	
	public void editMedicalTest() {

		this.tableModel.setValueAt(Boolean.TRUE, this.rowSelectedNr, 4);
	}
	
	
	public void disablePatientsListPanel() {
		
		this.tabPatientsList.setEnabled(false);
	}
	
	
	public void enablePatientsListPanel() {
		
		this.tabPatientsList.setEnabled(true);
	}
	
	
	public Object[] getSelectedPatientInfo() {
		
		Object[] patientInfo = new Object[this.tableModel.getColumnCount()];
		for (int i = 0; i < this.tableModel.getColumnCount(); i++) {
			patientInfo[i] = this.tableModel.getValueAt(this.rowSelectedNr, i);
	    }
		
		return patientInfo;
	}
}
