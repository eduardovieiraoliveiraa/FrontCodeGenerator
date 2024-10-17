package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionFormService extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		createSubFile(fileGenerateBean, "form");
	}

	@Override
	public String getExtensionFile() {
		return ".service.js";
	}

	@Override
	public String getSufixo() {
		return "Service";
	}
}
