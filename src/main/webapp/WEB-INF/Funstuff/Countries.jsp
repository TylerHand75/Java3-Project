<%@ page import="FunStuff.Country" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Country> countries = (List<Country>)request.getAttribute("countries");
    String show = "?show=" + (String)request.getAttribute("show");
    String sort = "&sort=" + (String)request.getAttribute("sort");
    String search = (String)request.getAttribute("search");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Countries of the World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<header class="bg-primary">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand text-light" href="#">Where in the World?</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">

                <form class="d-flex" action="countries" method="get">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="<%= search %>">
                    <button class="btn btn-light" type="submit">Search</button>
                </form>
                <div class="dropdown ms-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="filterDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        Show
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="filterDropdown">
                        <li><a class="dropdown-item" href="countries?show=all<%= sort %>">All</a></li>
                        <li><a class="dropdown-item" href="countries?show=Africa<%= sort %>">Africa</a></li>
                        <li><a class="dropdown-item" href="countries?show=Asia<%= sort %>">Asia</a></li>
                        <li><a class="dropdown-item" href="countries?show=Europe<%= sort %>">Europe</a></li>
                        <li><a class="dropdown-item" href="countries?show=Oceania<%= sort %>">Oceania</a></li>
                        <li><a class="dropdown-item" href="countries?show=North+America<%= sort %>">North America</a></li>
                        <li><a class="dropdown-item" href="countries?show=South+America<%= sort %>">South America</a></li>
                    </ul>
                </div>
                <div class="dropdown ms-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                        Sort
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="sortDropdown">
                        <li><a class="dropdown-item " href="countries<%= show %>&sort=alphaAZ">Alphabetical (A to Z)</a></li>
                        <li><a class="dropdown-item " href="countries<%= show %>&sort=alphaZA">Alphabetical (Z to A)</a></li>
                        <li><a class="dropdown-item " href="countries<%= show %>&sort=populationAsc"  >Population (Low to High)</a></li>
                        <li><a class="dropdown-item " href="countries<%= show %>&sort=populationDesc" >Population (High to Low)</a></li>
                    </ul>

                </div>
            </div>
        </div>
    </nav>

</header>
<div class="container my-4">
    <p class="lead">Showing <%= countries.size() %> countr<%= countries.size() == 1 ? "y" : "ies" %></p>
    <div class="row">
        <% for(Country country: countries) { %>
        <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
            <div class="card mb-4" style="width: 18rem;">
                <img src="https://flagcdn.com/<%=country.getAbbreviation().toLowerCase()%>.svg" class="card-img-top" alt="Flag of <%= country.getName() %>" height="200">
                <div class="card-body">
                    <h5 class="card-title"><%= country.getName() %></h5>
                    <p class="card-text">Region: <%= country.getContinent() %>
                        <br>Population: <%= country.getPopulation() %></p>
                    <button type="button" class="btn btn-primary btn-open" data-bs-toggle="modal" data-bs-target="#countryModal"
                            data-title="<%= country.getName() %>"
                            data-population="<%= country.getPopulation() %>"
                            data-region="<%= country.getContinent() %>"
                            data-area="<%= country.getArea() %>"
                            data-subregion="<%= country.getSubRegion() %>"
                            data-capital="<%= country.getCapital() %>"
                            data-capital-lat="<%= country.getCapitalLat() %>"
                            data-capital-ling="<%= country.getCapitalLong() %>"
                            >
                        Show more
                    </button>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<div class=" modal fade" id="countryModal"  aria-labelledby="countryModalLabel" aria-hidden="true">
    <% for(Country country: countries){ %>
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="countryModalLabel"><%= country.getName() %></h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Region: <span class="modal-region"> <%= country.getContinent()%> </span></br>
                    <br>Population: <span class="modal-population"> <%= country.getPopulation() %></span></br>
                    <br>Area: <span class="modal-area"> <%= country.getArea() %></span></br>
                    <br>Sub Region: <span class="modal-subregion"> <%= country.getSubRegion() %></span></br>
                    <br>Capital: <span class="modal-capital"> <%= country.getCapital() %></span></br>
                    <br>Capital Latitude: <span class="modal-capital-lat"> <%= country.getCapitalLat() %></span></br>
                    <br>Capital Longitude: <span class="modal-capital-ling"> <%= country.getCapitalLong() %></span></br>
                    <iframe
                            width="100%"
                            height="480"
                            frameborder="0"
                            scrolling="no"
                            marginheight="0"
                            marginwidth="0"
                            src="https://maps.google.com/maps?q=-15.79,-47.88&amp;z=4&amp;output=embed"
                    ></iframe>
                    <br>
                    <a      href="https://www.google.com/maps/<%=country.getCapitalLat() %>,<%= country.getCapitalLong()%>"
                            target="_blank">
                        See full page map
                    </a>
                </p>
            </div>
        </div>
    </div>
    <% } %>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.slim.min.js"
        integrity="sha256-a2yjHM4jnF9f54xUQakjZGaqYs/V1CYvWpoqZzC2/Bw="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<script src="countries.js"></script>
</body>
</html>