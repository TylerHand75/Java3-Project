<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Table of Contents</h1>
<br/>
<h3>Chapter 3 and 4</h3>
<ul>
    <li><a href="add">Add</a></li>
    <li><a href="temp">Temperature Converter </a></li>
    <li><a href="Bmi">Bmi Converter </a></li>
    <li><a href="send-message">Send that Message bois</a></li>

</ul>
<h3>Chapter 5</h3>
<ul>
    <li><a href="signup">Add User</a></li>
    <li><a href="login">Login</a></li>
    <%
        if (request.getSession().getAttribute("user") != null) {
            if (request.getSession().getAttribute("admin") != null) { %>
    <li><a href="view-users">View Users</a></li>
    <% } %>
    <li><a href="profile">User Profile</a></li>
    <li><a href="logout">Logout</a></li>
    <% } else { %>
    <%
        if (request.getSession().getAttribute("user") != null) { %>
    <li><a href="profile">User Profile</a></li>
    <li><a href="logout">Logout</a></li>
    <% } %>
    <% } %>


</ul>
<h3>Fun Stuff</h3>
<ul>
    <li><a href="countries"> Countries</a></li>
    <li><a href="artist">Spotify App</a></li>
    <li><a href="chat">Chat App</a></li>
    <li><a href="tictactoe">Tic Tac Toe</a></li>
</ul>

<ul>
    <li><a href="newsletter">Newsletter</a></li>
</ul>

</body>
</html>