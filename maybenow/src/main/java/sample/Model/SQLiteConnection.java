package sample.Model;

import java.sql.*;

public class SQLiteConnection {

    public static Connection Connector() {

        String url = "jdbc:sqlite:Vacation4uDB.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                createNewTable(url);
                System.out.println("Connected!");
                return conn;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void createNewTable(String url) {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	birth text real,\n"
                + "	firstName text NOT NULL,\n"
                + "	lastName text NOT NULL,\n"
                + "	city text NOT NULL\n"
                + ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS vacations (\n"
                + "	VacationID text PRIMARY KEY,\n"
                + "	UserName text NOT NULL,\n"
                + "	airlinecompany text NOT NULL,\n"
                + "	StartDate text real,\n"
                + "	EndDate text real,\n"
                + "	TicketNumber text NOT NULL,\n"
                + "	StateName text NOT NULL,\n"
                + "	IsIncludeReturnFlight text NOT NULL,\n"
                + "	TicketType text NOT NULL,\n"
                + "	IsIncludeRoomaccommodation text NOT NULL,\n"
                + "	Nameaccommodation text,\n"
                + "	Price text,\n"
                + "	Status text,\n"
                + "	Interested text\n"
                + ");";

        String sql3 = "CREATE TABLE IF NOT EXISTS Payments (\n"
                + "	vacationID text NOT NULL,\n"
                + "	CardOwner text real,\n"
                + "	CreditCardNum text NOT NULL,\n"
                + "	Validation text NOT NULL,\n"
                + "	Status text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.execute(sql2);
            stmt.execute(sql3);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
