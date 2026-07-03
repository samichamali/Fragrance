// import  PACKAGES
package service;
import model.Ingredient;
import model.PerfumeFormula;
import java.util.HashMap;
import java.util.Map;




public class InventoryManager {
    // maps each  ingredient to its current stock amount in ml
    private Map<Ingredient, Double> stock;
    private static final double LOW_STOCK_THRESHOLD = 20.0;

    public InventoryManager() {
        this.stock = new HashMap<>();
    }

    // Add raw materials to inventory (restocking)
    public void restock(Ingredient ingredient, double amountInMl) {
        double currentStock = stock.getOrDefault(ingredient, 0.0);
        stock.put(ingredient, currentStock + amountInMl);
        System.out.println("Restocked: " + ingredient.getName() + " +" + amountInMl + "ml (Total: " + stock.get(ingredient) + "ml)");
    }

    // Check if enough ingredients are present in order to fulfill a perfume formula batch  amount
    public boolean verifyAndDeductStock(PerfumeFormula formula, double batchSizeMultiplier) {
        //Verify if we have enough of EVERYTHING
        for (Map.Entry<Ingredient, Double> entry : formula.getComponents().entrySet()) {
            Ingredient requiredIng = entry.getKey();
            double requiredAmount = entry.getValue() * batchSizeMultiplier;
            double availableAmount = stock.getOrDefault(requiredIng, 0.0);

            if (availableAmount < requiredAmount) {
                System.out.println("PRODUCTION FAILED: Not Enough stock for " + requiredIng.getName() +
                        ". What's Required: " + requiredAmount + "ml, What's present: " + availableAmount + "ml");
                return false;
            }
        }
        //remove from the stock because we verified we have enough of it
        System.out.println("\nMANUFACTURING " + formula.getFormulaName() + "...");
        for (Map.Entry<Ingredient, Double> entry : formula.getComponents().entrySet()) {
            Ingredient requiredIng = entry.getKey();
            double requiredAmount = entry.getValue() * batchSizeMultiplier;
            double currentStock = stock.get(requiredIng);

            double newStock = currentStock - requiredAmount;
            stock.put(requiredIng, newStock);

            System.out.printf("  - Used %.2fml of %s. Stock left: %.2fml\n", requiredAmount, requiredIng.getName(), newStock);

            // alert in cases of low stock amounts
            if (newStock < LOW_STOCK_THRESHOLD) {
                System.out.println("ALERT: " + requiredIng.getName() + " IS RUNNING LOW!");
            }
        }
        return true;
    }
    // show current inventory status and amounts (important for monitoring )
    public void displayInventoryReport() {
        System.out.println("\n=================== INVENTORY ===================");
        System.out.printf("%-6s | %-20s | %-10s | %s\n", "ID", "Ingredient Name", "Stock (ml)", "Status");
        System.out.println("---------------------------------------------------");
        for (Map.Entry<Ingredient, Double> entry : stock.entrySet()) {
            Ingredient ing = entry.getKey();
            double qty = entry.getValue();
            String status = (qty < LOW_STOCK_THRESHOLD) ? "LOW STOCK" : "OK";
            System.out.printf("%-6s | %-20s | %-10.2f | %s\n", ing.getId(), ing.getName(), qty, status);
        }
        System.out.println("===================================================");
    }}