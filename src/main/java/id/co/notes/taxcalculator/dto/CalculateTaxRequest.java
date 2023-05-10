package id.co.notes.taxcalculator.dto;

import java.io.Serializable;
import java.util.List;

import id.co.notes.taxcalculator.model.Employee;
import id.co.notes.taxcalculator.model.IncomeComponent;

public class CalculateTaxRequest implements Serializable {
    final private Employee employee = new Employee();
    final private List<IncomeComponent> komponengaji = null;

    public Employee getEmployee() {
        return employee;
    }

    public List<IncomeComponent> getkomponengaji() {
        return komponengaji;
    }
}
