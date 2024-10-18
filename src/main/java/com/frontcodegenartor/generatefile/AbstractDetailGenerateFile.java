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
    
    public String getDetailName(FileGenerateBean fileGenerateBean, String type) {
    	return type.concat(firstTextLetterUpperCase(fileGenerateBean.getDetailName()));
    }
    
	public String getDetailFileNameSufixo(FileGenerateBean fileGenerateBean, String type) {
		return getDetailName(fileGenerateBean, type).concat(getSufixo());
	}
    
	public String getFolderName(FileGenerateBean fileGenerateBean, String type) {
		String folderNameDefault = fileGenerateBean.getFolderName()
				.concat("\\form")
				.concat("\\")
				.concat(fileGenerateBean.getDetailName())
				.concat("\\")
				.concat(type);
		
		return folderNameDefault;
	};
}
