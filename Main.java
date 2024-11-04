import java.util.Scanner;

// A class to hold all the order entries
class BouquetOrdered {
    private TypeOfFlower flowerType;
    private ColorOfFlower flowerColor;
    private SizeOfFlower flowerSize;
    private double totalPrice;

    public BouquetOrdered(TypeOfFlower flowerType, ColorOfFlower flowerColor, SizeOfFlower flowerSize, double totalPrice) {
        this.flowerType = flowerType;
        this.flowerColor = flowerColor;
        this.flowerSize = flowerSize;
        this.totalPrice = totalPrice;
    }

    // Getters for private members of the BouquetOrdered
    public TypeOfFlower getFlowerType() {
        return flowerType;
    }

    public ColorOfFlower getFlowerColor() {
        return flowerColor;
    }

    public SizeOfFlower getFlowerSize() {
        return flowerSize;
    }

    public double getPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "BouquetOrdered{" +
                "flowerType=" + flowerType +
                ", flowerColor=" + flowerColor +
                ", flowerSize=" + flowerSize +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

public class Main {
    // Fixed arrays to hold the orders of BouquetOrdered
    private static final int maximumOrders = 100;
    private static BouquetOrdered[] orders = new BouquetOrdered[maximumOrders];
    private static int orderCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selectionInteger = 0;

        // Arrays to hold the frequency counts
        int[] flowerTypeCount = new int[TypeOfFlower.values().length];
        int[] flowerColorCount = new int[ColorOfFlower.values().length];
        int[] flowerSizeCount = new int[SizeOfFlower.values().length];

        // Variables for statistics summary calculation
        double minTotalPrice = Double.MAX_VALUE;
        double maxTotalPrice = Double.MIN_VALUE;
        double sumTotalPrice = 0;

        // Using the do-while condition statement
        do {
            System.out.println("Welcome! Our Flower Shop Menu:");
            System.out.println("1. Order bouquet and get the totalPrice");
            System.out.println("2. Display statistics");
            System.out.println("3. Exit");

            // Validating user input
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter your selection: ");
                if (scanner.hasNextInt()) {
                    selectionInteger = scanner.nextInt();
                    scanner.nextLine(); // to allow for a line break for a string input, since the initial user input was for integers only.

                    if (selectionInteger >= 1 && selectionInteger <= 3) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // to allow for a new valid input
                }
            }

