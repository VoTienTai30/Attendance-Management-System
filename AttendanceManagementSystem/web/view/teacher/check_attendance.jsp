<%-- 
    Document   : home_teacher
    Created on : Feb 1, 2022, 11:54:15 AM
    Author     : midni
--%>

<%@page import="model.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="../css/CheckAttendanceStyle.css" rel="stylesheet" type="text/css"/>
        <%
            Account acc = (Account) request.getSession().getAttribute("account");
            Date date = (Date) request.getAttribute("date");
            ArrayList<Schedule> list = (ArrayList) request.getAttribute("list");
        %>
    </head>
    <body>
        <header>
            <a href="../teacher/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a></div>
        </header>

        <div id="schedule-container">
            <div>
                <form action="check-attendance" method="POST">
                    <input type="date" name="dateSearch" value="<%=date%>">
                    <input type="submit" value="Search">
                </form>

                <table id="schedule-table">
                    <tr>
                        <th>#</th>
                        <th>Subject Code</th>
                        <th>Subject Name</th>
                        <th>Class</th>
                        <th>Time Slot</th>
                        <th>Date</th>
                        <th>Action</th>
                    </tr>
                    <%
                        int index = 1;
                        for (int i = 0; i < list.size(); i++) {
                            Schedule s = list.get(i);
                            if (s.getScheduleDate().compareTo(Date.valueOf(date.toString())) == 0) {
                    %>
                    <tr>
                        <td><%=index%></td>
                        <td><%=s.getSubjectID().getSubjectCode()%></td>
                        <td><%=s.getSubjectID().getSubjectName()%></td>
                        <td><%=s.getClassID().getClassName()%></td>
                        <td><%=s.getTimeSlotID().getTimeSlotStart()%> - <%=s.getTimeSlotID().getTimeSlotEnd()%></td>
                        <td><%=s.getScheduleDate()%></td>
                        <td><a href="#">Check</a></td>
                    </tr>
                    <%
                                index = index + 1;
                            }
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
