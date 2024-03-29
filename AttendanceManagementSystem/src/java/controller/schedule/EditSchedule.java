/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import dal.AttendanceDBContext;
import dal.ClassDBContext;
import dal.ScheduleDBContext;
import dal.SubjectDBContext;
import dal.TeacherDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Schedule;
import model.Subject;
import model.Teacher;
import model.TimeSlot;

/**
 *
 * @author midni
 */
public class EditSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int scheduleID = Integer.parseInt(request.getParameter("id"));
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        Schedule schedule = scheduleDB.getScheduleByID(scheduleID);
        request.setAttribute("schedule", schedule);

        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();
        ArrayList<Teacher> listTeacher = teacherDB.getTeacher("");
        ArrayList<Subject> listSubject = subjectDB.getSubject("");
        ArrayList<model.Class> listClass = classDB.getClasses("");
        ArrayList<TimeSlot> listTimeSlot = timeSlotDB.getTimeSlot(-1);
        request.setAttribute("listTeacher", listTeacher);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listTimeSlot", listTimeSlot);

        request.getRequestDispatcher("/view/admin/edit_schedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        ScheduleDBContext scheduleDB = new ScheduleDBContext();

        int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));
        int teacherID = Integer.parseInt(request.getParameter("teacherID"));
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        int classID = Integer.parseInt(request.getParameter("classID"));
        int timeSlotID = Integer.parseInt(request.getParameter("timeSlotID"));
        Date date = Date.valueOf(request.getParameter("date"));

        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        attendanceDB.deleteAttendance(scheduleID);

        scheduleDB.editSchedule(scheduleID, teacherID, subjectID, classID, timeSlotID, date);

        request.setAttribute("classID", request.getParameter("classID"));
        request.getRequestDispatcher("../../attendance/add").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
