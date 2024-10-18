package com.frontcodegenartor.constants;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionFormController;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionFormHtml;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionFormService;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionListingController;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionListingHtml;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionListingService;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionModule;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionResource;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionRoute;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionRouteFactory;

public enum EnumDefaultFileNameExtension implements FileNameExtension{
	ROUTE(new FileNameDefaultExtensionRoute()::generateFile),
	RESOURCE(new FileNameDefaultExtensionResource()::generateFile),
	ROUTEFACTORY(new FileNameDefaultExtensionRouteFactory()::generateFile),
	MODULE(new FileNameDefaultExtensionModule()::generateFile),
	LISTING_CONTROLLER(new FileNameDefaultExtensionListingService()::generateFile),
	LISTING_SERVICE(new FileNameDefaultExtensionListingController()::generateFile),
	LISTING_HTML(new FileNameDefaultExtensionListingHtml()::generateFile),
	FORM_CONTROLLER(new FileNameDefaultExtensionFormController()::generateFile),
	FORM_SERVICE(new FileNameDefaultExtensionFormService()::generateFile),
	FORM_HTML(new FileNameDefaultExtensionFormHtml()::generateFile);
	
    private final FileNameExtension fileNameExtension;

    EnumDefaultFileNameExtension(FileNameExtension fileNameExtension) {
        this.fileNameExtension = fileNameExtension;
    }

    @Override
	public void generateFile(FileGenerateBean fileGenerateBean) {
		fileNameExtension.generateFile(fileGenerateBean);
	};
}