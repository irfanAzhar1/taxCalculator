package id.co.notes.taxcalculator.tax;

import java.util.List;
import id.co.notes.taxcalculator.enums.IncomeType;
import id.co.notes.taxcalculator.model.IncomeComponent;

public abstract class Tax {
  private List<IncomeComponent> incomeComponents;
  private boolean isMarried;
  private int children;

  public Tax(List<IncomeComponent> incomeComponents, boolean isMarried, int children) {
    this.incomeComponents = incomeComponents;
    this.isMarried = isMarried;
    this.children = children;
  }

  public Tax() {
  }

  public List<IncomeComponent> getIncomeComponents() {
    return incomeComponents;
  }

  public boolean isMarried() {
    return isMarried;
  }

  public int getChildren() {
    return children;
  }

  public void setIncomeComponents(List<IncomeComponent> incomeComponents) {
    this.incomeComponents = incomeComponents;
  }

  public void setMarried(boolean married) {
    isMarried = married;
  }

  public void setChildren(int children) {
    this.children = children;
  }

  public int getTotalEarning() {
    return incomeComponents.stream()
        .filter(incomeComponent -> incomeComponent.getType() == IncomeType.earning)
        .mapToInt(IncomeComponent::getAmount)
        .sum() * 1000;
  }

  public int getTotalDeduction() {
    return incomeComponents.stream()
        .filter(incomeComponent -> incomeComponent.getType() == IncomeType.deduction)
        .mapToInt(IncomeComponent::getAmount)
        .sum();
  }

  public int calculateLayer(int taxable, double percentage) {
    return (int) Math.round(taxable * percentage);
  }

  
  public abstract int calculateTax();
  public abstract int getPTKP();
  public abstract int getTaxableIncome();

}

