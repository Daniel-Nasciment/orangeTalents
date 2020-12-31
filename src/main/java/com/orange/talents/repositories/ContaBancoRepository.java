package com.orange.talents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.talents.domain.ContaBanco;

@Repository
public interface ContaBancoRepository extends JpaRepository<ContaBanco, Integer>{

}
