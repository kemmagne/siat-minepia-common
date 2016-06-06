package org.guce.siat.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.hibernate.annotations.Immutable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Utility class for XPath. There's a {@link #findNode(String,Element)} methods which allows you to find inside XML
 * document using the current instance of a given XPath. <code>XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED</code>
 * <code>UNABLE_EVALUATE_XPATH_EXPRESSION</code>
 *
 * @see <a href="http://www.w3.org/TR/xpath">XML Path Language (XPath)V1.0</a>
 * @see <a href="http://www.w3.org/TR/xpath#section-Expressions">Expressions</a>
 */
@Immutable
public final class XmlXPathUtils
{

	/** The Constant XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED. */
	private static final String XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED = "Xpath expression and root element required";

	/** The Constant UNABLE_EVALUATE_XPATH_EXPRESSION. */
	private static final String UNABLE_EVALUATE_XPATH_EXPRESSION = "Unable evaluate xpath expression";

	/** The Constant COMPILED_EXPRESSION_CACHE. */
	private static final Map<String, XPathExpression> COMPILED_EXPRESSION_CACHE = new HashMap<>();

	/** The Constant XPATH. */
	private static final XPath XPATH = XPathFactory.newInstance().newXPath();

	/**
	 * Instantiates a new xml x path utils.
	 */
	private XmlXPathUtils()
	{
		// Utility class, so hide default constructor.
	}

	/**
	 * Checks in under a given root element whether it can find a Sample value which matches the XPath expression
	 * supplied. Returns {@link String} if exists.
	 *
	 * Please note that the XPath parser used is NOT namespace aware. So if you want to find a element <beans><sec:http>
	 * you need to use the following XPath expression '</beans/http>'.
	 *
	 * @param xPathExpression
	 *           the xPathExpression (required)
	 * @param root
	 *           the parent DOM element (required)
	 *
	 * @return the String value if discovered (null if not found)
	 */
	public static String findSingleValue(final String xPathExpression, final Element root)
	{
		if (xPathExpression == null || root == null || xPathExpression.length() == 0)
		{
			throw new IllegalArgumentException(XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED);
		}
		String value = null;
		try
		{

			XPathExpression expr = COMPILED_EXPRESSION_CACHE.get(xPathExpression);
			if (expr == null)
			{
				expr = XPATH.compile(xPathExpression);
				COMPILED_EXPRESSION_CACHE.put(xPathExpression, expr);
			}
			value = expr.evaluate(root);
			XPATH.compile(xPathExpression).evaluate(root);
		}
		catch (final XPathExpressionException e)
		{
			throw new IllegalArgumentException(UNABLE_EVALUATE_XPATH_EXPRESSION, e);
		}
		return value;
	}

