package com.devsuperior.crudclientes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crudclientes.dto.ClientDTO;
import com.devsuperior.crudclientes.entities.Client;
import com.devsuperior.crudclientes.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly=true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly=true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.get();
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO obj) {
		Client entity = new Client();
		updateData(entity, obj);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO objdto) {
		Client entity = repository.getOne(id);
		updateData(entity, objdto);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	private void updateData(Client entity, ClientDTO objdto) {
		entity.setName(objdto.getName());
		entity.setCpf(objdto.getCpf());
		entity.setIncome(objdto.getIncome());
		entity.setBirthDate(objdto.getBirthDate());
		entity.setChildren(objdto.getChildren());
	}
}
