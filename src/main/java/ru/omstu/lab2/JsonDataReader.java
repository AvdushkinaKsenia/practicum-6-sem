package ru.omstu.lab2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class JsonDataReader implements DataReader {

    public String readField(String filePath, String fieldPath) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        JsonNode node = mapper.readTree(is);
        String[] parts = fieldPath.split("/");

        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            if (part.startsWith("[")) {
                int index = Integer.parseInt(part.replace("[", "").replace("]", ""));
                node = node.get(index);
            } else {
                node = node.get(part);
            }
        }

        return node.asText();
    }
}