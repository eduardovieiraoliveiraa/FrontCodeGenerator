package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionListagemService extends AbstractDetailGenerateFile {

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean,"listagem");
	}

	@Override
	public String getExtensionFile() {
		return null;
	}

}
