package ru.omstu.lab2;

public interface DataReader {
    String readField(String filePath, String fieldPath) throws Exception;
}