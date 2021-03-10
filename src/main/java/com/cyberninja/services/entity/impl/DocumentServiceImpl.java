package com.cyberninja.services.entity.impl;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Document;
import com.cyberninja.model.entity.Product;
import com.cyberninja.model.repository.DocumentRepository;
import com.cyberninja.services.entity.DocumentServiceI;

@Service
public class DocumentServiceImpl implements DocumentServiceI {

	@Autowired
	private DocumentRepository documentRepo;
	
	/**
	 * @param List multipartFile imgs
	 * @param Product product
	 * @return List Document
	 */
	@Override
	public List<Document> createDocuments(List<MultipartFile> imgs, Product product) {
		List<Document> documents = new ArrayList<>();
				
		for (MultipartFile img : imgs) {
			documents.add(new Document(img.getName(),
									   img.getContentType(),
									   img.getSize(),
									   createBlob(img),
									   product));
		}
		
		if (documents.isEmpty()) { throw new ResponseStatusException(BAD_REQUEST); }
		
		documentRepo.saveAll(documents);

		return documents;
	}

	/**
	 * Crea un blob
	 * 
	 * @param img
	 * @return Blob
	 */
	private Blob createBlob(MultipartFile img) {
		try {
			return new SerialBlob(img.getInputStream().readAllBytes());
		} catch (SQLException | IOException e) {
			throw new ResponseStatusException(NOT_ACCEPTABLE);
		}
	}

}
