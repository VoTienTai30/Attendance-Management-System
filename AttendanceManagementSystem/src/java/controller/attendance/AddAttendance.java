/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.attendance;

import dal.AttendanceDBContext;
import dal.ClassMemberDBContext;
import dal.ScheduleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Attendance;
import model.ClassMember;
import model.Schedule;

/**
 *
 * @author midni
 */
public class AddAttendance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> listAttendance = new ArrayList<>();
        ArrayList<Schedule> listSchedule = scheduleDB.getSchedule(null);
        ClassMemberDBContext classMemberDB = new ClassMemberDBContext();
        ArrayList<ClassMember> listStudent = classMemberDB.getClassMemberByClassID(Integer.parseInt(request.getAttribute("classID").toString()));
        for (int i = 0; i < listStudent.size(); i++) {
            Attendance a = new Attendance();
            a.setScheduleID(listSchedule.get(listSchedule.size() - 1));
            a.setStudentID(listStudent.get(i).getStudentID());
            listAttendance.add(a);
        }
        attendanceDB.addAttendance(listAttendance);

        response.sendRedirect("../../admin/schedule");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
