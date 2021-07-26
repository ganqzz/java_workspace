<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
    <jsp:useBean id="nameBean" class="com.example.model.Name" scope="session">
      <jsp:setProperty name="nameBean" property="*" /><%-- 全てのpropertyをparamからnameを基にセットする --%>
    </jsp:useBean>
    <p>First Name : <jsp:getProperty name="nameBean" property="firstName" /></p>
    <p>Last Name : <jsp:getProperty name="nameBean" property="lastName" /></p>
    <a href="getBeanValue.jsp">Click here</a>
  </body>
</html>
