package com.frontcodegenartor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frontcodegenartor.record.CodeGenareteRecord;
import com.frontcodegenartor.service.CodeGeneratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/code-generator")
public class CodeGeneratorController {
	
	@Autowired
	private CodeGeneratorService codeGeneratorService;
	
	@PostMapping("/genarate")
	public ResponseEntity<String> generate(@RequestBody @Valid  CodeGenareteRecord codeGenareteRecord) {
		codeGeneratorService.generate(codeGenareteRecord);
		
		return ResponseEntity.ok("Sucesso");
	}
}