package com.springbatch.proyectoPromociones.reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.springbatch.proyectoPromociones.model.Productos;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Reader {

	@Bean
	ArrayList<Productos> itemReader() throws Exception {
		log.info(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("LEYENDO XLSX CONVERGENTES");
		File excelFile = new File("terminales.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);

		// we create an XSSF Workbook object for our XLSX Excel File
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);

		// we iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		ArrayList<Productos> mapRetorno = new ArrayList<>();
		Productos xlxsActual = new Productos();
		int numColumn = 0;

		Row row = rowIt.next();// Saltar Cabecera
		while (rowIt.hasNext()) {
			row = rowIt.next();

			// iterate on cells for the current row
			Iterator<Cell> cellIterator = row.cellIterator();
			numColumn = 0;
			xlxsActual = new Productos();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (numColumn) {
				case 0: {
					xlxsActual.setId(cell.toString().replace(".0", ""));
					;
					break;
				}
				case 1: {
					xlxsActual.setName(cell.toString());
					break;
				}
				case 2: {
					xlxsActual.setDescription(cell.toString().replace(".0", ""));
					break;
				}
				case 3: {
					xlxsActual.setCode(cell.toString().replace(".0", ""));
					break;
				}
				default: {
					break;
				}

				}
				numColumn++;

			}
			mapRetorno.add(xlxsActual);

		}

		workbook.close();
		fis.close();

		log.info("Promos Leidas del fichero: " + mapRetorno.size());

		log.info("Fin Lector XLSX");
		return mapRetorno;
	}
}
