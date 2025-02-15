package cm;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * Class used to configure the application.
 */

/* TODO: "remember me" configuration 
 */
@Configuration
public class AppConfiguration {	
	private static Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
    private final boolean debug = logger.isInfoEnabled();

    private static final String ENDPOINT_PROJECTS = "/projects";
    private static final String ENDPOINT_CONTRIBUTOR_NEWACC = "/contributors/new-account";
    private static final String ENDPOINT_REPRESENTATIVE_NEWACC = "/representatives/new-account";


    private static final String CORS_LOOPBACK3000_LIVEPREVIEW = "http://127.0.0.1:3000";
    private static final String CORS_LOOPBACK3001_LIVEPREVIEW = "http://127.0.0.1:3001";
    private static final String CORS_LOOPBACK3002_LIVEPREVIEW = "http://127.0.0.1:3002";
    private static final String CORS_LOOPBACK_LIGHTTPD = "http://127.0.0.1";
    
    /**
     * Returns a connection to the physical data source, using environment variables and a JDBC URL.
     * @return a DataSource object, a connection to the physical data source
     * https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html (Java 8)
     */
    @Bean
    DataSource getDataSource() {
        if (debug) logger.info("Entering getDataSource()");        

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


    /**
     * Configures CORS settings for the application.
     * @return a WebMvcConfigurer object (to be developped)
     */
    @Bean
    WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() 
		{	
			@Override
            public void addCorsMappings(@NonNull CorsRegistry registry)
			{
         
                String[] origins = {CORS_LOOPBACK3000_LIVEPREVIEW, CORS_LOOPBACK3001_LIVEPREVIEW,
                    CORS_LOOPBACK3002_LIVEPREVIEW, CORS_LOOPBACK_LIGHTTPD};

                registry.addMapping(ENDPOINT_PROJECTS).allowedOrigins(origins).allowedMethods("GET");
                registry.addMapping(ENDPOINT_CONTRIBUTOR_NEWACC).allowedOrigins(origins).allowedMethods("POST", "OPTIONS");
                registry.addMapping(ENDPOINT_REPRESENTATIVE_NEWACC).allowedOrigins(origins).allowedMethods("POST", "OPTIONS");
            
            }
		};
	}

    /**
     * Configures security settings for the application.
     * @param http the HttpSecurity object
     * @return a SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // Temporary, to allow POST requests. A security hotspot to fix.
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

    /**
     * Provides a PasswordEncoder bean.
     * @return a PasswordEncoder object
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

