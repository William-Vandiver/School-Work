import java.sql.*;   // Needed for JDBC classes

/**
 * This program creates the Inventory database with Vendors and Products tables. 
 */
 
public class CreateInventory
{

    public static void main(String[] args)
    {
        final String DB_URL = "jdbc:derby:Inventory;create=true";

        try (Connection conn = DriverManager.getConnection(DB_URL))
        {
            // Drop tables if they exist
            dropTables(conn);

            // Create and populate Vendors table
            createAndAddVendors(conn);

            // Create and populate Products table
            createAndAddProducts(conn);

        }
        catch (Exception ex)
        {
            System.out.println("CONNECTION ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Drop existing tables
    public static void dropTables(Connection conn)
    {
        System.out.println("Checking for existing tables.");

        try (Statement stmt = conn.createStatement())
        {
            // Drop tables in correct order
            stmt.execute("DROP TABLE Products");
            System.out.println("Products Table dropped.");
            stmt.execute("DROP TABLE Vendors");
            System.out.println("Vendors Table dropped.");
        }
        catch (SQLException ex)
        {
            System.out.println("No previous tables exist to drop. Okay.");
        }
    }

    // Vendors module: create table and add data
    public static void createAndAddVendors(Connection conn)
    {
        try (Statement stmt = conn.createStatement())
        {
            String sql = "CREATE TABLE vendors (" +
                "VendorId INTEGER NOT NULL, " +
                "VendorName CHAR(25), " +
                "VendorPhone CHAR(25), " +
                "VendorEmail CHAR(25), " +
                "PRIMARY KEY (VendorId))";
            System.out.println("Executing: " + sql);
            stmt.execute(sql);
            System.out.println("Vendors table created.");

            // Add vendor data
            stmt.execute("INSERT INTO vendors VALUES (101, 'ABC Textiles', '(252)555-0133', 'abcorder@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (102, 'Elegant Designs', '(743)555-5784', 'elegantdesigns@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (103, 'KLM Plumbing and Fixtures', '(276)555-6565', 'ELM@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (104, 'Strong Tools', '(703)555-3451', 'stronginfo@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (105, 'Steelworks', '(540)555-9822', 'steelworks@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (106, 'Impressions', '(803)555-5523', 'ordermanager@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (107, 'PartsCo', '(703)555-4788', 'supply@nosuch.com')");
            stmt.execute("INSERT INTO vendors VALUES (108, 'TUF-Made', '(803)555-8213', 'tufenough@nosuch.com')");
            System.out.println("Vendors data added.");
        }
        catch (SQLException ex)
        {
            System.out.println("VENDORS CREATE/INSERT ERROR: " + ex.getMessage());
        }
    }

    // Products module: create table and add data
    public static void createAndAddProducts(Connection conn)
    {
        try (Statement stmt = conn.createStatement())
        {
            String sql = "CREATE TABLE products (" +
                "ProductId INTEGER NOT NULL PRIMARY KEY, " +
                "Description CHAR(40), " +
                "Quantity INTEGER, " +
                "Cost FLOAT, " +
                "WarehouseRow INTEGER, " +
                "WarehouseBin INTEGER, " +
                "VendorId INTEGER NOT NULL REFERENCES vendors(VendorId))";
            System.out.println("Executing: " + sql);
            stmt.execute(sql);
            System.out.println("Products table created.");

            // Add product data
            stmt.execute("INSERT INTO products VALUES (1010, 'Small Widget', 1022, 0.99, 1, 23, 105)");
            stmt.execute("INSERT INTO products VALUES (1011, 'Medium Widget', 812, 2.99, 1, 41, 105)");
            stmt.execute("INSERT INTO products VALUES (1012, 'Large Widget', 431, 5.99, 1, 62, 105)");
            stmt.execute("INSERT INTO products VALUES (2013, 'Curtains, Old Lace Ivory', 12, 29.99, 8, 3, 101)");
            stmt.execute("INSERT INTO products VALUES (2014, 'Curtains, Multistripe Red', 5, 35.50, 8, 16, 102)");
            stmt.execute("INSERT INTO products VALUES (2015, 'Curtains, Multistripe Blue', 8, 35.50, 8, 12, 102)");
            stmt.execute("INSERT INTO products VALUES (3016, 'Screwdriver #3 Phillips 4-inch', 6, 8.81, 14, 1, 104)");
            stmt.execute("INSERT INTO products VALUES (3017, 'Screwdriver Flat-tip 4-inch', 8, 7.38, 14, 3, 104)");
            stmt.execute("INSERT INTO products VALUES (3018, '#9 Wood screws, 300 count', 12, 26.71, 2, 18, 107)");
            stmt.execute("INSERT INTO products VALUES (3019, '1/2 in Zinc Roof Screw, 120 count', 18, 2.99, 2, 24, 107)");
            stmt.execute("INSERT INTO products VALUES (4020, 'PVC Pipe 330-PSI 10-foot', 11, 5.97, 10, 3, 103)");
            stmt.execute("INSERT INTO products VALUES (4021, 'PVC Pipe 330-PSI 20-foot', 3, 17.13, 10, 4, 103)");
            stmt.execute("INSERT INTO products VALUES (4022, 'Brass Gate Valve', 5, 6.85, 10, 19, 103)");
            stmt.execute("INSERT INTO products VALUES (5023, '2-handle Bathroom Faucet, 4-in center', 3, 51.00, 22, 8, 108)");
            stmt.execute("INSERT INTO products VALUES (5024, '2-handle Bathroom Faucet, 2-hole', 5, 99.98, 22, 19, 108)");
            stmt.execute("INSERT INTO products VALUES (6001, 'Copper Nails', 1000, 0.59, 100, 1, 108)");
            stmt.execute("INSERT INTO products VALUES (6002, 'Bronze Nails', 200, 0.79, 100, 1, 108)");
            stmt.execute("INSERT INTO products VALUES (6003, 'Iron Nails', 1000, 1.00, 100, 1, 108)");
            System.out.println("Products data added.");
        }
        catch (SQLException ex)
        {
            System.out.println("PRODUCTS CREATE/INSERT ERROR: " + ex.getMessage());
        }
    }
}