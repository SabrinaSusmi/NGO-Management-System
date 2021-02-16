package SupplyDetails;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplyInformation {
    public List<String> getItemNameList(String sql) {
        List<String> list = new ArrayList<>();

        OracleConnection oc = new OracleConnection();
        try {
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getString(1)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getItemNameList");
        }
        return list;
    }

    public boolean isDeleteSelectedItemSuccessful(String id,String sql) {
        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e + " isDeleteSuccessful");
            e.printStackTrace();
            return false;
        }
        return false;
    }



}
