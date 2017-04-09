package com.daghan.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaxCalculator {
	// A class to store mappings between different components of the equation
	static private class IncomeTaxMapper {
		private final List<Double> taxRateList = Arrays.asList(0.0, 0.19, 0.325, 0.37, 0.45);
		private final List<Integer> taxOffset = Arrays.asList(0, 18200, 37000, 80000, 180000);
		private final List<Integer> baseTaxCut = Arrays.asList(0, 0, 3572, 17547, 54547);

		public double getTaxRateForBracket(int income) {
			return taxRateList.get(getBracket(income));
		}

		public int getBaseTaxCut(int income) {
			return baseTaxCut.get(getBracket(income));
		}

		public int getTaxOffset(int income) {
			return taxOffset.get(getBracket(income));
		}

		private int getBracket(int income) {
			for (int i = 0;; i++) {
				if ((income - taxOffset.get(i + 1)) <= 0) {
					return i;
				}
			}
		}
	}

	static final private IncomeTaxMapper incomeTaxMapper = new IncomeTaxMapper();

	public int calculateTax(int income) {
		return (int) Math.round((incomeTaxMapper.getBaseTaxCut(income)
				+ (income - incomeTaxMapper.getTaxOffset(income)) * incomeTaxMapper.getTaxRateForBracket(income)) / 12);
	}

}