	/**
	 * Checks in under a given root element whether it can find a Sample value which matches the XPath expression
	 * supplied. Returns {@link java.util.List} if exists.
	 *
	 * Please note that the XPath parser used is NOT namespace aware. So if you want to find a element <beans><sec:http>
	 * you need to use the following XPath expression '</beans/http>'.
	 *
	 * @param xPathExpression
	 *           the xPathExpression (required)
	 * @param root
	 *           the parent DOM element (required)
	 *
	 * @return the String value if discovered (null if not found)
	 */
	public static List<String> findSingleValuesList(final String xPathExpression, final Element root)
	{
		final NodeList nodeList = findNodeList(xPathExpression, root);
		if (nodeList.getLength() > 0)
		{
			final List<String> values = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++)
			{
				values.add(nodeList.item(i).getFirstChild().getNodeValue());
			}
			return values;
		}
		return Collections.emptyList();
	}

	/**
	 * Checks in under a given root element whether it can find a child node which matches the XPath expression supplied.
	 * Returns {@link Node} if exists.
	 *
	 * Please note that the XPath parser used is NOT namespace aware. So if you want to find a element <beans><sec:http>
	 * you need to use the following XPath expression '</beans/http>'.
	 *
	 * @param xPathExpression
	 *           the xPathExpression (required)
	 * @param root
	 *           the parent DOM element (required)
	 *
	 * @return the Node if discovered (null if not found)
	 */
	public static Node findNode(final String xPathExpression, final Element root)
	{

		if (xPathExpression == null || root == null || xPathExpression.length() == 0)
		{
			throw new IllegalArgumentException(XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED);
		}
		Node node = null;
		try
		{
			XPathExpression expr = COMPILED_EXPRESSION_CACHE.get(xPathExpression);
			if (expr == null)
			{
				expr = XPATH.compile(xPathExpression);
				COMPILED_EXPRESSION_CACHE.put(xPathExpression, expr);
			}
			node = (Node) expr.evaluate(root, XPathConstants.NODE);
		}
		catch (final XPathExpressionException e)
		{
			throw new IllegalArgumentException(UNABLE_EVALUATE_XPATH_EXPRESSION, e);
		}
		return node;
	}

	/**
	 * Checks in under a given root element whether it can find a child node which matches the XPath expression supplied.
	 * Returns {@link Node} if exists.
	 *
	 * Please note that the XPath parser used is NOT namespace aware. So if you want to find a element <beans><sec:http>
	 * you need to use the following XPath expression '</beans/http>'.
	 *
	 * @param xPathExpression
	 *           the xPathExpression (required)
	 * @param root
	 *           the parent DOM element (required)
	 *
	 * @return the NodeList if discovered (null if not found)
	 */
	public static NodeList findNodeList(final String xPathExpression, final Element root)
	{

		if (xPathExpression == null || root == null || xPathExpression.length() == 0)
		{
			throw new IllegalArgumentException(XPATH_EXPRESSION_AND_ROOT_ELEMENT_REQUIRED);
		}
		NodeList node = null;
		try
		{
			XPathExpression expr = COMPILED_EXPRESSION_CACHE.get(xPathExpression);
			if (expr == null)
			{
				expr = XPATH.compile(xPathExpression);
				COMPILED_EXPRESSION_CACHE.put(xPathExpression, expr);
			}
			node = (NodeList) expr.evaluate(root, XPathConstants.NODESET);
		}
		catch (final XPathExpressionException e)
		{
			throw new IllegalArgumentException(UNABLE_EVALUATE_XPATH_EXPRESSION, e);
		}
		return node;
	}

	/**
	 * Returns attribute value of a node or <code>null</code> if attribute name not found. Specified attribute is
	 * searched on every call. Consider {@link #getAttributeValue(org.w3c.dom.Node,String)} for better performances.
	 *
	 * @param node
	 *           the node
	 * @param attrName
	 *           the attr name
	 * @return <code>String</code>
	 */
	public static String getAttributeValue(final Node node, final String attrName)
	{
		final NamedNodeMap nmm = node.getAttributes();
		for (int j = 0; j < nmm.getLength(); j++)
		{
			final Node attribute = nmm.item(j);
			if (attribute.getNodeType() != Node.ATTRIBUTE_NODE)
			{
				continue;
			}
			final String nodeName = attribute.getNodeName();
			if (nodeName.equals(attrName))
			{
				return attribute.getNodeValue();
			}
		}
		return null;
	}

	/**
	 * Get element's attribute value or <code>null</code> if attribute not found or empty.
	 *
	 * @param element
	 *           the element
	 * @param name
	 *           the name
	 * @return <code>String</code>
	 */
	public static String getAttributeValue(final Element element, final String name)
	{
		String value = element.getAttribute(name);
		if (value.length() == 0)
		{
			value = null;
		}
		return value;
	}

	/**
	 * Filters node list by keeping nodes of specified type.
	 *
	 * @param nodeList
	 *           the node list
	 * @param keepNodeType
	 *           the keep node type
	 * @return <code>List</code>
	 */
	public static List<Node> filterNodeList(final NodeList nodeList, final short keepNodeType)
	{
		return filterNodeList(nodeList, keepNodeType, null);
	}

	/**
	 * Filters node list by keeping nodes of specified type and node name.
	 *
	 * @param nodeList
	 *           the node list
	 * @param keepNodeType
	 *           the keep node type
	 * @param nodeName
	 *           the node name
	 * @return <code>List</code>
	 */
	public static List<Node> filterNodeList(final NodeList nodeList, final short keepNodeType, final String nodeName)
	{
		final List<Node> nodes = new ArrayList<>();
		for (int k = 0; k < nodeList.getLength(); k++)
		{
			final Node node = nodeList.item(k);
			if (node.getNodeType() != keepNodeType)
			{
				continue;
			}
			if (nodeName != null && (node.getNodeName().equals(nodeName) == false))
			{
				continue;
			}
			nodes.add(node);
		}
		return nodes;
	}

	/**
	 * Filter node list for all Element nodes.
	 *
	 * @param nodeList
	 *           the node list
	 * @return <code>List</code>
	 */
	public static List<Node> filterNodeListElements(final NodeList nodeList)
	{
		return filterNodeListElements(nodeList, null);
	}

	/**
	 * Filter node list for Element nodes of specified name.
	 *
	 * @param nodeList
	 *           the node list
	 * @param nodeName
	 *           the node name
	 * @return the list
	 */
	public static List<Node> filterNodeListElements(final NodeList nodeList, final String nodeName)
	{
		final List<Node> nodes = new ArrayList<>();
		for (int k = 0; k < nodeList.getLength(); k++)
		{
			final Node node = nodeList.item(k);
			if (node.getNodeType() != Node.ELEMENT_NODE)
			{
				continue;
			}
			if (nodeName != null && (node.getNodeName().equals(nodeName) == false))
			{
				continue;
			}
			nodes.add(node);
		}
		return nodes;
	}

	/**
	 * Returns a list of all child Elements,.
	 *
	 * @param node
	 *           the node
	 * @return <code>List</code>
	 */
	public static List<Node> getChildElements(final Node node)
	{
		return getChildElements(node, null);
	}

	/**
	 * Returns a list of child Elements of specified name.
	 *
	 * @param node
	 *           the node
	 * @param nodeName
	 *           the node name
	 * @return <code>List</code>
	 */
	public static List<Node> getChildElements(final Node node, final String nodeName)
	{
		final NodeList childs = node.getChildNodes();
		return filterNodeListElements(childs, nodeName);
	}

	/**
	 * Returns value of first available child text node or <code>null</code> if not found.
	 *
	 * @param node
	 *           the node
	 * @return <code>String</code>
	 */
	public static String getFirstChildTextNodeValue(final Node node)
	{
		final NodeList children = node.getChildNodes();
		final int len = children.getLength();
		for (int i = 0; i < len; i++)
		{
			final Node n = children.item(i);
			if (n.getNodeType() == Node.TEXT_NODE)
			{
				return n.getNodeValue();
			}
		}
		return null;
	}

	/**
	 * Returns value of single child text node or <code>null</code>.
	 *
	 * @param node
	 *           the node
	 * @return <code>String</code>
	 */
	public static String getChildTextNodeValue(final Node node)
	{
		if (node.getChildNodes().getLength() != 1)
		{
			return null;
		}
		final Node item0 = node.getChildNodes().item(0);
		if (item0.getNodeType() != Node.TEXT_NODE)
		{
			return null;
		}
		return item0.getNodeValue();
	}

	/**
	 * Returns XML DOM as string.
	 *
	 * @param document
	 *           Document
	 * @return String XML DOM as string
	 * @throws Exception
	 *            the exception
	 */
	public static String xmlDOMToString(final Document document) throws Exception
	{
		// Set up a transformer
		final TransformerFactory transfac = TransformerFactory.newInstance();
		final Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "no");

		// Create string from xml tree
		final StringWriter sw = new StringWriter();
		final StreamResult result = new StreamResult(sw);
		final DOMSource source = new DOMSource(document);
		trans.transform(source, result);

		return sw.toString();
	}

	/**
	 * Load string into XML DOM.
	 *
	 * @param xml
	 *           the xml
	 * @return Document XML string as XMLDOM
	 * @throws IOException
	 *            Signals that an I/O exception has occurred.
	 * @throws SAXException
	 *            the SAX exception
	 * @throws ParserConfigurationException
	 *            the parser configuration exception
	 */
	public static Document stringToXMLDOM(final String xml) throws IOException, SAXException, ParserConfigurationException
	{
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document doc = docBuilder.parse(new InputSource(new StringReader(xml)));

		return doc;
	}

}
