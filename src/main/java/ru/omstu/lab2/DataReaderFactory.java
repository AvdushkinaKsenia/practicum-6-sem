package ru.omstu.lab2;

public class DataReaderFactory {

    public static DataReader getReader(String filePath) {
        if (filePath.endsWith(".json")) {
            return new JsonDataReader();
        } else if (filePath.endsWith(".xml")) {
            return new XmlDataReader();
        }
        throw new IllegalArgumentException("Unsupported file format");
    }
}