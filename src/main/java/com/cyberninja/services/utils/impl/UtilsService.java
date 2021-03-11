package com.cyberninja.services.utils.impl;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.cyberninja.services.utils.UtilsServiceI;

@Service
public class UtilsService implements UtilsServiceI {
	/**
	 * Crea un blob
	 * 
	 * @param img
	 * @return Blob
	 */
	@Override
	public Blob createBlob(MultipartFile img) {
		try {
			return new SerialBlob(img.getInputStream().readAllBytes());
		} catch (SQLException | IOException e) {
			throw new ResponseStatusException(NOT_ACCEPTABLE);
		}
	}

}
