package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionDialogService extends AbstractDetailGenerateFile {

	public static final String DIALOG = "dialog";

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, DIALOG);
		String fileNameDetail = getDetailName(fileGenerateBean, DIALOG);
		
		createSubFileDetail(folderName, generateContentModule(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContentModule(FileGenerateBean fileGenerateBean) {
		String detailFileNameSufixo = getDetailFileNameSufixo(fileGenerateBean, DIALOG);
		String resourceName = new FileNameDetailExtensionResource().getDetailFileNameSufixo(fileGenerateBean, "");
		
		return """
(function () {
    'use strict';

		 %s
        .service('%s', %s);

        %s.$inject = [
        '%s',
        '$stateParams',
        'dcAlertService',
        'dcDialogService'
    ];

    function %s(
        %s,
        $stateParams,
        dcAlertService,
        dcDialogService
    ) {
        var model = {};
        var vm = this;
        var resource = %s;
        vm.getModel = getModel;
        vm.salvar= salvar;
        vm.limparModel= limparModel;
        vm.preencherModel = preencherModel;


        function getModel(){
            return model;
        }

        function limparModel(){
            model = {};
        }

		function salvar(){
		    var params = {};
		    params.parentId = $stateParams.id
		
		    return new Promise(function(resolve, reject) {
		        resource.saveOrUpdate(params, getDto(), function(){
		            dcAlertService.emitSuccessAlertSignal('Registro Salvo com sucesso.');
		            dcDialogService.hide();
		
		            resolve();
		        },function(){
		            reject();
		        });
		    });
		}

        function getDto(){
            var dto = getModel();
            dto.idsFiliais = [];

            getModel().filiais.forEach(function(filial){
                dto.idsFiliais.push(filial.id);
            })

            return dto;
        }

        function preencherModel(VO){
            if(VO !== undefined && VO !== null){
                var params = {};
                params.parentId = $stateParams.id;
                params.id = VO.id;
    
                resource.get(params, function(data){
                    model = data;
                })
            }
        }
    }
})();
				""".formatted(
							getModuleTextBlock(fileGenerateBean),
							detailFileNameSufixo,detailFileNameSufixo,detailFileNameSufixo,
							resourceName,detailFileNameSufixo,resourceName,resourceName
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
