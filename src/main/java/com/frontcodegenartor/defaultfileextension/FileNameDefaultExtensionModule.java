package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionModule extends AbstractGenerateFile{
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentModule(fileGenerateBean));
		
		createFile(fileGenerateBean);
	}
	
	private String generateContentModule(FileGenerateBean fileGenerateBean) {
		return """
				(function () {
					'use strict';

					angular.module('cw.%s.%s', []);
				})();
				""".formatted(fileGenerateBean.getModuleName(), fileGenerateBean.getFileName());
	}

	@Override
	public String getExtensionFile() {
		return ".module.js";
	}

	@Override
	public String getSufixo() {
		return "Module";
	}
}
