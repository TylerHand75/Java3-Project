package FunStuff;

import dataAccess.CountryDAO_CSV;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CountriesServlet", value = "/countries")
public class CountriesServlet extends HttpServlet {
    private List<Country> countries;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (countries == null) {
            countries = CountryDAO_CSV.getAll(request, response);
        }
        // https://stackoverflow.com/questions/715650/how-to-clone-arraylist-and-also-clone-its-contents
        List<Country> countriesCopy = new ArrayList<>(countries.size());
        for (Country country : countries) {
            try {
                countriesCopy.add((Country) country.clone());
            } catch (CloneNotSupportedException e) {

            }
        }


        String show = request.getParameter("show");
        if (show == null) {
            show = "all";
        }

        if (!show.equalsIgnoreCase("all")) {
            String finalShow = show.replaceAll("\\+", " ");
            ; // an effectively final variable that can be used with a lambda expression
            countriesCopy.removeIf(country -> !country.getContinent().equals(finalShow));
        }

        String search = request.getParameter("search");
        List<Country> matchingCountries = new ArrayList<>();
        if (search != null) {
            String finalSearch = search.toLowerCase();
            for (Country country : countriesCopy) {
                if (country.getName().toLowerCase().contains(finalSearch)) {
                    matchingCountries.add(country);
                }
            }
            matchingCountries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        } else {
            search = "";
            matchingCountries.addAll(countriesCopy);
        }

        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "alphaAZ";
        }
        switch (sort) {
            case "alphaAZ":
                matchingCountries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
                break;
            case "alphaZA":
                matchingCountries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()) * -1);
                break;
            case "populationAsc":
                matchingCountries.sort((c1, c2) -> c1.getPopulation() - c2.getPopulation());
                break;
            case "populationDesc":
                matchingCountries.sort((c1, c2) -> c2.getPopulation() - c1.getPopulation());
                break;
        }

        request.setAttribute("search", search);
        request.setAttribute("show", show);
        request.setAttribute("sort", sort);

        request.setAttribute("countries", matchingCountries);
        request.getRequestDispatcher("WEB-INF/Funstuff/Countries.jsp").forward(request, response);
    }
}
