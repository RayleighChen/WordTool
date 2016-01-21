package com.rayleigh.nina.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rayleigh.nina.bean.XmlBean;

public class XmlUtil {
	private DocumentBuilderFactory factory = null;
	private Document xmldoc;
	private Element theNode = null, theElem = null, root = null;
	private XmlBean xmlBean;

	public XmlUtil(XmlBean xmlBean) {
		this.xmlBean = xmlBean;
		factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringElementContentWhitespace(true);
		try {
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder db = factory.newDocumentBuilder();
			xmldoc = db.parse(new File(xmlBean.getFilePath()));
			root = xmldoc.getDocumentElement();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���ö�Ӧ�����Ľڵ�
	public void getIndexOfBean(int index) {
		NodeList nodeList = xmldoc.getElementsByTagName(xmlBean.getRootNode());
		HashMap<String, String> resultSet = new HashMap<String, String>();

		if (nodeList != null) {
			if (index <= nodeList.getLength()) {
				Node node = nodeList.item(index);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;

					HashMap<String, String> tag_value = xmlBean
							.getTagAndValue();
					Iterator<Entry<String, String>> itor = tag_value.entrySet()
							.iterator();
					while (itor.hasNext()) {
						Entry<String, String> tv = itor.next();
						resultSet.put(tv.getKey().toString(), elem
								.getElementsByTagName(tv.getKey().toString())
								.item(0).getTextContent());
					}
					xmlBean.setTagAndValue(resultSet);
				}
			}
		}
	}

	// ��ȡ���нڵ��������ں��������ڵ�
	public int getNumOfNode() {
		NodeList nodes = selectNodes(xmlBean.getRootNode(), root);
		if (nodes != null) {
			return nodes.getLength();
		}
		return -1;
	}

	// xml����ӽڵ�
	public void addMsg() {
		theNode = xmldoc.createElement(xmlBean.getRootNode());

		HashMap<String, String> tag_value = xmlBean.getTagAndValue();
		Iterator<Entry<String, String>> itor = tag_value.entrySet().iterator();
		while (itor.hasNext()) {
			Entry<String, String> tv = itor.next();

			theElem = xmldoc.createElement(tv.getKey());
			theElem.setTextContent(tv.getValue());
			theNode.appendChild(theElem);
		}
		root.appendChild(theNode);

		// output(xmldoc);
		saveXml(xmlBean.getFilePath(), xmldoc);
	}

	// xml���޸Ľڵ�
	public void modifyMsg() {
		theNode = (Element) selectSingleNode(xmlBean.getSelectString(), root);
		if (theNode != null) {
			HashMap<String, String> tag_value = xmlBean.getTagAndValue();
			Iterator<Entry<String, String>> itor = tag_value.entrySet()
					.iterator();
			while (itor.hasNext()) {
				Entry<String, String> tv = itor.next();

				theNode.getElementsByTagName(tv.getKey()).item(0)
						.setTextContent(tv.getValue());
			}
		}
		// output(xmldoc);
		saveXml(xmlBean.getFilePath(), xmldoc);
	}

	// xmlɾ���ڵ�
	public void delMsg() {
		theNode = (Element) selectSingleNode(xmlBean.getSelectString(), root);
		// �ڵ����
		if (theNode != null) {
			theNode.getParentNode().removeChild(theNode);
		}
		// output(xmldoc);
		// ����XML�����ļ�
		saveXml(xmlBean.getFilePath(), xmldoc);
	}

	// ���� �˴���key�� ÿ��XmlBean��selectStringһֱ
	public void SehMsg(String key) {
		for (int i = 0; i < getNumOfNode(); ++i) {
			getIndexOfBean(i);
			if (xmlBean.getSelectString().equals(key)) {
				return;
			}
		}
		System.out.println("�Ҳ�������Ϣ��");
		return;
	}

	// ���ҽڵ㣬�����ص�һ�����������ڵ�
	public static Node selectSingleNode(String express, Object source) {
		Node result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (Node) xpath
					.evaluate(express, source, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}

	// ���ҽڵ㣬���ط��������Ľڵ㼯��
	public static NodeList selectNodes(String express, Object source) {
		NodeList result = null;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		try {
			result = (NodeList) xpath.evaluate(express, source,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return result;
	}

	// ��node��XML�ַ������������̨
	public static void output(Node node) {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("encoding", "gbk");
			transformer.setOutputProperty("indent", "yes");

			DOMSource source = new DOMSource();
			source.setNode(node);
			StreamResult result = new StreamResult();
			result.setOutputStream(System.out);

			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	// ��Document������ļ�
	public static void saveXml(String fileName, Document doc) {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("encoding", "gbk");
			transformer.setOutputProperty("indent", "yes");

			DOMSource source = new DOMSource();
			source.setNode(doc);
			StreamResult result = new StreamResult();
			result.setOutputStream(new FileOutputStream(fileName));

			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}