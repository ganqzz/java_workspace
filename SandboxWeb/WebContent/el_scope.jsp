<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>EL Scope Demo</title>
  </head>
  <body>
    <h1>${requestScope.hoge} - ${sessionScope.fuga} - ${applicationScope.piyo}</h1>
    <h2>${hoge} - ${fuga} - ${piyo}</h2>

    <ul>
      <li>指定なし: ${same}
      <li>request: ${requestScope.same}
      <li>session: ${sessionScope.same}
      <li>application: ${applicationScope.same}
    </ul>

    <% pageContext.setAttribute("same", "PageScopeValue"); %>

    <h3>${same}</h3>
  </body>
</html>
