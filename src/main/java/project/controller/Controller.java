package project.controller;

import java.util.Date;

import project.interfaces.ViewListener;
import project.model.MedicalTest;
import project.model.Model;
import project.model.Patient;
import project.view.View;


public class Controller implements ViewListener {

	private View view = null;
    private Model model = null;
    
    private enum State {
    	PATIENT_ADDITION, EDITION, NOTHING;
    }
    private State state = State.NOTHING;
    
    
    public Controller(View v, Model m) {     
    	
    	this.view = v;
    	this.model = m;
    }

  
	@Override
	public void viewChanged(View view, Object source) {
		
		//System.out.println(this.model.toString());
		
		if (source == view.getPatientPanel().getBSavePatient()) {
			
			String name = new String(view.getPatientPanel().getTName());
			String surname = new String(view.getPatientPanel().getTSurname());
			String pesel = new String(view.getPatientPanel().getTPesel());
			String gender = new String(view.getPatientPanel().getBgGenderPanel());
			String insurance = new String(view.getPatientPanel().getCBInsurance());
			
			if (name.equals(new String("")) || surname.equals(new String("")) || pesel.equals("")) {
				view.showDialog("Wymagane dane pacjenta to: imię, nazwisko oraz prawidłowy numer pesel.");
				return;
			}
			
			if (this.state == State.PATIENT_ADDITION) {
				
				boolean b = this.model.addPatient(new Patient(name, surname, pesel, gender, insurance, null));
				if (b == true) {
					view.getPatientsListPanel().addToPatientsListPanel(name, surname, pesel, gender, insurance, null);
				} else {
					view.showDialog("W bazie istnieje już pacjent o takim numerze PESEL.");
					return;
				}
			} else if (this.state == State.EDITION) {

				boolean b = this.model.modifyPatient(view.getPatientsListPanel().getRowSelectedNr(), name, surname, pesel, gender, insurance);
				if (b == true) {
					view.getPatientsListPanel().editPatient(name, surname, pesel, gender, insurance);
				} else {
					view.showDialog("W bazie istnieje już inny pacjent o takim numerze PESEL.");
					return;
				}
			}
			
			view.getPatientPanel().disablePatientPanel();
			view.getMedicalTestPanel().disableMedicalTestPanel();
			view.getPatientsListPanel().enablePatientsListPanel();
			view.getButtonsPanel().enableButtonsPanel();
			this.state = State.NOTHING;
			
		} else if (source == view.getPatientPanel().getBCancelPatient()) {
			
			view.getPatientPanel().disablePatientPanel();
			view.getMedicalTestPanel().disableMedicalTestPanel();
			view.getPatientsListPanel().enablePatientsListPanel();
			view.getButtonsPanel().enableButtonsPanel();
			this.state = State.NOTHING;
			
		} else if (source == view.getMedicalTestPanel().getBSaveMedicalTest()) {
			
			Date date = view.getMedicalTestPanel().getDCDate();
			Double protein = view.getMedicalTestPanel().getTProtein();
			Double carbamide = view.getMedicalTestPanel().getTCarbamide();
			Double creatinine = view.getMedicalTestPanel().getTCreatinine();
			
			if (date == null || protein < 0.0 || carbamide < 0.0 || creatinine < 0.0) {
				view.showDialog("Wymagane prawidłowe uzupełnienie wszystkich pól badania.");
				return;
			}
			
			view.getPatientsListPanel().editMedicalTest();
			this.model.setMedicalTest(view.getPatientsListPanel().getRowSelectedNr(), new MedicalTest(date, protein, carbamide, creatinine));
			
			view.getPatientPanel().disablePatientPanel();
			view.getMedicalTestPanel().disableMedicalTestPanel();
			view.getPatientsListPanel().enablePatientsListPanel();
			view.getButtonsPanel().enableButtonsPanel();
			this.state = State.NOTHING;
			
		} else if (source == view.getMedicalTestPanel().getBCancelMedicalTest()) {
			
			view.getPatientPanel().disablePatientPanel();
			view.getMedicalTestPanel().disableMedicalTestPanel();
			view.getPatientsListPanel().enablePatientsListPanel();
			view.getButtonsPanel().enableButtonsPanel();
			this.state = State.NOTHING;
			
		} else if (source == view.getButtonsPanel().getBAdd()) {
			
			view.getPatientPanel().enablePatientPanel();
			view.getMedicalTestPanel().disableMedicalTestPanel();
			view.getPatientsListPanel().disablePatientsListPanel();
			view.getButtonsPanel().disableButtonsPanel();
			this.state = State.PATIENT_ADDITION;
			
		} else if (source == view.getButtonsPanel().getBDelete()) {
			
			if (this.state == State.EDITION) {
				view.getPatientsListPanel().deleteFromPatientsList();
				view.getPatientPanel().disablePatientPanel();
				view.getMedicalTestPanel().disableMedicalTestPanel();
				view.getPatientsListPanel().enablePatientsListPanel();
				view.getButtonsPanel().enableButtonsPanel();
				this.model.deletePatient(view.getPatientsListPanel().getRowSelectedNr());
			}
			this.state = State.NOTHING;
			
		} else if (source == view.getPatientsListPanel().getTabPatientsList()) {
			
			view.getPatientsListPanel().setRowSelectedNr(view.getPatientsListPanel().getTabPatientsList().getSelectedRow());
			if (view.getPatientsListPanel().getRowSelectedNr() != -1) {
				view.getPatientPanel().enablePatientPanel();
				view.getMedicalTestPanel().enableMedicalTestPanel();
				view.getPatientsListPanel().enablePatientsListPanel();
				view.getButtonsPanel().disableButtonsPanel("add");
				view.completePatientPanel();
				MedicalTest medicalTest = this.model.getMedicalTest(view.getPatientsListPanel().getRowSelectedNr());
				if (medicalTest != null) {
					view.getMedicalTestPanel().completeMedicalTestPanel(medicalTest);
				} else {
					view.getMedicalTestPanel().clearMedicalTestPanel();
				}
				this.state = State.EDITION;
			} else {
				this.state = State.NOTHING;
			}
		} else if (source == view.getMenu().getMClose()) {
			
			view.dispose();
		}
	}
}
