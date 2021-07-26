<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>JSP Fundamentals</title>
  </head>
  <body>
    <p>
      Converting a String To Upper Case :
      <!-- Expression tag -->
      <%="Hello World".toUpperCase()%>
      <br />
      <%="日本語だよ"%>
    </p>
    <p>
      What is 6 * 4 :
      <%=6 * 4%></p>
    <p>
      Is 2 lesser than 1 :
      <%=2 < 1%></p>
    <p>
      Current Date and Time :
      <%=new java.util.Date()%>
    </p>

    <%! // Diclaration tag

        double getBonus(double salary) {
            return salary * 0.05;
        }
    %>
    <p>
      Bonus for the Employee with the salary 5000 will be :
      <%=getBonus(5000)%>
    </p>

    <% // Scriptlet
        for (int i = 1; i <= 5; i++) {
            out.println(i + "<br/>");
        }
    %>
    <p/>
    
    <h4>${pageContext.request.contextPath}</h4>
  </body>
</html>
