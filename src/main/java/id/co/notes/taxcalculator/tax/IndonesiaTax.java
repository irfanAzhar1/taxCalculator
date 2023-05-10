package id.co.notes.taxcalculator.tax;

import id.co.notes.taxcalculator.model.IncomeComponent;

import java.util.List;

public class IndonesiaTax extends Tax {

    public IndonesiaTax(List<IncomeComponent> incomeComponent, boolean isMarried, int children) {
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
            countTax = calculateLayer(taxableIncome, 0.05);
            return countTax;
        }

        countTax += calculateLayer(50_000_000, 0.05);
        taxableIncome -= 50_000_000;

        if (taxableIncome <= 250_000_000 - 50_000_000) {
            countTax += calculateLayer(taxableIncome, 0.10);
            return countTax;
        }

        countTax += calculateLayer(250_000_000 - 50_000_000, 0.10);
        taxableIncome -= 250_000_000 - 50_000_000;

        if (taxableIncome > 0) {
            countTax += calculateLayer(taxableIncome, 0.15);
        }

        return countTax;
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
