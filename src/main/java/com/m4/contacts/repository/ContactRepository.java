package com.m4.contacts.repository;

import com.m4.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    @Query(value = "SELECT * FROM contacts WHERE last_name = :lastName", nativeQuery = true)
    public Contact findContactBYName(@Param("lastName") String lastName);

}
