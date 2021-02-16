package Main;

import Utilities.OracleConnection;
import com.sun.org.apache.xpath.internal.operations.Or;
import jdk.nashorn.internal.runtime.RecompilableScriptFunctionData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardModel {
    protected int getTotalEmployee(){
        String sql="select sum(num_of_employee(designation_name,id)) from designation where id like 'E%'";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalDoctor(){
        String sql="select sum(num_of_employee(designation_name,id)) from designation where id like 'D%'";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalDonor(){
        String sql="select count(donor_id) from donor";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalVolunteer(){
        String sql="select count(Volunteer_id) from Volunteer   ";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalDonation(){
        String sql="select nvl(sum(amount),0) from donation_history where extract (month from don_date)=extract (month from sysdate)  ";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalExpense(){
        String sql="select nvl(sum(amount),0) from expenses where extract (month from expense_date)=extract (month from sysdate)";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalEvent(){
        String sql="select count(id) from event_details where extract (month from event_date)=extract (month from sysdate)";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }
    protected int getTotalEventExpense(){
        String sql="select nvl(sum(actual_budget),0) from event_details where extract (month from event_date)=extract (month from sysdate)";
        Integer rs = getResultFromDB(sql);
        if (rs != null) return rs;
        return 0;
    }

    private Integer getResultFromDB(String sql) {
        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps=oc.conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
