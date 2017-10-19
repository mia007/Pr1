package ua.nure.mykytenko.SummaryTask3.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.nure.mykytenko.SummaryTask3.entity.Planes;
import ua.nure.mykytenko.SummaryTask3.constants.*;
import ua.nure.mykytenko.SummaryTask3.entity.plane.*;

/**
 * This class describes DOM controller functionality
 * 
 */
public class DOMController {
	/**
	 * XML document
	 */
	private Document doc = null;
	/**
	 * List with planes
	 */
	private Planes planes = null;
	/**
	 * Input XML file name
	 */
	private String fileName;

	/**
	 * Class constructor
	 * 
	 * @param fileName
	 *            input XML file name
	 */
	public DOMController(String fileName) {
		this.fileName = fileName;

	}

	/**
	 * Starts XML file parsing and creates Planes object from it
	 * 
	 * @param useNameSpace
	 *            Required for use NameSpace
	 * @param validate
	 *            Required for data validation
	 * @throws IOException
	 *             Throws an exception if there are problems with the input data
	 *             processing
	 * @throws SAXException
	 *             Throws an exception if there are problems with the input data
	 *             processing
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with parsing input
	 *             data
	 */
	public void parse(boolean useNameSpace, boolean validate)
			throws ParserConfigurationException, SAXException, IOException {
		doc = parserXML(new File(fileName), useNameSpace, validate);
		planes = createPlanes(doc);
	}

	/**
	 * Returns Planes object from the parsed XML document
	 * 
	 * @param doc
	 *            Parsed XML document
	 * @return Planes object
	 */
	public Planes createPlanes(Document doc) {
		Element root = doc.getDocumentElement();

		Planes newPlanes = new Planes();

		NodeList planeNodes = root.getElementsByTagName(XML.PLANE.getValue());

		for (int j = 0; j < planeNodes.getLength(); j++) {
			Plane plane = getPlane(planeNodes.item(j));
			newPlanes.add(plane);
		}

		return newPlanes;
	}

	/**
	 * Returns Plane object from the XML Plane node
	 * 
	 * @param planeNode
	 *            XML plane node
	 * @return Plane object from XML node
	 */
	public Plane getPlane(Node planeNode) {
		Plane plane = new Plane();
		Element planeElement = (Element) planeNode;

		Node modelNode = planeElement.getElementsByTagName(XML.MODEL.getValue()).item(0);
		plane.setModel(modelNode.getTextContent());

		Node originNode = planeElement.getElementsByTagName(XML.ORIGIN.getValue()).item(0);
		plane.setOrigin(originNode.getTextContent());

		Node charsNode = planeElement.getElementsByTagName(XML.CHARS.getValue()).item(0);
		plane.setChars(getChars(charsNode));

		Node parametersNode = planeElement.getElementsByTagName(XML.PARAMETERS.getValue()).item(0);
		plane.setParameters(getParameters(parametersNode));

		Node priceNode = planeElement.getElementsByTagName(XML.PRICE.getValue()).item(0);
		plane.setPrice(getPrice(priceNode));

		return plane;
	}

	/**
	 * Returns Plane price from the XML price node
	 * 
	 * @param priceNode
	 *            XML price node
	 * @return Price object from the XML node
	 */
	public Price getPrice(Node priceNode) {
		Element priceElement = (Element) priceNode;
		Price price = new Price();
		price.setValue(Double.parseDouble(priceElement.getTextContent()));
		price.setUnit(priceElement.getAttribute(XML.UNIT.getValue()));
		return price;
	}

	/**
	 * Returns Plane chars from the XML chars node
	 * 
	 * @param charsNode
	 *            XML chars node
	 * @return Chars object from the XML node
	 */
	public Chars getChars(Node charsNode) {
		Element charsElement = (Element) charsNode;
		Chars chars = new Chars();

		Node ammunitionNode = charsElement.getElementsByTagName(XML.AMMUNITION.getValue()).item(0);
		chars.setAmmunition(getAmmunition(ammunitionNode));

		Node seatssNode = charsElement.getElementsByTagName(XML.SEATS.getValue()).item(0);
		chars.setSeats(Byte.parseByte(seatssNode.getTextContent()));

		Node radarNode = charsElement.getElementsByTagName(XML.RADAR.getValue()).item(0);
		chars.setRadar(Boolean.parseBoolean(radarNode.getTextContent()));

		Node typeNode = charsElement.getElementsByTagName(XML.TYPE.getValue()).item(0);
		chars.setType(typeNode.getTextContent());

		return chars;
	}

	/**
	 * Returns Plane ammunition from the XML ammunition node
	 * 
	 * @param ammunitionNode
	 *            XML ammunition node
	 * @return Ammunition object from the XML node
	 */
	public Ammunition getAmmunition(Node ammunitionNode) {
		Element ammunitionElement = (Element) ammunitionNode;
		Ammunition ammunition = new Ammunition();

		Boolean isAmmunition = Boolean.parseBoolean(ammunitionElement.getTextContent());
		ammunition.setValue(isAmmunition);
		if (isAmmunition) {
			Byte rocket = Byte.parseByte(ammunitionElement.getAttribute(XML.ROCKET.getValue()));
			ammunition.setRocket(rocket);
		} else {
			ammunition.setRocket((byte) 0);
		}
		return ammunition;
	}

	/**
	 * Returns Plane parameters object from the XML parameters node
	 * 
	 * @param parametersNode
	 *            XML parameters node
	 * @return Parameters object from the XML node
	 */
	public Parameters getParameters(Node parametersNode) {
		Element parametersElement = (Element) parametersNode;
		Parameters parameters = new Parameters();

		Node heightNode = parametersElement.getElementsByTagName(XML.HEIGHT.getValue()).item(0);
		double height = Double.parseDouble(heightNode.getTextContent());
		parameters.setPlaneHeight(height);

		Node lengthNode = parametersElement.getElementsByTagName(XML.LENGTH.getValue()).item(0);
		double length = Double.parseDouble(lengthNode.getTextContent());
		parameters.setPlaneLength(length);

		Node widthNode = parametersElement.getElementsByTagName(XML.WIDTH.getValue()).item(0);
		double width = Double.parseDouble(widthNode.getTextContent());
		parameters.setPlaneWidth(width);

		parameters.setUnit(parametersElement.getAttribute(XML.UNIT.getValue()));
		return parameters;
	}

	/**
	 * 
	 * @param file
	 *            XML file
	 * @param useNameSpace
	 *            Need for use NameSpace
	 * @param validate
	 *            Need for data validation
	 * @return parsed XML document
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws IOException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws SAXException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public Document parserXML(File file, boolean useNameSpace, boolean validate)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(useNameSpace);
		if (validate) {
			// turn validation on
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

			// turn on xsd validation
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}
		return dbf.newDocumentBuilder().parse(file);
	}

	/**
	 * Returns parsed List of Plane
	 * 
	 * @return List of PLane objects
	 */
	public Planes getPlanes() {
		return planes;
	}


	/**
	 * Show the DOM controller functionality
	 * 
	 * @param args
	 *            command line arguments
	 * @throws IOException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws SAXException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String fileName = "";
		if (args.length == 1) {
			fileName = args[0];
		} else {
			fileName = Constants.XSD_FILE;
		}

		DOMController domController = new DOMController(fileName);
		boolean validate = true;
		boolean useNameSpace = true;
		domController.parse(validate, useNameSpace);
		System.out.println(domController.getPlanes());
	}
}