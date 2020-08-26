package com.study.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/********************************************
 * @Class Name : ServletInitializer
 * @Description : Servlet Init
 * @Modification : Information
 * @
 * @	 ������				������					��������
 * @ -------------------------------------------------------------------
 * @ 2020-08-26		sharic					���ʻ���
 * @
 * @author : sharic
 * @since : 2020-08-26
 * @version : 1.0
 * @see
 ********************************************/
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Study02Application.class);
	};
};