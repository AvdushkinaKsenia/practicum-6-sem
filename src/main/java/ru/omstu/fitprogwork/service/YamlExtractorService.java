package ru.omstu.fitprogwork.service;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

@Service("yaml")
public class YamlExtractorService implements DataExtractorService {

    public String extract(String data, String path) {

        Yaml yaml = new Yaml();
        Object obj = yaml.load(data);

        String[] parts = path.split("/");

        for (String part : parts) {

            if (part.isEmpty()) continue;

            if (part.startsWith("[")) {
                int index = Integer.parseInt(part.replace("[", "").replace("]", ""));
                obj = ((List<?>) obj).get(index);
            } else {
                obj = ((Map<?, ?>) obj).get(part);
            }
        }

        return obj.toString();
    }
}