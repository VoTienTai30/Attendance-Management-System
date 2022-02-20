<%-- 
    Document   : view_attendance
    Created on : Feb 20, 2022, 8:20:31 PM
    Author     : midni
--%>

<%@page import="model.Subject"%>
<%@page import="model.StudentAttendance"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.SubjectDBContext"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance</title>
        <link href="../css/ViewAttendance.css" rel="stylesheet" type="text/css"/>
        <%
            Account acc = (Account) request.getSession().getAttribute("account");
            int subjectID = Integer.parseInt(request.getAttribute("subjectID").toString());
            ArrayList<Subject> listSubject = (ArrayList<Subject>) request.getAttribute("listSubject");
            ArrayList<StudentAttendance> studentAttendance = (ArrayList<StudentAttendance>) request.getAttribute("listStudentAttendance");
        %>
    </head>
    <body>
        <header>
            <a href="../student/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../student/home">Home</a>
            <a href="#">View Attendance</a>
        </div>

        <div id="attendance">
            <div id="attendance-container">
                <div class="attendance-title">View Attendance</div>

                <div id="search-form">
                    <form action="view-attendance" id="searchAttendance" method="POST">
                        <select name="subjectID" onchange="submitForm()">
                            <% for (int i = 0; i < listSubject.size(); i++) {
                                    Subject s = listSubject.get(i);%>
                            <option value="<%=s.getSubjectID()%>" <%if (subjectID == s.getSubjectID()) {%>selected="selected"<%}%>><%=s.getSubjectName()%></option>
                            <%} %>
                        </select>
                    </form> 
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Date</th>
                            <th>Time Slot</th>
                            <th>Teacher</th>
                            <th>Class</th>
                            <th>Attendance</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (int i = 0; i < studentAttendance.size(); i++) {
                                StudentAttendance sa = studentAttendance.get(i);
                        %>
                        <tr>
                            <td><%=i + 1%></td>
                            <td><%=sa.getScheduleDate()%></td>
                            <td><%=sa.getTimeslot().getTimeSlotStart()%> - <%=sa.getTimeslot().getTimeSlotEnd()%></td>
                            <td><%=sa.getTeacherName()%></td>
                            <td><%=sa.getClassName()%></td>
                            <td><% if (sa.isPresent()) {%><span style="color: rgb(0, 128, 0)">Present</span><%} else {%><span style="color: rgb(255, 0, 0)">Absent</span><%} %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            function submitForm() {
                document.getElementById('searchAttendance').submit();
            }
        </script>
    </body>
</html>
