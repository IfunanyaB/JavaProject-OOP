import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    //  arrays for flower types, colors, sizes, and their corresponding prices
    private static String flowerType[] = {"ROSE", "LILY", "CARNATIONS", "DAFFODIL", "GERBERA", "CHRYSANTHEMUM", "ASSORTED"};
    private static double flowerTypePrices[] = {1.2, 1.3, 1.0, 1.0, 1.1, 1.1, 0.8};

    private static String flowerColor[] = {"WHITE", "RED", "PINK/SALMON", "YELLOW", "BLUE", "ASSORTED"};
    private static double flowerColorPrices[] = {1.3, 1.2, 1.1, 1.1, 1.2, 1.0};

    private static String flowerSize[] = {"SMALL", "MEDIUM", "LARGE"};
    private static double flowerSizePrices[] = {5.5, 7.5, 9.5};

    // Arrays to store order data 
    private static int[] orderFlowerTypes = new int[1000000];  // Index for flower types
    private static int[] orderFlowerColors = new int[1000000]; // Index for flower colors
    private static int[] orderFlowerSizes = new int[1000000];  // Index for flower sizes
    private static double[] orderPrices = new double[1000000]; // Prices for each order
    private static int orderCount = 0;  // to count the Total number of orders

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0; // variable to get user input
        boolean validInput; //control variable for the looping
            
        //applying the do..while loop 
        do {
            System.out.println("    "); //to create spaces in the text displays
            System.out.println("Welcome to our Flower Shop");
            System.out.println("Flower Menu");
            System.out.println("    ");
            System.out.println("1. Order Bouquet");
            System.out.println("2. Display Summary Statistics");
            System.out.println("3. Exit");
            System.out.println("    "); //to create spaces in the text displays

            validInput = false;
            while (!validInput) {
                System.out.print("Enter a number on the menu (1, 2, or 3): ");
                try {
                    userInput = scanner.nextInt();
                    validInput = true;
                    //to catch any non numerical input
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input, Please enter an integer.");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            switch (userInput) {
                case 1:
                    orderDetailsAndPriceCalculation();
                    break;
                case 2:
                    summaryStatistics();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input, please enter 1, 2, or 3.");
            }
        } while (userInput != 3);
        scanner.close();
    }

    // Method for Order details and price calculation
    public static void orderDetailsAndPriceCalculation() {
        Scanner newInput = new Scanner(System.in);
        boolean validInput = false;
        int flowerTypeIndex = 0, flowerColorIndex = 0, flowerSizeIndex = 0;

        // Flower Type Selection
        while (!validInput) {
            System.out.println("Choose Flower Type:");
            for (int i = 0; i < flowerType.length; i++) {
                System.out.println((i + 1) + ". " + flowerType[i]);
            }
            System.out.print("Enter a number: ");
            try {
                flowerTypeIndex = newInput.nextInt() - 1;
                if (flowerTypeIndex >= 0 && flowerTypeIndex < flowerType.length) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a valid number.");
                } //to catch any non numerical input
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                newInput.nextLine(); // Clear invalid input
            }
        }

        // Flower Color Selection
        validInput = false; //resetting the loop control variable
        while (!validInput) {
            System.out.println("Choose Flower Color:");
            for (int i = 0; i < flowerColor.length; i++) {
                System.out.println((i + 1) + ". " + flowerColor[i]);
            }
            System.out.print("Enter a number: ");
            try {
                flowerColorIndex = newInput.nextInt() - 1;
                if (flowerColorIndex >= 0 && flowerColorIndex < flowerColor.length) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a valid number.");
                } //to catch any non numerical input
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                newInput.nextLine(); // Clear invalid input
            }
        }

        // Flower Size Selection
        validInput = false;  //resetting the loop control variable
        while (!validInput) {
            System.out.println("Choose Flower Size:");
            for (int i = 0; i < flowerSize.length; i++) {
                System.out.println((i + 1) + ". " + flowerSize[i]);
            }
            System.out.print("Enter a number: ");
            try {
                flowerSizeIndex = newInput.nextInt() - 1;
                if (flowerSizeIndex >= 0 && flowerSizeIndex < flowerSize.length) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input, please enter a valid number.");
                }  //to catch any non numerical input
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                newInput.nextLine(); // Clear invalid input
            }
        }

        // Price calculation
        double price = (flowerTypePrices[flowerTypeIndex] + flowerColorPrices[flowerColorIndex]) * flowerSizePrices[flowerSizeIndex];
        System.out.println("    "); //to create spaces in the text displays
        System.out.printf("You chose %s in color %s, %s size. Total Price: %.2f\n", flowerType[flowerTypeIndex], flowerColor[flowerColorIndex], flowerSize[flowerSizeIndex], price);

        // Store the order details
        orderFlowerTypes[orderCount] = flowerTypeIndex;
        orderFlowerColors[orderCount] = flowerColorIndex;
        orderFlowerSizes[orderCount] = flowerSizeIndex;
        orderPrices[orderCount] = price;
        orderCount++;

        System.out.println("Thank you for your purchase! Your bouquet has been ordered.");
        System.out.println("    "); //to create spaces in the text displays
    }

    // Method for displaying summary statistics
    public static void summaryStatistics() {
        if (orderCount == 0) {
            System.out.println("No orders placed yet.");
            System.out.println("    "); //to create spaces in the text displays
            return;
        }

        double minPrice = orderPrices[0];
        double maxPrice = orderPrices[0];
        double totalPrice = 0;

        // Calculate minimum, maximum, and total prices
        for (int i = 0; i < orderCount; i++) {
            if (orderPrices[i] < minPrice) {
                minPrice = orderPrices[i];
            }
            if (orderPrices[i] > maxPrice) {
                maxPrice = orderPrices[i];
            }
            totalPrice += orderPrices[i];
        }

        // Frequency count for flower types
        int[] flowerTypeCount = new int[flowerType.length];
        for (int i = 0; i < orderCount; i++) {
            flowerTypeCount[orderFlowerTypes[i]]++;
        }

        // to Display statistics
        System.out.println("    "); //to create spaces in the text displays
        System.out.println("Summary Statistics:");
        System.out.println("    "); //to create spaces in the text displays
        System.out.println("Minimum Price: " + minPrice);
        System.out.println("Maximum Price: " + maxPrice);
        System.out.println("Range of Price: " +  (maxPrice - minPrice) );
        System.out.println("Total Number of Bouquets Ordered: " + orderCount);
        System.out.println("Average Price: " + (totalPrice / orderCount));

        // Display frequency of each flower type
        System.out.println("Flower Type Frequency:");
        for (int i = 0; i < flowerType.length; i++) {
            if (flowerTypeCount[i] > 0) {
                System.out.println(flowerType[i] + ": " + flowerTypeCount[i]);
            }
        }
        System.out.println("    ");  //to create spaces in the text displays
    }
}
