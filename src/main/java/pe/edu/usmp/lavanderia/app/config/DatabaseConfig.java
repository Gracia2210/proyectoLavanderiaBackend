package pe.edu.usmp.lavanderia.app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {
	
	
	@Bean(name = { "db_co_re_desa" })
	
	@Primary
	@ConfigurationProperties(prefix = "desa.datasource") // se debe modificar la propiedad produ
	public DataSource hostdataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = { "db_desa" }) // CONEXION
	@Autowired
	public JdbcTemplate hostJdbcTemplate(@Qualifier("db_co_re_desa") DataSource dbconexion) {
		return new JdbcTemplate(dbconexion);
	}
}
