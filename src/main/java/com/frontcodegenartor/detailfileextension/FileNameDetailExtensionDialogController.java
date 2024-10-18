package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionDialogController extends AbstractDetailGenerateFile {

	public static final String DIALOG = "dialog";

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, DIALOG);
		String fileNameDetail = getDetailName(fileGenerateBean, DIALOG);
		
		fileGenerateBean.setContent(generateContentModule(fileGenerateBean));
		
		createSubFileDetail(folderName,fileGenerateBean.getContent(),fileNameDetail);
	}
	
	private String generateContentModule(FileGenerateBean fileGenerateBean) {
		String detailFileNameSufixo = getDetailFileNameSufixo(fileGenerateBean, DIALOG);
		String detailFileNameService = new FileNameDetailExtensionDialogService().getDetailFileNameSufixo(fileGenerateBean, DIALOG);
		
		return """
				(function () {
    'use strict';

	    %s
        .controller('%s', %s);

        %s.$inject = [
        'VO',
        '%s'
    ];

    function %s(
        VO,
        %s

    ) {
        var vm = this;
        var service = %s;
        vm.getModel = getModel;
        vm.salvar = service.salvar;
        vm.disableSalvar = disableSalvar;
        vm.getParamsFilial = getParamsFilial;

        activate();

        function getVO(){
            return VO;
        }

        function getModel(){
            return service.getModel();
        }

        function disableSalvar(){
            return service.getModel().filiais == undefined;
        }

        function getParamsFilial(){
            return {
                ativo: true
            }
        }

        function activate() {
            service.limparModel();
            service.preencherModel(getVO())
        }
    }
})();
				""".formatted(
							getModuleTextBlock(fileGenerateBean),
							detailFileNameSufixo,detailFileNameSufixo,detailFileNameSufixo,
							detailFileNameService,detailFileNameSufixo,detailFileNameService,detailFileNameService
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
