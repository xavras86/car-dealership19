package pl.zajavka.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.zajavka.api.dto.CarPurchaseDTO;
import pl.zajavka.api.dto.CarToBuyDTO;
import pl.zajavka.api.dto.mapper.CarMapper;
import pl.zajavka.api.dto.mapper.CarPurchaseMapper;
import pl.zajavka.business.CarPurchaseService;
import pl.zajavka.domain.CarPurchaseRequest;
import pl.zajavka.domain.Invoice;
import pl.zajavka.domain.Salesman;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PurchaseController {
    private static final String PURCHASE = "/purchase";
    private final CarPurchaseService carPurchaseService;
    private final CarPurchaseMapper carPurchaseMapper;
    private final CarMapper carMapper;

    @GetMapping(value = PURCHASE)
    public ModelAndView carPurchasePage() {
        Map<String, ?> model = prepareCarPurchaseData();
        return new ModelAndView("car_purchase", model);
    }

    private Map<String, ?> prepareCarPurchaseData() {
        var availableCars = carPurchaseService.availableCars().stream()
                .map(carMapper::map)
                .toList();
        var availableCarVins = availableCars.stream()
                .map(CarToBuyDTO::getVin)
                .toList();
        var availableSalesmanPesels = carPurchaseService.availableSalesmen().stream()
                .map(Salesman::getPesel)
                .toList();
        return Map.of(
                "availableCarDTOs", availableCars,
                "availableCarVins", availableCarVins,
                "availableSalesmanPesels", availableSalesmanPesels,
                "carPurchaseDTO", CarPurchaseDTO.buildDefaultData()
        );
    }

    @PostMapping(value = PURCHASE)
    public String makePurchase(
            @ModelAttribute("carPurchaseDTO") CarPurchaseDTO carPurchaseDTO,
            BindingResult result,
            ModelMap model
            ){
        if(result.hasErrors()){
            return "error";
        }
        CarPurchaseRequest request = carPurchaseMapper.map(carPurchaseDTO);
        Invoice invoice = carPurchaseService.purchase(request);

        if(!carPurchaseDTO.getExistingCustomerEmail().isBlank()) {
            model.addAttribute("existingCustomerEmail", carPurchaseDTO.getExistingCustomerEmail());
        } else {
            model.addAttribute("customerName", carPurchaseDTO.getCustomerName());
            model.addAttribute("customerSurname", carPurchaseDTO.getCustomerSurname());
        }

        model.addAttribute("invoiceNumber", invoice.getInvoiceNumber());

        return "car_purchase_done";
    }


}
