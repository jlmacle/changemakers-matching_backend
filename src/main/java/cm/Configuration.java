package cm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
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
        String dbURL = null;
        // DB_URL contains the value of the JDBC URL
        // jdbc:postgresql://<hostname>:<port>/<databasename>
        // 5432 is the port used by the Docker service,
        // 5433	is used by the instance of PostgreSQL used for testing. 		
        String dbUSERNAME = null ;
        String dbPASSWORD = null ;
        boolean theAppIsRunningInADockerContainer = false;
        String dbJdbcRootFile = "DB_JDBC_ROOT_FILE";
        String postgresDbFile = "POSTGRES_DB_FILE";
        String postgresUserFile = "POSTGRES_USER_FILE";
        String postgresPasswordFile = "POSTGRES_PASSWORD_FILE";
        String dbUsername = "DB_USERNAME";
        String dbPassword = "DB_PASSWORD";
        String dbName = "DB_NAME";

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");

        // Case 1 : A Docker environment is being used		
        // Case 2 : the components are being run outside of Docker				
        
        if (System.getenv(postgresUserFile) != null)
        {
            theAppIsRunningInADockerContainer = true;
            dbUSERNAME = extractDockerSecretFromFile(System.getenv(postgresUserFile));
        } else
        {
            dbUSERNAME = System.getenv(dbUsername);
        }

        if (System.getenv(postgresPasswordFile) != null)
        {
            dbPASSWORD = extractDockerSecretFromFile(System.getenv(postgresPasswordFile));
        } else
        {
            dbPASSWORD = System.getenv(dbPassword);
        }

        if (System.getenv(postgresDbFile) != null && System.getenv(dbJdbcRootFile) != null)
        {
            dbURL = extractDockerSecretFromFile(System.getenv(dbJdbcRootFile)) + extractDockerSecretFromFile(System.getenv(postgresDbFile));
        } else
        {
            dbURL =  System.getenv(dbJdbcRootFile) + System.getenv(dbName);
        }

        if (theAppIsRunningInADockerContainer)
        {
            logger.info("*The app is running in a Docker container.*");
        } else
        {
            logger.info("*Use of the PostgreSQL Docker service alone*");
        }

        // Todo :  to remove when debugged
        logInfoEnabled(logger, "System.getenv(DB_USERNAME): *%s*", System.getenv(dbUsername));
        logInfoEnabled(logger, "System.getenv(DB_PASSWORD): *%s*", System.getenv(dbPassword));
        logInfoEnabled(logger, "System.getenv(DB_NAME): *%s*", System.getenv(dbName));
        logInfoEnabled(logger, "System.getenv(DB_JDBC_ROOT_FILE): *%s*", System.getenv(dbJdbcRootFile));
        logInfoEnabled(logger, "dbURL: *%s*", dbURL);
        logInfoEnabled(logger, "dbUSERNAME: *%s*", dbUSERNAME);
        logInfoEnabled(logger, "dbPASSWORD: *%s*", dbPASSWORD);
        
        dataSourceBuilder.url(dbURL);
        dataSourceBuilder.username(dbUSERNAME);
        dataSourceBuilder.password(dbPASSWORD);
        return dataSourceBuilder.build();

    }
	
    /**
     * A method used to extract a Docker secret from a Docker environment variable
     * ( the Docker environment variables are not stored with the other account environment variables )
     * @param 
     * @return
     */
	public static String extractDockerSecretFromFile(String pathToSecret)
	{	String secret=null;
		try {			
			secret = Files.readString(Paths.get(pathToSecret));
			logInfoEnabled(logger,"Path to secret:%s",pathToSecret);
			logInfoEnabled(logger,"Secret value extracted:%s",secret);
		} catch (IOException e) {
			logInfoEnabled(logger,"*** Caught an IOException in extractDockerSecretFromFile: %s",e.getLocalizedMessage());
			//e.printStackTrace //suppressed to avoid a security hotspot.
		}		
		return secret;
	}	
	
	//https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
    /*
     * No need for CORS configuration when running the backend with Docker
     */
    
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() 
		{	
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
                // When running the app without Docker
                final String CORS_LOCALHOST_4200 = "http://localhost:4200";
                // When running the app with Docker
                final String CORS_LOOPBACK = "http://127.0.0.1";
                // When running the app on Azure
                final String CORS_AZURE = "https://changemakers-matchmaking.azurewebsites.net";
                String[] origins= {CORS_LOCALHOST_4200,CORS_LOOPBACK, CORS_AZURE};
                // TODO : to restrict the methods
                // registry.addMapping("/projects").allowedOrigins(origins).allowedMethods("*");
                // TODO : to restrict the origins (used to avoid the cors policy issue when the hml file is aceessed on the file:// protocol)
                registry.addMapping("/projects").allowedOrigins("*").allowedMethods("*");
            
            }
		};
	}
	

	public static void logInfoEnabled(Logger logger, String msg, String data)
	{
		if (logger.isInfoEnabled()) logger.info(String.format(msg, data));
	}

}

