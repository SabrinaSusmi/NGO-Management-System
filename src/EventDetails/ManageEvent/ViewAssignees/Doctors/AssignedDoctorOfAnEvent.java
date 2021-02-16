package EventDetails.ManageEvent.ViewAssignees.Doctors;

public class AssignedDoctorOfAnEvent {
    String name,email,phone,speciality,qualification,id;

    public AssignedDoctorOfAnEvent(String name, String email, String phone, String speciality, String qualification, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.speciality = speciality;
        this.qualification = qualification;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
