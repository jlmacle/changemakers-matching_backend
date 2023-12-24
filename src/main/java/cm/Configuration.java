package cm;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class used to configure the application
 */
//TODO: to understand why @Configuration is not working
@org.springframework.context.annotation.Configuration
public class Configuration {	
	private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static final String ENDPOINT_PROJECTS = "/projects";
    private static final String ENDPOINT_CONTRIBUTOR_NEWACC = "/contributors/new-account";
    private static final String ENDPOINT_REPRESENTATIVE_NEWACC = "/representatives/new-account";


    private static final String CORS_LOOPBACK3000_LIVEPREVIEW = "http://127.0.0.1:3000";
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
         
                String[] origins = {CORS_LOOPBACK3000_LIVEPREVIEW};

                registry.addMapping(ENDPOINT_PROJECTS).allowedOrigins(origins).allowedMethods("GET");
                registry.addMapping(ENDPOINT_CONTRIBUTOR_NEWACC).allowedOrigins(origins).allowedMethods("POST");
                registry.addMapping(ENDPOINT_REPRESENTATIVE_NEWACC).allowedOrigins(origins).allowedMethods("POST");
            
            }
		};
	}

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // SpringSecurity doesn't allow POST requests if not explicitly allowed
        .csrf( csrf -> 
            {
                csrf.ignoringRequestMatchers( new AntPathRequestMatcher(ENDPOINT_CONTRIBUTOR_NEWACC, "POST") );
                csrf.ignoringRequestMatchers( new AntPathRequestMatcher(ENDPOINT_REPRESENTATIVE_NEWACC, "POST") );
            }
        )
        .authorizeHttpRequests
            ( authz -> authz
            // To allow access to endpoint without asking for authentication
            // .requestMatchers(HttpMethod.GET, ENDPOINT_PROJECTS).permitAll()
            .requestMatchers(HttpMethod.POST,ENDPOINT_CONTRIBUTOR_NEWACC).permitAll()
            .requestMatchers(HttpMethod.POST,ENDPOINT_REPRESENTATIVE_NEWACC).permitAll()
            // In all other cases, authentication is required
            .anyRequest().authenticated()
            )
        .httpBasic( withDefaults() );

        return http.build();

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

