package id.co.notes.taxcalculator.tax;

import java.util.List;
import id.co.notes.taxcalculator.enums.IncomeType;
import id.co.notes.taxcalculator.model.IncomeComponent;
import id.co.notes.taxcalculator.model.TaxLayer;

public abstract class Tax {
  final private List<IncomeComponent> incomeComponents;
  final private boolean isMarried;
  final private int children;

  public Tax(List<IncomeComponent> incomeComponents, boolean isMarried, int children) {
    this.incomeComponents = incomeComponents;
    this.isMarried = isMarried;
    this.children = children;
  }


  public boolean isMarried() {
    return isMarried;
  }

  public int getChildren() {
    return children;
  }

  public int getTotalEarning() {
    return incomeComponents.stream()
        .filter(incomeComponent -> incomeComponent.getType() == IncomeType.earning)
        .mapToInt(IncomeComponent::getAmount)
        .sum() * 12;
  }

  public int getTotalDeduction() {
    return incomeComponents.stream()
        .filter(incomeComponent -> incomeComponent.getType() == IncomeType.deduction)
        .mapToInt(IncomeComponent::getAmount)
        .sum() * 12;
  }

  public int calculateLayer(int taxable, double percentage) {
    return (int) Math.round(taxable * percentage);
  }


  public abstract int calculateCountryTax();
  public abstract int getPTKP();
  public abstract int getTaxableIncome();

  public int calculateTax(List<TaxLayer> taxLayers) {
    int taxableIncome = getTaxableIncome();
    int countTax = 0;

    if (taxableIncome <= 0) {
      return 0;
    }

    for (TaxLayer layer : taxLayers) {
      if (taxableIncome <= 0) {
        break;
      } else if (taxableIncome <= layer.getMaxIncome()) {
        countTax += calculateLayer(taxableIncome, layer.getTaxRate());
        break;
      } else {
        countTax += calculateLayer(layer.getMaxIncome(), layer.getTaxRate());
        taxableIncome -= layer.getMaxIncome();
      }
    }

    return countTax;
  }

}

