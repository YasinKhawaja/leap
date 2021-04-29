package edu.ap.group10.leapwebapp.useradmin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ap.group10.leapwebapp.useradmin.Useradmin;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UseradminRepository extends CrudRepository<Useradmin, Integer> {

}