<%-- 
    Document   : edit_schedule
    Created on : Jan 30, 2022, 10:10:30 PM
    Author     : midni
--%>

<%@page import="model.TimeSlot"%>
<%@page import="model.Subject"%>
<%@page import="model.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Schedule</title>
        <link href="../../css/EditScheduleAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            String scheduleID = request.getAttribute("scheduleID").toString();
            Account acc = (Account) request.getSession().getAttribute("account");
            ArrayList<Teacher> listTeacher = (ArrayList<Teacher>) request.getAttribute("listTeacher");
            ArrayList<Subject> listSubject = (ArrayList<Subject>) request.getAttribute("listSubject");
            ArrayList<model.Class> listClass = (ArrayList<model.Class>) request.getAttribute("listClass");
            ArrayList<TimeSlot> listTimeSlot = (ArrayList<TimeSlot>) request.getAttribute("listTimeSlot");
        %>
    </head>
    <body>
        <header>
            <a href="../../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../../admin/home">Home</a>
            <a href="../../admin/subject">Subject</a>
            <a href="../../admin/class">Class</a>
            <a href="../../admin/teacher">Teacher</a>
            <a href="../../admin/student">Student</a>
            <a href="../../admin/schedule">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../../admin/account">Total Account</a>
        </div>

        <div id="schedule">
            <form action="../schedule/edit" method="post">
                <input type="hidden" name="scheduleID" value="<%=scheduleID%>">
                <table>
                    <tr>
                        <th colspan="2">Edit Schedule</th>
                    </tr>
                    <tr>
                        <td>Teacher:</td>
                        <td>
                            <select name="teacherID">
                                <% for (int i = 0; i < listTeacher.size(); i++) {
                                        Teacher teacher = listTeacher.get(i);
                                %>
                                <option value="<%=teacher.getTeacherID()%>"><%=teacher.getTeacherName()%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Subject:</td>
                        <td>
                            <select name="subjectID">
                                <% for (int i = 0; i < listSubject.size(); i++) {
                                        Subject subject = listSubject.get(i);
                                %>
                                <option value="<%=subject.getSubjectID()%>"><%=subject.getSubjectName()%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Class:</td>
                        <td>
                            <select name="classID">
                                <% for (int i = 0; i < listClass.size(); i++) {
                                        model.Class classes = listClass.get(i);
                                %>
                                <option value="<%=classes.getClassID()%>"><%=classes.getClassName()%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 70px;">Time Slot:</td>
                        <td>
                            <select name="timeSlotID">
                                <% for (int i = 0; i < listTimeSlot.size(); i++) {
                                        TimeSlot timeSlot = listTimeSlot.get(i);
                                %>
                                <option value="<%=timeSlot.getTimeSlotID()%>"><%=timeSlot.getTimeSlotStart()%> - <%=timeSlot.getTimeSlotEnd()%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Date:</td>
                        <td><input name="date" type="date" style="width: 79%;"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
