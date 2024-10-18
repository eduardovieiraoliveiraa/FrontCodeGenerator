package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionResource extends AbstractDetailGenerateFile {

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
	}

	@Override
	public String getExtensionFile() {
		return null;
	}

	@Override
	public String getFolderName(FileGenerateBean fileGenerateBean, String type) {
		return super.getFolderName(fileGenerateBean, type);
	}
}
