<%-- 
    Document   : schedule_admin
    Created on : Jan 30, 2022, 5:00:51 PM
    Author     : midni
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Date"%>
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
    </head>
    <body>
        <c:set var="totalPage" value="${requestScope.totalPage}"></c:set>
        <c:set var="dateSearch" value="${null}"></c:set>
        <c:set var="pageIndex" value="1"></c:set>
        <c:if test="${requestScope.pageIndex != null}">
            <c:set var="pageIndex" value="${requestScope.pageIndex}"></c:set>
        </c:if>

        <header>
            <a href="../admin/home" id="header-title">Student Attendance Management System</a>
            <div id="logout">Welcome: ${sessionScope.account.displayName} | <a href="../logout">Log out</a> </div>
        </header>

        <div id="nav-bar">
            <a href="../admin/home">Home</a>
            <a href="../admin/subject">Subject</a>
            <a href="../admin/class">Class</a>
            <a href="../admin/teacher">Teacher</a>
            <a href="../admin/student">Student</a>
            <a href="#">Schedule</a>
            <a href="#">Attendance Report</a>
            <a href="../admin/account">Total Account</a>
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
                                        <c:forEach items="${requestScope.listTeacher}" var="teacher">
                                            <option value="${teacher.teacherID}">${teacher.teacherName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Subject:</td>
                                <td>
                                    <select name="subjectID">
                                        <c:forEach items="${requestScope.listSubject}" var="subject">
                                            <option value="${subject.subjectID}">${subject.subjectName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Class:</td>
                                <td>
                                    <select name="classID">
                                        <c:forEach items="${requestScope.listClass}" var="classes">
                                            <option value="${classes.classID}">${classes.className}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 70px;">Time Slot:</td>
                                <td>
                                    <select name="timeSlotID">
                                        <c:forEach items="${requestScope.listTimeSlot}" var="timeSlot">
                                            <option value="${timeSlot.timeSlotID}">${timeSlot.timeSlotStart} - ${timeSlot.timeSlotEnd}</option>
                                        </c:forEach>
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

                <form id="searchDateForm" action="../admin/schedule" method="post">
                    <input name="dateSearch" type="date" 
                           <c:if test="${requestScope.dateSearch != null}">
                               value="${requestScope.dateSearch}"
                               <c:set var="dateSearch" value="${requestScope.dateSearch}"></c:set>
                           </c:if>>
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
                        <c:set var="i" value="1"></c:set>
                        <c:forEach items="${requestScope.list}" var="schedule">
                            <tr>
                                <td>${i}<c:set var="i" value="${i+1}"></c:set></td>
                                <td>${schedule.scheduleDate}</td>
                                <td>${schedule.timeSlotID.timeSlotStart} - ${schedule.timeSlotID.timeSlotEnd}</td>
                                <td>${schedule.classID.className}</td>
                                <td>${schedule.teacherID.teacherName}</td>
                                <td>${schedule.subjectID.subjectName}</td>
                                <td><a href="../admin/schedule/edit?id=${schedule.scheduleID}">Edit</a></td>
                                <td><a href="../admin/schedule/delete?id=${schedule.scheduleID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <form id="searchPage" action="../admin/schedule" method="POST">
                    Page: <input type="number" name="pageIndex" value="${pageIndex}" size="${totalPage}" />/${totalPage}
                    <input type="hidden" name="dateSearch" value="${dateSearch}">
                    <input id="goPage" type="submit" value="Go" />
                </form>
            </div>
        </div>
    </body>
</html>
