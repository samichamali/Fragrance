package model;

public abstract class Ingredient {
    private String id;
    private String name;
    private String noteType;
    private double costPerMl;

    public Ingredient(String id, String name, String noteType, double costPerMl) {
        this.id = id;
        this.name = name;
        this.noteType = noteType;
        this.costPerMl = costPerMl;
    }

    //  method to get overridden by ingredient types (polymorphism)
    public abstract String getRegulatoryNotes();

    // getters and setters (encapsulation)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getNoteType() { return noteType; }
    public double getCostPerMl() { return costPerMl; }

    public void setCostPerMl(double costPerMl) { this.costPerMl = costPerMl; }
}