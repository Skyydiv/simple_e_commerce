package com.learning.simple_e_commerce;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class SimpleECommerceApplication implements CommandLineRunner {

	private final DataSource dataSource;

	public SimpleECommerceApplication(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("DataSource : " + dataSource.toString());
		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("select 1 ");
	}
}
