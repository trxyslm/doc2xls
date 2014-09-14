package com.susu.app.doc2xls.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��ע��ͷ�Ͷ��������ӳ��
 * @author liming.slm
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleAnno {
	String title() default "";
}
