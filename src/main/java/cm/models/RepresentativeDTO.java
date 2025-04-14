package cm.models;

/**
 * Data Transfer Object for Representative.
 */
public class RepresentativeDTO {
    
    private String username;
    private String email;
    private String projectName;

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
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    /**
     * Populates the DTO with data from a Representative object.
     * @param representative the representative object
     */
    public void getRepresentativeData(Representative representative) {        
        this.setUsername(representative.getUsername());
        this.setEmail(representative.getEmail());     
        this.setProjectName(representative.getProjectName());
    }

    
   
}