package com.frontcodegenartor.defaultfileextension;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.generatefile.AbstractGenerateFile;

public class FileNameDefaultExtensionFormHtml extends AbstractGenerateFile{

	@Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		String fileName = firstTextLetterUpperCase(fileGenerateBean.getFileName());
		String htmlName = "form".concat(fileName);
		
		fileGenerateBean.setContent(generateContentForm(fileGenerateBean, htmlName));
		
		createSubFile(fileGenerateBean,"form", htmlName);
	}
	
	private String generateContentForm(FileGenerateBean fileGenerateBean, String fileNameController) {
		String formController = fileNameController.concat(new FileNameDefaultExtensionFormController().getSufixo());
		
		
		return """
<dc-page-header></dc-page-header>
<md-content layout="column" layout-wrap layout-padding  ng-controller="%s as formCtrl">
    <form name="form">
        <div class="container-fluid gc-padding main">

            <div class="col-md-12 no-padding">
                <div class="col-md-2">
                    <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                        <dc-input 
                            flex 
                            label="Código"
                            model="formCtrl.getModel().main.id" 
                            disable="true">
                        </dc-input>
                    </div>
                </div>
 
                <div class="col-md-2">
                    <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                        <dc-input flex 
                            label="Descrição"
                            maxlength="255"
                            model="formCtrl.getModel().main.descricao" 
                            disable="false">
                        </dc-input>
                    </div>
                </div>
                
                 <div class="col-md-4">
                    <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                        <dc-selector-autocomplete
                            model="formCtrl.getModel().filial"
                            label="Filial" 
                            resource-params-fn="formTabloideFilialCtr.getParamsFilial()"
                            multiple="false"
                            require="true"
                            flex
                            resource-name="filialResource">
                        </dc-selector-autocomplete>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                        <dc-datepicker flex
                            model="formCtrl.getModel().main.inicioVigencia"
                            label="Data de Início"  
                            format="dd/MM/yyyy HH:mm" 
                            require="true"
                            disable="formCtrl.isEdit()"
                            on-change="formCtrl.validarDataInicio()">
                        </dc-datepicker>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                        <dc-datepicker flex
                            model="formCtrl.getModel().main.finalVigencia"
                            label="Data de Fim"  
                            format="dd/MM/yyyy HH:mm" 
                            require="true"
                            on-change="formCtrl.validarDataFim()">
                        </dc-datepicker>
                    </div>
                </div>
            </div>

            
            <div class="col-md-12 no-padding">
                <div class="default-crud-min-height-div" layout="row" layout-align="start end">
                    <dc-button 
                        on-click="formCtrl.salvar()"
                        label="Salvar">
                    </dc-button>
                </div>
            </div>
        </div>
        
        <dc-tabs tabs="formCtrl.getTabsConfig()" active-by-route="false"></dc-tabs>

    </form>
</md-content>
				""".formatted(formController);
	}

	@Override
	public String getExtensionFile() {
		return ".html";
	}
}
