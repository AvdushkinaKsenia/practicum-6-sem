package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;

@Service("xml")
public class XmlExtractorService implements DataExtractorService {

    public String extract(String data, String path) throws Exception {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(data.getBytes()));

        Node node = document.getDocumentElement();
        String[] parts = path.split("/");

        for (int i = 0; i < parts.length; i++) {

            String part = parts[i];
            if (part.isEmpty()) continue;

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