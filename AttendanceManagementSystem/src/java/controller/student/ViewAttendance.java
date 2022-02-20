/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import dal.StudentAttendanceDBContext;
import dal.StudentDBContext;
import dal.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Student;
import model.StudentAttendance;
import model.Subject;

/**
 *
 * @author midni
 */
public class ViewAttendance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentAttendanceDBContext saDB = new StudentAttendanceDBContext();
        StudentDBContext studentDB = new StudentDBContext();

        Account acc = (Account) request.getSession().getAttribute("account");
        Student student = studentDB.getStudentByUsername(acc.getUser());
        ArrayList<Integer> listSubjectID = saDB.listSubjectID(student.getStudentID());
        ArrayList<StudentAttendance> studentAttendance = saDB.getStudentAttendance(student.getStudentID(), listSubjectID.get(0));
        request.setAttribute("listSubjectID", listSubjectID);
        request.setAttribute("listStudentAttendance", studentAttendance);
        request.setAttribute("subjectID", listSubjectID.get(0));
        SubjectDBContext subjectDB = new SubjectDBContext();
        ArrayList<Subject> listSubject = new ArrayList<>();
        for (int i = 0; i < listSubjectID.size(); i++) {
            int id = listSubjectID.get(i);
            listSubject.add(subjectDB.getSubjectByID(id));
        }
        request.setAttribute("listSubject", listSubject);

        request.getRequestDispatcher("../view/student/view_attendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentAttendanceDBContext saDB = new StudentAttendanceDBContext();
        StudentDBContext studentDB = new StudentDBContext();

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        Account acc = (Account) request.getSession().getAttribute("account");
        Student student = studentDB.getStudentByUsername(acc.getUser());
        ArrayList<Integer> listSubjectID = saDB.listSubjectID(student.getStudentID());
        ArrayList<StudentAttendance> studentAttendance = saDB.getStudentAttendance(student.getStudentID(), subjectID);
        request.setAttribute("listSubjectID", listSubjectID);
        request.setAttribute("listStudentAttendance", studentAttendance);
        request.setAttribute("subjectID", subjectID);
        SubjectDBContext subjectDB = new SubjectDBContext();
        ArrayList<Subject> listSubject = new ArrayList<>();
        for (int i = 0; i < listSubjectID.size(); i++) {
            int id = listSubjectID.get(i);
            listSubject.add(subjectDB.getSubjectByID(id));
        }
        request.setAttribute("listSubject", listSubject);

        request.getRequestDispatcher("../view/student/view_attendance.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
