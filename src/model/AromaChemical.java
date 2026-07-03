package model;

public class AromaChemical extends Ingredient {
    private double maxSafePercentage; // IFRA regulatory safety limit

    public AromaChemical(String id, String name, String noteType, double costPerMl, double maxSafePercentage) {
        super(id, name, noteType, costPerMl);
        this.maxSafePercentage = maxSafePercentage;
    }

    @Override
    public String getRegulatoryNotes() {
        return "Synthetic Molecule. PLEASE ADHERE to the IFRA restriction limit of " + maxSafePercentage + "%.";
    }
}