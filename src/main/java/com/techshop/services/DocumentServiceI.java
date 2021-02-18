package com.techshop.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techshop.model.entity.Document;
import com.techshop.model.entity.Product;
import com.techshop.model.entity.dto.DocumentDTO;

public interface DocumentServiceI {

	public List<DocumentDTO> getDocumentsDTO(List<Document> documents) throws SQLException;

	public List<Document> createDocuments(List<MultipartFile> imgs, Product product);

}
