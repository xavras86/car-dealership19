package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zajavka.api.dto.CarHistoryDTO;
import pl.zajavka.api.dto.CarToServiceDTO;
import pl.zajavka.api.dto.mapper.CarMapper;
import pl.zajavka.business.CarService;
import pl.zajavka.domain.CarHistory;

import java.util.Objects;

@AllArgsConstructor
@Controller
public class CarHistoryController {

    private static final String CAR_HISTORY = "/car/history";

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = CAR_HISTORY)
    public String carHistory(@RequestParam(value = "carVin", required = false) String carVin,
            Model model
    ) {
        var allCars = carService.finAllCarsWithHistory().stream().map(carMapper::map).toList();
        var allCarVins = allCars.stream().map(CarToServiceDTO::getVin).toList();

        model.addAttribute("allCarDTOs", allCars);
        model.addAttribute("allCarVins", allCarVins);

        if(Objects.nonNull(carVin)){
            CarHistory carHistory = carService.findCarHistoryByVin(carVin);
            model.addAttribute("carHistoryDTO", carMapper.map(carHistory));
        } else {
            model.addAttribute("carHistoryDTO", CarHistoryDTO.buildDefault());
        }
        return "car_history";

    }

}
