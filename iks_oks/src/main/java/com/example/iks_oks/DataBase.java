package com.example.iks_oks;
import java.sql.*;

public class DataBase {
    private Connection veza;
    private String dbpath;
    public DataBase(String dbpath){
        this.dbpath=dbpath;
    }
    private void connect(){

        String url = "jdbc:sqlite:"+dbpath;

        try {
            veza = DriverManager.getConnection(url);
            if (veza != null) {
                System.out.println("Veza na bazu uspostavljena");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToDataBase(String username) {
        connect();
        String sql = "INSERT INTO pobednici (ime_pobednika, Broj_pobeda) VALUES (?, 1) " +
                "ON CONFLICT(ime_pobednika) DO UPDATE SET Broj_pobeda = Broj_pobeda + 1";
        try  {
            PreparedStatement pstmt = veza.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            closeDb();
            throw new RuntimeException(e);
        } finally {
            closeDb();
        }

    }
    private void closeDb() {
        try {
            if (veza != null && !veza.isClosed()) {
                veza.close();
                System.out.println("Veza na bazu zatvorena");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}












