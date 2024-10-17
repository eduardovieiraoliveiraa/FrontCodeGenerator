package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingController extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		
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
