package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionDialogController extends AbstractDetailGenerateFile {

	private static final String DIALOG = "dialog";

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, DIALOG);
		String fileNameDetail = getDetailName(fileGenerateBean, DIALOG);
		
		createSubFileDetail(folderName, "teste", fileNameDetail);
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
