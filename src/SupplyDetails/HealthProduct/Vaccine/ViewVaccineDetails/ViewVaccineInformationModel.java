package SupplyDetails.HealthProduct.Vaccine.ViewVaccineDetails;

import SupplyDetails.HealthProduct.Vaccine.Vaccine;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ViewVaccineInformationModel {

    protected ObservableList<Vaccine> getVaccineTableRecords(String item,int month) throws SQLException {
        String sql = "select * from HEALTH_PRODUCT where name=? and purchase_date > add_months(sysdate,-?) order by purchase_date";
        ObservableList<Vaccine> vacList = FXCollections.observableArrayList();

        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, item);
            ps.setInt(2,month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name=rs.getString(2);
                Date pdate=rs.getDate(3);
                Date edate=rs.getDate(4);
                int qty=rs.getInt(5);
                String sup=rs.getString(6);
                String manufacturer =rs.getString(7);
                int price=rs.getInt(8);

                Vaccine vaccine = new Vaccine(price,qty,pdate,edate,name,sup,manufacturer,id);

                vacList.add(vaccine);
            }
            return vacList;
        } catch (Exception e) {
            System.out.println("getTableRecords : vacModel");
            e.printStackTrace();
            throw e;
        }

    }
}
