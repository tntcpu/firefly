package com.tntcpu.agile.chap19;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-01-17
 **/
@Slf4j
public class PayrollTest {
	public void testAddSalariedEmployee() {
		int empId = 1;
		AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
		t.execute();

		Employee e = PayrollDatabase.getEmployee(empId);
		assertEquals("Bob", e.name);

		PaymentClassification pc = e.classification;
		assertTrue(pcSalariedClassification);
		SalariedClassification sc =
	}
}
