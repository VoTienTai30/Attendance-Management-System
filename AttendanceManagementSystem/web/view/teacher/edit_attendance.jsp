<%-- 
    Document   : edit_attendance
    Created on : Feb 19, 2022, 4:39:58 PM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page import="model.Attendance"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Attendance</title>
        <link href="../css/EditAttendanceStyle.css" rel="stylesheet" type="text/css"/>
        <%
            Account acc = (Account) request.getSession().getAttribute("account");
            ArrayList<Attendance> listAttendance = (ArrayList<Attendance>) request.getAttribute("list");
        %>
    </head>
    <body>
        <header>
            <a href="../teacher/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a></div>
        </header>

        <div id="nav-bar">
            <a href="../teacher/home">Home</a>
            <a href="../teacher/check-attendance">Check Attendance</a>
        </div>

        <div id="attendance">
            <div id="attendance-container">
                <div class="attendance-title">Check Attendance</div>
                <form method="POST" action="edit-attendance">
                    <input type="hidden" name="listAttendanceID" value="<%
                        for (int i = 0; i < listAttendance.size(); i++) {
                            Attendance a = listAttendance.get(i);%><%=a.getAttendenceID() + " "%><%}%>" />

                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>StudentID</th>
                                <th>Student Name</th>
                                <th>Attendance</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < listAttendance.size(); i++) {
                                    Attendance a = listAttendance.get(i);
                            %>
                            <tr>
                                <td><%=i + 1%></td>
                                <td><%=a.getStudentID().getStudentID()%></td>
                                <td><%=a.getStudentID().getStudentName()%></td>
                                <td>
                                    <input type="radio" name="<%=a.getAttendenceID()%>" value="0" 
                                           <% if (a.isPresent() == false) {%>checked="checked"<%}%>/>Absent
                                    <input type="radio" name="<%=a.getAttendenceID()%>" value="1" 
                                           <% if (a.isPresent()) {%>checked="checked"<%}%>/>Present
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <div id="submit-btn"><input type="submit" value="Save" style="cursor: pointer;"/></div>
                </form>
            </div>
        </div>
    </body>
</html>
