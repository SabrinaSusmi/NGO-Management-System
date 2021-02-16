package Person.DonorDetails;


import Utilities.OracleConnection;
import Person.PersonalInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonorDetailsModel {
    protected static boolean isMatchSuccessful(String newValue, Donor donor) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String lowerCaseResult = newValue.toLowerCase();
        if (String.valueOf(donor.getId()).contains(lowerCaseResult)) {
            return true;
        } else return (donor.getName()).contains(lowerCaseResult);
    }
    protected static ObservableList<Donor> getDonorTableRecords() throws SQLException {
        String sql = "select Donor_id,name,email,nvl(phone,'N/A') phone from Donor order by Donor_id";
        ObservableList<Donor> donorList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Donor_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                Donor Donor = new Donor(name, email, phone, id);

                donorList.add(Donor);
            }
            return donorList;
        } catch (Exception e) {
            System.out.println("getTableRecords : DonorModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected boolean isDeleteDonorSuccessful(String id) {
        String sql = "delete from Donor where Donor_id=?";
        return new PersonalInformation().isDeleteSuccessful(id,sql);

    }
}