            switch (selectionInteger) {
                case 1: // when option 1 is entered
                   
                    try {
                        System.out.println("Choose Flower Type (ROSE, LILY, CARNATIONS, DAFFODIL, GERBERA, CHRYSANTHEMUM, ASSORTED):");
                        String flowerTypeValue = scanner.nextLine().toUpperCase();
                        TypeOfFlower flowerType = TypeOfFlower.valueOf(flowerTypeValue);
    
                        System.out.println("Choose Flower Color (WHITE, RED, PINK, YELLOW, BLUE, ASSORTED):");
                        String flowerColorValue = scanner.nextLine().toUpperCase();
                        ColorOfFlower flowerColor = ColorOfFlower.valueOf(flowerColorValue);
    
                        System.out.println("Choose Bouquet Size (SMALL, MEDIUM, LARGE):");
                        String flowerSizeValue = scanner.nextLine().toUpperCase();
                        SizeOfFlower flowerSize = SizeOfFlower.valueOf(flowerSizeValue);
    

                        double totalPrice = orderDetailsAndPriceCalculation(flowerType, flowerColor, flowerSize);
                        System.out.println("Order Price and calculation is: " + totalPrice);

                        // using the if statement to record orders
                        if (orderCount < maximumOrders) {
                            BouquetOrdered order = new BouquetOrdered(flowerType, flowerColor, flowerSize, totalPrice);
                            orders[orderCount++] = order;

                            // to update frequency counts
                            flowerTypeCount[flowerType.ordinal()]++;
                            flowerColorCount[flowerColor.ordinal()]++;
                            flowerSizeCount[flowerSize.ordinal()]++;

                            // to update statistics of Min, Max, and total sum
                            if (totalPrice < minTotalPrice) minTotalPrice = totalPrice;
                            if (totalPrice > maxTotalPrice) maxTotalPrice = totalPrice;
                            sumTotalPrice += totalPrice;
                        } else {
                            System.out.println("Maximum order limit reached.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                    break;

                case 2: // when option 2 is entered
                    System.out.println("Summary Statistics Provided");

                    // Print orders
                    for (int i = 0; i < orderCount; i++) {
                        System.out.println(orders[i]);
                    }

                    // Print frequency counts
                    System.out.println("Flower Type Frequency:");
                    for (TypeOfFlower type : TypeOfFlower.values()) {
                        System.out.println(type + ": " + flowerTypeCount[type.ordinal()]);
                    }

                    System.out.println("Flower Color Frequency:");
                    for (ColorOfFlower color : ColorOfFlower.values()) {
                        System.out.println(color + ": " + flowerColorCount[color.ordinal()]);
                    }

                    System.out.println("Flower Size Frequency:");
                    for (SizeOfFlower size : SizeOfFlower.values()) {
                        System.out.println(size + ": " + flowerSizeCount[size.ordinal()]);
                    }

                    // Print Summary statistics
                    double averageTotalPrice = sumTotalPrice / orderCount;
                    double totalRange = maxTotalPrice - minTotalPrice;
                    System.out.println("Minimum Total Price: " + minTotalPrice);
                    System.out.println("Maximum Total Price: " + maxTotalPrice);
                    System.out.println("Range of Total Price: " + totalRange);
                    System.out.println("Total Count of Bouquets Ordered: " + orderCount);
                    System.out.println("Total Price of All Bouquets: " + sumTotalPrice);
                    System.out.println("Average Price of Bouquet: " + averageTotalPrice);
                    break;

                case 3: // when option 3 is entered
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (selectionInteger != 3);

        scanner.close();
    }

    // Method to calculate the value based on flower type, flower color, and bouquet size
    public static double orderDetailsAndPriceCalculation(TypeOfFlower flowerType, ColorOfFlower flowerColor, SizeOfFlower flowerSize) {
        double flowerTypeValue = flowerType.getValue();
        double flowerColorValue = flowerColor.getValue();
        double flowerSizeValue = flowerSize.getValue();
        return (flowerTypeValue + flowerColorValue) * flowerSizeValue;
    }
}

// Enum for flower types with corresponding values
enum TypeOfFlower {
    ROSE, LILY, CARNATIONS, DAFFODIL, GERBERA, CHRYSANTHEMUM, ASSORTED;

    public double getValue() {
        switch (this) {
            case ROSE: return 1.2;
            case LILY: return 1.3;
            case CARNATIONS: return 1.0;
            case DAFFODIL: return 1.0;
            case GERBERA: return 1.1;
            case CHRYSANTHEMUM: return 1.1;
            case ASSORTED: return 0.8;
            default: throw new IllegalArgumentException("invalid entry, please try again");
        }
    }
}

// Enum for flower colors with corresponding values
enum ColorOfFlower {
    WHITE, RED, PINK, YELLOW, BLUE, ASSORTED;

    public double getValue() {
        switch (this) {
            case WHITE: return 1.3;
            case RED: return 1.2;
            case PINK: return 1.1;
            case YELLOW: return 1.1;
            case BLUE: return 1.2;
            case ASSORTED: return 1.0;
            default: throw new IllegalArgumentException("invalid entry, please try again");
        }
    }
}

// Enum for bouquet sizes with corresponding values
enum SizeOfFlower {
    SMALL, MEDIUM, LARGE;

    public double getValue() {
        switch (this) {
            case SMALL: return 5.5;
            case MEDIUM: return 7.5;
            case LARGE: return 9.5;
            default: throw new IllegalArgumentException("invalid entry, please try again");
        }
    }
}





      

            
        


    


