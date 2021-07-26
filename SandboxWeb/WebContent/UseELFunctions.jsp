<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="elfn" uri="ELFunctions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demonstrate Custom EL Functions</title>
</head>
<body>
	<h2>SQUARE of 2.0:</h2>
	<span>${elfn:square(2.0)}</span>
	<h2>CUBE of 2.0:</h2>
	<span>${elfn:cube(2.0)}</span>
	<h2>SQUARE ROOT of 16.0</h2>
	<span>${elfn:sqrt(16.0)}</span>
</body>
</html>
