package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionDialogController extends AbstractDetailGenerateFile {

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		
		String conteudo = "";
		String folderNameDefault = fileGenerateBean.getFolderName().concat("//").concat(fileGenerateBean.getFileName())
		.concat("//form").concat("//").concat(fileGenerateBean.getDetailName())
		.concat("//dialog");
		
		fileGenerateBean.getDetailName().concat(getExtensionFile());
		
		createSubFileDetail(folderNameDefault, conteudo, getExtensionFile());
	}

	@Override
	public String getExtensionFile() {
		return ".controller.js";
	}

	@Override
	public String getSufixo() {
		return "Controller";
	}
}
