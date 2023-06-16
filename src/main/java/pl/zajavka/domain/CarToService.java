package pl.zajavka.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.With;

import java.util.Objects;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "vin")
@ToString(of = {"carToServiceId", "vin", "brand", "model", "year"})
public class CarToService {

    Integer carToServiceId;
    String vin;
    String brand;
    String model;
    Integer year;
    Set<CarServiceRequest> carServiceRequests;

    public boolean carBoughtHere() {
        return Objects.nonNull(vin)
            && Objects.isNull(brand)
            && Objects.isNull(model)
            && Objects.isNull(year);
    }
}
