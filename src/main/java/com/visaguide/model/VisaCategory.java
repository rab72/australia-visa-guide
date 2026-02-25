package com.visaguide.model;

import java.util.List;

public class VisaCategory {
    private String id;
    private String subclass;
    private String name;
    private String category;
    private String categoryIcon;
    private String description;
    private String processingTime;
    private String validity;
    private String cost;
    private List<DocumentItem> documents;
    private List<String> eligibilityPoints;
    private List<String> notes;

    public VisaCategory(String id, String subclass, String name, String category,
                        String categoryIcon, String description, String processingTime,
                        String validity, String cost,
                        List<DocumentItem> documents,
                        List<String> eligibilityPoints,
                        List<String> notes) {
        this.id = id;
        this.subclass = subclass;
        this.name = name;
        this.category = category;
        this.categoryIcon = categoryIcon;
        this.description = description;
        this.processingTime = processingTime;
        this.validity = validity;
        this.cost = cost;
        this.documents = documents;
        this.eligibilityPoints = eligibilityPoints;
        this.notes = notes;
    }

    public String getId() { return id; }
    public String getSubclass() { return subclass; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getCategoryIcon() { return categoryIcon; }
    public String getDescription() { return description; }
    public String getProcessingTime() { return processingTime; }
    public String getValidity() { return validity; }
    public String getCost() { return cost; }
    public List<DocumentItem> getDocuments() { return documents; }
    public List<String> getEligibilityPoints() { return eligibilityPoints; }
    public List<String> getNotes() { return notes; }

    public long getMandatoryCount() {
        return documents.stream().filter(DocumentItem::isMandatory).count();
    }

    public long getOptionalCount() {
        return documents.stream().filter(d -> !d.isMandatory()).count();
    }
}
