package pl.pp.spring.temperaturerecordapp.model;

import java.time.LocalDateTime;

public class Measurement {

    private Long id;

    private String city;
    private Double temperature;
    private LocalDateTime date = LocalDateTime.now();

    public Measurement() {
    }

    public Measurement(String city, Double temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                ", date=" + date +
                '}';
    }

}
