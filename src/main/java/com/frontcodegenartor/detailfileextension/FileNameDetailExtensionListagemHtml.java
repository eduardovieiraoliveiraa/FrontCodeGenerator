package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionListagemHtml extends AbstractDetailGenerateFile {

	public static final String LISTAGEM = "listagem";
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, LISTAGEM);
		String fileNameDetail = getDetailName(fileGenerateBean, LISTAGEM);
		
		createSubFileDetail(folderName, generateContentHtml(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContentHtml(FileGenerateBean fileGenerateBean) {
		String detailFileNameSufixo = new FileNameDetailExtensionListagemController().getDetailFileNameSufixo(fileGenerateBean, LISTAGEM);
		String fileNameSufixoResource = new FileNameDetailExtensionResource().getDetailFileNameSufixo(fileGenerateBean, "");
		
		return """
 <ui-view>
    <md-content layout="row" layout-wrap layout-padding ng-controller="%s as listagemCtrl">
        <form name="listagem">

            <div layout="row" class="spaced-top default-min-height-div">
                <dc-button 
                    on-click="listagemCtrl.novo()"
                    label="Novo"
                    disable="listagemCtrl.desabilitarNovo()">
                </dc-button>
            </div>   

            <div layout="row" class="action-container">
                <dc-generic-listing
                    actions="UD"
                    data="listagemCtrl.getRegistrosListagemList()"
                    call-fn-on-start="true"
                    page-change-callback-fn="listagemCtrl.fillRegistros"
                    resource-name="%s" 
                    total-records="listagemCtrl.getTotalRecords()"
                    delete-fn="listagemCtrl.deletar"
                    edit-fn="listagemCtrl.editar"
                    directive-api="listagemCtrl.listingApi">
                </dc-generic-listing>
            </div>          
        </form>
    </md-content>
</ui-view>"""
				.formatted(detailFileNameSufixo, fileNameSufixoResource);
	}

	@Override
	public String getExtensionFile() {
		return ".html";
	}
}
