package com.frontcodegenartor.defaultfileextension;

import java.util.ArrayList;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionFormService extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String fileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		String serviceName = "form".concat(fileName);
		
		fileGenerateBean.setContent(generateContentForm(fileGenerateBean, fileName, serviceName));
		
		createSubFile(fileGenerateBean,"form", serviceName);
	}
	
	private String generateContentForm(FileGenerateBean fileGenerateBean, String fileName, String serviceName) {
		String fileResource = new FileNameDefaultExtensionResource().getFileNameSufixo(fileGenerateBean);
		serviceName = serviceName.concat(getSufixo());
		
		return """
				(function () {
    'use strict';

    angular
        %s
        .service('%s', %s);

        %s.$inject = [
        '$stateParams',
        '$state',
        '%s',
        'dcAlertService',
        'dcDialogService'
    ];

    function %s(
        $stateParams,
        $state,
        %s,
        dcAlertService,
        dcDialogService
    ) {
        var model = { main: {} };
        var tabsConfig = {};

        var vm = this;
        var resource = %s;
        
        vm.getModel = getModel;
        vm.salvar = salvar;
        vm.preencherModel = preencherModel;
        vm.getTabsConfig= getTabsConfig;
        vm.desabilitarNovoDetails = desabilitarNovoDetails;
        vm.getFilter = getFilter;

        activate();

        function salvar(){
            var payLoad = getModel().main;

            return resource.saveOrUpdate({id : payLoad.id},payLoad, function(data){
                dcAlertService.emitSuccessAlertSignal('Registro Salvo com sucesso.');
                $state.go($state.current,{id:data.id});
                atualizarForm(data);
            });
        }

        function getFilter(){
            return filter;
        }

        function desabilitarNovoDetails(){
            return $stateParams.id === 'novo';
        }

        function getModel(){
            return model;
        }

        function getTabsConfig(){
            return tabsConfig;
        }

        function atualizarForm(data){
            var param = {};
            param.id = data.id;

            resource.get(param, function(data){
                getModel().main = data;
            })
        }

        function preencherModel(){
            if($stateParams.id !== 'novo'){
                var param = {};
                param.id = $stateParams.id;
    
                resource.get(param, function(data){
                    getModel().main = data;
                })
            }else{
                model.main = {};
            }
        }


		%s

        function activate() {
            setUpTabsConfig();
        }
    }
})();
				""".formatted(
						getModuleTextBlock(fileGenerateBean),
						serviceName,serviceName, serviceName,
						fileResource,serviceName,fileResource,fileResource,
						generateTextBlockAbasDetails(fileGenerateBean));
	}

	private String generateTextBlockAbasDetails(FileGenerateBean fileGenerateBean) {
		java.util.List<String> tabsConfig = new ArrayList<>(); 
		java.util.List<String> namesTabsConfig = new ArrayList<>(); 
		
		for (String detailEntity : fileGenerateBean.getDetails()) 
			tabsConfig.add(generateFunctionTabDetail(detailEntity,namesTabsConfig, fileGenerateBean));
		
		
		String.join("", namesTabsConfig);
		
		return """
		function setUpTabsConfig() {
            tabsConfig = [
				  %s
            ];
        }

				%s
				
				""".formatted(
						String.join("", namesTabsConfig),
						String.join("", tabsConfig));
	}
	
	private String generateFunctionTabDetail(String detailEntity,java.util.List<String> namesTabsConfig, FileGenerateBean fileGenerateBean) {
		String detailNameUpper = firstTextLetterUpperCase(detailEntity);
		String detailNameSepareteBySpace = separeteBySpace(detailNameUpper);
		
		String moduleName = fileGenerateBean.getModuleName();
		String fileName = fileGenerateBean.getFileName();
		
		namesTabsConfig.add("getAba".concat(detailNameUpper).concat("(),"));
		
		return """
		function getAba%s() {
            return {
                label: '%s',
                src: '%s/%s/form/%s/listagem/listagem.html',
            };
        }
				""".formatted(detailNameUpper, detailNameSepareteBySpace,
						moduleName,fileName, detailEntity);
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
