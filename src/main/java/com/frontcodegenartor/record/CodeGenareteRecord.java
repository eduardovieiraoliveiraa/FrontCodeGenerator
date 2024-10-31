package com.frontcodegenartor.record;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record CodeGenareteRecord(
		@NotBlank(message = "O campo File Path é obrigatório.")
		String filePath,
		@NotBlank(message = "O campo Entity Name é obrigatório.")
		String entityName,
		@NotBlank(message = "O campo Module Name é obrigatório.")
		String moduleName,
		@NotBlank(message = "O campo Sigla Path é obrigatório.")
		String siglaPathModule,
		boolean linuxEnviroment,
		List<String> detailsName) {}
