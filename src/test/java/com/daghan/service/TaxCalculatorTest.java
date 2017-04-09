package com.daghan.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaxCalculatorTest {

	TaxCalculator unitUnderTest = new TaxCalculator();

	@Test
	public void test0_18200_Case1() {
		// and
		int result = unitUnderTest.calculateTax(0);
		// then
		assertEquals(0, result);
	}

	@Test
	public void test0_18200_Case2() {
		// and
		int result = unitUnderTest.calculateTax(15000);
		// then
		assertEquals(0, result);
	}

	@Test
	public void test0_18200_Case3() {
		// and
		int result = unitUnderTest.calculateTax(18200);
		// then
		assertEquals(0, result);
	}

	@Test
	public void test18201_37000_Case1() {
		// and
		int result = unitUnderTest.calculateTax(18201);
		// then
		assertEquals(0, result);
	}

	@Test
	public void test18201_37000_Case2() {
		// and
		int result = unitUnderTest.calculateTax(20000);
		// then
		assertEquals(29, result);
	}

	@Test
	public void test18201_37000_Case3() {
		// and
		int result = unitUnderTest.calculateTax(37000);
		// then
		assertEquals(298, result);
	}

	@Test
	public void test37001_80000Case1() {
		// and
		int result = unitUnderTest.calculateTax(37001);
		// then
		assertEquals(298, result);
	}

	@Test
	public void test37001_80000Case2() {
		// and
		int result = unitUnderTest.calculateTax(60050);
		// then
		assertEquals(922, result);
	}

	@Test
	public void test37001_80000Case3() {
		// and
		int result = unitUnderTest.calculateTax(80000);
		// then
		assertEquals(1462, result);
	}
	
	@Test
	public void test80000_180000Case1() {
		// and
		int result = unitUnderTest.calculateTax(80001);
		// then
		assertEquals(1462, result);
	}

	@Test
	public void test80000_180000Case2() {
		// and
		int result = unitUnderTest.calculateTax(85000);
		// then
		assertEquals(1616, result);
	}

	@Test
	public void test80000_180000Case3() {
		// and
		int result = unitUnderTest.calculateTax(180000);
		// then
		assertEquals(4546, result);
	}

}
