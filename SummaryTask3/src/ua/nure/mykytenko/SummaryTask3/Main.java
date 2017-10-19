package ua.nure.mykytenko.SummaryTask3;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ua.nure.mykytenko.SummaryTask3.util.Sorter;
import ua.nure.mykytenko.SummaryTask3.entity.Planes;
import ua.nure.mykytenko.SummaryTask3.controller.*;

/**
 * Main program class
 * 
 */
public class Main {
	/**
	 * This function shows the DOM controller functionality.
	 * 
	 * @param fileName
	 *            name of the inputed XML file
	 * @throws IOException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws SAXException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws TransformerException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * 
	 */
	public static void domTask(String fileName)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {
		DOMController domController = new DOMController(fileName);
		boolean useNameSpace = true;
		boolean validate = true;
		domController.parse(useNameSpace, validate);
		Planes planes = domController.getPlanes();
		Sorter.sortPlanes(planes, Sorter.SORT_PLANES_BY_LENGTH);

		String outputXmlFile = "output.dom.xml";
		save(outputXmlFile, planes);
	}

	/**
	 * 
	 * @param fileName
	 *            name of the inputed XML file
	 * @throws TransformerException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws IOException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws SAXException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void saxTask(String fileName)
			throws ParserConfigurationException, TransformerException,
			SAXException, IOException {
		SAXController saxController = new SAXController(fileName);
		boolean useNameSpace = true;
		boolean validate = true;
		saxController.parse(validate, useNameSpace);
		Planes planes = saxController.getPlanes();

		Sorter.sortPlanes(planes, Sorter.SORT_PLANES_BY_ORIGIN);

		String outputXmlFile = "output.sax.xml";
		save(outputXmlFile, planes);
	}

	/**
	 * @param fileName
	 *            name of the inputed XML file
	 * @throws TransformerException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws XMLStreamException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void staxTask(String fileName)
			throws ParserConfigurationException, TransformerException,
			XMLStreamException {
		STAXController staxController = new STAXController(fileName);
		boolean useNameSpace = true;
		staxController.parse(useNameSpace);
		Planes planes = staxController.getPlanes();
		Sorter.sortPlanes(planes, Sorter.SORT_PLANES_BY_MODEL);

		String outputXmlFile = "output.stax.xml";
		save(outputXmlFile, planes);

	}

	/**
	 * Save output XML file
	 * 
	 * @param outputXmlFile
	 *            String output XML file name
	 * @param planes
	 *            list of Plane
	 * @throws TransformerException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 * @throws ParserConfigurationException
	 *             throws an exception if there are problems with the processing
	 *             of the input data
	 */
	public static void save(String outputXmlFile, Planes planes)
			throws ParserConfigurationException, TransformerException {
		DOMSaveController.saveToXML(planes, outputXmlFile);
		System.out.println("Output:\t" + outputXmlFile);
	}


	/**
	 * Main class method. Start program.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		if (args.length != 1 || args[0].length() == 0) {
			System.out.println("incorrect command line arguments");
		} else {
			String xmlFileName = args[0];
			System.out.println("Input:\t" + xmlFileName);
			try {
				domTask(xmlFileName);
				saxTask(xmlFileName);
				staxTask(xmlFileName);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			xmlFileName = "input.xml";
		}
	}
}