package model;

import java.sql.Date;

public class Teacher {

    private int teacherID;
    private String teacherName;
    private boolean teacherGender;
    private String teacherAddress;
    private String teacherEmail;
    private String teacherPhone;
    private Date teacherDOB;
    private Account teacherUsername;

    public Account getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(Account teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(boolean teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Date getTeacherDOB() {
        return teacherDOB;
    }

    public void setTeacherDOB(Date teacherDOB) {
        this.teacherDOB = teacherDOB;
    }
}
