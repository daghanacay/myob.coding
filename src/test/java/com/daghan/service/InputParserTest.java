package com.daghan.service;

import static org.junit.Assert.assertEquals;

import java.time.Month;

import org.junit.Test;

import com.daghan.exception.IncorrectInputException;
import com.daghan.exception.SalaryMustBeAPositiveNumberException;
import com.daghan.exception.SuperFormatNotAcceptableException;
import com.daghan.exception.SuperValueNotAcceptableException;
import com.daghan.model.Employee;

public class InputParserTest {

	InputParser unitUnderTest = new InputParser();

	@Test
	public void testParseCorrectString() {
		// given
		String parseString = "David,Rudd,60050,9%,01 March-31 March";
		// when
		Employee result = unitUnderTest.parse(parseString);
		// then
		assertEquals("David", result.getFirstName());
		assertEquals("Rudd", result.getLastName());
		assertEquals(60050, result.getAnnualSalary());
		assertEquals(9, result.getSuperRate());
		assertEquals(1, result.getPayPeriod().getStartDate().getDayOfMonth());
		assertEquals(Month.MARCH, Month.of(result.getPayPeriod().getStartDate().getMonthValue()));
		assertEquals(31, result.getPayPeriod().getEndDate().getDayOfMonth());
		assertEquals(Month.MARCH, Month.of(result.getPayPeriod().getEndDate().getMonthValue()));
	}

	// Error cases
	@Test(expected = IncorrectInputException.class)
	public void testParseNotEnoughInformation() {
		// given
		String parseString = "Rudd,60050,9%,01 March-31 March";
		// when
		unitUnderTest.parse(parseString);
	}
	
	@Test(expected = SalaryMustBeAPositiveNumberException.class)
	public void testParseSalaryIsANegativeNumber() {
		// given
		String parseString = "David,Rudd,-60050,9%,01 March-31 March";
		// when
		unitUnderTest.parse(parseString);
	}
	
	@Test(expected = SalaryMustBeAPositiveNumberException.class)
	public void testParseSalaryIsNotNumber() {
		// given
		String parseString = "David,Rudd,abs,9%,01 March-31 March";
		// when
		unitUnderTest.parse(parseString);
	}
	
	@Test(expected = SuperFormatNotAcceptableException.class)
	public void testParseSuperDoesNotEndWithPercent() {
		// given
		String parseString = "David,Rudd,60050,9,01 March-31 March";
		// when
		unitUnderTest.parse(parseString);
	}
	
	@Test(expected = SuperValueNotAcceptableException.class)
	public void testParseSuperLargerThen100Percent() {
		// given
		String parseString = "David,Rudd,60050,922%,01 March-31 March";
		// when
		unitUnderTest.parse(parseString);
	}

}
