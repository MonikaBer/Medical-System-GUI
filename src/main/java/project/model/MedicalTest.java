package project.model;

import java.util.Date;


public class MedicalTest {
	
	private Date date;
	private Double protein;
	private Double carbamide;
	private Double creatinine;
	
	
	public MedicalTest(Date d, Double p, Double ca, Double cr) {
		
		this.date = d;
		this.protein = p;
		this.carbamide = ca;
		this.creatinine = cr;
	}
	
	
	public Date getDate() {
		
		return this.date;
	}
	
	
	public void setDate(Date d) {
		
		this.date = d;
	}
	
	
	public Double getProtein() {
		
		return this.protein;
	}
	
	
	public void setProtein(Double p) {
		
		this.protein = p;
	}
	
	
	public Double getCarbamide() {
		
		return this.carbamide;
	}
	
	
	public void setCarbamide(Double ca) {
		
		this.carbamide = ca;
	}
	
	
	public Double getCreatinine() {
		
		return this.creatinine;
	}
	
	public void setCreatinine(Double cr) {
		
		this.creatinine = cr;
	}
	
	
	public boolean isProteinOK() {
		
		if (this.protein > 10.0)
			return false;
		return true;
	}

	
	public boolean isCarbamideOK() {
		
		if (this.carbamide < 15.0 || this.carbamide > 40.0)
			return false;
		return true;
	}

	
	public boolean isCreatinineOK() {
		
		if (this.creatinine < 0.57 || this.creatinine > 1.11)
			return false;
		return true;
	}
}
