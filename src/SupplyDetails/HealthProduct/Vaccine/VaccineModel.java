package SupplyDetails.HealthProduct.Vaccine;

import SupplyDetails.SupplyInformation;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VaccineModel {
    protected ObservableList<Vaccine> getVaccineTableRecords() throws SQLException {
        String sql = "select distinct name,sum(qty) qty,sum(unit_price*qty) price from HEALTH_PRODUCT where id LIKE 'V%' group by name order by name";
        ObservableList<Vaccine> vaccineList = FXCollections.observableArrayList();

        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int qty = rs.getInt(2);
                int price = rs.getInt(3);

                Vaccine vaccine = new Vaccine(price,qty,name);
                vaccineList.add(vaccine);
            }

            return vaccineList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getTableRecords : vacModel");
            throw e;
        }

    }

    protected boolean isDeleteSelectedVaccineSuccessful(String name) {
        String sql = "delete from HEALTH_PRODUCT where name=?";
        return new SupplyInformation().isDeleteSelectedItemSuccessful(name,sql);

    }
    protected  boolean isMatchSuccessful(String newValue, Vaccine vaccine) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        return String.valueOf(vaccine.getName()).contains(newValue);
    }
}
