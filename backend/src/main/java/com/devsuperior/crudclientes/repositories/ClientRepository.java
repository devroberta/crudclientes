package com.devsuperior.crudclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.crudclientes.entities.Client;

public interface ClientRepository extends JpaRepository <Client, Long> {

}
