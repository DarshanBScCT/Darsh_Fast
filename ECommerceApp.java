import java.util.*;

// ========== Product Class ==========
class Product {
    private int id;
    private String name;
    private double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Encapsulation - getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return id + ". " + name + " - $" + price;
    }
}

// ========== User Class ==========
class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void displayUser() {
        System.out.println("\n User: " + name + " | Email: " + email);
    }
}

// ========== Cart Class ==========
class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
        System.out.println(p.getName() + " added to cart!");
    }

    public void showCart() {
        if (products.isEmpty()) {
            System.out.println(" Your cart is empty!");
            return;
        }
        System.out.println("\n Your Cart:");
        for (Product p : products)
            System.out.println("- " + p.getName() + " ($" + p.getPrice() + ")");
        System.out.println("Total: $" + getTotal());
    }

    public double getTotal() {
        double total = 0;
        for (Product p : products)
            total += p.getPrice();
        return total;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}

// ========== Abstract Payment Class ==========
abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    // Abstract method (Polymorphism)
    public abstract void pay();
}

// ========== Credit Card Payment ==========
class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay() {
        System.out.println(" Processing Credit Card payment...");
        System.out.println("Card: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Paid $" + amount + " successfully!");
    }
}

// ========== UPI Payment ==========
class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println(" Processing UPI payment...");
        System.out.println("UPI ID: " + upiId);
        System.out.println("Paid $" + amount + " successfully!");
    }
}

// ========== Main Application ==========
public class ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Create User
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        User user = new User(name, email);
        user.displayUser();

        // Step 2: Available Products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 55000));
        products.add(new Product(2, "Smartphone", 25000));
        products.add(new Product(3, "Headphones", 1500));
        products.add(new Product(4, "Smartwatch", 4000));

        Cart cart = new Cart();

        // Step 3: Menu
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n Available Products:");
                    for (Product p : products)
                        System.out.println(p);
                    break;

                case 2:
                    System.out.print("Enter product ID to add: ");
                    int id = sc.nextInt();
                    boolean found = false;
                    for (Product p : products) {
                        if (p.getId() == id) {
                            cart.addProduct(p);
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Invalid product ID!");
                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty! Add items first.");
                    } else {
                        double total = cart.getTotal();
                        System.out.println("Your total bill: $" + total);
                        System.out.println("Select Payment Method:");
                        System.out.println("1. Credit Card");
                        System.out.println("2. UPI");
                        int payChoice = sc.nextInt();
                        sc.nextLine(); // consume newline

                        Payment payment;
                        if (payChoice == 1) {
                            System.out.print("Enter Credit Card Number: ");
                            String card = sc.nextLine();
                            payment = new CreditCardPayment(total, card);
                        } else {
                            System.out.print("Enter UPI ID: ");
                            String upi = sc.nextLine();
                            payment = new UpiPayment(total, upi);
                        }
                        payment.pay();
                        System.out.println(" Order placed successfully!");
                        choice = 0; // Exit after payment
                    }
                    break;

                case 0:
                    System.out.println(" Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}