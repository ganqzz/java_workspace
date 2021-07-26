<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
  </head>
  <body>
    <form name="f1" action="setBeanValue.jsp" method="post">
      <p><input type="text" name="firstName" PlaceHolder="Enter FirstName" /></p>
      <p><input type="text" name="lastName" PlaceHolder="Enter LastName" /></p>
      <input type="submit" value="Submit" />
    </form>
    <hr>
    <form name="f2" action="rewrite_params.do" method="post">
      <p><input type="text" name="fn" PlaceHolder="Enter FirstName" /></p>
      <p><input type="text" name="ln" PlaceHolder="Enter LastName" /></p>
      <input type="submit" value="Submit" />
    </form>
    <hr>
    <form name="clear" action="clear_session_cookies" method="post">
      <input type="submit" value="Clear session and cookies" />
    </form>
  </body>
</html>
