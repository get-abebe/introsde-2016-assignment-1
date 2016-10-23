package jaxbClass;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import model.HealthProfile;
import model.Person;
import jaxbClass.People;

// This class changing a java object of three persons to a Json file by Jackson Object Mapper
public class JAXBJson {
	
	public static People people = new People();

	public static void initializeDB() {
		Person Lary = new Person();
		Person Bob = new Person(new Long(1), "Bob", "Marley", "1944-06-03");
		HealthProfile hp = new HealthProfile(null, 70.0, 1.75, 22.0);
		Person Michael = new Person(new Long(2), "Michael", "Jackson", "1959-12-10", hp);

		people.getPeople().add(Lary);
		people.getPeople().add(Bob);
		people.getPeople().add(Michael);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        
        // marshaling to json and print the result
        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        
        // save to person.json
        mapper.writeValue(new File("PersonsMarsh.json"), people);
    }
}
