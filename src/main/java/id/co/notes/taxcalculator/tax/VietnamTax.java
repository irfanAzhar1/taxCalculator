package id.co.notes.taxcalculator.tax;

import java.util.List;

import id.co.notes.taxcalculator.model.IncomeComponent;
import id.co.notes.taxcalculator.model.TaxLayer;

public class VietnamTax extends Tax {

    final private List<TaxLayer> taxLayers = List.of(
            new TaxLayer(50_000_000, 0, 0.025),
            new TaxLayer(Integer.MAX_VALUE, 0, 0.075)
    );

    public VietnamTax(List<IncomeComponent> incomeComponent, boolean isMarried, int children) {
      super(incomeComponent, isMarried, children);
    } 
    @Override
    public int calculateCountryTax() {
      return calculateTax(taxLayers);
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
