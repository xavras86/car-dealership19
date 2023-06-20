package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.CarToBuyDTO;
import pl.zajavka.api.dto.SalesmanDTO;
import pl.zajavka.domain.CarToBuy;
import pl.zajavka.domain.Salesman;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanDTO map(final Salesman salesman);
}
