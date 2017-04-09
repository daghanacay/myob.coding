package com.daghan.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class PayPeriod {

	public abstract LocalDate getStartDate();

	public abstract LocalDate getEndDate();

	@Override
	public String toString() {
		return getStartDate().format(DateTimeFormatter.ofPattern("dd MMMM")) + "-"
				+ getEndDate().format(DateTimeFormatter.ofPattern("dd MMMM"));
	}
}
