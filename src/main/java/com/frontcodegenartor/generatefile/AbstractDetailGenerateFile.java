package com.frontcodegenartor.generatefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.frontcodegenartor.bean.FileGenerateBean;

public abstract class AbstractDetailGenerateFile extends AbstractGenerateFile{
	
    protected void createSubFileDetail(String folderName,String conteudo, String newFileName) {
		String fileName = newFileName.concat(getExtensionFile());
        Path pastaPath = Path.of(folderName);
        Path arquivoPath = pastaPath.resolve(fileName);

        try {
            if (Files.notExists(pastaPath)) 
                Files.createDirectories(pastaPath);
            
            Files.writeString(arquivoPath, conteudo, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
	public String getFolderName(FileGenerateBean fileGenerateBean, String type) {
		String folderNameDefault = fileGenerateBean.getFolderName().concat("//").concat(fileGenerateBean.getFileName())
				.concat("//form").concat("//").concat(fileGenerateBean.getDetailName())
				.concat("//"+type);
		
		return folderNameDefault;
	};
}
