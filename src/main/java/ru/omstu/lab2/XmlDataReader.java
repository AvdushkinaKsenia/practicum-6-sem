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

        for (int i = 0; i < parts.length; i++) {

            String part = parts[i];
            if (part.isEmpty()) continue;

            if (i + 1 < parts.length && parts[i + 1].startsWith("[")) {

                int index = Integer.parseInt(parts[i + 1]
                        .substring(1, parts[i + 1].length() - 1));

                NodeList list = document.getElementsByTagName(part);

                currentNode = list.item(index);

                i++;

            } else {

                NodeList children = currentNode.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {

                    Node child = children.item(j);

                    if (child.getNodeType() == Node.ELEMENT_NODE &&
                            child.getNodeName().equals(part)) {

                        currentNode = child;
                        break;
                    }
                }
            }
        }

        return currentNode.getTextContent();
    }
}