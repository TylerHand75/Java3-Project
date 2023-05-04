package dataAccess;

import FunStuff.Country;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountryDAO_CSV {

    public static List<Country> getAll(HttpServletRequest request, HttpServletResponse response) {
        List<Country> countries = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(request.getServletContext().getRealPath("/WEB-INF/Funstuff/countries.csv")))) {
            int lineCount = 0;
            while(scanner.hasNext()) {
                String[] dataStr = scanner.nextLine().split(",");
                lineCount++;
                if(lineCount != 1) {
                    String name = dataStr[0];
                    String abbreviation = dataStr[1];
                    String continent = dataStr[2];
                    String subRegion = dataStr[3];
                    int area = Integer.parseInt(dataStr[4]);
                    int population = Integer.parseInt(dataStr[5]);
                    String capital = dataStr[6];
                    double capital_lat = Double.parseDouble(dataStr[7]);
                    double capital_long = Double.parseDouble(dataStr[8]);
                    String GoogleMapsURL = dataStr[9];
                    Country country = new Country(name, continent, subRegion ,area ,population, abbreviation,
                            capital, capital_lat, capital_long , GoogleMapsURL );
                    countries.add(country);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        Collections.sort(countries);
        for (Country country : countries) {
            String abbreviation = country.getAbbreviation();
        }
        return countries;
    }

}
