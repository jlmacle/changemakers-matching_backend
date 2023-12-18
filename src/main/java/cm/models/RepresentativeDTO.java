package cm.models;

public class RepresentativeDTO {
    
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void getRepresentativeData(Representative representative) {
        this.username = representative.getUsername();
        this.email = representative.getEmail();
    }
   
}