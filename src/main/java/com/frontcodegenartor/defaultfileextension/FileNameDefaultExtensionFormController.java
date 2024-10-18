package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionFormController extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentForm(fileGenerateBean));
		
		createSubFile(fileGenerateBean, "form");
	}
	
	private String generateContentForm(FileGenerateBean fileGenerateBean) {
		String fileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		String controllerName = "form".concat(fileName).concat(getSufixo());
		String serviceName = "form".concat(fileName).concat("Service");
		
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

        activate();

        function getModel(){
            return service.getModel();
        }

        function preencherModel(){
            service.preencherModel();
        }

        function activate() {
            preencherModel();
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
