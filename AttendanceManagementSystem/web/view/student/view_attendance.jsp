<%-- 
    Document   : view_attendance
    Created on : Feb 20, 2022, 8:20:31 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </head>
    <body>
        <header>
            <a href="../student/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
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
                            <c:forEach items="${requestScope.listSubject}" var="s">
                                <option value="${s.subjectID}" <c:if test="${requestScope.subjectID==s.subjectID}">selected="selected"</c:if>>${s.subjectName}</option>
                            </c:forEach>
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
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.listStudentAttendance}" var="sa">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${sa.scheduleDate}</td>
                                <td>${sa.timeslot.timeSlotStart} - ${sa.timeslot.timeSlotEnd}</td>
                                <td>${sa.teacherName}</td>
                                <td>${sa.className}</td>
                                <td><c:if test="${sa.present}"><span style="color: rgb(0, 128, 0)">Present</span></c:if><c:if test="${sa.present==false}"><span style="color: rgb(255, 0, 0)">Absent</span></c:if></td>
                            </tr>
                        </c:forEach>
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
