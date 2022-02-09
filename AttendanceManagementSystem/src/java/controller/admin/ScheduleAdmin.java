/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.ClassDBContext;
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
import model.Schedule;
import model.Subject;
import model.Teacher;
import model.TimeSlot;

/**
 *
 * @author midni
 */
public class ScheduleAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();

        ArrayList<Schedule> listSchedule = scheduleDB.getSchedule(null);
        ArrayList<Teacher> listTeacher = teacherDB.getTeacher("");
        ArrayList<Subject> listSubject = subjectDB.getSubject("");
        ArrayList<model.Class> listClass = classDB.getClasses("");
        ArrayList<TimeSlot> listTimeSlot = timeSlotDB.getTimeSlot(-1);

        request.setAttribute("list", listSchedule);
        request.setAttribute("listTeacher", listTeacher);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listTimeSlot", listTimeSlot);

        request.getRequestDispatcher("../view/admin/schedule_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleDBContext db = new ScheduleDBContext();
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();

        ArrayList<Schedule> list = db.getSchedule(request.getParameter("dateSearch"));
        ArrayList<Teacher> listTeacher = teacherDB.getTeacher("");
        ArrayList<Subject> listSubject = subjectDB.getSubject("");
        ArrayList<model.Class> listClass = classDB.getClasses("");
        ArrayList<TimeSlot> listTimeSlot = timeSlotDB.getTimeSlot(-1);

        request.setAttribute("list", list);
        request.setAttribute("listTeacher", listTeacher);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listTimeSlot", listTimeSlot);

        request.getRequestDispatcher("../view/admin/schedule_admin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
