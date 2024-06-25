package com.example.rest_security_jwt_hometask.repositories;

import com.example.rest_security_jwt_hometask.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
