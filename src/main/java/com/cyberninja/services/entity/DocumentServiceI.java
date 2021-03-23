package com.cyberninja.services.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cyberninja.model.entity.Document;
import com.cyberninja.model.entity.Product;

public interface DocumentServiceI {

	List<Document> createDocuments(List<MultipartFile> imgs, Product product);

}
