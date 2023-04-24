<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<%
<<<<<<< HEAD
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
=======
    String result = (String)request.getAttribute("result");
>>>>>>> parent of 02b7134 (Class things)
%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Converter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link href="TempConverter.css" rel="stylesheet">
</head>
<body>
<<<<<<< HEAD
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
=======
<div class="container my-4">
    <div class="row">
        <div class="col-4">
            <h1>Temperature Conversion</h1>
            <p class="lead">Enter a temperature and select how you want to convert it.</p>
            <form action="TempConverter" method="post">
                <div class="form-group mb-2">
                    <label for="inputTemp">Please enter a temperature:</label>
                    <input type="text" class="form-control" id="inputTemp" name="temperature">
                </div>

                <p>Convert To</p>

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" value="celsius" id="convCels" name="conversion">
                    <label class="form-check-label" for="convCels">
                        Fahrenheit to Celsius
                    </label>
                </div>

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" value="fahrenheit" id="convFahr" name="conversion">
                    <label class="form-check-label" for="convFahr">
                        Celsius to Fahrenheit
                    </label>
                </div>

                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>

            </form>
        </div>

        <div class="col-4">

            <h2>Converted Temperature</h2>

            <% if (result != null && !result.isEmpty()) { %>
                <p><%= result %></p>
            <% } else { %>
                <p>Waiting for conversion...</p>
            <% } %>

        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
>>>>>>> parent of 02b7134 (Class things)
</body>
</html>