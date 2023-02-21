<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String> results = (Map<String, String>) request.getAttribute("results");
    if (results == null) {
        results = new HashMap<>();
    }
    String phone = results.containsKey("phone") ? results.get("phone") : "";
    String call = results.containsKey("call") ? results.get("call") : "";
    String message = results.containsKey("message") ? results.get("message") : "";
    String voiceMail = results.containsKey("voiceMail") ? results.get("voiceMail") : "";
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
<div class="container my-4">
    <div class="row">
        <div class="col-md-6">
            <select class="form-control" id="Options" name="select_an_option" aria-label="Choose-a-Option">
                <option value="Select_Option">Choose a Option</option>
                <option value="send-message">Send a Text Message</option>
                <option value="phoneCall">Send a Voice Mail</option>
            </select>
            <h2>Send a message</h2>
            <p class="lead">Enter your Valentine's phone number to send them a message</p>
            <form action="send-message " method="post">
                <div class="form-group mb-2 Choose-a-Option">
                    <label for="phone">Phone number</label>
                    <input type="text" id="phone" name="phone" class="form-control" value="<%= phone %>">
                </div>
                <div class="form-group mb-2">
                    <label for="message">Message</label>
                    <textarea id="message" name="message" class="form-control" rows="3"><%= message %></textarea>
                </div>
                <input type="submit" value="Send" class="btn btn-primary mb-2">
            </form>

            <% if (!messageError.equals("")) { %>
            <div class="alert alert-danger" role="alert">
                <%= messageError %>
            </div>
            <% } %>
            <% if (!messageSuccess.equals("")) { %>
            <div class="alert alert-success" role="alert">
                <%= messageSuccess %>
            </div>
            <% } %>
        </div>
    </div>
</div>
<div class="container">
    <div class="row py-4">
        <div class="col-md">
            <h2>Send a text message</h2>
            <form action="messaging" method="post">
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="text" class="form-control mb-2 <%= phoneIsInvalid %>" id="phone" name="phone" value="<%= phone %>">
                    <% if(!phoneInvalidMsg.equals("")) { %>
                    <div class="invalid-feedback"><%= phoneInvalidMsg %></div>
                    <% } %>
                </div>
                <div>
                    <select name="messageOption" id="messageOption" class="form-select form-select-sm" aria-label=".form-select-sm example">
                        <h3>Send a text or phone call?</h3>
                        <option for="messageOption" selected value="0">Open this select menu</option>
                        <option for="messageOption" value="1">Text Message</option>
                        <option for="messageOption" value="2">Phone Call</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="message">Message</label>
                    <textarea class="form-control mb-2 <%= messageIsInvalid %>" rows="3" id="message" name="message"><%= message %></textarea>
                    <% if(!messageInvalidMsg.equals("")) { %>
                    <div class="invalid-feedback"><%= messageInvalidMsg %></div>
                    <% } %>
                </div>
                <input type="submit" value="Send" class="btn btn-primary">
            </form>
            <% if(!twilioMsg.equals("")) { %>
            <p><%= twilioMsg %></p>
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