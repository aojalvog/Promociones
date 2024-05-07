package com.springbatch.proyectoPromociones.process;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Processor {

	@Bean
	StockProcessor stockProcessor() {
		return new StockProcessor();
	}
}
