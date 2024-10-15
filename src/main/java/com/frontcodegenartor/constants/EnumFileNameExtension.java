package com.frontcodegenartor.constants;

public enum EnumFileNameExtension {
	CONTROLLER(".controller.js"),
	SERVICE(".service.js"),
	ROUTE(".route.js"),
	HTML(".html"),
	RESOURCE(".resource.js"),
	ROUTEFACTORY(".route.factory.js"),
	MODULE(".module.js");
	
	
	private EnumFileNameExtension(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private String name;
}
