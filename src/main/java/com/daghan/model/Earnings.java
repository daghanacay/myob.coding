package com.daghan.model;

public abstract class Earnings {

	abstract public String getEmployeeName();

	abstract public PayPeriod getPayPeriod();

	abstract public int getGrossIncome();

	abstract public int getIncomeTax();

	abstract public int getNetIncome();

	abstract public int getSuperContribution();

	@Override
	public String toString() {
		return getEmployeeName() + "," + getPayPeriod() + "," + getGrossIncome() + "," + getIncomeTax() + ","
				+ getNetIncome() + "," + getSuperContribution();
	}

}
