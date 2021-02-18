package com.techshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techshop.model.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

}
