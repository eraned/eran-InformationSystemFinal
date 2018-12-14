package sample.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private Connection connection;
    private String currentUser;
    private HashMap<String, String> userInfo;

    public Model() {
        connection = SQLiteConnection.Connector();
    }

    public boolean login(String userName, String password) {
        String sql = "SELECT username "
                + "FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean AddPayment(String vacationID, String CardOwner, String CreditCardNum, LocalDate Validation) {
        String sql = "INSERT INTO Payments(vacationID,CardOwner,CreditCardNum,Validation,Status) VALUES(?,?,?,?,?)";
        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vacationID);
            pstmt.setString(2, CardOwner);
            pstmt.setString(3, CreditCardNum);
            String strdate = Validation.getDayOfMonth() + "/" + Validation.getMonthValue() + "/" + Validation.getYear();
            pstmt.setString(4, strdate);
            pstmt.setString(5, "Pending");
            pstmt.executeUpdate();
        } catch (SQLException e) {
             System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean AddVacation(String UserName, String AirlineCompany, LocalDate Startdate, LocalDate Enddate, String TicketNum, String country, boolean IsIncludeReturnFlight, String TicketType, boolean IsIncludeaccommodation, String Nameaccommodation, String Price, String status, String interested) {
        String sql = "INSERT INTO vacations(VacationID,UserName,airlinecompany,StartDate,EndDate,TicketNumber,StateName,IsIncludeReturnFlight,TicketType,IsIncludeRoomaccommodation,Nameaccommodation,Price,status,interested) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, (getNumberOfVacationInDB() + 1) + "");
            pstmt.setString(2, UserName);
            pstmt.setString(3, AirlineCompany);
            String strStartdate = Startdate.getDayOfMonth() + "/" + Startdate.getMonthValue() + "/" + Startdate.getYear();
            pstmt.setString(4, strStartdate);
            String strEndtdate = Enddate.getDayOfMonth() + "/" + Enddate.getMonthValue() + "/" + Enddate.getYear();
            pstmt.setString(5, strEndtdate);

            pstmt.setString(6, TicketNum + "");
            pstmt.setString(7, country.toUpperCase());
            if (IsIncludeReturnFlight) {
                pstmt.setString(8, "Yes");
            } else {
                pstmt.setString(8, "No");
            }
            pstmt.setString(9, TicketType);
            if (IsIncludeaccommodation)
                pstmt.setString(10, "Yes");
            else
                pstmt.setString(10, "No");

            pstmt.setString(11, Nameaccommodation);
            pstmt.setString(12, Price + "");
            pstmt.setString(13, status);
            pstmt.setString(14, interested);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;


    }

    public boolean RegisterUser(String userName, String password, LocalDate date, String firstName, String lastName, String city) {

        String sql = "INSERT INTO users(userName,password,birth,firstName,lastName,city) VALUES(?,?,?,?,?,?)";
        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            String strdate = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
            pstmt.setString(3, strdate);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, city);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateUser(String userName, String password, String date, String firstName, String lastName, String city) {
        String sql = "UPDATE users SET username = ?, password = ?, birth = ?, firstName = ?, lastName = ?, city = ?"
                + "  WHERE username = ?";

        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, date);
            pstmt.setString(4, firstName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, city);
            pstmt.setString(7, userName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public HashMap<String, String> searchUser(String userName) {
        userInfo = new HashMap<>();
        String sql = "SELECT username,birth,firstName,lastName,city " +
                "FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            userInfo.put("username", rs.getString("username"));
            userInfo.put("birth", rs.getString("birth"));
            userInfo.put("firstName", rs.getString("firstName"));
            userInfo.put("lastName", rs.getString("lastName"));
            userInfo.put("city", rs.getString("city"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userInfo;
    }

    public HashMap<String, String> GetVacationDetalies(String VacationID) {
        HashMap<String, String> vacationDetailes = new HashMap<>();
        String sql = "SELECT VacationID,UserName,airlinecompany,StartDate,EndDate,TicketNumber,StateName" +
                ",IsIncludeReturnFlight,TicketType,IsIncludeRoomaccommodation,Nameaccommodation,Price " +
                "FROM vacations WHERE VacationID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, VacationID);
            ResultSet rs = preparedStatement.executeQuery();
            vacationDetailes.put("UserName", rs.getString("UserName"));
            vacationDetailes.put("airlinecompany", rs.getString("airlinecompany"));
            vacationDetailes.put("StartDate", rs.getString("StartDate"));
            vacationDetailes.put("EndDate", rs.getString("EndDate"));
            vacationDetailes.put("TicketNumber", rs.getString("TicketNumber"));
            vacationDetailes.put("StateName", rs.getString("StateName"));
            vacationDetailes.put("IsIncludeReturnFlight", rs.getString("IsIncludeReturnFlight"));
            vacationDetailes.put("TicketType", rs.getString("TicketType"));
            vacationDetailes.put("IsIncludeRoomaccommodation", rs.getString("IsIncludeRoomaccommodation"));
            vacationDetailes.put("Nameaccommodation", rs.getString("Nameaccommodation"));
            vacationDetailes.put("Price", rs.getString("Price"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vacationDetailes;
    }

    public void getInfo(String userName) {
        userInfo = new HashMap<>();
        String sql = "SELECT username,password,birth,firstName,lastName,city " +
                "FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            userInfo.put("username", rs.getString("username"));
            userInfo.put("password", rs.getString("password"));
            userInfo.put("birth", rs.getString("birth"));
            userInfo.put("firstName", rs.getString("firstName"));
            userInfo.put("lastName", rs.getString("lastName"));
            userInfo.put("city", rs.getString("city"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, String> getUserInfo() {
        return userInfo;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public int getNumberOfVacationInDB() {
        String sql = "SELECT Count(*) AS VacationID FROM vacations";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();

            return rs.getInt("VacationID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setCurrentUser(String userName) {
        currentUser = userName;
    }

    public boolean findUser(String userName) {
        String sql = "SELECT username "
                + "FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void DeleteUser() {

        String sql = "DELETE FROM users WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getVacationResults(String Country) {
        ArrayList<String> VacationNumbers = new ArrayList<>();
        String sql = "SELECT VacationID "
                + "FROM vacations WHERE StateName  = ? AND Status = ?";


        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, Country);
            pstmt.setString(2, "Waiting");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VacationNumbers.add(rs.getString("VacationID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return VacationNumbers;

    }

    public boolean addInterested(String vacationId, String userName) {
        String sql = "UPDATE vacations SET status = ?, interested = ?"
                + "  WHERE VacationID = ?";

        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "WaitingApproval");
            pstmt.setString(2, userName);
            pstmt.setString(3, vacationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<String> getVacationsUserIsSelling(String userName) {
        ArrayList<String> VacationNumbers = new ArrayList<>();
        String sql = "SELECT VacationID "
                + "FROM vacations WHERE UserName  = ? AND Status = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, "WaitingApproval");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VacationNumbers.add(rs.getString("VacationID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return VacationNumbers;

    }

    public ArrayList<String> getVacationsUserIsInterested(String userName) {
        ArrayList<String> VacationNumbers = new ArrayList<>();
        String sql = "SELECT VacationID "
                + "FROM vacations WHERE Interested  = ? AND Status = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, "WaitingApproval");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VacationNumbers.add(rs.getString("VacationID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return VacationNumbers;
    }

    public boolean approveSale(String id) {
        String sql = "UPDATE vacations SET status = ?"
                + "  WHERE VacationID = ?";
        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Sold");
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        sql = "UPDATE Payments SET Status = ?"
                + "  WHERE VacationID = ?";
        try (Connection conn = SQLiteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Approved");
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}