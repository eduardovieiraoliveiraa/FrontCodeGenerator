package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionRoute extends AbstractGenerateFile{
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentRoute(fileGenerateBean));
		
		createFile(fileGenerateBean);
	}
	
	private String generateContentRoute(FileGenerateBean fileGenerateBean) {
		String fileNameSufixo = getFileNameSufixo(fileGenerateBean);
		
		return """
			(function() {
			    'use strict';
			
				    angular
				    .module('cw.%s.%s')
					.run(%s);
			
			        %s.$inject = [
			            'routerHelper',
			            '%sRouteConstants'
			        ];
			
			        function %s(
			            routerHelper,
			            %sRouteConstants
			        ) {
			            routerHelper.configureStates(%sRouteConstants);
			        }
			})();
				""".formatted(
						fileGenerateBean.getModuleName(), 
						fileGenerateBean.getFileName(),
						fileNameSufixo,fileNameSufixo,
						fileGenerateBean.getFileName(),
						fileNameSufixo,
						fileGenerateBean.getFileName(),fileGenerateBean.getFileName());
	}

	@Override
	public String getExtensionFile() {
		return ".route.js";
	}

	@Override
	public String getSufixo() {
		return "Run";
	}
}
