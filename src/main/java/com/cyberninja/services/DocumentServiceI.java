package com.cyberninja.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.entity.Document;
import com.cyberninja.model.entity.Product;
import com.cyberninja.model.entity.dto.DocumentDTO;

public interface DocumentServiceI {

	public List<DocumentDTO> getDocumentsDTO(List<Document> documents) throws SQLException;

	public List<Document> createDocuments(List<MultipartFile> imgs, Product product);

}
