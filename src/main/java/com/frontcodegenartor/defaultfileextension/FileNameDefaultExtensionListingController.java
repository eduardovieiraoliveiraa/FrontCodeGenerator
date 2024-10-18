package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingController extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentListingService(fileGenerateBean));
		
		createSubFile(fileGenerateBean,"listagem", "listagem");
	}
	
	private String generateContentListingService(FileGenerateBean fileGenerateBean) {
		return """
(function () {
	'use strict';
				
	%s
        .controller('listagemController', listagemController);

        listagemController.$inject = [
        'listagemService',
    ];

    function listagemController(
        listagemService,
    ) {
        var vm = this;

        vm.getListaRegistros = listagemService.getListaRegistros;
        vm.getTotalRecords = listagemService.getTotalRecords
        vm.novo = listagemService.novo;
        vm.editar = listagemService.editar;
        vm.deletar = listagemService.deletar;
        vm.fillListagem = listagemService.fillListagem;

        activate();

        function activate() {

        }
    }
})();
				""".formatted(getModuleTextBlock(fileGenerateBean));
	}

	@Override
	public String getExtensionFile() {
		return ".controller.js";
	}

	@Override
	public String getSufixo() {
		return "Controller";
	}

}
