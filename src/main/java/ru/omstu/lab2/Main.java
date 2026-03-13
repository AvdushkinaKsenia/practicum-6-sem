package ru.omstu.lab2;

public class Main {

    public static void main(String[] args) throws Exception {

        String jsonFile = "data.json";
        String xmlFile = "data.xml";

        DataReader jsonReader = DataReaderFactory.getReader(jsonFile);
        DataReader xmlReader = DataReaderFactory.getReader(xmlFile);

        System.out.println(jsonReader.readField(jsonFile, "/name"));
        System.out.println(jsonReader.readField(jsonFile, "/relation/[1]/name"));

        System.out.println(xmlReader.readField(xmlFile, "/name"));
        System.out.println(xmlReader.readField(xmlFile, "/relation/[1]/name"));
    }
}