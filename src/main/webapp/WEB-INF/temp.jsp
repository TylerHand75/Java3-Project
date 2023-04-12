<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String result = (String) request.getAttribute("result");
%>
<html>
<head>
    <title>Temperature Converter</title>
</head>
<body>
<h1>Temperature Converter</h1>
<form action="/temp" method="post">
    <label for="temperature">Enter Temperature:</label>
    <input type="text" id="temperature" name="temperature">
    <br>
    <input type="radio" id="celsius" name="conversion" value="celsius" checked>
    <label for="celsius">Celsius</label>
    <br>
    <input type="radio" id="fahrenheit" name="conversion" value="fahrenheit">
    <label for="fahrenheit">Fahrenheit</label>
    <br>
    <input type="submit" value="Convert">
</form>
<br>
<h2>Result:</h2>
<p>

    <% if (result != null && !result.isEmpty()) { %>
    <%= result %>
    <% } else { %>
    Please enter a temperature and choose a conversion option.
    <% } %>
</p>
</body>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>