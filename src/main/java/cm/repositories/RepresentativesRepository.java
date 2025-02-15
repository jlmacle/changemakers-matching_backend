package cm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.models.Representative;

/**
 * Repository interface for {@link Representative} entities.
 */
public interface RepresentativesRepository extends JpaRepository<Representative, Integer> {
    
    /**
     * Finds a representative by their username.
     *
     * @param username the username of the representative
     * @return the representative with the given username
     */
    public Representative findByUsername(String username);
    
}
