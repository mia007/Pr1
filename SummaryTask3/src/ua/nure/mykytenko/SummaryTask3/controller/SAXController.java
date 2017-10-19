package ua.nure.mykytenko.SummaryTask3.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.mykytenko.SummaryTask3.entity.Planes;
import ua.nure.mykytenko.SummaryTask3.constants.*;
import ua.nure.mykytenko.SummaryTask3.entity.plane.*;

/**
 * This class describes SAX parser functionality
 * 
 */
public class SAXController extends DefaultHandler {

	private String currElement = "";
	private Planes planes;
	private Plane plane;
	private Chars chars;
	private Parameters parameters;
	private Ammunition ammunition;
	private Price price;
	private String fileName;

	/**
	 * Class constructor
	 * 
	 * @param fileName
	 *            input xml file name
	 */
	public SAXController(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validates XML document against its XML schema. 
	 *
	 * @param useNamespace
	 *            If true sets nameSpaceAware
	 * @throws ParserConfigurationException
	 * @throws IOException
	 *             Throws an exception if there are problems with the  input data processing
	 * @throws SAXException
	 *             Throws an exception if there are problems with the  input data processing
	 */
	public void parse(boolean validate, boolean useNamespace)
			throws ParserConfigurationException, SAXException, IOException {

		// obtain sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// XML document contains namespaces
		factory.setNamespaceAware(useNamespace);

		// set validation
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(
					Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		javax.xml.parsers.SAXParser parser = factory.newSAXParser();
		parser.parse(fileName, this);
	}

	@Override
	public void startElement(String uri, String elementName, String qName,
			Attributes attributes) throws SAXException {
		currElement = elementName;

		if (XML.PLANES.equalsTo(currElement)) {
			planes = new Planes();
			return;
		}

		if (XML.PLANE.equalsTo(currElement)) {
			plane = new Plane();
			return;
		}

		if (XML.CHARS.equalsTo(currElement)) {
			chars = new Chars();
			return;
		}

		if (XML.AMMUNITION.equalsTo(currElement)) {
			ammunition = new Ammunition();
			if (attributes.getLength() > 0) {
				ammunition.setRocket(Byte.parseByte(attributes.getValue(uri,
						XML.ROCKET.getValue())));
			}
			return;
		}

		if (XML.PARAMETERS.equalsTo(currElement)) {
			parameters = new Parameters();
			if (attributes.getLength() > 0) {
				parameters
						.setUnit(attributes.getValue(uri, XML.UNIT.getValue()));
			}
			return;
		}

		if (XML.PRICE.equalsTo(currElement)) {
			price = new Price();
			if (attributes.getLength() > 0) {
				price.setUnit(attributes.getValue(uri, XML.UNIT.getValue()));
			}
			return;
		}

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) {
			return;
		}

		if (XML.MODEL.equalsTo(currElement)) {
			plane.setModel(elementText);
			return;
		}

		if (XML.ORIGIN.equalsTo(currElement)) {
			plane.setOrigin(elementText);
			return;
		}

		if (XML.PRICE.equalsTo(currElement)) {
			double priceValue = Double.parseDouble(elementText);
			price.setValue(priceValue);
			return;
		}

		charsCharacter(elementText);
		parametersCharacter(elementText);
	}

	/**
	 * Sets Plane Chars data
	 * 
	 * @param elementText
	 *            String text of the element
	 */
	public void charsCharacter(String elementText) {
		if (XML.RADAR.equalsTo(currElement)) {
			boolean radar = Boolean.parseBoolean(elementText);
			chars.setRadar(radar);
			return;
		}

		if (XML.TYPE.equalsTo(currElement)) {
			chars.setType(elementText);
			return;
		}

		if (XML.SEATS.equalsTo(currElement)) {
			byte seats = Byte.parseByte(elementText);
			chars.setSeats(seats);
			return;
		}

		if (XML.AMMUNITION.equalsTo(currElement)) {
			boolean ammunitionValue = Boolean.parseBoolean(elementText);
			if (!ammunitionValue) {
				ammunition.setRocket((byte) 0);
			}
			ammunition.setValue(ammunitionValue);
			return;
		}
	}

	/**
	 * Sets Plane parameters data
	 * 
	 * @param elementText
	 *            String text of the element
	 */
	public void parametersCharacter(String elementText) {
		if (XML.LENGTH.equalsTo(currElement)) {
			parameters.setPlaneLength(Double.parseDouble(elementText));
			return;
		}

		if (XML.WIDTH.equalsTo(currElement)) {
			parameters.setPlaneWidth(Double.parseDouble(elementText));
			return;
		}

		if (XML.HEIGHT.equalsTo(currElement)) {
			parameters.setPlaneHeight(Double.parseDouble(elementText));
			return;
		}
	}

	@Override
	public void endElement(String uri, String elementName, String qName)
			throws SAXException {
		if (XML.PLANE.equalsTo(elementName)) {
			planes.add(plane);
			return;
		}

		if (XML.CHARS.equalsTo(elementName)) {
			plane.setChars(chars);
			return;
		}

		if (XML.PARAMETERS.equalsTo(elementName)) {
			plane.setParameters(parameters);
			return;
		}

		if (XML.PRICE.equalsTo(elementName)) {
			plane.setPrice(price);
			return;
		}

		if (XML.AMMUNITION.equalsTo(elementName)) {
			chars.setAmmunition(ammunition);
			return;
		}
	}

	/**
	 * Return current Planes object
	 * 
	 * @return Planes object
	 */
	public Planes getPlanes() {
		return planes;
	}

	/**
	 * Shows the SAX parser functionality
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IOException
	 *             Throws an exception if there are problems with the  input data processing
	 * @throws SAXException
	 *             Throws an exception if there are problems with the  input data processing
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with the  input data processing
	 */
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		String fileName = "";
		if (args.length == 1) {
			fileName = args[0];
		} else {
			fileName = Constants.XSD_FILE;
		}
		SAXController saxController = new SAXController(fileName);
		boolean validate = true;
		boolean useNamespace = true;
		saxController.parse(validate, useNamespace);
		System.out.println(saxController.getPlanes());
	}
}