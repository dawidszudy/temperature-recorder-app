package pl.pp.spring.temperaturerecordapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.temperaturerecordapp.model.Measurement;
import pl.pp.spring.temperaturerecordapp.services.MeasurementService;

@Component
public class DataLoader implements CommandLineRunner {

    private final MeasurementService measurementService;

    public DataLoader(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @Override
    public void run(String... args) throws Exception {

        Measurement measurement1 = getExampleMeasurement1();
        Measurement measurement2 = getExampleMeasurement2();

        measurementService.save(measurement1);
        measurementService.save(measurement2);

        System.out.println("Data loaded");

    }

    private Measurement getExampleMeasurement1() {
        Measurement measurement1 = new Measurement();
        measurement1.setCity("Warszawa");
        measurement1.setTemperature(25.5);

        return measurement1;
    }

    private Measurement getExampleMeasurement2() {
        Measurement measurement2 = new Measurement();
        measurement2.setCity("Krakow");
        measurement2.setTemperature(23.2);

        return measurement2;
    }
}
