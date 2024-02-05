<%--
  Created by IntelliJ IDEA.
  User: Andreas
  Date: 9/10/2022
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="1.js"></script>
    <link rel="stylesheet" href="styles.css"></head>
<body><div class="norma">
    <br><br>
    <label for="changepass">Change password:
        <input type="password" id="changepass" name="changepass" placeholder="Enter new password here">
        <input type="Submit"  value="Change" onclick="changePass()"></label>
    <span id="passch"></span>
    <br><br>
    <label for="changemail">Change email:
        <input type="email" id="changemail" name="changemail" placeholder="Enter new email here">
        <input type="Submit"  value="Change" onclick="changeMail()"></label>
    <span id="mailch"></span>
</div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a>
</div>
</body>
</html>
