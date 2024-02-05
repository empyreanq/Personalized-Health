<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/11/2022
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css"></head>
<body><div class="norma">
    <form action="bloodTestServlet" method="post">
        <label for="tdate">Blood test date:
            <input type="date" id="tdate" name="tdate" required></label><br><br>
        <label for="medcent">Medical Center:
            <input type="text" id="medcent" name="medcent" required></label><br><br>
        <label for="bloodsug">Blood sugar:
            <input type="number" id="bloodsug" name="bloodsug" required></label><br><br>
        <label for="cholest">Cholesterol:
            <input type="number" id="cholest" name="cholest" required></label><br><br>
        <label for="iron">Iron:
            <input type="number" id="iron" name="iron" required></label><br><br>
        <label for="d3">Vitamin d3:
            <input type="number" id="d3" name="d3" required></label><br><br>
        <label for="b12">Vitamin b12:
            <input type="number" id="b12" name="b12" required></label><br><br>
        <input type="submit" value="Submit blood test!">
    </form></div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>

</body>
</html>
