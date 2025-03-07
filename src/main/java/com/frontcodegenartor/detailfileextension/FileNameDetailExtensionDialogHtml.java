package com.frontcodegenartor.detailfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractDetailGenerateFile;

public class FileNameDetailExtensionDialogHtml extends AbstractDetailGenerateFile {

	public static final String DIALOG = "dialog";
	
	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String folderName = getFolderName(fileGenerateBean, DIALOG);
		String fileNameDetail = getDetailName(fileGenerateBean, DIALOG);
		
		createSubFileDetail(folderName, generateContentHtml(fileGenerateBean), fileNameDetail);
	}
	
	private String generateContentHtml(FileGenerateBean fileGenerateBean) {
		return """
 <md-dialog flex="60">
    <md-toolbar>
        <div class="md-toolbar-tools">
            <h2>%s</h2>
            <span flex></span>
            <dc-dialog-close></dc-dialog-close>
        </div>
    </md-toolbar>

<md-dialog-content layout="column" layout-wrap layout-padding class="modal-md">
    <div class="col-md-12 spaced-bottom">
    
         <form name="form">
			<div class="col-md-12 main">
			
                <div class="col-md-6">
                    <dc-selector-autocomplete
                        model="dialogCtrl.getModel().filiais"
                        label="(Seleciona ao menos uma Filial)" 
                        resource-params-fn="dialogCtrl.getParamsFilial()"
                        require="true"
                        ignore-ids-not-in="true"
                        multiple="true"
                        flex="100"
                        resource-name="filialResource">
                    </dc-selector-autocomplete>
                </div>
                
			</div>
			
        </form>

        <div class="action-container">
            <dc-button 
                label="Salvar"
                on-click="dialogCtrl.salvar()"
                disable="dialogCtrl.disableSalvar()">
            </dc-button>
        </div>    

    </div>
</md-dialog-content>
</md-dialog>
				""".formatted(separeteByHifen(fileGenerateBean.getDetailName()));
	}

	@Override
	public String getExtensionFile() {
		return ".html";
	}
}
