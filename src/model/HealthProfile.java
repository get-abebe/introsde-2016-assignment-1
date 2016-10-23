package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "healthprofile")
@XmlType(propOrder = { "weight", "height", "lastupdate", "bmi" })
@XmlAccessorType (XmlAccessType.FIELD)

public class HealthProfile {
	private double weight;
	private double height;
	private String lastupdate;
	private double bmi;

	public HealthProfile(String lupString, double weight, double height, double bmi) {
		this.lastupdate = lupString;
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
	}

	public HealthProfile() {
		this.lastupdate = "2014-09-20T18:00:00.000+02:00";
		this.weight = 85.5;
		this.height = 1.72;
		this.bmi = 31.14;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public String getLastUpdate() {
		return lastupdate;
	}

	public void setLastUpdate(String lupdate) {
		this.lastupdate = lupdate;
	}

	public double getBMI() {
		return bmi;
	}

	public void setBMI(double bmi) {
		this.bmi = bmi;
	}

	public String toString() {
		return "LastUpdateDate= " + lastupdate +", Height= " + height + ", Weight= " + weight + ", BMI= " + bmi;
	}

	// add accessor for the newly created BMI
	// the getter can respond with the calculation (weight divided the height in
	// meters elevated to the power of 2)

}
