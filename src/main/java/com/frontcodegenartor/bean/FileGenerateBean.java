package com.frontcodegenartor.bean;

import java.util.Collections;
import java.util.List;

public class FileGenerateBean {

	private String folderName;
	private String fileName;
	private String content;
	private String moduleName;
	private String siglaPathModule;

	private List<String> details;

	public String getFolderName() {
		return folderName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSiglaPathModule() {
		return siglaPathModule.toUpperCase();
	}

	public void setSiglaPathModule(String siglaPathModule) {
		this.siglaPathModule = siglaPathModule;
	}

	public List<String> getDetails() {
		if(details == null) return Collections.emptyList();
		
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}