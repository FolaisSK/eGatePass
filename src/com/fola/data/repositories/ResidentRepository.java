package com.fola.data.repositories;

import com.fola.data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepository extends MongoRepository<Resident,String> {

    boolean existsResidentByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<Resident> findByEmail(String email);
}
