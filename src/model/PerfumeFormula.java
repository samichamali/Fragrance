package model;

import java.util.HashMap;
import java.util.Map;

public class PerfumeFormula {
    private String formulaName;
    // Providese the ingredients an exact amount in milliliters (ml) for the formula
    private Map<Ingredient, Double> components;

    public PerfumeFormula(String formulaName) {
        this.formulaName = formulaName;
        this.components = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient, double amountInMl) {
        this.components.put(ingredient, this.components.getOrDefault(ingredient, 0.0) + amountInMl);
    }

    // cost calculation (polymorphic iteration
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Map.Entry<Ingredient, Double> entry : components.entrySet()) {
            totalCost += entry.getKey().getCostPerMl() * entry.getValue();
        }
        return totalCost;
    }

    public void displayFormulaDetails() {
        System.out.println("\n--- Formula: " + formulaName + " ---");
        System.out.printf("%-20s | %-6s | %-8s | %s\n", "Ingredient Name", "Layer", "Qty (ml)", "Safety/Regulatory Guidelines");
        System.out.println("---------------------------------------------------------------------------");

        for (Map.Entry<Ingredient, Double> entry : components.entrySet()) {
            Ingredient ing = entry.getKey();
            System.out.printf("%-20s | %-6s | %-8.2f | %s\n",
                    ing.getName(), ing.getNoteType(), entry.getValue(), ing.getRegulatoryNotes());
        }
        System.out.printf("\nTotal Raw Batch Production Cost: €%.2f\n", calculateTotalCost());
    }
    public Map<Ingredient, Double> getComponents() {
        return this.components;
    }

    public String getFormulaName() {
        return this.formulaName;
    }
}

