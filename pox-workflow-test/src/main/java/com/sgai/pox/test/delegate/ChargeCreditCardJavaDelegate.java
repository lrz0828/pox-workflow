package com.sgai.pox.test.delegate;

import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class ChargeCreditCardJavaDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) {
		System.out.println("===============Charge credit card,并抛出异常=====================");

		throw new BpmnError("Charge credit card error");
		
	}

}
