import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Restaurant {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Welcome to the Restaurant Management System");
            System.out.println("Press the index below to locate your operation");
            System.out.println("1. Employee Operations");
            System.out.println("2. Order Operations");
            System.out.println("3. Bill Operations");
            System.out.println("4. Exit");
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

        String sql = "INSERT INTO Employee (employeeID, name, position, salary) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, position);
            pstmt.setDouble(4, salary);
            pstmt.executeUpdate();
            System.out.println("Employee inserted successfully!");
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

        String sql = "UPDATE Employee SET name = ?, position = ?, salary = ? WHERE employeeID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("No employee found with the provided ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error:" + e.getMessage());
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
                System.err.println("Error closing resources: " + e.getMessage());
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
        int orderId = scanner.nextInt();
        System.out.print("Enter Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        System.out.print("Enter Tax Amount: ");
        double tax = scanner.nextDouble();
        System.out.print("Enter Tip Amount: ");
        double tip = scanner.nextDouble();

        String sql = "INSERT INTO CustomerOrder (orderID, tableNumber, totalAmount, tax, tip) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, tableNumber);
            pstmt.setDouble(3, totalAmount);
            pstmt.setDouble(4, tax);
            pstmt.setDouble(5, tip);
            pstmt.executeUpdate();
            System.out.println("Order inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Error inserting order: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    private static void updateOrder(Scanner scanner) {
        System.out.println("Update Order:");
        System.out.print("Enter Order ID to update: ");
        int orderId = scanner.nextInt();
        System.out.print("Enter new Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter new Total Amount: ");
        double totalAmount = scanner.nextDouble();
        System.out.print("Enter new Tax Amount: ");
        double tax = scanner.nextDouble();
        System.out.print("Enter new Tip Amount: ");
        double tip = scanner.nextDouble();

        String sql = "UPDATE CustomerOrder SET tableNumber = ?, totalAmount = ?, tax = ?, tip = ? WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tableNumber);
            pstmt.setDouble(2, totalAmount);
            pstmt.setDouble(3, tax);
            pstmt.setDouble(4, tip);
            pstmt.setInt(5, orderId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order updated successfully!");
            } else {
                System.out.println("No order found with the provided ID.");
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
    private static void deleteOrder(Scanner scanner) {
        System.out.println("Delete Order:");
        System.out.print("Enter Order ID to delete: ");
        int orderId = scanner.nextInt();

        String sql = "DELETE FROM CustomerOrder WHERE orderID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("No order found with the provided ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
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
        System.out.print("Enter Table Number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter Total Amount: ");
        double totalAmount = scanner.nextDouble();
        System.out.print("Enter Tax Amount: ");
        double tax = scanner.nextDouble();
        System.out.print("Enter Tip Amount: ");
        double tip = scanner.nextDouble();

        double finalAmount = totalAmount + tax + tip;
        if (finalAmount < 0) finalAmount = 0;

        String sql = "INSERT INTO Bill (tableNumber, totalAmount, tax, tip, billDate) VALUES (?, ?, ?, ?, NOW())";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tableNumber);
            pstmt.setDouble(2, finalAmount);
            pstmt.setDouble(3, tax);
            pstmt.setDouble(4, tip);
            pstmt.executeUpdate();
            System.out.println("Bill generated successfully! Final Amount: " + finalAmount);
        } catch (SQLException e) {
            System.err.println("Error generating bill: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
