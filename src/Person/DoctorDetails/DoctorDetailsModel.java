package Person.DoctorDetails;


import Utilities.OracleConnection;
import Person.PersonalInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDetailsModel {


    public void getSpeciality(ObservableList<String> list) {
        String sql = "select designation_name from designation where type='Doctor'";
        OracleConnection oc = new OracleConnection();
        try {
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\n setDoctorInformation -> DoctorDetailsModel");
        }
    }

    protected static boolean isMatchSuccessful(String newValue, Doctor doctor) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String lowerCaseResult = newValue.toLowerCase();
        if (String.valueOf(doctor.getId()).contains(lowerCaseResult)) {
            return true;
        } else if ((doctor.getName()).contains(lowerCaseResult)) {
            return true;
        } else return (doctor.getSpeciality()).contains(lowerCaseResult);
    }


    protected static ObservableList<Doctor> getDoctorTableRecords() throws SQLException {
        String sql = "select doctor_id,name,email,nvl(phone,'N/A') phone,designation_name from doctor,designation where speciality=id order by doctor_id";
        ObservableList<Doctor> docList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("doctor_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String speciality = rs.getString(5);

                Doctor doctor = new Doctor(name, email, phone, speciality, id);

                docList.add(doctor);
            }
            return docList;
        } catch (Exception e) {
            System.out.println("getTableRecords : DoctorModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected boolean isDeleteDoctorSuccessful(String id) {

        String sql = "delete from doctor where doctor_id=?";
        return new PersonalInformation().isDeleteSuccessful(id,sql);

    }

}
