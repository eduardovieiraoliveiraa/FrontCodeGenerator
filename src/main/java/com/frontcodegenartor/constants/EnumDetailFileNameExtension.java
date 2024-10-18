package com.frontcodegenartor.constants;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionDialogController;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionDialogHtml;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionDialogService;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionListagemController;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionListagemHtml;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionListagemService;
import com.frontcodegenartor.detailfileextension.FileNameDetailExtensionResource;

public enum EnumDetailFileNameExtension implements FileNameExtension{
	
	RESOURCE(new FileNameDetailExtensionResource()::generateFile),
	LISTING_CONTROLLER(new FileNameDetailExtensionListagemController()::generateFile),
	LISTING_SERVICE(new FileNameDetailExtensionListagemService()::generateFile),
	LISTING_HTML(new FileNameDetailExtensionListagemHtml()::generateFile),
	DIALOG_CONTROLLER(new FileNameDetailExtensionDialogController()::generateFile),
	DIALOG_SERVICE(new FileNameDetailExtensionDialogService()::generateFile),
	DIALOG_HTML(new FileNameDetailExtensionDialogHtml()::generateFile);
	
    private final FileNameExtension fileNameExtension;

    EnumDetailFileNameExtension(FileNameExtension fileNameExtension) {
        this.fileNameExtension = fileNameExtension;
    }

    @Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileNameExtension.generateFile(fileGenerateBean);
	};
}