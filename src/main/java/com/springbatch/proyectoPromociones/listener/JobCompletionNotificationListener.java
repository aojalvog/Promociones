package com.springbatch.proyectoPromociones.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	/**
	 * Método llamado después de que se complete un trabajo. Registra un mensaje de
	 * información en el registro si el estado del trabajo es COMPLETED.
	 *
	 * @param jobExecution el objeto JobExecution que contiene información sobre la
	 *                     ejecución del trabajo
	 */

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("JOB FINISHED! ");
		}
	}

}
