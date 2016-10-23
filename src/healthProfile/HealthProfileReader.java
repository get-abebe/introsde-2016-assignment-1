package healthProfile;

import java.io.IOException;
import java.util.Formatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import healthProfile.HealthProfileReader;
import model.Person;
import model.HealthProfile;

//This class is basically did XPath query from an xml file. Among the queries get the list of people in detail, 
//return health profile of a person with id 5 and return list of person satisfying certain conditions.

public class HealthProfileReader {
	
	Document document;
	XPath xpath;

	final static String fileName = "people.xml";
	

	public void loadFile() throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.parse(fileName);

		getXPathObj();
	}

	public XPath getXPathObj() {

		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		return xpath;
	}
	
	// print the health profile of person with a particular id
	private void printHealthProfile(Long id) {
		try {
			System.out.println("Printing healthprofile of id " + id + ": ");
			HealthProfile hp = this.getHealthProfile(id);
			System.out.println(hp.toString());
		} catch (XPathExpressionException e) {
			System.out.println(e);
		}
	}
   
	// return the health profile of person having specific id
	public HealthProfile getHealthProfile(Long id)
			throws XPathExpressionException {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		String expression = "/people/person[@id=" + id.toString()
				+ "]/healthprofile";
		XPathExpression expr = xpath.compile(expression);
		Node node = (Node) expr.evaluate(document, XPathConstants.NODE);

		return nodeToHealthProfile(node);
	}
	
	private HealthProfile nodeToHealthProfile(Node node) {
		HealthProfile hp = new HealthProfile();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Node importedNode = document.importNode(node, true);
			document.appendChild(importedNode);

			XPath xpath = XPathFactory.newInstance().newXPath();

			XPathExpression expr = xpath.compile("/healthprofile/weight");
			hp.setWeight((Double) expr
					.evaluate(document, XPathConstants.NUMBER));
			expr = xpath.compile("/healthprofile/height");
			hp.setHeight((Double) expr
					.evaluate(document, XPathConstants.NUMBER));
			expr = xpath.compile("/healthprofile/lastupdate");
			hp.setLastUpdate((String) expr.evaluate(document,
					XPathConstants.STRING));
			expr = xpath.compile("/healthprofile/bmi");
			hp.setBMI((Double) expr.evaluate(document, XPathConstants.NUMBER));
		} catch (ParserConfigurationException e) {
			System.out.println(e.toString());
		} catch (XPathExpressionException e) {

		}

		return hp;
	}
	
    // accept condition and weight and call printPeople
	public void printPeopleByWeight(String condition, double weight) {
		try {
			System.out.println("People with weight " + condition + " " + weight
					+ ":");
			NodeList nodeList = this.getPeopleByWeight(condition, weight);
			int x = nodeList.getLength();
			if (x != 0)
			this.printPeople(nodeList);
			else 
			System.out.println("no people satisfying this condition ");
		} catch (XPathExpressionException e) {
			System.out.println(e);
		}
	}
	
	private void printPeople(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Person person = nodeToPerson(node, i);
			System.out.println(person.toString());
		}
	}
   
	//return people satisfies the condition to printPeopleByWeight
	public NodeList getPeopleByWeight(String condition, double weight)
			throws XPathExpressionException {

		String expression = "/people/person[healthprofile/weight " + condition
				+ " " + weight + "]";
		XPathExpression expr = xpath.compile(expression);
		NodeList nodes = (NodeList) expr.evaluate(document,
				XPathConstants.NODESET);

		return nodes;
	}
   
	//print the height of all people
	public void getHeightOfPeople() throws XPathExpressionException {
		XPathExpression expr = xpath
				.compile("/people/person/healthprofile/height/text()");

		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}
	}
	
	// print the weight of all people
	public void getWeightOfPeople() throws XPathExpressionException {
		XPathExpression expr = xpath
				.compile("/people/person/healthprofile/weight/text()");

		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println(nodes.item(i).getNodeValue());
		}
	}
    
	// print details of all people in the file
	public void getDetailsOfPeople() throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person");

		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		int i = 0;
		while (i < nodes.getLength()) {
			Node node = nodes.item(i);
			Person person = nodeToPerson(node, i);
			System.out.println(person.toString());
			i++;
			
		}
	}

	private Person nodeToPerson(Node node, int id) {
		Person p = new Person();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Node importedNode = document.importNode(node, true);
			document.appendChild(importedNode);

			XPath xpath = XPathFactory.newInstance().newXPath();

			XPathExpression expr = xpath.compile("/person/firstname");
			node = (Node) expr.evaluate(document, XPathConstants.NODE);
			p.setFirstname(node.getTextContent());
			expr = xpath.compile("/person/@id");
			node = (Node) expr.evaluate(document, XPathConstants.NODE);
			String personId = node.getTextContent();
			p.setPersonId(Integer.parseInt(personId));
			expr = xpath.compile("/person/lastname");
			node = (Node) expr.evaluate(document, XPathConstants.NODE);
			p.setLastname(node.getTextContent());
			expr = xpath.compile("/person/birthdate");
			node = (Node) expr.evaluate(document, XPathConstants.NODE);
			p.setBirthdate(node.getTextContent());
			expr = xpath.compile("/person/healthprofile");
			node = (Node) expr.evaluate(document, XPathConstants.NODE);

			if (node != null) {
				p.sethProfile(nodeToHealthProfile(node));
			} else {
				p.sethProfile(new HealthProfile());
			}
		} catch (ParserConfigurationException e) {
			System.out.println(e.toString());
		} catch (XPathExpressionException e) {

		}
		return p;
	}
	
	// the main method that starts everything 
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException, Exception {
		
		HealthProfileReader health = new HealthProfileReader();
		health.loadFile();
		
		// To see the list of people in detail, call getDetailsOfPeople method
		health.getDetailsOfPeople();
		System.out.println();
		
		// To see the specific health profile of person with id 5, call printHealthProfile method
		health.printHealthProfile((long) 5);
		System.out.println();
		
		//To see people whose weight is greater than 90 kg , call printPeopleByWeight method
		health.printPeopleByWeight(">", 90);
		
		
}
}
