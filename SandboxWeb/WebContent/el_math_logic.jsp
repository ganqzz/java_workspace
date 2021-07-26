<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Some More EL Operations</title>
  </head>
  <body>
    <!-- We can use EL to do plain old math and logic -->
    <h1>EL math and Logic</h1>
    <span>2 + 2 = ${2 + 2}</span><br>
    <span>2 * 6 = ${2 * 6}</span><br>
    <span>16 / 8 = ${16 / 8}</span><br>
    <span>Is 32 > 50? -- ${32 > 50}</span><br>
    <span>Is 32 > 50?</span><input type="text" value="${32 gt 50}"><br>
    <span>Is 100 < 1000? -- ${100 < 1000}</span><br>
    <span>Is 100 < 1000? -- </span><input type="text" value="${100 lt 1000}"><br>
    <span>100 == 100 -- </span><input type="text" value="${100 eq 100}"><br>
    <span>Is (100 < 1000 && 10 < 100) -- ${100 < 1000 && 10 < 100}</span><br>
    <span>Is (100 > 1000 || 10 < 100) -- ${100 > 1000 || 10 < 100}</span><br>
    <%
        //set some page vars
        pageContext.setAttribute("PI", 3.14159);
        pageContext.setAttribute("radius", 2);
        pageContext.setAttribute("width", 40);
        pageContext.setAttribute("length", 10);
    %>
    <span>Results:</span><br>
    <span>Circle Area = ${pageScope.PI} * ${pageScope.radius}^2</span>&nbsp;
    <strong>${pageScope.PI * pageScope.radius * pageScope.radius}</strong><br>
    <span>Square Area = ${pageScope.length} * ${pageScope.length}</span>&nbsp;
    <strong>${pageScope.length * pageScope.length}</strong><br>
    <span>Rectangle Area = ${pageScope.length} * ${pageScope.width}</span>&nbsp;
    <strong>${pageScope.length * pageScope.width}</strong><br>		

    <form name="userCalcs" method="post" action="ShowResults.jsp">

    </form>
  </body>
</html>
