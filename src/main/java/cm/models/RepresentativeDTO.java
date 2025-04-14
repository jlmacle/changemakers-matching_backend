package cm.models;

/**
 * Data Transfer Object for Representative.
 */
public class RepresentativeDTO {
    
    private String username;
    private String email;

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Populates the DTO with data from a Representative object.
     * @param representative the representative object
     * @return the populated DTO
     */
    public RepresentativeDTO getRepresentativeData(Representative representative) {
        RepresentativeDTO dto = new RepresentativeDTO();
        dto.setUsername(representative.getUsername());
        dto.setEmail(representative.getEmail());     
        
        return dto;
    }
   
}