<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Converter</title>
    <link href="TempConverter.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<header>
    <h1>Temperature Converter</h1>
</header>
<div class="form-container">
    <form name="main" action="convert" method="POST">
        Enter temperature <input type="text" name="inputTemp" value="" size="10"/>
        <div class="form-check">
            <input class="form-check-input" type="radio" value="Fahrenheit to Celsius" name="fToC"
                   id="flexRadioDefault1">
            <label class="form-check-label" for="flexRadioDefault1">
                Default radio
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" value="Celsius to Fahrenheit" name="cToF"
                   id="flexRadioDefault2" checked>
            <label class="form-check-label" for="flexRadioDefault2">
                Default checked radio
            </label>
        </div>
    </form>

    <div>
        <h1>Temperature Converter</h1>
        <%System.out.println(request.getParameter("inputTemp") + "\tFahrenheit");%>
        Equals to <% int ftemp = Integer.parseInt(request.getParameter("inputTemp"));
        System.out.println((ftemp - 32) * 5 / 9 + "\tCelsius");%>
    </div>
    <div>
        <h1>Temperature Converter</h1>
        <%System.out.println(request.getParameter("inputTemp") + "\tCelsius");%>
        Equals to <% int ctemp = Integer.parseInt(request.getParameter("inputTemp"));
        System.out.println((ctemp * 9 / 5) + 32 + "\tFahrenheit");%>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>