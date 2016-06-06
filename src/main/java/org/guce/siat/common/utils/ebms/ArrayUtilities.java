package org.guce.siat.common.utils.ebms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;


/**
 * ArrayUtilities is a convenient class for handling some common array processing.
 */
@SuppressWarnings({"rawtypes","unchecked"})
public final class ArrayUtilities
{

	/**
	 * Creates a new instance of ArrayUtilities.
	 */
	private ArrayUtilities()
	{
	}

	/**
	 * Returns the Class object array associated with the class or interface with the given string name array.
	 *
	 * @param classNames
	 *           the fully qualified names of the desired classes.
	 * @return the Class object array for the classes with the specified names.
	 * @throws ClassNotFoundException
	 *            the class not found exception
	 */
	public static Class[] forNames(final String[] classNames) throws ClassNotFoundException
	{
		final int size = classNames == null ? 0 : classNames.length;
		final Class[] classes = new Class[size];
		for (int i = 0; i < classes.length; i++)
		{
			classes[i] = Class.forName(classNames[i]);
		}
		return classes;
	}

	/**
	 * Returns the Class object array associated with the class or interface with the given object array.
	 *
	 * @param objs
	 *           the objects of the desired classes.
	 * @return the Class object array for the classes with the specified objects.
	 */
	public static Class[] forObjects(final Object[] objs)
	{
		final int size = objs == null ? 0 : objs.length;
		final Class[] classes = new Class[size];
		for (int i = 0; i < classes.length; i++)
		{
			classes[i] = (objs[i] == null ? null : objs[i].getClass());
		}
		return classes;
	}

	/**
	 * To classes.
	 *
	 * @param objs
	 *           the objs
	 * @return the class[]
	 * @throws ClassNotFoundException
	 *            the class not found exception
	 */
	public static Class[] toClasses(final Object[] objs) throws ClassNotFoundException
	{
		Class[] classes;
		if (objs == null)
		{
			classes = new Class[] {};
		}
		else if (objs instanceof Class[])
		{
			classes = (Class[]) objs;
		}
		else if (objs instanceof String[])
		{
			classes = forNames((String[]) objs);
		}
		else
		{
			classes = forObjects(objs);
		}
		return classes;
	}

	/**
	 * Tokenizes a string by the specified delimiter(s) and converts it into a string array.
	 *
	 * @param s
	 *           the string to be tokenized.
	 * @param delim
	 *           the delimiter(s).
	 * @return the tokenized strings as a string array.
	 */
	public static String[] toArray(final String s, final String delim)
	{
		final StringTokenizer stk = new StringTokenizer(s == null ? "" : s, delim);
		return (String[]) toArray(stk, new String[] {});
	}

	/**
	 * Converts an iterator into an object array.
	 *
	 * @param it
	 *           the iterator to be converted.
	 * @return an array representation of the iterator.
	 */
	public static Object[] toArray(final Iterator it)
	{
		return toArray(it, null);
	}

	/**
	 * Converts an iterator into an object array.
	 *
	 * @param it
	 *           the iterator to be converted.
	 * @param a
	 *           the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new
	 *           array of the same runtime type is allocated for this purpose.
	 * @return an array representation of the iterator.
	 */
	public static Object[] toArray(final Iterator it, final Object[] a)
	{
		final ArrayList array = new ArrayList();
		if (it != null)
		{
			while (it.hasNext())
			{
				array.add(it.next());
			}
		}
		return a == null ? array.toArray() : array.toArray(a);

	}

	/**
	 * Converts an enumeration into an object array.
	 *
	 * @param enumeration
	 *           the enumeration to be converted.
	 * @return an array representation of the enumeration.
	 */
	public static Object[] toArray(final Enumeration enumeration)
	{
		return toArray(enumeration, null);
	}

	/**
	 * Converts an enumeration into an object array.
	 *
	 * @param enumeration
	 *           the enumeration to be converted.
	 * @param a
	 *           the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new
	 *           array of the same runtime type is allocated for this purpose.
	 * @return an array representation of the enumeration.
	 */
	public static Object[] toArray(final Enumeration enumeration, final Object[] a)
	{
		final ArrayList array = new ArrayList();
		if (enumeration != null)
		{
			while (enumeration.hasMoreElements())
			{
				array.add(enumeration.nextElement());
			}
		}
		return a == null ? array.toArray() : array.toArray(a);
	}
}
