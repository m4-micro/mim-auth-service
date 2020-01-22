package mim.auth.service.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories("mim.auth.service")
@EntityScan("mim.auth.service")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("mim.auth.service")

public class DataConfiguration {

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create()
				.url("jdbc:postgresql://localhost:5432/mim-auth-service")
				.username("postgres")
				.driverClassName("org.postgresql.Driver")
				.build();
	}
}
