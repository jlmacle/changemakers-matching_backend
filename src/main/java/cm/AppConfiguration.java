package cm;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
public class AppConfiguration {	
	private static Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
    private static final String ENDPOINT_PROJECTS = "/projects";
    private static final String ENDPOINT_CONTRIBUTOR_NEWACC = "/contributors/new-account";
    private static final String ENDPOINT_REPRESENTATIVE_NEWACC = "/representatives/new-account";


    private static final String CORS_LOOPBACK3000_LIVEPREVIEW = "http://127.0.0.1:3000";
    private static final String CORS_LOOPBACK3001_LIVEPREVIEW = "http://127.0.0.1:3001";
    private static final String CORS_LOOPBACK3002_LIVEPREVIEW = "http://127.0.0.1:3002";
    private static final String CORS_LOOPBACK_LIGHTTPD = "http://127.0.0.1";
    
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
       
        String postgresPort = System.getenv("DB_POSTGRES_PORT");
        String dbName = System.getenv("DB_NAME");
        String jdbcURL = "jdbc:postgresql://localhost:"+postgresPort+"/"+dbName;
        String dbUSERNAME = System.getenv("DB_USERNAME");
        String dbPASSWORD = System.getenv("DB_PASSWORD");

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");       			
        
        dataSourceBuilder.url(jdbcURL);
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
         
                String[] origins = {CORS_LOOPBACK3000_LIVEPREVIEW, CORS_LOOPBACK3001_LIVEPREVIEW,
                    CORS_LOOPBACK3002_LIVEPREVIEW, CORS_LOOPBACK_LIGHTTPD};

                registry.addMapping(ENDPOINT_PROJECTS).allowedOrigins(origins).allowedMethods("GET");
                registry.addMapping(ENDPOINT_CONTRIBUTOR_NEWACC).allowedOrigins(origins).allowedMethods("POST", "OPTIONS");
                registry.addMapping(ENDPOINT_REPRESENTATIVE_NEWACC).allowedOrigins(origins).allowedMethods("POST", "OPTIONS");
            
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
            .requestMatchers(HttpMethod.GET, ENDPOINT_PROJECTS).permitAll()
            .requestMatchers(HttpMethod.OPTIONS,ENDPOINT_CONTRIBUTOR_NEWACC).permitAll()
            .requestMatchers(HttpMethod.POST,ENDPOINT_CONTRIBUTOR_NEWACC).permitAll()
            .requestMatchers(HttpMethod.OPTIONS,ENDPOINT_REPRESENTATIVE_NEWACC).permitAll()
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

