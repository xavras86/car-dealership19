package pl.zajavka.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.zajavka.api.dto.CarPurchaseDTO;
import pl.zajavka.domain.CarPurchaseRequestInputData;

@Mapper(componentModel = "spring")
public interface CarPurchaseMapper {
    CarPurchaseRequestInputData map(final CarPurchaseDTO dto);
}
