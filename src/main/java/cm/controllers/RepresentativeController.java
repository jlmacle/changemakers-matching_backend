package cm.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cm.models.Representative;
import cm.models.RepresentativeDTO;
import cm.repositories.RepresentativesRepository;




@RestController
public class RepresentativeController {
    
    Logger logger = LoggerFactory.getLogger(getClass());     

    private final PasswordEncoder passwordEncoder;
    private final RepresentativesRepository representativeRepository;

    public RepresentativeController(PasswordEncoder passwordEncoder, RepresentativesRepository representativeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.representativeRepository = representativeRepository;
    }

    /** A method used to retrieve representative data when authentication succeeds.
     * 
     */
     @PostMapping("/representative_newacc")
     public String authenthicator(@RequestBody Map<String, String> credentials) {

         String username = credentials.get("username");
         String encodedPassword = passwordEncoder.encode(credentials.get("password"));
         // Checking if the username already exists
         if (representativeRepository.findByUsername(username) != null) {
             return "Username already exists";
         }
         else {
            Representative representative = Representative.createRepresentative(username, encodedPassword);
            Representative rep = representativeRepository.save(representative);                
            RepresentativeDTO repDTO = new RepresentativeDTO();
            repDTO.getRepresentativeData(rep);
            //Email unrelated to real and personal user data
            repDTO.setEmail(username+"@mail.com");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString="";
            try {
                jsonString = mapper.writeValueAsString(repDTO);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                // TODO : logging level to set
                if (logger.isInfoEnabled()) { logger.info("Error while parsing JSON"); }
                e.printStackTrace();
            }

            if (logger.isInfoEnabled()) { logger.info(String.format("New account created for representative: %s",repDTO.toString())); }   
            return jsonString;
         }
         
     }    
}
