package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionListagemController extends AbstractDetailGenerateFile {

	public static final String LISTAGEM = "listagem";
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, LISTAGEM);
		String fileNameDetail = getDetailName(fileGenerateBean, LISTAGEM);
		
		createSubFileDetail(folderName, generateContentModule(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContentModule(FileGenerateBean fileGenerateBean) {
		String detailFileNameSufixo = getDetailFileNameSufixo(fileGenerateBean, LISTAGEM);
		String serviceName = new FileNameDetailExtensionListagemService().getDetailFileNameSufixo(fileGenerateBean, LISTAGEM);
		
		return """
(function () {
    'use strict';

				%s
        .controller('%s', %s);

    %s.$inject = [
        '%s'
    ];

    function %s(
        %s
    ) {
        var vm = this;
        var service = %s;

        vm.novo = service.novo;
        vm.desabilitarNovo = service.desabilitarNovo;
        vm.getRegistrosListagemList = service.getRegistrosListagemList;
        vm.getTotalRecords = service.getTotalRecords;
        vm.fillRegistros = service.fillRegistros;
        vm.deletar  = service.deletar;
        vm.editar = service.editar;

        activate();

        function activate() {
        }
    }
})();""".formatted(
							getModuleTextBlock(fileGenerateBean),
							detailFileNameSufixo,
							detailFileNameSufixo,
							detailFileNameSufixo,
							serviceName,
							detailFileNameSufixo,
							serviceName,
							serviceName
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
