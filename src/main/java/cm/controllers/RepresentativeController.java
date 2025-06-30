package cm.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cm.models.Representative;
import cm.models.RepresentativeDTO;
import cm.repositories.RepresentativesRepository;


/* Todo: String sanitizations */
/* Todo: Password policy check: length, complexity, ... */

/**
 * Controller for handling representative-related requests.
 */
@RestController
public class RepresentativeController {
    
    Logger logger = LoggerFactory.getLogger(getClass());    
    private final boolean debug = logger.isDebugEnabled(); 

    private final PasswordEncoder passwordEncoder;
    private final RepresentativesRepository representativeRepository;

    /**
     * Constructor for RepresentativeController.
     * @param passwordEncoder the password encoder
     * @param representativeRepository the RepresentativesRepository object
     */
    public RepresentativeController(PasswordEncoder passwordEncoder, RepresentativesRepository representativeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.representativeRepository = representativeRepository;
        
    }

    /**
     * Creates a new representative account.
     * @param credentials the credentials data structure containing username and password
     * @return a ResponseEntity with the result of the account creation
     */
    @PostMapping("/representatives/new-account")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, String> credentials) {

        
         String username = credentials.get("username");
         String encodedPassword = passwordEncoder.encode(credentials.get("password"));
         // Checking if the username already exists
         if (representativeRepository.findByUsername(username) != null) {
            return ResponseEntity
               .status(HttpStatus.CONFLICT) // HTTP 409 Conflict
               .body("Username already exists");
         }
             
         else {
           /* Planning to monitor  if the frontend validation has been bypassed 
           (using Postman e.g.) */
            Representative representative = new Representative(username, encodedPassword);
            representativeRepository.save(representative);                
            
            return ResponseEntity
            .status(HttpStatus.CREATED) // HTTP 201 Created
            .body("Username created"); // Assuming jsonString is a well-formed JSON string
        }
         
         
     }    
}
