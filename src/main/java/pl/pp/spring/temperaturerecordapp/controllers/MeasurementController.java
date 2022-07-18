package pl.pp.spring.temperaturerecordapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pp.spring.temperaturerecordapp.model.Measurement;
import pl.pp.spring.temperaturerecordapp.services.MeasurementService;

@Controller
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @GetMapping("/saveMeasurement")
    public String showAddForm(Model model) {
        model.addAttribute("measurement", new Measurement());
        return "saveMeasurement";
    }

    @GetMapping("/citiesList")
    public String showList(Model model) {
        model.addAttribute("measurements", measurementService.findAll());
        model.addAttribute("average24", measurementService.average24Hours());
        return "citiesList";
    }

    @PostMapping("/saveMeasurement")
    public String saveTraining(@ModelAttribute Measurement measurement) {
        measurementService.save(measurement);
        return "redirect:/citiesList";
    }
}
