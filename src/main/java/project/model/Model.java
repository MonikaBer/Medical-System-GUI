package project.model;

import java.util.ArrayList;


public class Model {
	
	private ArrayList<Patient> patients;
	
	
	public Model() {
		
		this.patients = new ArrayList<Patient>();
	}
	
	
	public boolean addPatient(Patient patient) {  //return true if success or false if no
		
		if (this.patients.contains(patient))
			return false;
		
		this.patients.add(patient);
		return true;
	}
	
	
	public void deletePatient(int index) { 
		
		this.patients.remove(index);
	}
	
	
	public boolean modifyPatient(int index, String name, String surname, String pesel, String gender, String insurance) {

		if (index == -1)
			return false;

		Patient patient = this.patients.get(index);

		for (Patient p : this.patients) {
			if (p.getPesel().equals(pesel)) {
				if (!patient.getPesel().equals(pesel)) {
					return false;
				}
			}
		}

		this.patients.set(index, new Patient(name, surname, pesel, gender, insurance, patient.getMedicalTest()));
		return true;
	}
	
	
	public void setMedicalTest(int index, MedicalTest medicalTest) {
		
		this.patients.get(index).setMedicalTest(medicalTest);
	}

	
	public MedicalTest getMedicalTest(int index) {
		
		return this.patients.get(index).getMedicalTest();
	}
	
	
	public String toString() {
		
		StringBuilder s = new StringBuilder();
		for (Patient p : this.patients) {
			String str = new String(p.toString());
			s.append(str);
		}	
		return s.toString();
	}


	public ArrayList<Patient> getPatients() {

		return this.patients;
	}
}
