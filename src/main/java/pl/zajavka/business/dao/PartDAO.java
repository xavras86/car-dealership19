package pl.zajavka.business.dao;

import pl.zajavka.domain.Part;

import java.util.List;
import java.util.Optional;

public interface PartDAO {

    Optional<Part> findBySerialNumber(String serialNumber);


    List<Part> findAll();
}
