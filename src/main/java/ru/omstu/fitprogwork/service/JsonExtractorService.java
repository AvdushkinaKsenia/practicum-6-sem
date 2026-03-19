package ru.omstu.fitprogwork.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service("json")
public class JsonExtractorService implements DataExtractorService {

    public String extract(String data, String path) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(data);

        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty()) continue;

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