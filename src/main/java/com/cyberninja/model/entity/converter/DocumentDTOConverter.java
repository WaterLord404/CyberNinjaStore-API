package com.cyberninja.model.entity.converter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.model.entity.Document;
import com.cyberninja.model.entity.dto.DocumentDTO;

@Component
public class DocumentDTOConverter {

	public DocumentDTO documentToDocumentDTO(Document document) {
		DocumentDTO dto = new DocumentDTO();
		
		dto.setId(document.getId());
		dto.setFileName(document.getFileName());
		dto.setFileType(document.getFileType());
		dto.setFileSize(document.getFileSize());
		try {
			dto.setPicture(document.getPicture().getBytes(1L, (int) document.getPicture().length()));
		}catch (SQLException e) {
			throw new ResponseStatusException(INTERNAL_SERVER_ERROR);
		}
		
		return dto;
	}
	
}
