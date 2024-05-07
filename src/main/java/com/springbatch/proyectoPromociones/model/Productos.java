package com.springbatch.proyectoPromociones.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Productos {

	private String ID;
	private String NAME;
	private String DESCRIPTION;
	private String CODE;

}
