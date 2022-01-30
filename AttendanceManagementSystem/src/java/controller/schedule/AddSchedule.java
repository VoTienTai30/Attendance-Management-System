/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import dal.ClassDBContext;
import dal.ScheduleDBContext;
import dal.SubjectDBContext;
import dal.TeacherDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        scheduleDB.addSchedule(schedule);
        response.sendRedirect("../schedule");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
