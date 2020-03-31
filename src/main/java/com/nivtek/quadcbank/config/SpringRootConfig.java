package com.nivtek.quadcbank.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author AsimSubedi
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.nivtek"} )
public class SpringRootConfig {
	
	// we configure services, daos, datasource etc or some other business layer beans here
	@Bean
	public BasicDataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("com.cj.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/quadcbank");
		dataSource.setPassword("nivtekdev");
		dataSource.setPassword("nivtekdev");
		
		dataSource.setMaxTotal(4);
		dataSource.setInitialSize(1);
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("SELECT 1");
		
		return dataSource;
		
	}

}
