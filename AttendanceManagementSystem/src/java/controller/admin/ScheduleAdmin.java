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

        ArrayList<Schedule> listSchedule = scheduleDB.getSchedule(null, 1, 15);
        ArrayList<Teacher> listTeacher = teacherDB.getTeacher("");
        ArrayList<Subject> listSubject = subjectDB.getSubject("");
        ArrayList<model.Class> listClass = classDB.getClasses("");
        ArrayList<TimeSlot> listTimeSlot = timeSlotDB.getTimeSlot(-1);
        int totalPage = 0;
        if (scheduleDB.count(null) % 15 == 0) {
            totalPage = scheduleDB.count(null) / 15;
        } else {
            totalPage = (scheduleDB.count(null) / 15) + 1;
        }

        request.setAttribute("list", listSchedule);
        request.setAttribute("listTeacher", listTeacher);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listTimeSlot", listTimeSlot);
        request.setAttribute("totalPage", totalPage);

        request.getRequestDispatcher("../view/admin/schedule_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        TeacherDBContext teacherDB = new TeacherDBContext();
        SubjectDBContext subjectDB = new SubjectDBContext();
        ClassDBContext classDB = new ClassDBContext();
        TimeSlotDBContext timeSlotDB = new TimeSlotDBContext();

        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        String dateSearch = request.getParameter("dateSearch");
        if (request.getParameter("dateSearch").compareToIgnoreCase("null") == 0 || request.getParameter("dateSearch").length() == 0) {
            dateSearch = null;
        }
        ArrayList<Schedule> list = scheduleDB.getSchedule(dateSearch, pageIndex, 15);
        ArrayList<Teacher> listTeacher = teacherDB.getTeacher("");
        ArrayList<Subject> listSubject = subjectDB.getSubject("");
        ArrayList<model.Class> listClass = classDB.getClasses("");
        ArrayList<TimeSlot> listTimeSlot = timeSlotDB.getTimeSlot(-1);
        int totalPage = 0;
        if (scheduleDB.count(dateSearch) % 15 == 0) {
            totalPage = scheduleDB.count(dateSearch) / 15;
        } else {
            totalPage = (scheduleDB.count(dateSearch) / 15) + 1;
        }

        request.setAttribute("list", list);
        request.setAttribute("listTeacher", listTeacher);
        request.setAttribute("listSubject", listSubject);
        request.setAttribute("listClass", listClass);
        request.setAttribute("listTimeSlot", listTimeSlot);
        request.setAttribute("dateSearch", dateSearch);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);

        request.getRequestDispatcher("../view/admin/schedule_admin.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
