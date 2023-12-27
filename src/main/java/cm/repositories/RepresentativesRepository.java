package cm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.models.Representative;

public interface  RepresentativesRepository extends JpaRepository<Representative, Integer>{
    public Representative findByUsername(String name);
    
}
