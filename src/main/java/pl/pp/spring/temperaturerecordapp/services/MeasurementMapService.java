package pl.pp.spring.temperaturerecordapp.services;

import org.springframework.stereotype.Service;
import pl.pp.spring.temperaturerecordapp.model.City;
import pl.pp.spring.temperaturerecordapp.model.Measurement;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeasurementMapService implements MeasurementService {

    private Map<Long, Measurement> measurementMap = new HashMap<>();

    @Override
    public List<City> findAll() {

        Map <String, List<Measurement>> mapOfCity = measurementMap.values()
                .stream().filter(m -> m.getDate().isAfter(LocalDateTime.now().minusDays(1)))
                .collect(Collectors.groupingBy(Measurement::getCity));

        System.out.println(mapOfCity);

        List<City> list = new ArrayList<>();

        for (Map.Entry<String, List<Measurement>> entry : mapOfCity.entrySet()) {
            City city = new City();
            city.setCity(entry.getKey());
            city.setAverage24Hours((double) Math.round(entry.getValue().
                    stream().mapToDouble(Measurement::getTemperature).average().orElse(0)));

            city.setAverageDaily((double) Math.round(entry.getValue().
                    stream().filter(m -> m.getDate().getHour() >= 6 && m.getDate().getHour() < 20).mapToDouble(Measurement::getTemperature).average().orElse(0)));

            city.setAverageNight((double) Math.round(entry.getValue().
                    stream().filter(m -> m.getDate().getHour() < 6 || m.getDate().getHour() >= 20).mapToDouble(Measurement::getTemperature).average().orElse(0)));

            list.add(city);
        }

        list.sort(Comparator.comparing(City::getCity));

        return list;
    }

    @Override
    public String average24Hours() {
//        int sumTemperature = 0;
//        ArrayList<Measurement> listMeasurements = new ArrayList(measurementMap.values());
//        for (Measurement list : listMeasurements) {
//            sumTemperature += list.getTemperature();
//        }
//        System.out.println(sumTemperature/numberTemperature);
//        return sumTemperature/measurementMap.values().size() + "";

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
