package EventDetails.CreateEvent;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.util.Date;

public class CreateEventModel {
    OracleConnection oc = new OracleConnection();

    protected boolean isScheduleEventSuccessful(String name, Date event_date, String type, int est_budget) {

        try {
            int type_int;
            if(type.equals("Food")){
                type_int=1;
            }else if(type.equals("Health")){
                type_int=2;
            } else type_int=-1;

            java.sql.Date sqlDate = new java.sql.Date(event_date.getTime());

            String sql = "insert into event_details(name,event_date,type,est_budget) values(?,?,?,?)";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, sqlDate);
            ps.setInt(3, type_int);
            ps.setInt(4, est_budget);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isScheduleEventSuccessful\n\n");
            e.printStackTrace();
            return false;
        }


        return false;
    }
}
