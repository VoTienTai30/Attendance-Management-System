<%-- 
    Document   : edit_teacher
    Created on : Jan 28, 2022, 11:14:59 PM
    Author     : midni
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Teacher</title>
        <link href="../../css/EditTeacherAdminStyle.css" rel="stylesheet" type="text/css"/>
        <%
            int teacherID = Integer.parseInt(request.getAttribute("teacherID").toString());
            Account acc = (Account) request.getSession().getAttribute("account");
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

        <div id="teacher">
            <form action="../teacher/edit" method="post">
                <input type="hidden" name="teacherID" value="<%=teacherID%>">
                <table>
                    <tr>
                        <th colspan="2">Edit Teacher</th>
                    </tr>
                    <tr>
                        <td><span>Teacher Name:</span></td>
                        <td><input name="teacherName" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Teacher Gender:</span></td>
                        <td>
                            <input type="radio" name="teacherGender" value="1">Male
                            <input type="radio" name="teacherGender" value="0">Female
                        </td>
                    </tr>
                    <tr>
                        <td><span>Teacher Address:</span></td>
                        <td><input name="teacherAddress" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Teacher Email:</span></td>
                        <td><input name="teacherMail" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Teacher Phone:</span></td>
                        <td><input name="teacherPhone" type="text" class="input-box"></td>
                    </tr>
                    <tr>
                        <td><span>Date of Birth:</span></td>
                        <td><input type="date" name="teacherDOB" class="input-box"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" id="submit-btn" value="Save"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
