<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<%
    String result = (String)request.getAttribute("conversion");


%>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Converter</title>
    <link href="TempConverter.css" rel="stylesheet">
</head>
<body>
<header>
    <h1>Temperature Converter</h1>
</header>
<div class="container my-4">
    <div class="row">
        <div class="col-4">
            <h1>Temperature Conversion</h1>
            <p class="lead">Enter a temperature and select how you want to convert it.</p>
            <form action="temp" method="post">
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

            <%    if(!result.equals("")){%>
            <p> <%= result %> </p>
            <%} else{%>
            <p> Awaiting conversion.</p>
            <%}%>

        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>