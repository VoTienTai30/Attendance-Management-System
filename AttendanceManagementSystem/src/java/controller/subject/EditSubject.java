/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.subject;

import dal.SubjectDBContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subject;

/**
 *
 * @author midni
 */
public class EditSubject extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int subjectID = Integer.parseInt(request.getParameter("id"));
        SubjectDBContext subjectDB = new SubjectDBContext();
        Subject subject = subjectDB.getSubjectByID(subjectID);
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/view/admin/edit_subject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        SubjectDBContext db = new SubjectDBContext();
        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        String subjectCode = request.getParameter("subjectCode");
        int totalSlot = Integer.parseInt(request.getParameter("totalSlot"));
        int semesterID = Integer.parseInt(request.getParameter("semester"));
        String subjectName = request.getParameter("subjectName");
        db.editSubject(subjectID, subjectCode, totalSlot, semesterID, subjectName);
        response.sendRedirect("../subject");
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
