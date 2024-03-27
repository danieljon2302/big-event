package com.itheima.bigevent.validation;

import com.itheima.bigevent.anno.State;

import ch.qos.logback.core.Context;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String>{
//	value 將來要驗證的數據
//	Context 
//	return 若返回false沒通過
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//提供驗證規則
		if(value == null) {
			return false;
		}
		if(value.equals("草稿")||value.equals("已發布")) {
			return true;
		}
		return false;
	}
	
}
