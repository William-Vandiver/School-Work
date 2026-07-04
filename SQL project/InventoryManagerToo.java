import javax.xml.catalog.Catalog;
import java.util.Scanner;
import java.sql.*;
import java.text.NumberFormat;

/*
    InventoryManagerToo

    This Program connects to a Derby database named "Inventory"
    and allows the user to view products, view vendors, or find
    the vendor supplying a selected product.
*/

public class InventoryManagerToo {
    public static void main(String[] args) {
        // database connection URL
        final String DB_URL = "jdbc:derby:Inventory";

        // attempt to connect to the database
        try (Connection connect = DriverManager.getConnection(DB_URL)) {
            int menuChoice;

            // continue showing menu util user selects exit
            do {
                menuChoice = menu();

                switch (menuChoice) {
                    case 1:
                        displayProducts(connect);
                        break;

                    case 2:
                        displayVendors(connect);
                        break;

                    case 3:
                        findVendorInfo(connect);
                        break;

                    case 4:
                        System.out.println("Thank you for using the program.");
                }
            }
            while (menuChoice != 4);
        } catch (SQLException e) {
            System.out.println("Could not connect to database: " + e);
        } catch (Exception e) {
            System.out.println("Code failed for undetermind reason: " + e);
        }
    }

    /*
        menu()

        displays the program menu and returns the user's selection.
        input is validated to ensure a value between 1 and 4
    */
    public static int menu() {
        int choice;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("\nIntentory Menu");
            System.out.println("1) Print all Products");
            System.out.println("2) Print all Vendors");
            System.out.println("3) Find Vendor of Products");
            System.out.println("4) Exit");
            System.out.print("Selection --> ");

            choice = keyboard.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("Choice not in menu, try again");
            }
        }
        while (choice < 1 || choice > 4);

        return choice;
    }

    /*
        displayProducts()

        retrieves all products from the products table
        and displays them in a formatted table.
    */
    public static void displayProducts(Connection conn) {
        System.out.println("\n");

        // print colum headers
        System.out.printf("%-10s %-40s %10s %12s\n",
                "ProductID", "Description", "Quantity", "Cost");

        System.out.println("-----------------------------------------------------------------------------");

        String sqlStatement =
                "SELECT ProductId, Description, Quantity, Cost FROM Products";

        // execute query and display results
        try (Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
             ResultSet result = stmt.executeQuery(sqlStatement)
        ) {
            while (result.next()) {
                System.out.printf("%-10d %-40s %10d %12.2f\n",
                        result.getInt("ProductId"),
                        result.getString("Description"),
                        result.getInt("Quantity"),
                        result.getDouble("Cost"));
            }

            System.out.println();
        } catch (SQLException ex) {
            System.out.println("Display Products ERROR: " + ex.getMessage());
        }
    }

    /*
        displayVendors()

        retrieves all vendor recors from the vendors table
        and displays them in a formatted list.
     */
    public static void displayVendors(Connection conn) {
        System.out.println("\n");

        //print colum headers
        System.out.printf("%-10s %-40s %10s %25s\n",
                "VendorID", "VendorName", "VendorPhone", "VendorEmail");

        System.out.println("--------------------------------------------------------------------------------------------------------");

        String sqlStatement =
                "SELECT VendorId, VendorName, VendorPhone, VendorEmail FROM Vendors";

        // execute query and display results
        try (Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
             ResultSet result = stmt.executeQuery(sqlStatement)
        ) {
            while (result.next()) {
                System.out.printf("%-10d %-40s %10s %20s\n",
                        result.getInt("VendorId"),
                        result.getString("VendorName"),
                        result.getString("VendorPhone"),
                        result.getString("VendorEmail"));
            }

            System.out.println();
        } catch (SQLException ex) {
            System.out.println("Display Products ERROR: " + ex.getMessage());
        }
    }

    /*
        findVendorInfo()

        displays the list of products, prompts the user to select
        a product ID, and then retrieves the vendor associated
        with that product.
     */
    public static void findVendorInfo(Connection conn) {
        int id;
        
        displayProducts(conn);
        
        String sqlVendor =
                "SELECT Products.VendorId, Products.ProductId, Vendors.VendorName, Vendors.VendorPhone, Vendors.VendorEmail  " +
                        "FROM Vendors, Products " +
                        "WHERE ProductId = ? and Products.VendorId = Vendors.VendorId";

        Scanner keyboard = new Scanner(System.in);

        // get the Id # from the user
        System.out.print("Enter the number of the Product ID: ");
        id = keyboard.nextInt();

        // execute search and display results
        try (PreparedStatement stmt = conn.prepareStatement(sqlVendor)){

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
                
            while (result.next()){
                System.out.println("Product ID# " + id + " is made by: ");
                System.out.println("Vendor " + result.getString("VendorName"));
                System.out.println("Phone # is: " + result.getString("VendorPhone"));
                System.out.println("Email is: " + result.getString("VendorEmail"));
            
            }
        }
        catch (SQLException ex) {
            System.out.println("Display Vendor ERROR: " + ex.getMessage());
        }
    }
}