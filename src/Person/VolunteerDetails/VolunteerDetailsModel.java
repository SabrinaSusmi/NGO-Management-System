package Person.VolunteerDetails;

import Utilities.OracleConnection;
import Person.PersonalInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolunteerDetailsModel {
    public static boolean isMatchSuccessful(String newValue, Volunteer Volunteer) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String lowerCaseResult = newValue.toLowerCase();
        if (String.valueOf(Volunteer.getId()).contains(lowerCaseResult)) {
            return true;
        } else return (Volunteer.getName()).contains(lowerCaseResult);
    }
    public static ObservableList<Volunteer> getVolunteerTableRecords() throws SQLException {
        String sql = "select Volunteer_id,name,email,phone from Volunteer order by Volunteer_id";
        ObservableList<Volunteer> VolunteerList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Volunteer_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Volunteer Volunteer = new Volunteer(name, email, phone, id);

                VolunteerList.add(Volunteer);
            }
            return VolunteerList;
        } catch (Exception e) {
            System.out.println("getTableRecords : VolunteerModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected boolean isDeleteVolunteerSuccessful(String id) {
        String sql = "delete from Volunteer where Volunteer_id=?";
        return new PersonalInformation().isDeleteSuccessful(id,sql);

    }
}
