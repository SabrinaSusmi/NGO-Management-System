package SupplyDetails.HealthProduct.Medicine.ViewMedicineDetails;

import SupplyDetails.HealthProduct.Medicine.Medicine;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewMedicineInformationModel {

    protected ObservableList<Medicine> getMedicineTableRecords(String item,int month) throws SQLException {
        String sql = "select * from HEALTH_PRODUCT where name=? and purchase_date > add_months(sysdate,-?) order by purchase_date";
        ObservableList<Medicine> medList = FXCollections.observableArrayList();

        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, item);
            ps.setInt(2,month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name=rs.getString(2);
                String pdate= String.valueOf(rs.getDate(3));
                String edate= String.valueOf(rs.getDate(4));
                int qty=rs.getInt(5);
                String sup=rs.getString(6);
                String manufacturer =rs.getString(7);
                int price=rs.getInt(8);
                System.out.println(pdate+"  ko  "+edate);

                Medicine medicine = new Medicine(price,qty,pdate,edate,name,sup,manufacturer,id);
                System.out.println("lpl");
                medList.add(medicine);
            }
            return medList;
        } catch (Exception e) {
            System.out.println("getTableRecords : MedModel");
            e.printStackTrace();
            throw e;
        }

    }
}
