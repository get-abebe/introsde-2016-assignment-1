package model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import model.HealthProfile;

@XmlRootElement(name = "person")
@XmlType(propOrder = { "firstname", "lastname", "birthdate", "healthprofile" })
@XmlAccessorType (XmlAccessType.FIELD)

public class Person {

	@XmlAttribute(name="id")
	private Long personId;
	private String firstname;
	private String lastname;
	private String birthdate;
	@XmlElement(name="healthprofile")
	private HealthProfile healthprofile;

	public Person(Long personId, String fname, String lname, String birthdate,
			HealthProfile hp) {
		this.setPersonId(personId);
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate);
		this.healthprofile = hp;
	}

	public Person(Long personId, String fname, String lname, String birthdate) {
		this.setPersonId(personId);
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate);
		this.healthprofile = new HealthProfile();
	}

	public Person() {

		this.firstname = "Lary";
		this.lastname = "Page";
		this.healthprofile = new HealthProfile();
		this.personId = Math.round(Math.floor(Math.random() * 9998) + 1);
		this.birthdate = this.getRandomDate();
	}

	/*
	 * Classes have methods, which are basically pieces of programs that can be
	 * executed on objects of the class this dummy class, has only 'accesor'
	 * methods (i.e. methods to access its properties, which are all private)
	 */
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public HealthProfile gethProfile() {
		return healthprofile;
	}

	public void sethProfile(HealthProfile hProfile) {
		this.healthprofile = hProfile;
	}

	/* Solution to Exercise #01-1a and #01-1b */
	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(long id) {
		this.personId = id;
	}

	/*
	 * Solution to Exercise #01-1e creating a random date between now and 1950
	 */
	private String getRandomDate() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR); // 1. get
																		// the
																		// current
																		// year
		int year = (int) Math
				.round(Math.random() * (currentYear - 1950) + 1950); // 2.
																		// generate
																		// a
																		// random
																		// year
																		// between
																		// 1950
																		// and
																		// currentYear
		int month = (int) Math.round(Math.floor(Math.random() * 11) + 1); // 3.
																			// select
																			// a
																			// random
																			// month
																			// of
																			// the
																			// year
		// 4. select a random day in the selected month
		// 4.1 prepare a months array to store how many days in each month
		int[] months = new int[] { 31, 28, 30, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 4.2 if it is a leap year, feb (months[1]) should be 29
		if ((currentYear % 4 == 0)
				&& ((currentYear % 100 != 0) || (currentYear % 400 == 0))) {
			months[1] = 29;
		}
		long day = Math.round(Math.floor(Math.random()
				* (months[month - 1] - 1) + 1));
		return "" + year + "-" + month + "-" + day;
	}
	
	public String toString() {
		return "Firstname= " + firstname +", Lastname=" + lastname + ", Birthday= " + birthdate + " " + healthprofile.toString();
	}

}
