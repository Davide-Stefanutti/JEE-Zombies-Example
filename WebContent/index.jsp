<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" href="style.css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zombies - JEE - Davide Stefanutti</title>
</head>
<body>

<c:choose>
	<c:when test = "${not win_status || win_status == null}">
			<form name="adv" action="Servlet" method="post">
				<button class="button_green" name="btn" value="adv">Avventura</button>
			</form>
	</c:when>
	<c:when test = "${win_status}">	
			<form name="adv" action="Servlet" method="post">
				<button class="button_red" name="btn" value="adv" disabled>Avventura</button>
			</form>
			GLI ZOMBIE HANNO CONQUISTATO IL MONDO!!! 
			<form name="calc" action="Servlet" method="post">
				<button class="button_yellow" name="btn" value="reset">Reset</button>
			</form>
	</c:when>
</c:choose>
<br>
<hr>
<br>
<c:forEach items="${zombies}" var="zombie"> 

	<img src="${zombie.getImg()}" alt="${zombie.getImg()}" height="100" width="100"> 
	<br>
	Vita: ${zombie.getVita()} / 100<br>
	${zombie.getCommento()}

<br>
<hr>
</c:forEach>


</body>
</html>