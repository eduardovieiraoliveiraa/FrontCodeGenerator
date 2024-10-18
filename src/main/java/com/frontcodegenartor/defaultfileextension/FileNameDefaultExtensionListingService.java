package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingService extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentListingService(fileGenerateBean));
		
		String firstTextLetterUpperCaseFileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		String serviceName = "listagem".concat(firstTextLetterUpperCaseFileName);
		
		createSubFile(fileGenerateBean,"listagem", serviceName);
	}
	
	private String generateContentListingService(FileGenerateBean fileGenerateBean) {
		String firstTextLetterUpperCaseFileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		return """
(function () {
	'use strict';
				
	%s
		.service('listagem%sService', listagem%sService);			
		
		listagem%sService.$inject = [
			'$state',
			'%sResource'
	];
				
	function listagem%sService(
		$state,
		%sResource
	) {
		var totalRecords = null;
		var listaRegistros = [];
		var resource = %sResource;
				
		var vm = this;
		vm.getTotalRecords = getTotalRecords;
		vm.getListaRegistros = getListaRegistros;
		vm.editar = editar;
		vm.novo = novo;
		vm.deletar = deletar;
		vm.fillListagem = fillListagem;
				
		function getTotalRecords() {
			return totalRecords;
		}
				 
		function novo() {
			$state.go(".form", {id:'novo'});
		}
				
		function editar(registro) {
			$state.go(".form", {id:registro.id});
		}
				
		function deletar(registro) {
			var params = {};
			params.id = registro.id;
			return resource.delete(params, angular.noop);
		}
				         
		function getListaRegistros() {
			return listaRegistros;
		}
				
		function fillListagem(param) {
			return resource.getPaginated(param, function (data) {
				listaRegistros = data.items;
				totalRecords = data.totalElements;
			});
		}
	}
})();
				""".formatted(
						getModuleTextBlock(fileGenerateBean),
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						fileGenerateBean.getFileName(),
						firstTextLetterUpperCaseFileName,
						fileGenerateBean.getFileName(),
						fileGenerateBean.getFileName()
						);
	}

	@Override
	public String getExtensionFile() {
		return ".service.js";
	}

	@Override
	public String getSufixo() {
		return "Service";
	}

}
