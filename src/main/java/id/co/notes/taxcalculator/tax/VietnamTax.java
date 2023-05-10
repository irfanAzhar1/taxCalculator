package id.co.notes.taxcalculator.tax;

import java.util.List;

import id.co.notes.taxcalculator.model.IncomeComponent;

public class VietnamTax extends Tax {

    public VietnamTax(List<IncomeComponent> incomeComponent, boolean isMarried, int children) {
      super(incomeComponent, isMarried, children);
    } 
    @Override
    public int calculateTax() {
      int taxableIncome = getTaxableIncome();
      int countTax = 0;

      if (taxableIncome <= 0) {
        return 0;
    }

      if (taxableIncome <= 50_000_000) {
        countTax = calculateLayer(taxableIncome, 0.025);
        return countTax;
      }

      countTax += calculateLayer(50_000_000, 0.025);
      taxableIncome -= 50_000_000;

      if (taxableIncome > 0) {
        countTax += calculateLayer(taxableIncome, 0.075);
      }

      return countTax;
    }

    @Override
    public int getPTKP() {
        if (isMarried()) {
            return 30_000_000;
        }
        return 15_000_000;
    }

    @Override
    public int getTaxableIncome() {
        return getTotalEarning() - getPTKP() - getTotalDeduction();
    }
}
