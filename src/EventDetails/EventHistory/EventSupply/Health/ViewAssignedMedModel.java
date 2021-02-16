package EventDetails.EventHistory.EventSupply.Health;

import SupplyDetails.HealthProduct.Medicine.Medicine;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ViewAssignedMedModel {
    protected ObservableList<Medicine> getTableRecords(String eventID) throws SQLException {
        String sql = "select distinct v.name,ev.amount,max(v.UNIT_PRICE)*ev.amount ,max(v.EXPIRE_DATE),max(v.MANUFACTURER) " +
                    "from event_health ev,HEALTH_PRODUCT v where ev.health_name=v.name and ev.event_id=? " +
                    "group by v.name,ev.amount order by v.name";

        ObservableList<Medicine> medList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int qty = rs.getInt(2);
                int price = rs.getInt(3);
                java.util.Date date = rs.getDate(4);
                String manufacturer = rs.getString(5);

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String expDate = df.format(date);

                Medicine medicine = new Medicine(price,qty,expDate,name,manufacturer);

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
