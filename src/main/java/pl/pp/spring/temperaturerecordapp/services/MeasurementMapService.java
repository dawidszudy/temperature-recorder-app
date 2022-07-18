package pl.pp.spring.temperaturerecordapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.pp.spring.temperaturerecordapp.model.City;
import pl.pp.spring.temperaturerecordapp.model.Measurement;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeasurementMapService implements MeasurementService {

    private Logger log = LoggerFactory.getLogger(MeasurementMapService.class);

    private Map<Long, Measurement> measurementMap = new HashMap<>();

    @Override
    public List<City> findAll() {
        Map <String, List<Measurement>> mapOfCity = measurementMap.values()
                .stream().filter(m -> m.getDate().isAfter(LocalDateTime.now().minusDays(1)))
                .collect(Collectors.groupingBy(Measurement::getCity));

        log.info(String.valueOf(mapOfCity));

        List<City> list = new ArrayList<>();

        calculationOfAverages(mapOfCity, list);

        list.sort(Comparator.comparing(City::getCity));

        return list;
    }

    private void calculationOfAverages(Map<String, List<Measurement>> mapOfCity, List<City> list) {
        for (Map.Entry<String, List<Measurement>> entry : mapOfCity.entrySet()) {
            City city = new City();
            city.setCity(entry.getKey());

            calculateAverageForCountry(entry, city);
            calculateAverageDayForCity(entry, city);
            calculateAverageNightForCity(entry, city);

            list.add(city);
        }
    }

    private void calculateAverageForCountry(Map.Entry<String, List<Measurement>> entry, City city) {
        city.setAverage24Hours((double) Math.round(entry.getValue().
                stream().mapToDouble(Measurement::getTemperature).average().orElse(0)));
    }

    private void calculateAverageDayForCity(Map.Entry<String, List<Measurement>> entry, City city) {
        city.setAverageDaily((double) Math.round(entry.getValue().
                stream().filter(m -> m.getDate().getHour() >= 6 && m.getDate().getHour() < 20).mapToDouble(Measurement::getTemperature).average().orElse(0)));
    }

    private void calculateAverageNightForCity(Map.Entry<String, List<Measurement>> entry, City city) {
        city.setAverageNight((double) Math.round(entry.getValue().
                stream().filter(m -> m.getDate().getHour() < 6 || m.getDate().getHour() >= 20).mapToDouble(Measurement::getTemperature).average().orElse(0)));
    }

    @Override
    public String average24Hours() {
        return String.valueOf(Math.round(measurementMap.values().stream().mapToDouble(Measurement::getTemperature).average().orElse(0)));
    }

    @Override
    public Measurement save(Measurement measurement) {
        if ( measurement.getId() == null ) {
            Long maxId = measurementMap.keySet().stream().max(Long::compare).orElse(0L);
            measurement.setId(maxId + 1);
        }
        measurementMap.put(measurement.getId(), measurement);
        return measurement;
    }
}
