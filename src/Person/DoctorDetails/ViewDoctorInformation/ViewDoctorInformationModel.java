package Person.DoctorDetails.ViewDoctorInformation;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewDoctorInformationModel {
    protected String[] showDoctorDetails(String[] info, String doc_id) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "select d.doctor_id,d.name,d.email,d.dob,d.gender,d.address,d.phone,ds.designation_name," +
                    "d.available_hr,d.qualification,show_Doctor_Salary(?) from doctor d,designation ds where d.speciality=ds.id and d.doctor_id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, doc_id);
            ps.setString(2, doc_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(String.valueOf(rs.getDate(4)));
                for (int i = 5; i < 11; i++) {
                    list.add(rs.getString(i));
                }
                list.add(String.valueOf(rs.getInt(11)));
            }
            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
