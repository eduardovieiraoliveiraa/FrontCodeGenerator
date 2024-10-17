package com.frontcodegenartor.service;

import org.springframework.stereotype.Service;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.constants.EnumDefaultFileNameExtension;
import com.frontcodegenartor.record.CodeGenareteRecord;

@Service
public class CodeGeneratorService {

	public void generate(CodeGenareteRecord codeGenareteRecord) {
		String filePath = codeGenareteRecord.filePath();
		String entityName = firstTextLetterLowerCase(codeGenareteRecord.entityName());
		String moduleName = firstTextLetterLowerCase(codeGenareteRecord.moduleName());
		
		FileGenerateBean fileGenerateBean = new FileGenerateBean();
		fileGenerateBean.setFileName(entityName);
		fileGenerateBean.setFolderName(filePath.concat("\\").concat(entityName));
		fileGenerateBean.setModuleName(moduleName);
		fileGenerateBean.setSiglaPathModule(codeGenareteRecord.siglaPathModule().toLowerCase());
		fileGenerateBean.setDetails(codeGenareteRecord.detailsName());
		
		for (EnumDefaultFileNameExtension enumFileNameExtension : EnumDefaultFileNameExtension.values()) {
			enumFileNameExtension.generateFile(fileGenerateBean);
		}
		
	}

	private static String firstTextLetterLowerCase(String text) {
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}
}