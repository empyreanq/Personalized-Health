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
<body><div class="norma">
<h2>Admin Welcome!</h2>
<label for="userlist">User list:
    <input type="submit" id="userlist" value="Show" onclick="userList()"></label><br>
    <span id="userspan"></span><br><br>
<label for="deleteuser">Delete user:
       <input type="text" placeholder="input username" id="deleteuser" name="deleteuser" required>
<input type="submit" value="Delete" onclick="deleteuser()"></label><span id="delresult"></span>
<br><br>
<label for="certifydoc">Certify Doctor:
    <input type="text" placeholder="input username" id="certifydoc" name="certifydoc" required>
    <input type="submit" value="Certify" onclick="certifydoc()"></label><span id="certresult"></span>
</div><div class="footer"><br>
    <a href="https://www.vrisko.gr/efimeries-farmakeion/irakleio/" style="color:#fff;">Find online pharmacy here!</a><br>
    <a href="https://eody.gov.gr/category/covid-19/" style="color:#fff;">Find covid information here!</a></div>
</body>
</html>
