package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductMgmtApp {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(3128874119L, "Banana", LocalDate.of(2023, 1, 24), 124,0.55));
        products.add(new Product(2927458265L, "Apple", LocalDate.of(2022, 12,9), 18, 1.09));
        products.add(new Product(9189927460L, "Carrot", LocalDate.of(2023, 3, 31), 89, 2.99));

        printProducts(products);

    }

    public static void printProducts(List<Product> products){
        Collections.sort(products, Comparator.comparing(Product::getName));
        printProductsAsJson(products);
        printProductsAsXml(products);
        printProductsAsCsv(products);
    }

    private static void printProductsAsJson(List<Product> products) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"productId\": ").append(product.getProductId()).append(",\n");
            jsonBuilder.append("    \"name\": \"").append(product.getName()).append("\",\n");
            jsonBuilder.append("    \"dateSupplied\": \"").append(product.getDateSupplied()).append("\",\n");
            jsonBuilder.append("    \"quantityInStock\": ").append(product.getQuantityInStock()).append(",\n");
            jsonBuilder.append("    \"unitPrice\": ").append(product.getUnitPrice()).append("\n");
            jsonBuilder.append("  }");
            if (i < products.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        jsonBuilder.append("]");
        System.out.println("Printed in JSON Format:\n" + jsonBuilder.toString());
    }

    private static void printProductsAsXml(List<Product> products) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\"?>\n");
        xmlBuilder.append("<products>\n");
        for (Product product : products) {
            xmlBuilder.append("  <product");
            xmlBuilder.append(" productId=\"").append(product.getProductId()).append("\"");
            xmlBuilder.append(" name=\"").append(product.getName()).append("\"");
            xmlBuilder.append(" dateSupplied=\"").append(product.getDateSupplied()).append("\"");
            xmlBuilder.append(" quantityInStock=\"").append(product.getQuantityInStock()).append("\"");
            xmlBuilder.append(" unitPrice=\"").append(product.getUnitPrice()).append("\"");
            xmlBuilder.append("/>\n");
        }
        xmlBuilder.append("</products>");
        System.out.println("------------------------------------");
        System.out.println("Printed in XML Format\n" + xmlBuilder.toString());
    }

    private static void printProductsAsCsv(List<Product> products) {
        // Calculate the maximum width for each column
        int productIdWidth = "productId".length();
        int nameWidth = "name".length();
        int dateSuppliedWidth = "dateSupplied".length();
        int quantityInStockWidth = "quantityInStock".length();
        int unitPriceWidth = "unitPrice".length();

        for (Product product : products) {
            productIdWidth = Math.max(productIdWidth, String.valueOf(product.getProductId()).length());
            nameWidth = Math.max(nameWidth, product.getName().length());
            dateSuppliedWidth = Math.max(dateSuppliedWidth, product.getDateSupplied().toString().length());
            quantityInStockWidth = Math.max(quantityInStockWidth, String.valueOf(product.getQuantityInStock()).length());
            unitPriceWidth = Math.max(unitPriceWidth, String.valueOf(product.getUnitPrice()).length());
        }

        // Create format strings for each column
        String format = "%-" + productIdWidth + "s %-"+ nameWidth +"s %-"+ dateSuppliedWidth +"s %-"+ quantityInStockWidth +"s %-"+ unitPriceWidth + "s\n";

        // Build the CSV string
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(String.format(format, "productId", "name", "dateSupplied", "quantityInStock", "unitPrice"));
        for (Product product : products) {
            csvBuilder.append(String.format(format,
                    product.getProductId(),
                    product.getName(),
                    product.getDateSupplied(),
                    product.getQuantityInStock(),
                    product.getUnitPrice()));
        }

        // Print the CSV string
        System.out.println("------------------------------------");
        System.out.println("Printed in Comma-Separated Value(CSV) Format");
        System.out.println(csvBuilder.toString());
    }
}
