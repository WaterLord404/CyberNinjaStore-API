package com.cyberninja.services.utils.impl;

import static com.cyberninja.common.ApplicationConstans.CUPON_ALPHANUM;
import static com.cyberninja.common.ApplicationConstans.CUPON_CODE_LENGHT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

import java.io.IOException;
import java.security.SecureRandom;
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

	static SecureRandom rnd = new SecureRandom();

	/**
	 * Genera un código alfanumerico de 8 caracteres
	 */
	@Override
	public String generateRandomCode() {
		StringBuilder code = new StringBuilder(CUPON_CODE_LENGHT);
		
		for (int i = 0; i < CUPON_CODE_LENGHT; i++)
			code.append(CUPON_ALPHANUM.charAt(
					rnd.nextInt(CUPON_ALPHANUM.length())));
		
		return code.toString();
	}

}
