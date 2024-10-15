package com.frontcodegenartor.service;

import org.springframework.stereotype.Service;

import com.frontcodegenartor.record.CodeGenareteRecord;

@Service
public class CodeGeneratorService {

	public void generate(CodeGenareteRecord codeGenareteRecord) {
	}

	private static String firstTextLetterLowerCase(String text) {
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}
}