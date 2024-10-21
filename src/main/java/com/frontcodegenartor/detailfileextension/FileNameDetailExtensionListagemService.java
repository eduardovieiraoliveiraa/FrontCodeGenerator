package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionListagemService extends AbstractDetailGenerateFile {

public static final String LISTAGEM = "listagem";
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, LISTAGEM);
		String fileNameDetail = getDetailName(fileGenerateBean, LISTAGEM);
		
		createSubFileDetail(folderName, generateContentModule(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContentModule(FileGenerateBean fileGenerateBean) {
		String detailFileNameSufixo = getDetailFileNameSufixo(fileGenerateBean, LISTAGEM);
		String firstTextLetterUpperCaseDetailName = firstTextLetterUpperCase(fileGenerateBean.getDetailName());
		String resourceName = new FileNameDetailExtensionResource().getDetailFileNameSufixo(fileGenerateBean, "");
		
		return """
(function () {
    'use strict';

		%s
        .service('%s', %s);

        %s.$inject = [
        '%s',
        '$stateParams',
        'dcDialogService',
        'dialog%sService'
    ];

    function %s(
        %s,
        $stateParams,
        dcDialogService,
        dialog%sService
    ) {

        var totalRecords = null;
        var registrosListagemList = [];

        var vm = this;
        vm.getTotalRecords = getTotalRecords;
        vm.getRegistrosListagemList = getRegistrosListagemList;
        vm.fillRegistros = fillRegistros;
        vm.listingApi = {};
        vm.novo = novo;
        vm.editar = editar;
        vm.deletar = deletar;
        vm.desabilitarNovo = desabilitarNovo;

        function getTotalRecords() {
            return totalRecords;
        }
         function getRegistrosListagemList() {
            return registrosListagemList;
        }

        function desabilitarNovo(){
            return $stateParams.id === 'novo';
        } 
        
        function novo(){
            dcDialogService.show({
                templateUrl: '%s/%s/form/%s/dialog/dialog%s.html',
                controller: 'dialog%sController',
                controllerAs: 'dialogCtrl',
                locals: {
                    VO : undefined
                }
            }).finally(function(){
                dialog%sService.limparModel();
                atualizarListagem();
            });
        }

        function editar(regitro) {   
                dcDialogService.show({
                templateUrl: '%s/%s/form/%s/dialog/dialog%s.html',
                controller: 'dialog%sController',
                controllerAs: 'dialogCtrl',
                locals: {
                    VO : regitro
                }
            }).finally(function(){
                dialog%sService.limparModel();
                atualizarListagem();
            });
        }

        function atualizarListagem(){
            var param = {};
            fillRegistros(param);
        }

        function fillRegistros(param) {
            if(desabilitarNovo()){
                param.parentId = 0;
            }else{
                param.parentId = $stateParams.id;
            }
            
            return %s.getPagedVO(param, function (data) {
                registrosListagemList = data.items;
                totalRecords = data.totalElements;
            });
        }

        function deletar(regitro) {
            var params = {};
            params.parentId =  $stateParams.id;
            params.id = regitro.id;
            return %s.delete(params, angular.noop);
        }
        
    }
})();
				""".formatted(
							getModuleTextBlock(fileGenerateBean),
							detailFileNameSufixo,
							detailFileNameSufixo,
							detailFileNameSufixo,
							resourceName,
							firstTextLetterUpperCaseDetailName,
							detailFileNameSufixo,
							resourceName,
							firstTextLetterUpperCaseDetailName,
							fileGenerateBean.getModuleName(),
							fileGenerateBean.getFileName(),
							fileGenerateBean.getDetailName(),
							firstTextLetterUpperCaseDetailName,
							firstTextLetterUpperCaseDetailName,
							firstTextLetterUpperCaseDetailName,
							fileGenerateBean.getModuleName(),
							fileGenerateBean.getFileName(),
							fileGenerateBean.getDetailName(),
							firstTextLetterUpperCaseDetailName,
							firstTextLetterUpperCaseDetailName,
							firstTextLetterUpperCaseDetailName,
							resourceName,
							resourceName
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
