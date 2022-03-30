package com.devsuperior.crudclientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crudclientes.entities.Client;
import com.devsuperior.crudclientes.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.get();
	}
	
	@Transactional
	public Client insert(Client obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public Client update(Long id, Client obj) {
		Client entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Client entity, Client obj) {
		entity.setName(obj.getName());
		entity.setCpf(obj.getCpf());
		entity.setIncome(obj.getIncome());
		entity.setBirthDate(obj.getBirthDate());
		entity.setChildren(obj.getChildren());
	}
}
