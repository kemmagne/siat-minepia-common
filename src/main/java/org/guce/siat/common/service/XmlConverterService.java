package org.guce.siat.common.service;


import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.xml.bind.JAXBException;

import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileItem;
import org.guce.siat.common.model.Flow;
import org.guce.siat.common.model.ItemFlow;
import org.guce.siat.common.utils.ebms.UtilitiesException;
import org.guce.siat.common.utils.exception.ValidationException;
import org.xml.sax.SAXException;



/**
 * The Class XmlConverter.
 */
public interface XmlConverterService
{

	/**
	 * Save received file and execute flow.
	 *
	 * @param document
	 *           the document
	 * @return the file
	 * @throws ParseException
	 *            the parse exception
	 * @throws PersistenceException
	 *            the persistence exception
	 * @throws NullPointerException
	 *            the null pointer exception
	 * @throws ValidationException
	 *            the validation exception
	 */
	File saveReceivedFileAndExecuteFlow(final Serializable document) throws ParseException, PersistenceException,
			NullPointerException, ValidationException;

	/**
	 * Convert document to file.
	 *
	 * @param document
	 *           the document
	 * @return the file
	 * @throws ParseException
	 *            the parse exception
	 * @throws ValidationException
	 *            the validation exception
	 */
	File convertDocumentToFile(final Serializable document) throws ParseException, ValidationException;

	/**
	 * Convert file to document.
	 *
	 * @param file
	 *           the file
	 * @param fileItemList
	 *           the file item list
	 * @param itemFlowList
	 *           the item flow list
	 * @param flowToExecute
	 *           the flow to execute
	 * @return the serializable
	 * @throws UtilitiesException
	 *            the utilities exception
	 * @throws IOException
	 * @throws SAXException
	 * @throws JAXBException
	 * @throws ParseException
	 */
	Serializable convertFileToDocument(final File file, List<FileItem> fileItemList, final List<ItemFlow> itemFlowList,
			Flow flowToExecute) throws UtilitiesException, JAXBException, SAXException, IOException, ParseException;

}
