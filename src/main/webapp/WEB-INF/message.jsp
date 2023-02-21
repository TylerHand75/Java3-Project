<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    if (results == null) {
        results = new HashMap<>();
    }
    String phone = results.containsKey("phone") ? results.get("phone") : "";
    String message = results.containsKey("message") ? results.get("message") : "";
    String messageError = results.containsKey("messageError") ? results.get("messageError") : "";
    String messageSuccess = results.containsKey("messageSuccess") ? results.get("messageSuccess") : "";
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Messaging App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row py-4">
        <div class="col-md">
            <h2>Send a text message</h2>
            <form action="messaging" method="post">
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="text" class="form-control mb-2 <%= messageError %>" id="phone" name="phone" value="<%= phone %>">
                    <% if(!messageError.equals("")) { %>
                    <div class="invalid-feedback"><%= messageError %></div>
                    <% } %>
                </div>
                <div>
                    <select name="options" id="options" class="form-select form-select-sm" aria-label=".form-select-sm example">
                        <h3>Send a text or phone call?</h3>
                        <option for="options" selected value="choose">Open this select menu</option>
                        <option for="options" value="textMessage">Send a Text Message</option>
                        <option for="options" value="voiceMail">Send a Voice Mail</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="message">Message</label>
                    <textarea class="form-control mb-2 <%= messageError %>" rows="3" id="message" name="message"><%= message %></textarea>
                    <% if(!messageError.equals("")) { %>
                    <div class="invalid-feedback"><%= messageError %></div>
                    <% } %>
                </div>
                <input type="submit" value="Send" class="btn btn-primary">
            </form>
            <% if(!!messageError.equals("")) { %>
            <p><%= messageError %></p>
            <% } %>
        </div>
        <div class="col-md">

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>