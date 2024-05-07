package com.springbatch.proyectoPromociones.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.springbatch.proyectoPromociones.listener.JobCompletionNotificationListener;
import com.springbatch.proyectoPromociones.model.Productos;
import com.springbatch.proyectoPromociones.process.StockProcessor;

/**
 * Clase de configuración para definir un trabajo de procesamiento por lotes
 * utilizando Spring Batch.
 */
@Configuration
public class BatchConfig {

	/**
	 * Definición del bean para crear un JobCompletionNotificationListener.
	 *
	 * @return Instancia de JobCompletionNotificationListener
	 */
	@Bean
	JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	/**
	 * Definición del bean para crear un Job.
	 *
	 * @param jobRepository Repositorio de trabajos (JobRepository)
	 * @param step          Paso (Step) de procesamiento
	 * @param listener      Listener para notificaciones de finalización del trabajo
	 *                      (JobCompletionNotificationListener)
	 * @return Instancia de Job
	 */
	@Bean
	Job job(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
		return new JobBuilder("xlsx-to-csv", jobRepository).listener(listener).start(step).build();
	}

	/**
	 * Definición del bean para crear un Step.
	 *
	 * @param jobRepository      Repositorio de trabajos (JobRepository)
	 * @param transactionManager Gestor de transacciones de origen de datos
	 *                           (DataSourceTransactionManager)
	 * @param reader             Lector de elementos (ItemReader)
	 * @param processor          Procesador de elementos (StockProcessor)
	 * @param writer             Escritor de elementos en archivo plano
	 *                           (FlatFileItemWriter)
	 * @return Instancia de Step
	 */
	@Bean
	Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			ItemReader<Productos> reader, StockProcessor processor, FlatFileItemWriter<Productos> writer) {
		return new StepBuilder("step1", jobRepository).<Productos, Productos>chunk(10, transactionManager)
				.reader(reader).processor(processor).writer(writer).build();
	}
}
