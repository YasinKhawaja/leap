package edu.ap.group10.leapwebapp.informationfix;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends CrudRepository<Information, Long> {
    public Information findByName(String name);
}