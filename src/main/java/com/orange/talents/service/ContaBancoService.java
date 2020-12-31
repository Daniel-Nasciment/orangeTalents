package com.orange.talents.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.talents.domain.ContaBanco;
import com.orange.talents.dto.ContaBancoDTO;
import com.orange.talents.repositories.ContaBancoRepository;

@Service
public class ContaBancoService {

	@Autowired
	private ContaBancoRepository repo;
	
	public ContaBanco find(Integer id) {
		Optional<ContaBanco> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ContaBanco.class.getName(), null));
	}
	
	public List<ContaBanco> findAll() {
		List<ContaBanco> lista = repo.findAll();
		
		return lista;
	}
	
	public ContaBanco insert(ContaBanco obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public ContaBanco fromDTO(ContaBancoDTO contaDTO) {
		return new ContaBanco(null, contaDTO.getNome(), contaDTO.getEmail(), contaDTO.getCpf(), contaDTO.getDataNasc());
	}
	
	
}
