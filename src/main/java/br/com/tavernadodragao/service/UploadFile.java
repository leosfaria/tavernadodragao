package br.com.tavernadodragao.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

	/**
	*@param file = arquivo recebido no request
	*@param path = path da pasta onde será criada/salvo o arquivo
	*@return path relativo da location do arquivo referente a view (que deve ser salvo no banco) 
	*/
	public static String uploadFile(MultipartFile file, String path) throws Exception {
		String name = file.getOriginalFilename();
		
		File dir = new File(path);
		
		if(!dir.exists())
			dir.mkdirs();
		
		File img = new File(path + name);

		file.transferTo(img);
		
		return ".." + path.split("webapp")[1] + "/" + name;
	}
}
