package ru.omstu.fitprogwork.dto;

public class RequestDto {
    private String type;
    private String data;
    private String path;

    public String getType() { return type; }
    public String getData() { return data; }
    public String getPath() { return path; }

    public void setType(String type) { this.type = type; }
    public void setData(String data) { this.data = data; }
    public void setPath(String path) { this.path = path; }
}