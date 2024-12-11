import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;

public class Restaurant {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to the Restaurant Management System");
            System.out.println("Press the index below to locate your operation");
            System.out.println("1. Employee Operations");
            System.out.println("2. Order Operations");
            System.out.println("3. Bill Operations");
            System.out.println("4. Menu Item Operations");
            System.out.println("5. Feedback Operations");
            System.out.println("6. Coupon Operations");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    employeeOperations(scanner);
                    break;
                case 2:
                    orderOperations(scanner);
                    break;
                case 3:
                    billOperations(scanner);
                    break;
                case 4:
                    menuItemOperations(scanner);
                    break;
                case 5:
                    feedbackOperations(scanner);
                    break;
                case 6:
                    couponOperations(scanner);
                    break;
                case 7:
                    System.out.println("Goodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }

    private static void employeeOperations(Scanner scanner) {
        while (true) {

            System.out.println("Employee Operations:");
            System.out.println("1. Insert Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner);
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }

    private static void insertEmployee(Scanner scanner) {

        System.out.println("Insert Employee:");
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Position: ");
        String position = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();


        String hireDate = hireDate = LocalDate.now().toString();


        System.out.print("Enter Employee Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Employee Email: ");
        String email = scanner.nextLine();


        String sql = "INSERT INTO Employee (employeeID, name, position, salary, hireDate, phoneNumber, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, position);
            pstmt.setDouble(4, salary);
            pstmt.setString(5, hireDate);
            pstmt.setString(6, phoneNumber);
            pstmt.setString(7, email);
            pstmt.executeUpdate();
            System.out.println("Employee inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting employee: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void updateEmployee(Scanner scanner) {

        System.out.println("Update Employee:");
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Employee Position: ");
        String position = scanner.nextLine();

        System.out.print("Enter new Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        String hireDate = hireDate = LocalDate.now().toString();

        System.out.print("Enter new Employee Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter new Employee Email: ");
        String email = scanner.nextLine();


        String sql = "UPDATE Employee SET name = ?, position = ?, salary = ?, hireDate = ?, phoneNumber = ?, email = ? WHERE employeeID= ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setDouble(3, salary);
            pstmt.setString(4, hireDate);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, email);
            pstmt.setInt(7, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void deleteEmployee(Scanner scanner) {

        System.out.println("Delete Employee:");
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Employee WHERE employeeID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("No employee found with the provided ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void orderOperations(Scanner scanner) {
        while (true) {

            System.out.println("Order Operations:");
            System.out.println("1. Insert Order");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertOrder(scanner);
                    break;
                case 2:
                    updateOrder(scanner);
                    break;
                case 3:
                    deleteOrder(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }
    private static void insertOrder(Scanner scanner) {
        System.out.println("Insert Order:");
        System.out.print("Enter Order ID: ");
        int orderID = scanner.nextInt();
        System.out.print("Enter Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter Item ID: ");
        int itemID = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        String orderDate = LocalDate.now().toString();

        String sql = "INSERT INTO CustomerOrder (orderID, tableNumber, itemID, quantity, orderDate) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, tableNumber);
            pstmt.setInt(3, itemID);
            pstmt.setInt(4, quantity);
            pstmt.setString(5, orderDate);
            pstmt.executeUpdate();
            System.out.println("Order inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void updateOrder(Scanner scanner) {
        System.out.println("Update Order:");
        System.out.print("Enter Order ID to update: ");
        int orderID = scanner.nextInt();
        System.out.print("Enter new Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter new Item ID: ");
        int itemID = scanner.nextInt();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        String orderDate = LocalDate.now().toString();

        String sql = "UPDATE CustomerOrder SET tableNumber = ?, itemID = ?, quantity = ?, orderDate = ? WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tableNumber);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, orderDate);
            pstmt.setInt(5, orderID);
            pstmt.executeUpdate();
            System.out.println("Order updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void deleteOrder(Scanner scanner) {
        System.out.println("Delete Order:");
        System.out.print("Enter Order ID to delete: ");
        int orderID = scanner.nextInt();

        String sql = "DELETE FROM CustomerOrder WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderID);
            pstmt.executeUpdate();
            System.out.println("Order deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void billOperations(Scanner scanner) {
        while (true) {

            System.out.println("Bill Operations:");
            System.out.println("1. Generate Bill");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    generateBill(scanner);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }


    private static void generateBill(Scanner scanner) {
        System.out.println("Generate Bill:");
        System.out.print("Enter Bill ID: ");
        int billID = scanner.nextInt();
        System.out.print("Enter Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        System.out.print("Enter Tax Percentage (e.g., 0.1): ");
        double taxPercentage = scanner.nextDouble();
        System.out.print("Enter Tip Amount: ");
        double tip = scanner.nextDouble();


        double tax = totalAmount * taxPercentage;
        String billDate = LocalDate.now().toString();
        double finalAmount = totalAmount + tax + tip;

        if (finalAmount < 0) {
            finalAmount = 0;

        }


        String sql = "INSERT INTO Bill (billID, tableNumber, totalAmount, tax, tip, billDate) VALUES (?, ?, ?, ?, ?, NOW())";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, billID);
            pstmt.setInt(2, tableNumber);
            pstmt.setDouble(3, totalAmount);
            pstmt.setDouble(4, tax);
            pstmt.setDouble(5, tip);
            pstmt.executeUpdate();

            System.out.printf("Bill generated successfully! Order ID: %d, Table Number: %d, Final Amount: %.2f\n", billID, tableNumber, finalAmount);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        }
    private static void menuItemOperations(Scanner scanner) {
        while (true) {
            System.out.println("Menu Item Operations:");
            System.out.println("1. Insert Menu Item");
            System.out.println("2. Update Menu Item");
            System.out.println("3. Delete Menu Item");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertMenuItem(scanner);
                    break;
                case 2:
                    updateMenuItem(scanner);
                    break;
                case 3:
                    deleteMenuItem(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void insertMenuItem(Scanner scanner) {
        System.out.println("Insert Menu Item:");
        System.out.print("Enter Item ID: ");
        int itemID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Item Price: ");
        double price = scanner.nextDouble();
        System.out.print("Is the Item Available? (true/false): ");
        boolean availability = scanner.nextBoolean();

        String sql = "INSERT INTO MenuItem (itemID, name, price, availability) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemID);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setBoolean(4, availability);
            pstmt.executeUpdate();
            System.out.println("Menu item inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting menu item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void updateMenuItem(Scanner scanner) {
        System.out.println("Update Menu Item:");
        System.out.print("Enter Item ID to update: ");
        int itemID = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter New Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Item Price: ");
        double price = scanner.nextDouble();
        System.out.print("Is the Item Available? (true/false): ");
        boolean availability = scanner.nextBoolean();

        String sql = "UPDATE MenuItem SET name = ?, price = ?, availability = ? WHERE itemID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setBoolean(3, availability);
            pstmt.setInt(4, itemID);
            pstmt.executeUpdate();
            System.out.println("Menu item updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating menu item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void deleteMenuItem(Scanner scanner) {
        System.out.println("Delete Menu Item:");
        System.out.print("Enter Item ID to delete: ");
        int itemID = scanner.nextInt();

        String sql = "DELETE FROM MenuItem WHERE itemID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemID);
            pstmt.executeUpdate();
            System.out.println("Menu item deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting menu item: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void feedbackOperations(Scanner scanner) {
        while (true) {
            System.out.println("Feedback Operations:");
            System.out.println("1. Insert Feedback");
            System.out.println("2. Update Feedback");
            System.out.println("3. Delete Feedback");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertFeedback(scanner);
                    break;
                case 2:
                    updateFeedback(scanner);
                    break;
                case 3:
                    deleteFeedback(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void insertFeedback(Scanner scanner) {
        System.out.println("Insert Feedback:");
        System.out.print("Enter Feedback ID: ");
        int feedbackID = scanner.nextInt();
        System.out.print("Enter Order ID: ");
        int orderID = scanner.nextInt();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();

        String sql = "INSERT INTO Feedback (feedbackID, orderID, rating) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, feedbackID);
            pstmt.setInt(2, orderID);
            pstmt.setInt(3, rating);
            pstmt.executeUpdate();
            System.out.println("Feedback inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void updateFeedback(Scanner scanner) {
        System.out.println("Update Feedback:");
        System.out.print("Enter Feedback ID to update: ");
        int feedbackID = scanner.nextInt();
        System.out.print("Enter new Rating (1-5): ");
        int rating = scanner.nextInt();

        String sql = "UPDATE Feedback SET rating = ? WHERE feedbackID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rating);
            pstmt.setInt(2, feedbackID);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Feedback updated successfully!");
            } else {
                System.out.println("Feedback ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void deleteFeedback(Scanner scanner) {
        System.out.println("Delete Feedback:");
        System.out.print("Enter Feedback ID to delete: ");
        int feedbackID = scanner.nextInt();

        String sql = "DELETE FROM Feedback WHERE feedbackID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, feedbackID);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Feedback deleted successfully!");
            } else {
                System.out.println("Feedback ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    private static void couponOperations(Scanner scanner) {
        while (true) {
            System.out.println("\nCoupon Operations:");
            System.out.println("1. Insert Coupon");
            System.out.println("2. Update Coupon");
            System.out.println("3. Delete Coupon");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertCoupon(scanner);
                    break;
                case 2:
                    updateCoupon(scanner);
                    break;
                case 3:
                    deleteCoupon(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }

    private static void insertCoupon(Scanner scanner) {
        System.out.println("Insert Coupon:");
        System.out.print("Enter Coupon ID: ");
        int couponID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Coupon Content: ");
        String content = scanner.nextLine();
        System.out.print("Enter Discount Amount: ");
        double discountAmount = scanner.nextDouble();

        String sql = "INSERT INTO Coupon (couponID, content, discountAmount) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, couponID);
            pstmt.setString(2, content);
            pstmt.setDouble(3, discountAmount);
            pstmt.executeUpdate();
            System.out.println("Coupon inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void updateCoupon(Scanner scanner) {
        System.out.println("Update Coupon:");
        System.out.print("Enter Coupon ID to Update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Coupon Content: ");
        String content = scanner.nextLine();
        System.out.print("Enter New Discount Amount: ");
        double discountAmount = scanner.nextDouble();

        String sql = "UPDATE Coupon SET content = ?, discountAmount = ? WHERE couponID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setDouble(2, discountAmount);
            pstmt.setInt(3, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coupon updated successfully!");
            } else {
                System.out.println("Coupon ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void deleteCoupon(Scanner scanner) {
        System.out.println("Delete Coupon:");
        System.out.print("Enter Coupon ID to Delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Coupon WHERE couponID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coupon deleted successfully!");
            } else {
                System.out.println("Coupon ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
