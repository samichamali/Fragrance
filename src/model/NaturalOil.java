package model;

public class NaturalOil extends Ingredient {
    private String botanicalSource;
    private String originCountry;

    public NaturalOil(String id, String name, String noteType, double costPerMl, String botanicalSource, String originCountry) {
        super(id, name, noteType, costPerMl);
        this.botanicalSource = botanicalSource;
        this.originCountry = originCountry;
    }

    @Override
    public String getRegulatoryNotes() {
        return "Natural extract (" + botanicalSource + ") sourced from " + originCountry + ". Monitor batch allergens.";
    }
}