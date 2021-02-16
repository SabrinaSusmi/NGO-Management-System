package EventDetails.ManageEvent;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetInformationForEvent {
    public String[] getSearchedListForEvent(String[] info, String text, String sql) {
        List<String> list = new ArrayList<>();

        try {

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getTotalNumberOfIDs(String sql) {
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String[] getList(String[] info, String sql) {
        List<String> list = new ArrayList<>();

        try {

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isAssignSuccessful(ArrayList<String> id, String eventID, String sql) {
        int arraySize = id.size();
        //System.out.println(arraySize);
        try {
            OracleConnection oc = new OracleConnection();
            for(int i=0; i<arraySize;i++){
                PreparedStatement ps = oc.conn.prepareStatement(sql);
                ps.setString(1, eventID);
                ps.setString(2, id.get(i));
                int x = ps.executeUpdate();
                if(x<1)
                    return false;
            }
        } catch (SQLException e) {
            System.out.println("isAssignVolunteerSuccessful\n\n");
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
