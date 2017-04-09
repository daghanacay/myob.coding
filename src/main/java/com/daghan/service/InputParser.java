package com.daghan.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.daghan.exception.IncorrectInputException;
import com.daghan.exception.SalaryMustBeAPositiveNumberException;
import com.daghan.exception.SuperFormatNotAcceptableException;
import com.daghan.exception.SuperValueNotAcceptableException;
import com.daghan.model.Employee;
import com.daghan.model.PayPeriod;

@Service
public class InputParser {

	private final static Pattern percentPattern = Pattern.compile("^(\\d+)%");

	/**
	 * Parses an employee from a string. also makes necessary validations.
	 * 
	 * @param inputString,
	 *            comma separated string e.g. "David,Rudd,60050,9%,01 March-31
	 *            March"
	 * @return
	 */
	public Employee parse(String inputString) {
		String[] parseValue = inputString.split(",");
		validate(parseValue);

		return new Employee() {
			@Override
			public String getFirstName() {
				return parseValue[0];
			}

			@Override
			public String getLastName() {
				return parseValue[1];
			}

			@Override
			public int getAnnualSalary() {
				return Integer.valueOf(parseValue[2]);
			}

			@Override
			public int getSuperRate() {
				Matcher matcher = percentPattern.matcher(parseValue[3]);
				matcher.matches();
				return Integer.valueOf(matcher.group(1));
			}

			@Override
			public PayPeriod getPayPeriod() {

				String[] starEndDate = parseValue[4].split("-");
				String[] startDate = starEndDate[0].split("\\s");
				String[] endDate = starEndDate[1].split("\\s");

				return new PayPeriod() {
					@Override
					public LocalDate getStartDate() {
						return LocalDate.of(0, Month.valueOf(startDate[1].toUpperCase()),
								Integer.valueOf(startDate[0]));
					}

					@Override
					public LocalDate getEndDate() {
						return LocalDate.of(0, Month.valueOf(endDate[1].toUpperCase()), Integer.valueOf(endDate[0]));
					}
				};

			}
		};
	}

	private void validate(String[] parseValue) {
		if (parseValue.length != 5) {
			throw new IncorrectInputException();
		}

		// WARNING checks if this is a positive number. It does not check if it
		// is between MIN and MAX integer values
		if (!parseValue[2].matches("^\\d+$")) {
			throw new SalaryMustBeAPositiveNumberException();
		}

		Matcher matcher = percentPattern.matcher(parseValue[3]);

		if (!matcher.matches()) {
			throw new SuperFormatNotAcceptableException();
		}

		if (!(Integer.valueOf(matcher.group(1)) <= 100)) {
			throw new SuperValueNotAcceptableException();
		}

	}
}
