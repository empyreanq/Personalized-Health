<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/11/2022
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script type="text/javascript" src="1.js"></script>
  <link rel="stylesheet" href="styles.css">
</head>
<body><div class="norma">
    <form action="therapyServlet" method="post">
        <label for="usid">User id:
            <input type="number" id="usid" name="usid"></label><br><br>
        <label for="bloodid">Bloodtest id:
            <input type="number" id="bloodid" name="bloodid"></label><br><br>
        <label for="startit">Starting date:
            <input type="date" id="startit" name="startit"></label><br><br>
        <label for="endit">Ending date:
            <input type="date" id="endit" name="endit"></label><br><br>
        <label for="treatext">Treatment text:
            <input type="text" id="treatext" name="treatext"></label><br><br>
        <input type="submit" value="Post treatment">
    </form>
</div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>
</body>
</html>
