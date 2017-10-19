package ua.nure.mykytenko.SummaryTask3.controller;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.mykytenko.SummaryTask3.entity.Planes;
import ua.nure.mykytenko.SummaryTask3.constants.*;
import ua.nure.mykytenko.SummaryTask3.entity.plane.*;

/**
 * This class describes StAX parser functionality
 * 
 */
public class STAXController extends DefaultHandler {
	/**
	 * Planes object handler
	 */
	private Planes planes;
	/**
	 * String current xml element name
	 */
	private String currElement = null;
	/**
	 * String input XML file name
	 */
	private String fileName;
	/**
	 * Plane object handler
	 */
	private Plane plane = null;
	/**
	 * Chars object handler
	 */
	private Chars chars = null;
	/**
	 * Ammunition object handler
	 */
	private Ammunition ammunition = null;
	/**
	 * Parameters object handler
	 */
	private Parameters parameters = null;
	/**
	 * Price object handler
	 */
	private Price price = null;

	/**
	 * Class constructor
	 * 
	 * @param fileName
	 *            input XML file name
	 */
	public STAXController(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Parses input XML file. 
	 * 
	 * @param useNamespace
	 * @throws XMLStreamException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws SAXException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public void parse(boolean useNamespace) throws XMLStreamException {

		XMLInputFactory factory = XMLInputFactory.newInstance();

		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, useNamespace);

		XMLEventReader reader = factory.createXMLEventReader(new StreamSource(
				fileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (event.isStartElement()) {
				startTagHandler(event);
				continue;
			}

			if (event.isCharacters()) {
				characterHandler(event);
				continue;
			}

			if (event.isEndElement()) {
				endHandler(event);
			}
		}
		reader.close();
	}

	/**
	 * Starts tag processing function
	 * 
	 * @param event
	 *            Current XML event
	 */
	public void startTagHandler(XMLEvent event) {
		StartElement startElement = event.asStartElement();
		currElement = startElement.getName().getLocalPart();

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

		if (XML.PRICE.equalsTo(currElement)) {
			price = new Price();
			Attribute attribute = startElement.getAttributeByName(new QName(
					XML.UNIT.getValue()));
			if (attribute != null) {
				price.setUnit(attribute.getValue());
			}
			return;
		}

		if (XML.PARAMETERS.equalsTo(currElement)) {
			parameters = new Parameters();
			Attribute attribute = startElement.getAttributeByName(new QName(
					XML.UNIT.getValue()));
			if (attribute != null) {
				parameters.setUnit(attribute.getValue());
			}
			return;
		}

		if (XML.AMMUNITION.equalsTo(currElement)) {
			ammunition = new Ammunition();
			Attribute attribute = startElement.getAttributeByName(new QName(
					XML.ROCKET.getValue()));
			if (attribute != null) {
				ammunition.setRocket(Byte.parseByte(attribute.getValue()));
			}
			return;
		}
	}

	/**
	 * Processing function for elements data
	 * 
	 * @param event
	 *            Current XML event
	 */
	public void characterHandler(XMLEvent event) {
		Characters characters = event.asCharacters();

		String data = characters.getData();

		if (XML.MODEL.equalsTo(currElement)) {
			plane.setModel(data);
			return;
		}

		if (XML.ORIGIN.equalsTo(currElement)) {
			plane.setOrigin(data);
			return;
		}

		charsCharacterHandler(data);

		parametersCharacterhandler(data);

		if (XML.PRICE.equalsTo(currElement)) {
			double priceValue = Double.parseDouble(data);
			price.setValue(priceValue);
			return;
		}
	}

	/**
	 * Processing function for Chars elements data
	 * 
	 * @param data
	 *            String current element data value
	 */
	public void charsCharacterHandler(String data) {
		if (XML.TYPE.equalsTo(currElement)) {
			chars.setType(data);
			return;
		}

		if (XML.SEATS.equalsTo(currElement)) {
			byte seats = Byte.parseByte(data);
			chars.setSeats(seats);
			return;
		}

		if (XML.RADAR.equalsTo(currElement)) {
			boolean radar = Boolean.parseBoolean(data);
			chars.setRadar(radar);
			return;
		}

		if (XML.AMMUNITION.equalsTo(currElement)) {
			boolean ammunitionValue = Boolean.parseBoolean(data);
			ammunition.setValue(ammunitionValue);
			return;
		}
	}

	/**
	 * Processing function for parameters elements data
	 * 
	 * @param data
	 *            String current element data value
	 */
	public void parametersCharacterhandler(String data) {
		if (XML.LENGTH.equalsTo(currElement)) {
			parameters.setPlaneLength(Double.parseDouble(data));
			return;
		}

		if (XML.WIDTH.equalsTo(currElement)) {
			parameters.setPlaneWidth(Double.parseDouble(data));
			return;
		}

		if (XML.HEIGHT.equalsTo(currElement)) {
			parameters.setPlaneHeight(Double.parseDouble(data));
			return;
		}
	}

	/**
	 * End tag processing function
	 * 
	 * @param event
	 *            Current XML event
	 */
	public void endHandler(XMLEvent event) {

		EndElement endElement = event.asEndElement();
		String elementName = endElement.getName().getLocalPart();

		if (XML.PLANE.equalsTo(elementName)) {
			planes.add(plane);
			return;
		}

		if (XML.CHARS.equalsTo(elementName)) {
			plane.setChars(chars);
			return;
		}

		if (XML.AMMUNITION.equalsTo(elementName)) {
			chars.setAmmunition(ammunition);
			return;
		}

		if (XML.PRICE.equalsTo(elementName)) {
			plane.setPrice(price);
			return;
		}

		if (XML.PARAMETERS.equalsTo(elementName)) {
			plane.setParameters(parameters);
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
	 * Shows the StAX parser functionality
	 * 
	 * @param args
	 *            command line arguments
	 * @throws XMLStreamException
	 *             Throws an exception if there are problems with the  input data processing
	 */
	public static void main(String[] args) throws XMLStreamException {
		String fileName = "";
		if (args.length == 1) {
			fileName = args[0];
		} else {
			fileName = Constants.XSD_FILE;
		}
		STAXController staxController = new STAXController(fileName);
		boolean useNanespace = true;
		staxController.parse(useNanespace);

		System.out.println(staxController.getPlanes());
	}

}