<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/7/2022
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body><div class="norma">
<form action="loginServlet" method="get">

<label for="usrname">Username:
    <input type="text" id="usrname" name="usrname" required> <br><br>
</label>
<label for="passwrd">Password:
    <input type="password" id="passwrd" name="passwrd"  required >
    </label><br><br>
<input type="Submit" id="loginbtn" name="loginbtn">

</form></div>
<div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>
</body>





</html>
