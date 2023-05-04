package FunStuff;

public class Country implements Comparable<Country>, Cloneable {
    private String name;
    private String continent;

    private String subRegion;
    private int area;
    private int population;
    private String abbreviation;

    private String capital;

    private double capitalLat;
    private double capitalLong;
    private String GoogleMapsURL;




    public Country() {
        this("Unknown", "Unknown", "", 0,0,  "Unknown", "Unknown", 0, 0 , "Unknown");
    }



    public Country(String name, String continent, String subRegion , int area  , int population, String abbreviation,
                   String capital, double capitalLat, double capitalLong, String GoogleMapsURL) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.abbreviation = abbreviation;
        this.capital = capital;
        this.capitalLat = capitalLat;
        this.capitalLong = capitalLong;
        this.GoogleMapsURL = GoogleMapsURL;
        this.subRegion = subRegion;
        this.area = area;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public String getAbbreviation() {
        String[] words = abbreviation.split(" ");
        String abbreviation = "";
        if (words.length > 0) {
            abbreviation = words[0].substring(0, 2);
        }
        return abbreviation.toLowerCase();
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getCapitalLat() {
        return capitalLat;
    }

    public void setCapitalLat(double capitalLat) {
        this.capitalLat = capitalLat;
    }

    public double getCapitalLong() {
        return capitalLong;
    }

    public void setCapitalLong(double capitalLong) {
        this.capitalLong = capitalLong;
    }
    public String getGoogleMapsURL() {
        return GoogleMapsURL;
    }

    public void setGoogleMapsURL(String googleMapsURL) {
        GoogleMapsURL = googleMapsURL;
    }
    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }



    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
