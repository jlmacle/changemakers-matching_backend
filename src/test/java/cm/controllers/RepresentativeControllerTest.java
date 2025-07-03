package cm.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import cm.models.Representative;
import cm.repositories.RepresentativesRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8080")
class RepresentativeControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RepresentativesRepository representativeRepository;

    @InjectMocks
    private RepresentativeController representativeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

        System.out.println("Pausing code for 20s");
        try {
            Thread.sleep(20000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Testing that Spring JPA is able to find the user      
        Representative userRetrieval = representativeRepository.findByUsername("testuser");
        System.out.println("User id: "+userRetrieval.getId());
        
        assertNotEquals(null, userRetrieval);  

        
    }

   

    
}
