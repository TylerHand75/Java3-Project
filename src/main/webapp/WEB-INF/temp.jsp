<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<%

    String result = (String) request.getAttribute("result");
    String conversionFormula = "";
    String conversionType = request.getParameter("conversion");
    if (conversionType != null) {
        if (conversionType.equals("celsius to fahrenheit")) {
            conversionFormula = " * 9/5 + 32 = ";
        } else if (conversionType.equals("fahrenheit to celsius")) {
            conversionFormula = " - 32 * 5/9 = ";
        }
    }

%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Converter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link href="TempConverter.css" rel="stylesheet">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

</body>
</html>