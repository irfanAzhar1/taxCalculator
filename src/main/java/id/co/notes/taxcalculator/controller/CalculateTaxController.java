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
    int annualTax;
    int monthlyTax;
    String currency;

    if (request.getEmployee().getCountry().equals("indonesia")) {
      currency = "IDR ";
      annualTax = new IndonesiaTax(
              request.getkomponengaji(),
              request.getEmployee().isMarried(),
              request.getEmployee().getChilds()
      ).calculateCountryTax();
    } else if (request.getEmployee().getCountry().equals("vietnam")) {
      currency = "VND ";
      annualTax = new VietnamTax(
              request.getkomponengaji(),
              request.getEmployee().isMarried(),
              request.getEmployee().getChilds()
      ).calculateCountryTax();
    } else {
      return "Negara tidak didukung";
    }

    monthlyTax = annualTax / 12;

    String anualTaxFormated = String.format("%,d", annualTax).replace(",", ".");
    String monthlyTaxFormated = String.format("%,d", monthlyTax).replace(",", ".");

    return "Pajak tahunan: "+ currency +  anualTaxFormated + "\nPajak bulanan: " + currency + monthlyTaxFormated;

  }

}
