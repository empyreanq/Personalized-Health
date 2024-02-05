<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/8/2022
  Time: 2:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body><div class="norma"><br><br>
<input type="Submit"  value="Change doctor credentials" onclick="location.href = 'changeCred.jsp'"><br><br>
<label for="seepatients">See patient list and their blood tests:
    <input type="submit" id="seepatients" name="seepatients" value="See" onclick="seePatient()"><span id="seep"></span></label><br><br>
<input type="submit" value="Select a therapy for a patient" onclick="location.href='therapy.jsp'"><br><br>
<input type="Submit"  value="Logout" onclick="logoutt()">
</div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>
</body>
</html>
