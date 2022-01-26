<%-- 
    Document   : newjsp
    Created on : Jan 26, 2022, 7:43:55 PM
    Author     : midni
--%>

<%@page import="model.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% ArrayList<Subject> list = (ArrayList<Subject>) request.getAttribute("list");%>
    </head>
    <body>
        <h1>hello</h1>
        <% for (int i = 0; i < list.size(); i++) {
                Subject s = list.get(i);%>
        <h5><%=s.getSubjectName()%></h5>
        <%}%>
    </body>
</html>
