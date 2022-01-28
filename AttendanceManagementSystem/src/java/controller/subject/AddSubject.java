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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddSubject</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSubject at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
