package com.itheima.bigevent.anno;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.itheima.bigevent.validation.StateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {StateValidation.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface State {
	//提供驗證失敗的 “提示訊息”
	String message() default "state參數值只能為已發布或者是草稿";
	//指定分組
	Class<?>[] groups() default { };
	//負載, 獲取state註解的附加訊息
	Class<? extends Payload>[] payload() default { };

}
