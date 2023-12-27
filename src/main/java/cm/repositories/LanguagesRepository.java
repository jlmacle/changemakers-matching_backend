package cm.repositories;

import cm.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagesRepository extends JpaRepository<Language, Integer> {
    
}
