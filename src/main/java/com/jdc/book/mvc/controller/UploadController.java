package com.jdc.book.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.book.root.service.FileUploadService;

@Controller
public class UploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("upload")
	String uploadFile(@RequestParam("uploadInput") MultipartFile file, RedirectAttributes rd) {

		if (null != file && !file.isEmpty()) {
			String message = fileUploadService.upload(file);
			rd.addFlashAttribute("uploadMessage", message);
			return "redirect:/home";
		}
		return "home";
	}
}
