package project.model;

import java.util.ArrayList;


public class Patient {
	
	private String name;
	private String surname;
	private String pesel;
	private String gender;
	private String insurance;
	private MedicalTest medicalTest = null;
	
	
	public Patient(String n, String s, String p, String g, String i, MedicalTest mt) {
		
		this.name = n;
		this.surname = s;
		this.pesel = p;
		this.gender = g;
		this.insurance = i;
		this.medicalTest = mt;
	}
	
	
	public String getName() {
		
		return this.name;
	}
	
	
	public String getSurname() {
		
		return this.surname;
	}
	
	
	public String getPesel() {
		
		return this.pesel;
	}
	
	
	public String getGender() {
		
		return this.gender;
	}
	
	
	public String getInsurance() {
		
		return this.insurance;
	}
	
	
	public MedicalTest getMedicalTest() {
		
		return this.medicalTest;
	}
	
	
	public void setName(String n) {
		
		this.name = n;
	}
	
	
	public void setSurname(String s) {
		
		this.surname = s;
	}
	
	
	public void setPesel(String p) {
		
		this.pesel = p;
	}
	
	
	public void setGender(String g) {
		
		this.gender = g;
	}
	
	
	public void setInsurance(String i) {
		
		this.insurance = i;
	}
	
	
	public void setMedicalTest(MedicalTest mt) {
		
		this.medicalTest = mt;
	}

	
	@Override
	public String toString() {
		
		return "[name=" + name + ", surname=" + surname + ", pesel=" + pesel + ", gender=" + 
				gender + ", insurance=" + insurance + "]\n";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Patient other = (Patient)obj;
		if (this.pesel == null) {
			if (other.pesel != null) {
				return false;
			}
		} else if (!this.pesel.equals(other.pesel)) {
			return false;
		}
		return true;
	}
	
	
	public static boolean isPeselCorrect(String p){
		
		if (p.length() != 11) 
			return false;
		
		char[] pesel = p.toCharArray();
		for (char c : pesel) {
			if (!Character.isDigit(c))
				return false;
		}
		
		return true;
	}
}
