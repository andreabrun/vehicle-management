package com.andreabrun.vehiclemanagement.form.fields;

import java.time.Period;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.IntegerField;

public class PeriodField extends CustomField<Period> {

    private static final long serialVersionUID = 1L;
	private final IntegerField yearsField = new IntegerField("Years");
    private final IntegerField monthsField = new IntegerField("Months");
    private final IntegerField daysField = new IntegerField("Days");
    
    private final int WIDTH = 300;

    public PeriodField() {
        Div layout = new Div();
        layout.addClassName("period-field");

        // Configure fields
        yearsField.setWidth(WIDTH / 5 + "px");
        yearsField.getStyle().set("margin-right", WIDTH / 5 + "px");
        monthsField.setWidth(WIDTH / 5 + "px");
        monthsField.getStyle().set("margin-right", WIDTH / 5 + "px");
        daysField.setWidth(WIDTH / 5 + "px");

        // Add listeners to update the value
        yearsField.addValueChangeListener(e -> updateValue());
        monthsField.addValueChangeListener(e -> updateValue());
        daysField.addValueChangeListener(e -> updateValue());

        // Add components to layout
        layout.add(yearsField, monthsField, daysField);

        add(layout);
    }

    @Override
    protected Period generateModelValue() {
        int years = yearsField.getValue() != null ? yearsField.getValue() : 0;
        int months = monthsField.getValue() != null ? monthsField.getValue() : 0;
        int days = daysField.getValue() != null ? daysField.getValue() : 0;
        return Period.of(years, months, days);
    }

    @Override
    protected void setPresentationValue(Period period) {
        if (period != null) {
            yearsField.setValue(period.getYears());
            monthsField.setValue(period.getMonths());
            daysField.setValue(period.getDays());
        } else {
            yearsField.clear();
            monthsField.clear();
            daysField.clear();
        }
    }
}
