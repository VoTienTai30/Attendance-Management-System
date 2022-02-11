<%-- 
    Document   : edit_student
    Created on : Jan 29, 2022, 10:17:12 PM
    Author     : midni
--%>

<%@page import="model.Student"%>
<%@page import="dal.StudentDBContext"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student</title>
        <link href="../../css/EditStudentAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            String studentID = request.getAttribute("studentID").toString();
            Account acc = (Account) request.getSession().getAttribute("account");
            ArrayList<model.Class> listClass = (ArrayList<model.Class>) request.getAttribute("listClass");
            StudentDBContext studentDB = new StudentDBContext();
            Student s = studentDB.getStudentByID(studentID);
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

        <div id="student">
            <form action="../student/edit" method="post">
                <table>
                    <tr>
                        <th colspan="2">Edit Student</th>
                    </tr>
                    <tr>
                        <td><span>Student ID:</span></td>
                        <td>
                            <span><%=studentID%></span>
                            <input type="hidden" name="studentID" value="<%=studentID%>">
                        </td>
                    </tr>
                    <tr>
                        <td><span>Student Name:</span></td>
                        <td><input name="studentName" type="text" class="input-box" value="<%=s.getStudentName()%>"></td>
                    </tr>
                    <tr>
                        <td><span>Student Gender:</span></td>
                        <td>
                            <input <% if (s.isStudentGender()) {%>checked="checked"<%}%> type="radio" name="studentGender" value="1">Male
                            <input <% if (s.isStudentGender() == false) {%>checked="checked"<%}%> type="radio" name="studentGender" value="0">Female
                        </td>
                    </tr>
                    <tr>
                        <td><span>Student Address:</span></td>
                        <td><input name="studentAddress" type="text" class="input-box" value="<%=s.getStudentAddress()%>"></td>
                    </tr>
                    <tr>
                        <td><span>Student Email:</span></td>
                        <td><input name="studentEmail" type="text" class="input-box" value="<%=s.getStudentEmail()%>"></td>
                    </tr>
                    <tr>
                        <td><span>Student Phone:</span></td>
                        <td><input name="studentPhone" type="text" class="input-box" value="<%=s.getStudentPhone()%>"></td>
                    </tr>
                    <tr>
                        <td><span>Date of Birth:</span></td>
                        <td><input type="date" name="studentDOB" class="input-box" value="<%=s.getStudentDOB()%>"></td>
                    </tr>
                    <tr>
                        <td>Class:</td>
                        <td>
                            <select name="class">
                                <%
                                    for (int i = 0; i < listClass.size(); i++) {
                                        model.Class c = listClass.get(i);
                                %>
                                <option <% if (s.getClassID().getClassID() == c.getClassID()) {%>selected="selected"<%}%> value="<%=c.getClassID()%>"><%=c.getClassName()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Semester:</td>
                        <td>
                            <select name="semester">
                                <option <% if (s.getSemester().getSemesterID() == 1) {%>selected="selected"<%}%> value="1">Semester 1</option>
                                <option <% if (s.getSemester().getSemesterID() == 2) {%>selected="selected"<%}%> value="2">Semester 2</option>
                                <option <% if (s.getSemester().getSemesterID() == 3) {%>selected="selected"<%}%> value="3">Semester 3</option>
                                <option <% if (s.getSemester().getSemesterID() == 4) {%>selected="selected"<%}%> value="4">Semester 4</option>
                                <option <% if (s.getSemester().getSemesterID() == 5) {%>selected="selected"<%}%> value="5">Semester 5</option>
                                <option <% if (s.getSemester().getSemesterID() == 6) {%>selected="selected"<%}%> value="6">Semester 6</option>
                                <option <% if (s.getSemester().getSemesterID() == 7) {%>selected="selected"<%}%> value="7">Semester 7</option>
                                <option <% if (s.getSemester().getSemesterID() == 8) {%>selected="selected"<%}%> value="8">Semester 8</option>
                                <option <% if (s.getSemester().getSemesterID() == 9) {%>selected="selected"<%}%> value="9">Semester 9</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span>Username:</span></td>
                        <td>
                            <span><%=s.getStudentUsername().getUser()%></span>
                            <input type="hidden" name="user" class="input-box" value="<%=s.getStudentUsername().getUser()%>">
                        </td>
                    </tr>
                    <tr>
                        <td><span>Password:</span></td>
                        <td><input type="text" name="pass" class="input-box" value="<%=s.getStudentUsername().getPass()%>"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
