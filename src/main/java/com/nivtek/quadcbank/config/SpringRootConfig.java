package com.nivtek.quadcbank.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author AsimSubedi
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.nivtek.quadcbank"} )
public class SpringRootConfig {
	
	// we configure services, daos, datasource etc or some other business layer beans here
	@Bean("dataSource")
	public BasicDataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/quadcbank");
		dataSource.setUsername("nivtekdev");
		dataSource.setPassword("nivtekdev");
		
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		
		return dataSource;
		
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate;
	}

}
