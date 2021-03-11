package com.cyberninja.model.entity.converter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Document;
import com.cyberninja.model.entity.dto.DocumentDTO;

@Component
public class DocumentConverter {
	
	public DocumentDTO documentToDocumentDTO(Document document) {
		DocumentDTO dto = new DocumentDTO();
		
		dto.setId(document.getId());
		dto.setFileType(document.getFileType());
		try {
			dto.setPicture(document.getPicture().getBytes(1L, (int) document.getPicture().length()));
		}catch (SQLException e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
		
		return dto;
	}
	

	public List<DocumentDTO> getDocumentsDTO(List<Document> documents) {
		List<DocumentDTO> documentsDTO = new ArrayList<>();
		
		for (Document document : documents) {
			// Transforma Document a DocumentDTO y lo añade a la nueva lista creada
			documentsDTO.add(documentToDocumentDTO(document));
		}
		
		return documentsDTO;
	}
}
