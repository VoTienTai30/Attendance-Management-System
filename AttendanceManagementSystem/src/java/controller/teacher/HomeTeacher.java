/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.teacher;

import dal.ScheduleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Schedule;

/**
 *
 * @author midni
 */
public class HomeTeacher extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        request.setAttribute("date", date);
        
        String user = ((Account) request.getSession().getAttribute("account")).getUser();
        
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        ArrayList<Schedule> scheduleByUsername = scheduleDB.getScheduleByUsername(user);
        request.setAttribute("list", scheduleByUsername);
        
        request.getRequestDispatcher("../view/teacher/home_teacher.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date date = Date.valueOf(request.getParameter("dateSearch"));
        request.setAttribute("date", date);
        
        String user = ((Account) request.getSession().getAttribute("account")).getUser();
        
        ScheduleDBContext scheduleDB = new ScheduleDBContext();
        ArrayList<Schedule> scheduleByUsername = scheduleDB.getScheduleByUsername(user);
        request.setAttribute("list", scheduleByUsername);
        
        request.getRequestDispatcher("../view/teacher/home_teacher.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
