import java.util.Scanner;

class generate_bill {
    // Data members
    private int item_id;
    private String item_name;
    private int item_type;
    private int item_quantity;
    private double item_price;

    // Constructor
    public generate_bill(int item_id, String item_name, int item_type, int item_quantity, double item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
    }

    // Method to set details of an item
    public void setDetails(int item_id, String item_name, int item_type, int item_quantity, double item_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_quantity = item_quantity;
        this.item_price = item_price;
    }

    // Method to get item details (for debugging or information purposes)
    public void getDetails() {
        System.out.println("ID: " + item_id + ", Name: " + item_name + ", Type: " + item_type + 
                           ", Quantity: " + item_quantity + ", Price: " + item_price);
    }

    // Private method to calculate GST based on item type and price
    private double calculateTax() {
        double taxRate = 0;
        switch (item_type) {
            case 1:
                taxRate = 0.05;
                break;
            case 2:
                taxRate = 0.12;
                break;
            case 3:
                taxRate = 0.18;
                break;
            case 4:
                taxRate = 0.28;
                break;
            default:
                System.out.println("Invalid item type for item ID " + item_id);
        }
        return item_price * item_quantity * taxRate;
    }

    // Method to print the generate_bill with item details and calculated tax
    public void print_bill() {
        double tax = calculateTax();
        double total = (item_price * item_quantity) + tax;
        System.out.println("Item ID: " + item_id + " | Name: " + item_name + " | Type: " + item_type +
                           " | Quantity: " + item_quantity + " | Price per unit: " + item_price);
        System.out.printf("Tax: %.2f  | Total:%.2f ", tax,total);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user how many items to add to the generate_bill
        System.out.print("Enter number of items: ");
        int itemCount = scanner.nextInt();
        generate_bill[] items = new generate_bill[itemCount];

        // Collect item details from user
        for (int i = 0; i < itemCount; i++) {
            System.out.println("\nEnter details for item " + (i + 1) + ":");
            System.out.print("Item ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Item Name: ");
            String name = scanner.nextLine();
            System.out.print("Item Type (1-4): ");
            int type = scanner.nextInt();
            System.out.print("Item Quantity: ");
            int quantity = scanner.nextInt();
            System.out.print("Item Price: ");
            double price = scanner.nextDouble();

            items[i] = new generate_bill(id, name, type, quantity, price);
        }

        // Print the complete generate_bill
        System.out.println("\n--- Final generate_bill ---");
        double grandTotal = 0;
        for (generate_bill item : items) {
            item.print_bill();
            grandTotal += (item.item_price * item.item_quantity) + item.calculateTax();
        }
        System.out.println("Grand Total (with GST): " + grandTotal);

        scanner.close();
    }
}
