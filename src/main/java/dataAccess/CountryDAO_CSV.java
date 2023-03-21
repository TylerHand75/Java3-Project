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
    public static List<Country> getAll(HttpServletRequest request, HttpServletResponse response)   {
        List<Country> countries = new ArrayList<>();
        try(Scanner in = new Scanner(new File(request.getServletContext().getRealPath("/WEB-INF/Funstuff/countries.csv")))){
                int lineCount = 0;
                while (in.hasNext()){
                    String[] dataStr = in.nextLine().split(",");
                    lineCount++;
                    if (lineCount != 1) {
                        String name = dataStr[0];
                        String continent = dataStr[2];
                        int population = Integer.parseInt(dataStr[5]);
                        Country country = new Country(name,continent,population);
                        countries.add(country);

                    };
                }
        }catch (FileNotFoundException e){
            System.out.printf("File not found");
        }
        Collections.sort(countries);
        return countries;
    }
}