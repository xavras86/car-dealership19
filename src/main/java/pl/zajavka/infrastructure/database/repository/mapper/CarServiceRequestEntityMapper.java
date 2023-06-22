package pl.zajavka.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.domain.CarServiceRequest;
import pl.zajavka.domain.CarToService;
import pl.zajavka.infrastructure.database.entity.CarServiceRequestEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarServiceRequestEntityMapper {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "serviceMechanics", ignore = true)
    @Mapping(target = "serviceParts", ignore = true)
    CarServiceRequest mapFromEntity(CarServiceRequestEntity entity);

    default CarServiceRequest mapFromEntityWithCar(CarServiceRequestEntity entity) {
        return mapFromEntity(entity)
                .withCar(CarToService.builder()
                        .vin(entity.getCar().getVin())
                        .build());
    }

    @Mapping(target = "customer.address", ignore = true)
    @Mapping(target = "customer.carServiceRequests", ignore = true)
    CarServiceRequestEntity mapToEntity(CarServiceRequest request);
}
