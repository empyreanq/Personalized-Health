<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/7/2022
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css">
</head>
<body><div class="norma">
<br><br>
<input type="Submit"  value="Change user credentials" onclick="location.href = 'changeCred.jsp'"><br><br>
<label for="seedoc">See available doctors:
    <input type="submit" id="seedoc" name="seedoc" value="See" onclick="seeDoc()"></label><span id="sedoc"></span>
<br><br>
<label for="bookran">Book randevouz:
    <input type="text" id="bookran" name="bookran" placeholder="enter doctor id here">
    <input type="submit" value="Book" onclick="bookRan()"><span id="bookres"></span></label>
<br><br>
<input type="submit" value="Enter new blood test" onclick="location.href = 'bloodtestEntry.jsp'">
<br><br>
<input type="submit" value="Check Blood tests" onclick="checkBT()"><br><span id="checkbt"></span><br>
<input type="submit" value="Check available treatments" onclick="checkAT()"><span id="checkat"></span>
<br><br><input type="Submit"  value="Logout" onclick="logoutt()">
</div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>
</body>
</html>

