package pl.zajavka.business.dao;

import pl.zajavka.domain.CarHistory;
import pl.zajavka.domain.CarToService;

import java.util.List;
import java.util.Optional;

public interface CarToServiceDAO {

    Optional<CarToService> findCarToServiceByVin(String vin);

    CarToService saveCarToService(CarToService car);

    CarHistory findCarHistoryByVin(String vin);

    List<CarToService> findAll();
}
