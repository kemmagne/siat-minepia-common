package org.guce.siat.utility.jaxb.aperak;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 * The Class JAXBContextCreator.
 */
public final class JAXBContextCreator
{

	/** The instance. */
	private static JAXBContext instance;

	/**
	 * Instantiates a new JAXB context creator.
	 */
	private JAXBContextCreator()
	{
	}

	/**
	 * Gets the single instance of JAXBContextCreator.
	 *
	 * @return single instance of JAXBContextCreator
	 */
	public static synchronized JAXBContext getInstance()
	{
		try
		{
			if (instance == null)
			{
				instance = JAXBContext.newInstance(DOCUMENT.class);
			}
		}
		catch (final JAXBException e)
		{

		}
		return instance;
	}

}
