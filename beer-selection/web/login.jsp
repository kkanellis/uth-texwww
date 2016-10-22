<html>
<body>
<h1 align="center">Beer recommendation login</h1>

<div align="center">

Please enter your credentials given to you by UTH </br></br>

<form method="post" action="">
    Username: </br>
    <input type="text" name="username"></br></br>
    Password: </br>
    <input type="text" name="password">
    </br>
    <input type="submit" value="Submit">
</form>

</br></br>

Total sessions: <%= request.getAttribute("activeSessions") %>

</body>
</html>

