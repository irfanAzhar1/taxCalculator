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
  public int calculateTax(
    @RequestBody CalculateTaxRequest request
  ) {
    if (request.getEmployee().getCountry().equals("indonesia")) {
      return new IndonesiaTax(
              request.getkomponengaji(),
              request.getEmployee().isMarried(),
              request.getEmployee().getChilds()
      ).calculateTax();
    } 

    return new VietnamTax(
      request.getkomponengaji(),
      request.getEmployee().isMarried(),
      request.getEmployee().getChilds()
    ).calculateTax();

  }

}
