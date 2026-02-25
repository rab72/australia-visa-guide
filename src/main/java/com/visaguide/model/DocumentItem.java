package com.visaguide.model;

public class DocumentItem {
    private String name;
    private String description;
    private boolean mandatory;

    public DocumentItem(String name, String description, boolean mandatory) {
        this.name = name;
        this.description = description;
        this.mandatory = mandatory;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isMandatory() { return mandatory; }
}
