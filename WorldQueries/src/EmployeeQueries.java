import java.sql.*;
import java.util.*;

public class EmployeeQueries {

    // --- Inner Employee class for part (f) ---
    public static class Employee {
        private int id;
        private String firstName;
        private String lastName;
        private String department;

        public Employee(int id, String firstName, String lastName, String department) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.department = department;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getDepartment() { return department; }

        @Override
        public String toString() {
            return id + ": " + firstName + " " + lastName + " (" + department + ")";
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employee"; 
        String user = "root";         
        String password = "Vanderlofske1"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to employee database!");

            // (a) Employees in Engineering
            System.out.println("\n(a) Employees in Engineering:");
            queryEngineeringEmployees(conn);

            // (b) Employees in HR or Finance (alphabetical)
            System.out.println("\n(b) Employees in HR or Finance:");
            queryHRAndFinanceEmployees(conn);

            // (c) Update employee’s department (id=5 → IT)
            System.out.println("\n(c) Updating employee with id=5 to IT:");
            updateEmployeeDepartment(conn, 5, "IT");

            // (d) Add new department + employees
            System.out.println("\n(d) Adding Cybersecurity department with 3 employees:");
            addDepartmentAndEmployees(conn);

            // (e) User input: employees by department
            System.out.println("\n(e) Employees by user-entered department:");
            queryEmployeesByDepartmentInput(conn);

            // (f) Employee objects + filter
            System.out.println("\n(f) Employees loaded into objects, filtered by 'A':");
            List<Employee> employees = loadEmployees(conn);
            List<Employee> filtered = filterByNameStartsWith(employees, "A");
            for (Employee emp : filtered) {
                System.out.println(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (a) Employees in Engineering
    public static void queryEngineeringEmployees(Connection conn) throws SQLException {
        String sql = "SELECT e.id, e.first_name, e.last_name " +
                     "FROM employee e JOIN department d ON e.dept_id = d.id " +
                     "WHERE d.name = 'Engineering'";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                                   rs.getString("first_name") + " " +
                                   rs.getString("last_name"));
            }
        }
    }

    // (b) Employees in HR or Finance (alphabetical by last name)
    public static void queryHRAndFinanceEmployees(Connection conn) throws SQLException {
        String sql = "SELECT e.id, e.first_name, e.last_name, e.manager_id " +
                     "FROM employee e JOIN department d ON e.dept_id = d.id " +
                     "WHERE d.name IN ('HR','Finance') ORDER BY e.last_name ASC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                                   rs.getString("first_name") + " " +
                                   rs.getString("last_name") +
                                   " | Manager ID: " + rs.getInt("manager_id"));
            }
        }
    }

    // (c) Update employee’s department
    public static void updateEmployeeDepartment(Connection conn, int empId, String newDept) throws SQLException {
        String sql = "UPDATE employee SET dept_id = (SELECT id FROM department WHERE name = ?) WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDept);
            pstmt.setInt(2, empId);
            int rows = pstmt.executeUpdate();
            System.out.println("Updated " + rows + " employee(s).");
        }
    }

    // (d) Add new department + 3 employees
    public static void addDepartmentAndEmployees(Connection conn) throws SQLException {
        String insertDept = "INSERT INTO department (name) SELECT ? WHERE NOT EXISTS " +
                            "(SELECT 1 FROM department WHERE name=?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertDept)) {
            pstmt.setString(1, "Cybersecurity");
            pstmt.setString(2, "Cybersecurity");
            pstmt.executeUpdate();
        }
        int deptId = -1;
        String getDeptId = "SELECT id FROM department WHERE name = 'Cybersecurity' LIMIT 1";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getDeptId)) {
            if (rs.next()) {
                deptId = rs.getInt("id");
            }
        }
        String insertEmp = "INSERT INTO employee (first_name, last_name, dept_id) VALUES (?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertEmp)) {
            pstmt.setString(1, "Alice");
            pstmt.setString(2, "Johnson");
            pstmt.setInt(3, deptId);
            pstmt.executeUpdate();

            pstmt.setString(1, "Bob");
            pstmt.setString(2, "Smith");
            pstmt.setInt(3, deptId);
            pstmt.executeUpdate();

            pstmt.setString(1, "Clara");
            pstmt.setString(2, "Brown");
            pstmt.setInt(3, deptId);
            pstmt.executeUpdate();
        }

        System.out.println("Cybersecurity department + 3 employees added.");
    }


    // (e) Ask user for department and print employees
    public static void queryEmployeesByDepartmentInput(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter department name: ");
        String deptName = sc.nextLine();

        String sql = "SELECT e.id, e.first_name, e.last_name " +
                     "FROM employee e JOIN department d ON e.dept_id = d.id WHERE d.name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deptName);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nEmployees in " + deptName + ":");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                                   rs.getString("first_name") + " " +
                                   rs.getString("last_name"));
            }
        }
    }

    // (f) Load employees into list
    public static List<Employee> loadEmployees(Connection conn) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.id, e.first_name, e.last_name, d.name AS dept " +
                     "FROM employee e JOIN department d ON e.dept_id = d.id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("dept")
                ));
            }
        }
        return employees;
    }

    // (f) Filter employees by name starting with x
    public static List<Employee> filterByNameStartsWith(List<Employee> employees, String x) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getFirstName().startsWith(x) || emp.getLastName().startsWith(x)) {
                result.add(emp);
            }
        }
        return result;
    }
}
