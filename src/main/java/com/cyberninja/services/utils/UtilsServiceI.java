package com.cyberninja.services.utils;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public interface UtilsServiceI {

	public Blob createBlob(MultipartFile img);

	public String generateRandomCode();

}
