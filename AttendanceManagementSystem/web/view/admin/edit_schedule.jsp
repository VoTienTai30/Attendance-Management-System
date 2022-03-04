<%-- 
    Document   : edit_schedule
    Created on : Jan 30, 2022, 10:10:30 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.ScheduleDBContext"%>
<%@page import="model.Schedule"%>
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
    </head>
    <body>
        <header>
            <a href="../../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../../logout">Log out</a> </div>
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
                <input type="hidden" name="scheduleID" value="${requestScope.schedule.scheduleID}">
                <table>
                    <tr>
                        <th colspan="2">Edit Schedule</th>
                    </tr>
                    <tr>
                        <td>Teacher:</td>
                        <td>
                            <select name="teacherID">
                                <c:forEach items="${requestScope.listTeacher}" var="teacher">
                                    <option <c:if test="${requestScope.schedule.teacherID.teacherID==teacher.teacherID}">selected="selected"</c:if> value="${teacher.teacherID}">${teacher.teacherName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Subject:</td>
                        <td>
                            <select name="subjectID">
                                <c:forEach items="${requestScope.listSubject}" var="subject">
                                    <option <c:if test="${requestScope.schedule.subjectID.subjectID==subject.subjectID}">selected="selected"</c:if> value="${subject.subjectID}">${subject.subjectName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Class:</td>
                        <td>
                            <select name="classID">
                                <c:forEach items="${requestScope.listClass}" var="class">
                                    <option <c:if test="${requestScope.schedule.classID.classID==class.classID}">selected="selected"</c:if> value="${class.classID}">${class.className}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 70px;">Time Slot:</td>
                        <td>
                            <select name="timeSlotID">
                                <c:forEach items="${requestScope.listTimeSlot}" var="timeSlot">
                                    <option <c:if test="${requestScope.schedule.timeSlotID.timeSlotID==timeSlot.timeSlotID}">selected="selected"</c:if> value="${timeSlot.timeSlotID}">${timeSlot.timeSlotStart} - ${timeSlot.timeSlotEnd}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Date:</td>
                        <td><input value="${requestScope.schedule.scheduleDate}" name="date" type="date" style="width: 79%;"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
