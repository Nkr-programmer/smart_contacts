package com.smart_contact_management.smart_contact_management.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.smart_contact_management.smart_contact_management.dao.ContactRepository;
import com.smart_contact_management.smart_contact_management.dao.UserRepository;
import com.smart_contact_management.smart_contact_management.entities.Contact;
import com.smart_contact_management.smart_contact_management.entities.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class SearchController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal) {
        System.out.println(query);
        User user=this.userRepository.getUserByUserName(principal.getName());
        List<Contact> contact= this.contactRepository.findByNameContainingAndUser(query, user);
        return ResponseEntity.ok(contact);
    }
    

}
