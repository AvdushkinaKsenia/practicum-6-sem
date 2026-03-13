package ru.omstu.lab2;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;

public class XmlDataReader implements DataReader {

    public String readField(String filePath, String fieldPath) throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(is);
        Node node = document.getDocumentElement();
        String[] parts = fieldPath.split("/");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (part.isEmpty()) {
                continue;
            }

            NodeList list = ((Element) node).getElementsByTagName(part);

            if (i + 1 < parts.length && parts[i + 1].startsWith("[")) {

                int index = Integer.parseInt(parts[i + 1].replace("[", "").replace("]", ""));
                node = list.item(index);
                i++;

            } else {
                node = list.item(0);
            }
        }

        return node.getTextContent();
    }
}