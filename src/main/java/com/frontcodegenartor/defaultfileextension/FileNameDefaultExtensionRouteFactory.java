package com.frontcodegenartor.defaultfileextension;

import java.util.ArrayList;
import java.util.List;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionRouteFactory extends AbstractGenerateFile{
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentRouteFactory(fileGenerateBean));
		
		createFile(fileGenerateBean);
	}

	private String generateContentRouteFactory(FileGenerateBean fileGenerateBean) {
		String fileNameSufixo = getFileNameSufixo(fileGenerateBean);
		
		return """
		(function () {
		    'use strict';
		
		    %s
		
		    %s.$inject = [
		  
		    ];
		
		    function %s( 
		        ) {
		        return {
		        %s
		    }
		})();
				""".formatted(
						getTextBlockModule(fileGenerateBean),
						fileNameSufixo,
						fileNameSufixo,
						generateContentRoutes(fileGenerateBean)
						);
	}

	
	private String generateContentRoutes(FileGenerateBean fileGenerateBean) {
		return """
				    %s
				    %s     
		        };
		
				 %s
		""".formatted(
				generateRouteListagem(fileGenerateBean),
				generateRouteForm(fileGenerateBean),
				generateDependencies(fileGenerateBean)
				);
	}
	
	private String generateDependencies(FileGenerateBean fileGenerateBean) {
		return """
				function getDependencies() {
		            return [
		                '%s/%s/%s.min.js',
		                '%s/%s/listagem/listagem.min.js',
		                '%s/%s/form/form.min.js',
						     %s
		            ];
		        }
				""".formatted(
						fileGenerateBean.getModuleName(),fileGenerateBean.getFileName(),fileGenerateBean.getFileName(),
						fileGenerateBean.getModuleName(),fileGenerateBean.getFileName(),
						fileGenerateBean.getModuleName(),fileGenerateBean.getFileName(),
						generateDependenciesDetails(fileGenerateBean));
	}
	
	private String generateDependenciesDetails(FileGenerateBean fileGenerateBean) {
		List<String> detailsList = new ArrayList<>();
	
		for (String entityName : fileGenerateBean.getDetails()) {
			String minDetail = """
								   '%s/%s/form/%s/listagem/listagem.min.js',
								   '%s/%s/form/%s/dialog/dialog.min.js',
					""".formatted(
							fileGenerateBean.getModuleName(),fileGenerateBean.getFileName(),entityName,
							fileGenerateBean.getModuleName(),fileGenerateBean.getFileName(),entityName
							);
			
			 detailsList.add(minDetail);
		}
		
		return String.join("",detailsList).trim();
	}
	
	private String generateRouteListagem(FileGenerateBean fileGenerateBean) {
		String fileName = fileGenerateBean.getFileName();
		String moduleName = fileGenerateBean.getModuleName();
		String siglaPathModule = fileGenerateBean.getSiglaPathModule();
		
		return """
			'%s': {
		                state: '%s.%s',	
		                config: {
		                    url: '/%s',
		                    templateUrl:'%s/%s/listagem/listagem.html',
		                    resolve: {
		                        loadDependencies: ['loadModulesService', function (loadModulesService) {
		                            return loadModulesService.loadFiles(getDependencies());
		                        }]
		                    }
		                }
		            },
				""".formatted(
						fileName,
						siglaPathModule,fileName,
						separeteByHifen(fileName),
						moduleName,fileName
						);
	}
	
	private String generateRouteForm(FileGenerateBean fileGenerateBean) {
		String fileName = fileGenerateBean.getFileName();
		String moduleName = fileGenerateBean.getModuleName();
		String siglaPathModule = fileGenerateBean.getSiglaPathModule();
		
		return """
				'form': {
		                state: '%s.%s.form',
		                config: {
		                    url: '/%s/:id',
		                    templateUrl:'%s/%s/form/form.html',
		                    resolve: {
		                        loadDependencies: ['loadModulesService', function (loadModulesService) {
		                            return loadModulesService.loadFiles(getDependencies());
		                        }]
		                    }
		                }
		            }
				""".formatted(
						siglaPathModule,fileName,
						separeteByHifen(fileName),
						moduleName,fileName
						);
	}
	
	@Override
	public String getExtensionFile() {
		return ".route.factory.js";
	}

	@Override
	public String getSufixo() {
		return "RouteConstants";
	}
}
