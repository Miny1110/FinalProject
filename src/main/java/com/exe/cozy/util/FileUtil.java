package com.exe.cozy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {

	private static final String filePath = "D:\\test\\";

	public static String FileWriter(MultipartFile multipartFile) throws IOException {
		String storedFileName = "";
		FileOutputStream fos = null;
		String fileExtension = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");

		try {
			storedFileName = UUID.randomUUID().toString();

			byte fileData[] = multipartFile.getBytes();
			fos = new FileOutputStream(filePath + storedFileName + "." + fileExtension);

			fos.write(fileData);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

		return storedFileName;
	}
}
