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

	private String id;
	private String name;
	private String description;
	private String code;

}
