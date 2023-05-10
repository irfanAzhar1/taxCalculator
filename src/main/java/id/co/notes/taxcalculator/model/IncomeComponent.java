package id.co.notes.taxcalculator.model;

import id.co.notes.taxcalculator.enums.IncomeType;

public class IncomeComponent {
    private String name;
    private IncomeType type;
    private Integer amount;

    public IncomeComponent(String name, IncomeType type, Integer amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public IncomeComponent() {
    }

    public String getName() {
        return name;
    }

    public IncomeType getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
