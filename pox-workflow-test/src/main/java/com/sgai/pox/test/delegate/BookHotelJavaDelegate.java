package com.sgai.pox.test.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class BookHotelJavaDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		System.out.println("===============booking hotel=====================");

	}

}
