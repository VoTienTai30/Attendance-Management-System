<%-- 
    Document   : schedule_admin
    Created on : Jan 30, 2022, 5:00:51 PM
    Author     : midni
--%>

<%@page import="model.TimeSlot"%>
<%@page import="model.Subject"%>
<%@page import="model.Teacher"%>
<%@page import="model.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <link href="../css/ScheduleAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            Account acc = (Account) request.getSession().getAttribute("account");
            ArrayList<Schedule> list = (ArrayList<Schedule>) request.getAttribute("list");
            ArrayList<Teacher> listTeacher = (ArrayList<Teacher>) request.getAttribute("listTeacher");
            ArrayList<Subject> listSubject = (ArrayList<Subject>) request.getAttribute("listSubject");
            ArrayList<model.Class> listClass = (ArrayList<model.Class>) request.getAttribute("listClass");
            ArrayList<TimeSlot> listTimeSlot = (ArrayList<TimeSlot>) request.getAttribute("listTimeSlot");
        %>
    </head>
    <body>
        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: <%=acc.getDisplayName()%> | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../admin/home">Home</a>
            <a href="../admin/subject">Subject</a>
            <a href="../admin/class">Class</a>
            <a href="../admin/teacher">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="#">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="#">Total Account</a>
        </div>

        <div id="schedule">
            <div id="add-schedule">
                <div class="schedule-title">Add New Schedule</div>
                <div id="add-schedule-form">
                    <form action="../admin/schedule/add" method="post">
                        <table>
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
                                <td><input name="date" type="date"></td>
                            </tr>
                            <tr id="save-btn"><td colspan="2"><input type="submit" value="Save"></td></tr>
                        </table>
                    </form>
                </div>
            </div>

            <div id="schedule-list">
                <div class="schedule-title">Schedule List</div>
                <form action="../admin/schedule" method="post">
                    <input name="date" type="date">
                    <input type="submit" value="Search">
                </form>
                <table id="schedule-table" border="1px" border-collapse="collapse">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Date</th>
                            <th>Time Slot</th>
                            <th>Class Name</th>
                            <th>Teacher Name</th>
                            <th>Subject Name</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < list.size(); i++) {
                                Schedule schedule = list.get(i);
                        %>
                        <tr>
                            <td><%=i + 1%></td>
                            <td><%=schedule.getScheduleDate()%></td>
                            <td><%=schedule.getTimeSlotID().getTimeSlotStart()%> - <%=schedule.getTimeSlotID().getTimeSlotEnd()%></td>
                            <td><%=schedule.getClassID().getClassName()%></td>
                            <td><%=schedule.getTeacherID().getTeacherName()%></td>
                            <td><%=schedule.getSubjectID().getSubjectName()%></td>
                            <td><a href="../admin/schedule/edit?id=<%=schedule.getScheduleID()%>">Edit</a></td>
                            <td><a href="../admin/schedule/delete?id=<%=schedule.getScheduleID()%>">Delete</a></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
