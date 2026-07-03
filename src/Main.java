// Author: Sami Chamali
// Student Number: GH1033269
import model.*;
import service.InventoryManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();
        // predefine catalog ingredients (used for simulations) (we can customize these anytime)
        Ingredient bergamot = new NaturalOil("N01", "Bergamot Oil", "Top", 1.50, "Citrus bergamia", "Italy");
        Ingredient jasmine = new NaturalOil("N02", "Jasmine Absolute", "Heart", 4.20, "Jasminum grandiflorum", "Egypt");
        Ingredient isoESuper = new AromaChemical("S01", "Iso E Super", "Base", 0.65, 21.4);

        // place default stock amounts for the initiation of the  program
        inventory.restock(bergamot, 60.0);
        inventory.restock(jasmine, 30.0);
        inventory.restock(isoESuper, 100.0);

        System.out.println("\nWelcome to ScentStore: The Perfume Management System!");

        boolean running = true;
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. View Ingredient Stock Levels");
            System.out.println("2. Blend Signature Perfume (Midnight Velvet)");
            System.out.println("3. Restock an Ingredient");
            System.out.println("4. Exit Application");
            System.out.print("OPTION (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    inventory.displayInventoryReport();
                    break;

                case "2":
                    // Set up our formula recipe using the seeded ingredients
                    PerfumeFormula midnightVelvet = new PerfumeFormula("Midnight Velvet EdP");
                    midnightVelvet.addIngredient(bergamot, 5.0);   // 5ml REQUIRED
                    midnightVelvet.addIngredient(jasmine, 8.0);    // 8ml
                    midnightVelvet.addIngredient(isoESuper, 12.0); // 12ml

                    midnightVelvet.displayFormulaDetails();

                    System.out.print("\nEnter batch production scale factor multiplier (e.g., 1.0 for standard, 2.0 for double batch): ");
                    try {
                        double multiplier = Double.parseDouble(scanner.nextLine());
                        if (multiplier <= 0) {
                            System.out.println("MULTIPLIER MUST BE POSITIVE");
                        } else {
                            inventory.verifyAndDeductStock(midnightVelvet, multiplier);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("PLEASE ENTER VALID NUMBERS/DECIMALS");
                    }
                    break;

                case "3":
                    System.out.println("\nSelect an ingredient to add:");
                    System.out.println("1. Bergamot Oil");
                    System.out.println("2. Jasmine Absolute");
                    System.out.println("3. Iso E Super");
                    System.out.print("Selection: ");
                    String itemChoice = scanner.nextLine();

                    Ingredient selectedIngredient = null;
                    if (itemChoice.equals("1")) selectedIngredient = bergamot;
                    else if (itemChoice.equals("2")) selectedIngredient = jasmine;
                    else if (itemChoice.equals("3")) selectedIngredient = isoESuper;

                    if (selectedIngredient == null) {
                        System.out.println("INVALID SELECTION");
                        break;
                    }

                    System.out.print("How much would you like to add(ML): ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        if (amount <= 0) {
                            System.out.println("NEGATIVE AMOUNTS NOT ACCEPTED");
                        } else {
                            inventory.restock(selectedIngredient, amount);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("PLEASE ONLY ENTER A NUMBER.");
                    }
                    break;

                case "4":
                    System.out.println("Thanks for using ScentStore. bye!");
                    running = false;
                    break;

                default:
                    System.out.println("INVALID CHOICE, PLEASE CHOICE ONE OF THE OPTIONS ABOVE.");
            }
        }
        scanner.close();
    }
}