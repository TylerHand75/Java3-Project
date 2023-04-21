<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String result = (String) request.getAttribute("result");
    String conversionFormula = "";
    String conversionType = request.getParameter("conversion");
    if (conversionType.equals("celsius to fahrenheit")) {
        conversionFormula = "Fahrenheit = Celsius * 1.8 + 32";
    } else if (conversionType.equals("fahrenheit to celsius")) {
        conversionFormula = "Celsius = (Fahrenheit - 32) / 1.8";
    }
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
    <input type="radio" id="celsius" name="conversion" value="celsius to fahrenheit " checked>
    <label for="celsius">Celsius to Fahrenheit</label>
    <br>
    <input type="radio" id="fahrenheit" name="conversion" value="fahrenheit to celsius">
    <label for="fahrenheit">Fahrenheit to Celsius</label>
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

    <br><br>
    <% if (!conversionFormula.isEmpty()) { %>
    Conversion Formula: <%= conversionFormula + result %>
    <% } %>
</p>
</body>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>