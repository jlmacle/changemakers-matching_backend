package cm.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cm.models.Representative;
import cm.models.RepresentativeDTO;
import cm.repositories.RepresentativesRepository;

// Class kept default to meet SonarQube quality standards
class RepresentativeControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RepresentativesRepository representativeRepository;

    @InjectMocks
    private RepresentativeController representativeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_Success() throws JsonProcessingException {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testuser");
        credentials.put("password", "password");

        when(representativeRepository.findByUsername("testuser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(representativeRepository.save(any(Representative.class))).thenReturn(new Representative("testuser", "encodedPassword"));

        ResponseEntity<String> response = representativeController.createAccount(credentials);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        RepresentativeDTO repDTO = new RepresentativeDTO();
        repDTO.setUsername("testuser");
        repDTO.setEmail("testuser@mail.com");
        String expectedJson = mapper.writeValueAsString(repDTO);
        assertEquals(expectedJson, response.getBody());
    }

    @Test
    void testCreateAccount_UsernameExists() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "existinguser");
        credentials.put("password", "password");

        when(representativeRepository.findByUsername("existinguser")).thenReturn(new Representative("existinguser", "encodedPassword"));

        ResponseEntity<String> response = representativeController.createAccount(credentials);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @SuppressWarnings("null")
    @Test
    void testCreateAccount_JsonProcessingException() throws JsonProcessingException {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testuser");
        credentials.put("password", "password");

        when(representativeRepository.findByUsername("testuser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(representativeRepository.save(any(Representative.class))).thenReturn(new Representative("testuser", "encodedPassword"));
        ObjectMapper mapper = mock(ObjectMapper.class);
        when(mapper.writeValueAsString(any(RepresentativeDTO.class))).thenThrow(new JsonProcessingException("Error") {});

        ResponseEntity<String> response = representativeController.createAccount(credentials);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("Error while parsing JSON"));
    }
}
