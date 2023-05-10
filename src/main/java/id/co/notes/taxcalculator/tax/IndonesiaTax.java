package id.co.notes.taxcalculator.tax;

import id.co.notes.taxcalculator.model.IncomeComponent;
import id.co.notes.taxcalculator.model.TaxLayer;

import java.util.List;

public class IndonesiaTax extends Tax {

    final private List<TaxLayer> taxLayers = List.of(
            new TaxLayer(50_000_000, 0, 0.05),
            new TaxLayer(250_000_000, 50_000_000, 0.10),
            new TaxLayer(500_000_000, 250_000_000, 0.15)
    );

    public IndonesiaTax(List<IncomeComponent> incomeComponent, boolean isMarried, int children) {
        super(incomeComponent, isMarried, children);
    }

    @Override
    public int calculateCountryTax() {
        return calculateTax(taxLayers);
    }

    @Override
    public int getPTKP() {
        if (!isMarried()) {
            return 25_000_000;
        }

        if (getChildren() == 0) {
            return 50_000_000;
        }

        return 75_000_000;
    }

    @Override
    public int getTaxableIncome() {
        return getTotalEarning() - getPTKP();
    }
}
