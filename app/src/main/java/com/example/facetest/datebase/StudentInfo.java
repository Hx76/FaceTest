package com.example.facetest.datebase;

import org.litepal.crud.LitePalSupport;

public class StudentInfo extends LitePalSupport {
    public String getStudent_Number() {
        return Student_Number;
    }

    public void setStudent_Number(String student_Number) {
        Student_Number = student_Number;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getID_Number() {
        return ID_Number;
    }

    public void setID_Number(String ID_Number) {
        this.ID_Number = ID_Number;
    }

    private String Student_Number;
    private String Student_Name;
    private String ID_Number;

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    private String Major;

}
