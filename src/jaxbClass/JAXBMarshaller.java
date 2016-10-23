package jaxbClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import people.generated.ObjectFactory;
import people.generated.People;
import people.generated.People.Person;
import people.generated.People.Person.Healthprofile;

//This class create person using java object and converting/marshaling to an xml file using JAXBMarshaller.

public class JAXBMarshaller {
	
	
	public void generateXMLDocument(File xmlDocument) throws ParseException, DatatypeConfigurationException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			ObjectFactory factory = new ObjectFactory();

			People people = factory.createPeople();

			List<Person> peopleList = people.getPerson();
			
			//create first Person  with an id of 65
			Person person = factory.createPeoplePerson();
			person.setFirstname("Lary");
			person.setLastname("Page");
			person.setId(65.0);
			person.setBirthdate(stringToXMLGregorianCalendar("1973-09-20T18:00:00.000+02:00"));

			Healthprofile hProfile = factory.createPeoplePersonHealthprofile();
			hProfile.setLastupdate(stringToXMLGregorianCalendar("2014-09-20T18:00:00.000+02:00"));
			hProfile.setHeight(1.8);
			hProfile.setWeight(80);
			hProfile.setBmi(31);
		
			person.setHealthprofile(hProfile);
			peopleList.add(person);
			
            //create second person with an id of 86
			Person person1 = factory.createPeoplePerson();
			person1.setFirstname("Bob");
			person1.setLastname("Marley");
			person1.setId(86.0);
			person1.setBirthdate(stringToXMLGregorianCalendar("1944-06-03T18:00:00.000+02:00"));

			Healthprofile hProfile1 = factory.createPeoplePersonHealthprofile();
			hProfile.setLastupdate(stringToXMLGregorianCalendar("2014-09-20T18:00:00.000+02:00"));
			hProfile1.setHeight(1.75);
			hProfile1.setWeight(70);
			hProfile1.setBmi(32);
			
			person1.setHealthprofile(hProfile1);
			peopleList.add(person1);
			
			//create third person with id 96
			Person person2 = factory.createPeoplePerson();
			person2.setFirstname("Michael");
			person2.setLastname("Jackson");
			person2.setId(96.0);
			person2.setBirthdate(stringToXMLGregorianCalendar("1959-12-10T18:00:00.000+02:00"));

			Healthprofile hProfile2 = factory.createPeoplePersonHealthprofile();
			hProfile2.setLastupdate(stringToXMLGregorianCalendar("2002-09-20T18:00:00.000+02:00"));
			hProfile2.setHeight(1.76);
			hProfile2.setWeight(67);
			hProfile2.setBmi(32);
			
			person2.setHealthprofile(hProfile2);
			peopleList.add(person2);
           
			// marshaling the people object to an personsmarshjaxb.xml file
			marshaller.marshal(people,
					new FileOutputStream(xmlDocument));
			// print the result
			marshaller.marshal(people, System.out);
								

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}
	
	public XMLGregorianCalendar stringToXMLGregorianCalendar(String s)
			throws ParseException, DatatypeConfigurationException {
		XMLGregorianCalendar result = null;
		Date date;
		SimpleDateFormat simpleDateFormat;
		GregorianCalendar gregorianCalendar;

		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		date = simpleDateFormat.parse(s);
		gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
		gregorianCalendar.setTime(date);
		result = DatatypeFactory.newInstance().newXMLGregorianCalendar(
				gregorianCalendar);
		return result;
	}
    // the main method
	public static void main(String[] argv) throws DOMException,
			DatatypeConfigurationException, ParseException,
			ParserConfigurationException, SAXException, IOException {

		String file = "PersonsMarsh.xml";
		JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
		// call a method by passing an xml format file
		jaxbMarshaller.generateXMLDocument(new File(file));

	}

}
