package jaxbClass;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.Person;

@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.FIELD)

public class People {
	
	@XmlElement(name = "person")
	private List<Person> people = new ArrayList<Person>();;

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

}
