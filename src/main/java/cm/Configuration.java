package cm;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class used to configure the application
 */

@org.springframework.context.annotation.Configuration
public class Configuration {	
	private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    /**
     * Retrieves database information and credentials from environment variables.
     * @return a DataSource object
     * https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html (Java 8)
     */
    @Bean
    DataSource getDataSource() {
        logger.info("Entering getDataSource()");        
        // DB_URL contains the value of the JDBC URL
        // jdbc:postgresql://<hostname>:<port>/<databasename>      
        String envDbJdbcRootFile = "DB_JDBC_ROOT_FILE";
        String envDbUsername = "DB_USERNAME";
        String envDbPassword = "DB_PASSWORD";
        String envDbName = "DB_NAME";
        String dbURL = null;
        String dbUSERNAME = null;
        String dbPASSWORD = null;

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");       			
        
        dbUSERNAME = System.getenv(envDbUsername);
        dbPASSWORD = System.getenv(envDbPassword);
        dbURL =  System.getenv(envDbJdbcRootFile) + System.getenv(envDbName);
     
        dataSourceBuilder.url(dbURL);
        dataSourceBuilder.username(dbUSERNAME);
        dataSourceBuilder.password(dbPASSWORD);
        return dataSourceBuilder.build();

    }
	
    
	//https://spring.io/blog/2015/06/08/cors-support-in-spring-framework    
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() 
		{	
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
         
                // TODO : to restrict the origins (used to avoid the cors policy issue when the hml file is aceessed on the file:// protocol)
                // Might be possible to get the File protocol to be accepted in the allowed origins list
                registry.addMapping("/projects").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/contributor_auth").allowedOrigins("*").allowedMethods("*");
            
            }
		};
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	public static void logInfoEnabled(Logger logger, String msg, String data)
	{
		if (logger.isInfoEnabled()) logger.info(String.format(msg, data));
	}

}

