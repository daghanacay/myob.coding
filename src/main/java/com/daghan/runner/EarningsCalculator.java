package com.daghan.runner;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.daghan.model.Earnings;
import com.daghan.model.Employee;
import com.daghan.model.PayPeriod;
import com.daghan.service.InputParser;
import com.daghan.service.TaxCalculator;

@Component
public class EarningsCalculator implements CommandLineRunner {

	@Autowired
	TaxCalculator taxCalculator;

	@Autowired
	InputParser parser;

	@Override
	public void run(String... args) throws Exception {

		if (args.length != 2) {
			System.err.println(errorMessage("Please provide input and output file."));
			System.exit(0);
		}
		if (!Paths.get(args[0]).toFile().exists() || !Paths.get(args[1]).toFile().exists()) {
			System.err.println(errorMessage("Input and output files must exist."));
			System.exit(0);
		}

		// read, calculate, write, and close streams.
		try (Stream<String> stream = Files.lines(Paths.get(args[0]));
				PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get(args[1])))) {
			stream.map(this::calculateEarnings).peek(System.out::println).forEach(pw::println);
		}

	}

	private Earnings calculateEarnings(String str) {
		Employee employee = parser.parse(str);
		int grossIncome = Math.round(employee.getAnnualSalary() / 12);
		int incomeTax = taxCalculator.calculateTax(employee.getAnnualSalary());
		int netIncome = grossIncome - incomeTax;
		int superContribution = Math.round(employee.getSuperRate() * grossIncome / 100);
		return new Earnings() {

			@Override
			public String getEmployeeName() {
				return employee.getFirstName() + " " + employee.getLastName();
			}

			public int getGrossIncome() {
				return grossIncome;
			};

			@Override
			public int getIncomeTax() {
				return incomeTax;
			}

			@Override
			public int getNetIncome() {
				return netIncome;
			}

			@Override
			public PayPeriod getPayPeriod() {
				return employee.getPayPeriod();
			}

			@Override
			public int getSuperContribution() {
				return superContribution;
			}
		};
	}

	private String errorMessage(String msg) {
		StringBuilder errorMessage = new StringBuilder();
		return errorMessage.append(System.lineSeparator()).append("Application error".toUpperCase())
				.append(System.lineSeparator()).append("-----------------").append(System.lineSeparator()).append(msg)
				.append(System.lineSeparator()).append("-----------------").append(System.lineSeparator()).toString();
	}

}
