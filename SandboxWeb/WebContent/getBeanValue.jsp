<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
    <jsp:useBean id="nameBean" class="com.example.model.Name" scope="session" />
    <p>First Name : <jsp:getProperty name="nameBean" property="firstName" /></p>
    <p>Last Name : <jsp:getProperty name="nameBean" property="lastName" /></p>
    <a href="JavaBeanSetDemo.jsp">Return</a>
  </body>
</html>
