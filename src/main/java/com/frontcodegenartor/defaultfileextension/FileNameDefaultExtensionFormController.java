package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionFormController extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String fileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		String controllerName = "form".concat(fileName);
		fileGenerateBean.setContent(generateContentForm(fileGenerateBean, fileName, controllerName));
		
		createSubFile(fileGenerateBean,"form", controllerName);
	}
	
	private String generateContentForm(FileGenerateBean fileGenerateBean, String fileName, String controllerName) {
		String serviceName = "form".concat(fileName).concat("Service");
		controllerName = controllerName.concat(getSufixo());
		
		return """
(function () {
    'use strict';

			%s
        .controller('%s', %s);

    %s.$inject = [
        '%s',
        'searchConfigOptionsService' 
    ];

    function %s(
        %s,
        searchConfigOptionsService    
    ) {
        var vm = this;
        var service = %s;

        vm.getModel = getModel;
        vm.salvar = service.salvar;
        vm.getSearchConfigOptions = searchConfigOptionsService.from;
        vm.getTabsConfig = service.getTabsConfig;
        vm.validarDataInicio = service.validarDataInicio;
        vm.validarDataFim = service.validarDataFim;
        vm.isEdit = isEdit;
        vm.getParamsFilial = getParamsFilial;

        activate();

        function getModel(){
            return service.getModel();
        }
        
         function getParamsFilial(){
            return {
                ativo: true
            }
        }
        
        function isEdit(){
            return getModel().main.id ? true : false;
        }

        function preencherModel(){
            service.preencherModel();
        }
        
        function setDatas() {
            if(getModel().main.id == undefined || getModel().main.id == null){
                getModel().main.dataInicio = new Date();
            }
        }

        function activate() {
            preencherModel();
            setDatas();
        }
    }
})();
				""".formatted(
						getModuleTextBlock(fileGenerateBean),
						controllerName, controllerName,controllerName, controllerName,controllerName,
						serviceName, serviceName,serviceName);
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
