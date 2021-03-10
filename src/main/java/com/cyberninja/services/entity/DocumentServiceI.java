package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.Document;
import com.cyberninja.model.Product;

public interface DocumentServiceI {

	public List<Document> createDocuments(List<MultipartFile> imgs, Product product);

}
