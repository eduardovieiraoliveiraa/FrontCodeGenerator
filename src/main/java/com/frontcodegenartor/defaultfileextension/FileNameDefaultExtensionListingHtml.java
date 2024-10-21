package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionListingHtml extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileGenerateBean.setContent(generateContentListingHtml(fileGenerateBean));
		
		String firstTextLetterUpperCaseFileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		
		String htmlName = "listagem".concat(firstTextLetterUpperCaseFileName);
		
		createSubFile(fileGenerateBean,"listagem", htmlName);
	}
	
	private String generateContentListingHtml(FileGenerateBean fileGenerateBean) {
		return """
<ui-view>
    <dc-page-header></dc-page-header>
    <md-content layout="row" layout-wrap layout-padding ng-controller="listagem%sController as listagemCtrl">
        <form name="formListagem">

            <div layout="row" class="action-container">
                <dc-button 
                    label="Novo"
                    on-click="listagemCtrl.novo()"
                ></dc-button>  
            </div> 

            <div layout="row" class="spaced-top default-min-height-div">
                <dc-generic-listing
                    actions="CRUD"
                    data="listagemCtrl.getListaRegistros()"
                    call-fn-on-start="true"
                    resource-name="%sResource"    
                    page-change-callback-fn="listagemCtrl.fillListagem"
                    edit-fn="listagemCtrl.editar"
                    delete-fn="listagemCtrl.deletar"
                    total-records="listagemCtrl.getTotalRecords()">
                </dc-generic-listing>
            </div>

        </form>
    </md-content>
</ui-view>
				""".formatted(firstTextLetterUpperCase(fileGenerateBean.getFileName()),	fileGenerateBean.getFileName());
	}

	@Override
	public String getExtensionFile() {
		return ".html";
	}

	@Override
	public String getSufixo() {
		return null;
	}

}
