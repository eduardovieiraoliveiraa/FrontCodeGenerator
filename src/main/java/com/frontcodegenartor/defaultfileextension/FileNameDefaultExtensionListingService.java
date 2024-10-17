package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingService extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentListingService(fileGenerateBean));
		
		createSubFile(fileGenerateBean, "listagem");
	}
	
	private String generateContentListingService(FileGenerateBean fileGenerateBean) {
		return """
(function () {
	'use strict';
				
	%s
		.service('listagemService', listagemService);			
		
		listagemService.$inject = [
			'$state',
			'%sResource'
	];
				
	function listagemService(
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
						getTextBlock(fileGenerateBean),
						fileGenerateBean.getFileName(),
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
