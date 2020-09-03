package edu.escuelaing.arep.app;

import java.sql.*;
import java.util.ArrayList;
import java.sql.DriverManager;

public class DBConnection {
    private static String urlDB = "jdbc:postgresql://ec2-3-223-9-166.compute-1.amazonaws.com:5432/dfhoha29iia5eu?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static String usuarioDB = "eksahhhdpmkiwi";
    private static String passwordDB = "92c543b791089bc4474bfb474add97c447d0a11cf53b8fff7c896101a183838c";
    private static Connection connection = null;

    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDB, usuarioDB, passwordDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(){
        String CREATE_TABLE="CREATE TABLE Information ("
                + "ID INT NOT NULL,"
                + "USERN VARCHAR(60) NOT NULL,"
                + "ADDRESS VARCHAR(30) NOT NULL,"
                + "PRIMARY KEY (ID))";

        try {
            Statement stmnt = connection.createStatement();
            stmnt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getInformation(){
        ArrayList<String[]> list = new ArrayList<>();
        String select = "SELECT * FROM Information;";
        try {

            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery(select);
            while(rs.next()){
                String usern = rs.getString("usern");
                String address = rs.getString("address");
                String[] tmp = {usern,address};
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
