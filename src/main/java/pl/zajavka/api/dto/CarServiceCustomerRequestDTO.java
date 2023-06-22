package pl.zajavka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarServiceCustomerRequestDTO {

    private String existingCustomerEmail;

    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private String customerEmail;
    private String customerAddressCountry;
    private String customerAddressCity;
    private String customerAddressPostalCode;
    private String customerAddressStreet;

    private String existingCarVin;
    private String carVin;
    private String carBrand;
    private String carModel;
    private Integer carYear;

    private String customerComment;

    public static CarServiceCustomerRequestDTO buildDefault() {
        return CarServiceCustomerRequestDTO.builder()
                .existingCustomerEmail("alf.samochod@gmail.com")
                .existingCarVin("1FT7X2B60FEA74019")
                .customerComment("Olej cieknie mi na stopy")
                .build();
    }

    public boolean isNewCarCandidate() {
        return Objects.isNull(getExistingCustomerEmail())
                || getExistingCustomerEmail().isBlank()
                || Objects.isNull(getExistingCarVin())
                || getExistingCarVin().isBlank();
    }
}
