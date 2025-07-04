package cm.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import cm.models.Representative;
import cm.repositories.RepresentativesRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8081")
class RepresentativeControllerTest {

    @Autowired
    private RepresentativesRepository representativeRepository;

    @Autowired
    private RepresentativeController representativeController;    

    @Test
    void testCreateAccount_Success() {
        // At start, the database is empty by configuration
        // (spring.jpa.hibernate.ddl-auto=create)

        // At first, searching for a user should return null
        assertEquals(null, representativeRepository.findByUsername("testuser"));
        
        // Then, after creating a user, the user should be found successfully
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testuser");
        credentials.put("password", "password");        
        ResponseEntity<String> response = representativeController.createAccount(credentials);        
        // Testing the response status code
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        System.out.println("Response status: "+response.getStatusCode());

        // Testing that Spring JPA is able to find the user      
        Representative userRetrieval = representativeRepository.findByUsername("testuser");
        System.out.println("User id: "+userRetrieval.getId());
        
        assertNotEquals(null, userRetrieval);  

        
    }

   

    
}
