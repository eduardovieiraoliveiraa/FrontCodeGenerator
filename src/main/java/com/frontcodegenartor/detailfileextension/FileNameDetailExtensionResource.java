package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionResource extends AbstractDetailGenerateFile {

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, "");
		String fileNameDetail = getDetailName(fileGenerateBean, "");
		
		createSubFileDetail(folderName, generateContent(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContent(FileGenerateBean fileGenerateBean) {
		String resourceName = getDetailFileNameSufixo(fileGenerateBean, "");
		
		return """
(function () {
    'use strict';
  
	   %s
      .factory('%s', %s);
  
      %s.$inject = [
      'RESTFulHelperFactory',
      'apiService'
    ];
  
    function %s(
      RESTFulHelperFactory,
      apiService
    ) {
      return configResource();
  
      function configResource() {
        var baseUrl = apiService.getApi('%s').baseUrl;
        var resourceName = '/%s/:parentId/%s/:id';
        var url = baseUrl + resourceName;
  
        return RESTFulHelperFactory.configureRESTFulResource(url);
      }
     }
  })();
				""".formatted(getModuleTextBlock(fileGenerateBean),
						resourceName,resourceName,resourceName,resourceName,
						fileGenerateBean.getModuleName().toLowerCase(),
						separeteByHifen(fileGenerateBean.getFileName()),
						separeteByHifen(fileGenerateBean.getDetailName()));
	}

	@Override
	public String getExtensionFile() {
		return ".resource.js";
	}
	
	@Override
	public String getSufixo() {
		return "Resource";
	}
	
	@Override
	public String getDetailName(FileGenerateBean fileGenerateBean, String type) {
		String detailName = super.getDetailName(fileGenerateBean, type);
		return firstTextLetterLowerCase(detailName);
	}
	
	private static String firstTextLetterLowerCase(String text) {
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}

	@Override
	public String getFolderName(FileGenerateBean fileGenerateBean, String type) {
		return super.getFolderName(fileGenerateBean, type);
	}
}
