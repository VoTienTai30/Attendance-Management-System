/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.subject;

import dal.SubjectDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Semester;
import model.Subject;

/**
 *
 * @author midni
 */
public class AddSubject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        SubjectDBContext db = new SubjectDBContext();
        Subject subject = new Subject();
        Semester semester = new Semester();

        String subjectCode = request.getParameter("subjectCode");
        int totalSlot = Integer.parseInt(request.getParameter("totalSlot"));
        int semesterID = Integer.parseInt(request.getParameter("semester"));
        String semesterName = "Semester " + semesterID;
        semester.setSemesterID(semesterID);
        semester.setSemesterName(semesterName);
        String subjectName = request.getParameter("subjectCode");

        subject.setSubjectCode(subjectCode);
        subject.setTotalSlot(totalSlot);
        subject.setSemester(semester);
        subject.setSubjectName(subjectName);
        db.addSubject(subject);
        response.sendRedirect("../subject");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
