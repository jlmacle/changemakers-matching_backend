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

import cm.models.Contributor;
import cm.repositories.ContributorsRepository;



@RestController
public class ContributorController {
    Logger logger = LoggerFactory.getLogger(ContributorController.class);

    private final PasswordEncoder passwordEncoder;

    private final ContributorsRepository contributorRepository;

    public ContributorController(PasswordEncoder passwordEncoder, ContributorsRepository contributorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.contributorRepository = contributorRepository;
    }

    /** A method used to retrieve contributor data when authentication succeeds.
     * 
     */

    @PostMapping("/contributors/new-account")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String encodedPassword = passwordEncoder.encode(credentials.get("password"));
        // Checking if the username already exists
        if (contributorRepository.findByUsername(username) != null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Username already exists");
        }
        else {
            Contributor contributor = new Contributor(username, encodedPassword);
            contributorRepository.save(contributor);        
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("New account created successfully");
        }
        
    }
    

    
    
    
    
}
