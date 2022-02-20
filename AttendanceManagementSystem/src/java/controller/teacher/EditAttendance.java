/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import dal.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Attendance;

/**
 *
 * @author midni
 */
public class EditAttendance extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        int scheduleID = Integer.parseInt(request.getParameter("id"));
        
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        ArrayList<Attendance> listAttendance = attendanceDB.getAttendance(scheduleID);
        request.setAttribute("list", listAttendance);
        
        request.getRequestDispatcher("../view/teacher/edit_attendance.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        AttendanceDBContext attendanceDB = new AttendanceDBContext();
        String[] listAttendanceID = request.getParameter("listAttendanceID").split(" ");
        ArrayList<Attendance> list = new ArrayList<>();
        for (int i = 0; i < listAttendanceID.length; i++) {
            Attendance a = new Attendance();
            a.setAttendenceID(Integer.parseInt(listAttendanceID[i]));
            if (Integer.parseInt(request.getParameter(listAttendanceID[i])) == 1) {
                a.setPresent(true);
            } else {
                a.setPresent(false);
            }
            list.add(a);
        }
        
        attendanceDB.editAttendance(list);
        response.sendRedirect("check-attendance");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
