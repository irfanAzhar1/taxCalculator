package id.co.notes.taxcalculator.controller;

import id.co.notes.taxcalculator.tax.IndonesiaTax;
import id.co.notes.taxcalculator.tax.VietnamTax;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.notes.taxcalculator.dto.CalculateTaxRequest;

@RestController
public class CalculateTaxController {

  @PostMapping("/hitunggaji")
  public String calculateTax(
    @RequestBody CalculateTaxRequest request
  ) {
    int anualTax;
    int monthlyTax;
    String currency;

    if (request.getEmployee().getCountry().equals("indonesia")) {
      currency = "IDR ";
      anualTax = new IndonesiaTax(
              request.getkomponengaji(),
              request.getEmployee().isMarried(),
              request.getEmployee().getChilds()
      ).calculateCountryTax();
    } else if (request.getEmployee().getCountry().equals("vietnam")) {
      currency = "VND ";
      anualTax = new VietnamTax(
              request.getkomponengaji(),
              request.getEmployee().isMarried(),
              request.getEmployee().getChilds()
      ).calculateCountryTax();
    } else {
      throw new RuntimeException("Country not supported");
    }

    monthlyTax = anualTax / 12;

    String anualTaxFormated = String.format("%,d", anualTax);
    String monthlyTaxFormated = String.format("%,d", monthlyTax);

    return "Pajak tahunan: "+ currency +  anualTaxFormated + "\nPajak bulanan: " + currency + monthlyTaxFormated;

  }

}
