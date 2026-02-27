package ru.omstu.lab2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader implements DataReader {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String readField(String filePath, String fieldPath) throws Exception {

        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        JsonNode currentNode = mapper.readTree(is);

        String[] parts = fieldPath.split("/");

        for (String part : parts) {
            if (part.isEmpty()) continue;

            if (part.startsWith("[") && part.endsWith("]")) {
                int index = Integer.parseInt(part.substring(1, part.length() - 1));
                currentNode = currentNode.get(index);
            } else {
                currentNode = currentNode.get(part);
            }
        }

        return currentNode.asText();
    }
}