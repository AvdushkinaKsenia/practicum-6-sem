package ru.omstu.lab2;

import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.io.InputStream;

public class XmlDataReader implements DataReader {

    @Override
    public String readField(String filePath, String fieldPath) throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);

        Node currentNode = document.getDocumentElement();
        String[] parts = fieldPath.split("/");

        for (String part : parts) {
            if (part.isEmpty()) continue;

            if (part.startsWith("[") && part.endsWith("]")) {
                int index = Integer.parseInt(part.substring(1, part.length() - 1));
                currentNode = currentNode.getChildNodes().item(index);
            } else {
                NodeList children = currentNode.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    if (children.item(i).getNodeName().equals(part)) {
                        currentNode = children.item(i);
                        break;
                    }
                }
            }
        }

        return currentNode.getTextContent();
    }
}