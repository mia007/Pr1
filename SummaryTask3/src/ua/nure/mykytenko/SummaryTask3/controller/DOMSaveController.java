package ua.nure.mykytenko.SummaryTask3.controller;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ua.nure.mykytenko.SummaryTask3.entity.plane.*;
import ua.nure.mykytenko.SummaryTask3.constants.XML;
import ua.nure.mykytenko.SummaryTask3.entity.*;

/**
 * This class describes DOM controller save functionality
 * 
 */
public class DOMSaveController {
	/**
	 * Save Planes object to XML
	 * 
	 * @param planes
	 *            Planes objects
	 * @param xmlFileName
	 *            input XMl file name
	 * @throws ParserConfigurationException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws TransformerException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void saveToXML(Planes planes, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		saveToXML(getDocument(planes), xmlFileName);
	}

	/**
	 * Save DOM to XML.
	 * 
	 * @param document
	 *            DOM to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 * @throws TransformerException
	 *             Throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void saveToXML(Document document, String xmlFileName)
			throws TransformerException {

		StreamResult result = new StreamResult(new File(xmlFileName));

		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");

		// run transformation
		t.transform(new DOMSource(document), result);
	}

	/**
	 * Creates and returns DOM of the Test container.
	 * 
	 * @param test
	 *            Test object.
	 * @throws ParserConfigurationException
	 */
	public static Document getDocument(Planes planes)
			throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element rootElement = document.createElement(XML.PLANES.getValue());

		document.appendChild(rootElement);

		for (Plane plane : planes.getPlanes()) {
			addPlane(plane, rootElement, document);
		}

		return document;
	}

	/**
	 * add Plane entity to the XML document tree
	 * 
	 * @param plane
	 *            Plane object
	 * @param rootElement
	 *            root element
	 * @param document
	 *            XML document tree node
	 */
	public static void addPlane(Plane plane, Element rootElement,
			Document document) {
		Element planeElement = document.createElement(XML.PLANE.getValue());
		rootElement.appendChild(planeElement);

		Element modelElement = document.createElement(XML.MODEL.getValue());
		modelElement.setTextContent(plane.getModel());
		planeElement.appendChild(modelElement);

		Element originElement = document.createElement(XML.ORIGIN.getValue());
		originElement.setTextContent(plane.getOrigin());
		planeElement.appendChild(originElement);

		Element charsElement = document.createElement(XML.CHARS.getValue());
		addChars(plane.getChars(), charsElement, document);
		planeElement.appendChild(charsElement);

		Element parametersElement = document.createElement(XML.PARAMETERS
				.getValue());
		addParameters(plane.getParameters(), parametersElement, document);
		planeElement.appendChild(parametersElement);

		Element priceElement = document.createElement(XML.PRICE.getValue());
		priceElement.setTextContent(plane.getPrice().getValue() + "");
		if (plane.getPrice().getUnit() != null) {
			priceElement.setAttribute(XML.UNIT.getValue(), plane.getPrice()
					.getUnit());
		}
		planeElement.appendChild(priceElement);

	}

	/**
	 * Save Plane parameters entity to the XML document tree
	 * 
	 * @param parameters
	 *            parameters object
	 * @param parametersElement
	 *            parameters root element
	 * @param document
	 *            XML document tree node
	 */
	public static void addParameters(Parameters parameters,
			Element parametersElement, Document document) {
		Element paramElement = document.createElement(XML.LENGTH.getValue());
		paramElement.setTextContent(parameters.getPlaneLength() + "");
		parametersElement.appendChild(paramElement);

		paramElement = document.createElement(XML.WIDTH.getValue());
		paramElement.setTextContent(parameters.getPlaneWidth() + "");
		parametersElement.appendChild(paramElement);

		paramElement = document.createElement(XML.HEIGHT.getValue());
		paramElement.setTextContent(parameters.getPlaneHeight() + "");
		parametersElement.appendChild(paramElement);

		if (parameters.getUnit() != null) {
			parametersElement.setAttribute(XML.UNIT.getValue(),
					parameters.getUnit());
		}
	}

	/**
	 * Save Plane Chars entity to the XMl document tree
	 * 
	 * @param chars
	 *            Chars object
	 * @param charsElement
	 *            chars root element
	 * @param document
	 *            XML document tree node
	 */
	public static void addChars(Chars chars, Element charsElement,
			Document document) {
		if (chars.getType() != null) {
			Element typeElement = document.createElement(XML.TYPE.getValue());
			typeElement.setTextContent(chars.getType());
			charsElement.appendChild(typeElement);
		}

		if (chars.getSeats() != 0) {
			Element placesElement = document.createElement(XML.SEATS
					.getValue());
			placesElement.setTextContent(chars.getSeats().toString());
			charsElement.appendChild(placesElement);
		}

		Ammunition ammo = chars.getAmmunition();
		if (ammo.getValue() != null) {
			Element ammoElement = document.createElement(XML.AMMUNITION.getValue());
			ammoElement.setTextContent(ammo.getValue().toString());
			if (ammo.getValue()) {
				ammoElement.setAttribute(XML.ROCKET.getValue(), ammo
						.getRocket().toString());
			}
			charsElement.appendChild(ammoElement);
		}
		if (chars.getRadar() != null) {
			Element radarElement = document.createElement(XML.RADAR.getValue());
			radarElement.setTextContent(chars.getRadar().toString());
			charsElement.appendChild(radarElement);
		}
	}
}
