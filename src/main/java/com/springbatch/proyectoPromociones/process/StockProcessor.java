package com.springbatch.proyectoPromociones.process;

import java.io.IOException;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.proyectoPromociones.model.Productos;

public class StockProcessor implements ItemProcessor<Productos, Productos> {

	@Override
	public Productos process(Productos item) throws IOException {
		return item;
	}

}
