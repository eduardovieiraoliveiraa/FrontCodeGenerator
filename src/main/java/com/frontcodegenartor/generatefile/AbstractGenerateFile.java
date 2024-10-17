package com.frontcodegenartor.generatefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.frontcodegenartor.bean.FileGenerateBean;

public abstract class AbstractGenerateFile {

	public abstract void generateFile(FileGenerateBean fileGenerateBean);
	
	public abstract String getExtensionFile();
	
	public String getSufixo() {
		return "";
	};
	
	public String getFileNameSufixo(FileGenerateBean fileGenerateBean) {
		return fileGenerateBean.getFileName().concat(getSufixo());
	}
	
	public String getTextBlockModule(FileGenerateBean fileGenerateBean) {
		String fileNameSufixo = getFileNameSufixo(fileGenerateBean);
		
		return """
		angular
	      .module('cw.%s.%s')
	      .factory('%s', %s);
		""".formatted(
				fileGenerateBean.getModuleName(), 
				fileGenerateBean.getFileName(),fileNameSufixo,fileNameSufixo);
	}
	
	public String getTextBlock(FileGenerateBean fileGenerateBean) {
		return """
		 angular
				.module('cw.%s.%s')
		""".formatted(
				fileGenerateBean.getModuleName(), 
				fileGenerateBean.getFileName());
	}
	
    public String separeteByHifen(String input) {
        String result = input.replaceAll("([a-z])([A-Z])", "$1-$2");
        
        return result.toLowerCase();
    }
    
    protected void createSubFile(FileGenerateBean fileGenerateBean,String nameNewFolder) {
		String fileName = nameNewFolder.concat(getExtensionFile());
        Path pastaPath = Path.of(fileGenerateBean.getFolderName().concat("\\").concat(nameNewFolder));
        Path arquivoPath = pastaPath.resolve(fileName);

        try {
            if (Files.notExists(pastaPath)) 
                Files.createDirectories(pastaPath);
            
            Files.writeString(arquivoPath, fileGenerateBean.getContent(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	protected void createFile(FileGenerateBean fileGenerateBean) {
		String fileName = fileGenerateBean.getFileName().concat(getExtensionFile());
		
        Path pastaPath = Path.of(fileGenerateBean.getFolderName());
        Path arquivoPath = pastaPath.resolve(fileName);

        try {
            if (Files.notExists(pastaPath)) 
                Files.createDirectories(pastaPath);
            
            Files.writeString(arquivoPath, fileGenerateBean.getContent(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
