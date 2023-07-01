import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class BillingSystem {
    private ArrayList<Product> products;
    private double total;

    public BillingSystem() {
        products = new ArrayList<>();
        total = 0;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void generateBill() {
        System.out.println("--------- Grocery Shop Billing System ---------");
        System.out.println("Product Name\tPrice\tQuantity\tTotal");

        for (Product product : products) {
            double productTotal = product.getPrice() * product.getQuantity();
            if(product.getName().length()>=8)
            System.out.printf("%s\t%.2f\t%d\t\t%.2f%n first", product.getName(), product.getPrice(),
                    product.getQuantity(), productTotal);
            if(!(product.getName().length()>=8))
            System.out.printf("%s\t \t%.2f\t%d\t\t%.2f%n", product.getName(), product.getPrice(),
                    product.getQuantity(), productTotal);
            total += productTotal;
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Total: %.2f%n", total);
    }
}

public class Grocery {
    public static void main(String[] args) {
        // Creating a new instance of the billing system
        BillingSystem billingSystem = new BillingSystem();

        // GUI components
        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JButton addButton = new JButton("Add Product");
        JButton generateButton = new JButton("Generate Bill");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);

        JFrame frame = new JFrame("Grocery Shop Billing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        // Adding action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                Product product = new Product(name, price, quantity);
                billingSystem.addProduct(product);
                JOptionPane.showMessageDialog(null, "Product added successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                priceField.setText("");
                quantityField.setText("");
            }
        });

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                billingSystem.generateBill();
                frame.dispose();
            }
        });
    }
}

