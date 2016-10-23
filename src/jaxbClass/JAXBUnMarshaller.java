package jaxbClass;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import people.generated.People;
import people.generated.People.Person;
import people.generated.People.Person.Healthprofile;

//After getting the xml file this class un-marshaling to java object of persons and print the result
public class JAXBUnMarshaller {
	
	
	public void unMarshall(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance("people.generated");

			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(new File("PersonsMarsh.xsd"));
			unMarshaller.setSchema(schema);
			CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
			unMarshaller.setEventHandler(validationEventHandler);

			People pElement = (People) unMarshaller
					.unmarshal(xmlDocument);

			List<Person> peopleList = pElement.getPerson();
			for (int i = 0; i < peopleList.size(); i++) {

				Person person = (Person) peopleList.get(i);

				System.out.println("Person firstName: " + person.getFirstname() + " " + "lastName: " + person.getLastname());
				Healthprofile hProfile = person.getHealthprofile();
				System.out.println("HealthProfile: ");
				System.out.println("Weight: " + hProfile.getWeight() + " " +  " Height: " + hProfile.getHeight());

			}
		} catch (JAXBException e) {
			System.out.println(e.toString());
		} catch (SAXException e) {
			System.out.println(e.toString());
		}
	}
    
	//The main method which passes the xml doc to unMarshaller method
	public static void main(String[] argv) {
		File xmlDocument = new File("PersonsMarsh.xml");
		JAXBUnMarshaller jaxbUnmarshaller = new JAXBUnMarshaller();

		jaxbUnmarshaller.unMarshall(xmlDocument);

	}

	//validates the xml doc against its xml schema(xsd) file 
	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}

}
