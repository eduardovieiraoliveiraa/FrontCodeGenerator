package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionResource extends AbstractGenerateFile{
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentResource(fileGenerateBean));
		
		createFile(fileGenerateBean);
	}
	
	private String generateContentResource(FileGenerateBean fileGenerateBean) {
		String fileNameSufixo = getFileNameSufixo(fileGenerateBean);
		
		return """
		(function () {
		    'use strict';
		  
		    %s
		  
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
		        var resourceName = '/%s/:id';
		        var url = baseUrl + resourceName;
		
		        var conf = {
		        };
		
		      return RESTFulHelperFactory.configureRESTFulResource(url, conf);
		      }
		     }
		  })();
				""".formatted(
						getTextBlockModule(fileGenerateBean),
						fileNameSufixo,
						fileNameSufixo,
						fileGenerateBean.getModuleName().toLowerCase(),
						separeteByHifen(fileGenerateBean.getFileName()));
	}

	@Override
	public String getExtensionFile() {
		return ".resource.js";
	}

	@Override
	public String getSufixo() {
		return "Resource";
	}
}
