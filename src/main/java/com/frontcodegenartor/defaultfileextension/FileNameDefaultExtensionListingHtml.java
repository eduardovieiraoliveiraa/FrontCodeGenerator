package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingHtml extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		
	}

	@Override
	public String getExtensionFile() {
		return ".html";
	}

	@Override
	public String getSufixo() {
		return null;
	}

}
