import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class WorldQueries {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world";  
        String user = "root";  
        String password = "Vanderlofske1"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            String sql = "SELECT city.Name AS City, country.Name AS Country " +
                         "FROM city JOIN country ON city.CountryCode = country.Code " +
                         "LIMIT 10";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    System.out.println(rs.getString("City") + " - " + rs.getString("Country"));
                }
            }
            // Run Q1
            System.out.println("\nQ1: Cities and their Countries");
            queryCitiesAndCountries(conn);

            // Run Q4
            System.out.println("\nQ4: Countries and their Capitals");
            queryCountriesAndCapitals(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // Q1: Retrieve the list of cities along with their country names
    public static void queryCitiesAndCountries(Connection conn) throws SQLException {
        String sql = "SELECT city.Name AS City, country.Name AS Country " +
                     "FROM city JOIN country ON city.CountryCode = country.Code " +
                     "LIMIT 10";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("City") + " - " + rs.getString("Country"));
            }
        }
    }

    // Q4: List the countries and the corresponding city capitals
    public static void queryCountriesAndCapitals(Connection conn) throws SQLException {
        String sql = "SELECT country.Name AS Country, city.Name AS Capital " +
                     "FROM country JOIN city ON country.Capital = city.ID " +
                     "LIMIT 10";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("Country") + " - " + rs.getString("Capital"));
            }
        }
    }
}
