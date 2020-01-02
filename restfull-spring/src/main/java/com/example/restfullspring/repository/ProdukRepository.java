package com.example.restfullspring.repository;

import com.example.restfullspring.model.ProdukModel;
import org.springframework.data.repository.CrudRepository;

public interface ProdukRepository extends CrudRepository<ProdukModel, Integer> {
}
