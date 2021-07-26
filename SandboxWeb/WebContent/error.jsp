<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <title>Oops!</title>
  </head>
  <body>
    <h2>Oops!</h2>
    <div>
      <pre>
        ${pageContext.exception.message}
        ${param.ex}
      </pre>
    </div>
  </body>
</html>
