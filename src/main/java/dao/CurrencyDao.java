package dao;

import java.sql.*;

import datasource.MariaDBConnection;
import entity.CurrencyType;

import java.util.*;

public class CurrencyDao {

    public double getRate(String abbreviation) {
        Connection conn = MariaDBConnection.getConnection();
        String sql = "SELECT rate FROM currency WHERE abbreviation=?";
        double rate = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double rateToEur = rs.getDouble(1);
                rate = rateToEur;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }

    public ArrayList<String> getAbbreviations() {
        Connection conn = MariaDBConnection.getConnection();
        String sql = "SELECT abbreviation FROM currency";
        ArrayList<String> abbreviations = new ArrayList<>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                abbreviations.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abbreviations;
    }
}
