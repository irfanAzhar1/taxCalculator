package id.co.notes.taxcalculator.model;

public class TaxLayer {
    final private int maxIncome;
    final private int minIncome;
    final private double taxRate;

    public TaxLayer(int maxIncome, int minIncome, double taxRate) {
        this.maxIncome = maxIncome;
        this.minIncome = minIncome;
        this.taxRate = taxRate;
    }

    public int getMaxIncome() {
        return maxIncome;
    }

    public int getMinIncome() {
        return minIncome;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
