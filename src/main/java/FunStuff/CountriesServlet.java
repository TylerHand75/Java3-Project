package FunStuff;

import dataAccess.CountryDAO_CSV;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "CountriesServlet", value = "/countries")
public class CountriesServlet extends HttpServlet {
    private List<Country> countries;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(countries == null) {
            countries = CountryDAO_CSV.getAll(request, response);
        }


        List<Country> countriesFiltered = new ArrayList<Country>();
        String show = request.getParameter("show");
        if (show == null) {
            show = "all";
        }
        if (show.equals("all")){
            request.setAttribute("countries", countries);
        }else {
            for (Country country: countries){
                if (country.getContinent().equals(show)){
                    countriesFiltered.add(country);
                }
            }
            request.setAttribute("countries", countriesFiltered);
        }


        String sort = request.getParameter("sort");
        if (sort != null) {
            switch (sort) {
                case "alphaAZ":
                    countries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
                    break;
                case "alphaZA":
                    countries.sort((c1, c2) -> c1.getName().compareTo(c2.getName()) * -1);
                    break;
                case "populationAsc":
                    countries.sort((c1, c2) -> c1.getPopulation() - c2.getPopulation());
                    break;
                case "populationDesc":
                    countries.sort((c1, c2) -> c2.getPopulation() - c1.getPopulation());
                    break;
            }
        }




//        request.setAttribute("countries", countries);
        request.getRequestDispatcher("WEB-INF/Funstuff/Countries.jsp").forward(request, response);
    }
}
