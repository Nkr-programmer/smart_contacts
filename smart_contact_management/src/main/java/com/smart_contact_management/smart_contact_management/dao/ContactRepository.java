package com.smart_contact_management.smart_contact_management.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart_contact_management.smart_contact_management.entities.Contact;
import com.smart_contact_management.smart_contact_management.entities.User;

public interface ContactRepository extends JpaRepository<Contact,Integer>{


        @Query("from Contact as u where u.user.id=:userId")
    public Page<Contact> findContactsByUser(@Param("userId") int userId, Pageable pageable);

    //for search bar
    public List<Contact> findByNameContainingAndUser(String name, User user);
}
