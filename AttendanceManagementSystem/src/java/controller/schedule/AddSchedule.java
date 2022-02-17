/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import dal.AttendanceDBContext;
import dal.ClassDBContext;
import dal.ClassMemberDBContext;
import dal.ScheduleDBContext;
import dal.SubjectDBContext;
import dal.TeacherDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.sql.Date;
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
public class AddSchedule extends HttpServlet {

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
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();

        Schedule schedule = new Schedule();

        schedule.setTeacherID(teacherDB.getTeacherByID(Integer.parseInt(request.getParameter("teacherID"))));
        schedule.setSubjectID(subjectDB.getSubjectByID(Integer.parseInt(request.getParameter("subjectID"))));
        schedule.setClassID(classDB.getClasseByID(Integer.parseInt(request.getParameter("classID"))));
        schedule.setTimeSlotID(timeSlotDB.getTimeSlot(Integer.parseInt(request.getParameter("timeSlotID"))).get(0));
        schedule.setScheduleDate(Date.valueOf(request.getParameter("date")));

        request.setAttribute("classID", request.getParameter("classID"));
        scheduleDB.addSchedule(schedule);
        request.getRequestDispatcher("../../attendance/add").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
