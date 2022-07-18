package pl.pp.spring.temperaturerecordapp.services;

import pl.pp.spring.temperaturerecordapp.model.City;
import pl.pp.spring.temperaturerecordapp.model.Measurement;

import java.util.List;

public interface MeasurementService {
    List<City> findAll();

    String average24Hours();

    Measurement save(Measurement measurement);

}
