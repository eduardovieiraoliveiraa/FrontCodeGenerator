package com.frontcodegenartor.constants;

import java.util.function.Consumer;

import com.frontcodegenartor.bean.FileGenerateBean;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionModule;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionResource;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionRoute;
import com.frontcodegenartor.defaultfileextension.FileNameDefaultExtensionRouteFactory;

public enum EnumDefaultFileNameExtension {
	ROUTE(new FileNameDefaultExtensionRoute()::generateFile),
	RESOURCE(new FileNameDefaultExtensionResource()::generateFile),
	ROUTEFACTORY(new FileNameDefaultExtensionRouteFactory()::generateFile),
	MODULE(new FileNameDefaultExtensionModule()::generateFile);
	
	private final Consumer<FileGenerateBean> consumer;
	
	EnumDefaultFileNameExtension(Consumer<FileGenerateBean> consumer) {
		this.consumer = consumer;
	}

	public void generateFile(FileGenerateBean fileGenerateBean) {
		consumer.accept(fileGenerateBean);
	};
}