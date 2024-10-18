package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingController extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentListingController(fileGenerateBean));
		
		String firstTextLetterUpperCaseFileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		String controllerName = "listagem".concat(firstTextLetterUpperCaseFileName);
		
		createSubFile(fileGenerateBean,"listagem", controllerName);
	}
	
	private String generateContentListingController(FileGenerateBean fileGenerateBean) {
		String firstTextLetterUpperCaseFileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		return """
(function () {
	'use strict';
				
	%s
        .controller('listagem%sController', listagem%sController);

        listagem%sController.$inject = [
        'listagem%sService',
    ];

    function listagem%sController(
        listagem%sService,
    ) {
        var vm = this;
		var service = listagem%sService;

        vm.getListaRegistros = service.getListaRegistros;
        vm.getTotalRecords = service.getTotalRecords
        vm.novo = service.novo;
        vm.editar = service.editar;
        vm.deletar = service.deletar;
        vm.fillListagem = service.fillListagem;

        activate();

        function activate() {

        }
    }
})();
				""".formatted(getModuleTextBlock(fileGenerateBean),
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName,
						firstTextLetterUpperCaseFileName
						);
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
