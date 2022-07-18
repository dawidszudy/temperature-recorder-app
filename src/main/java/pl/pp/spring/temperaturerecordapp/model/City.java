package pl.pp.spring.temperaturerecordapp.model;

public class City {

    private String city;
    private Double average24Hours;
    private Double averageDaily;
    private Double averageNight;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getAverage24Hours() {
        return average24Hours;
    }

    public void setAverage24Hours(Double average24Hours) {
        this.average24Hours = average24Hours;
    }

    public Double getAverageDaily() {
        return averageDaily;
    }

    public void setAverageDaily(Double averageDaily) {
        this.averageDaily = averageDaily;
    }

    public Double getAverageNight() {
        return averageNight;
    }

    public void setAverageNight(Double averageNight) {
        this.averageNight = averageNight;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", average24Hours=" + average24Hours +
                ", averageDaily=" + averageDaily +
                ", averageNight=" + averageNight +
                '}';
    }
}
