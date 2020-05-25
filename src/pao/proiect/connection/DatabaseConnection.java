package pao.proiect.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() throws SQLException {
        String username = "root";
        String password = "paoproject233";
        String url = "jdbc:mysql://localhost/pao?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
            createTables(instance.getConnection());
        }

        if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private static void createTables(Connection connection) {
        String delimiter = ";";
        try (Scanner scanner = new Scanner(new File("table.sql"))) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                String rawStatement = scanner.next() + delimiter;
                if (rawStatement.trim().length() < 2) {
                    continue;
                }
                //System.out.println("Executing statement: " + rawStatement);

                try (Statement currentStatement = connection.createStatement()) {
                    currentStatement.execute(rawStatement);
                    System.out.println("Successfully executed statement!");
                } catch (SQLException e) {
                    System.out.println("Failed to execute statement: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load file: 'table.sql'!");
        }
    }
}