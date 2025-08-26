package com.example.extension_guard.controller;

import com.example.extension_guard.dto.FileExtensionBlockDto;
import com.example.extension_guard.service.FileExtensionBlockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FileExtensionBlockController {

    private final FileExtensionBlockService fileExtensionBlockService;

    public FileExtensionBlockController(FileExtensionBlockService fileExtensionBlockService) {
        this.fileExtensionBlockService = fileExtensionBlockService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<FileExtensionBlockDto> allExtensions = fileExtensionBlockService.getAllExtensions();
        long customCount = allExtensions.stream().filter(ext -> !ext.isFixed()).count();
        model.addAttribute("extensions", allExtensions);
        model.addAttribute("customCount", customCount);
        return "index";
    }

    @PostMapping("/fixed/update")
    public String updateFixedExtensionStatus(@RequestParam String name, @RequestParam boolean isChecked, Model model) {
        fileExtensionBlockService.updateFixedExtensionStatus(name, isChecked);
        updateModelWithAllExtensionsAndCount(model);
        return "index :: fixed-list";
    }

    @PostMapping("/custom/save")
    public String saveCustomExtension(@RequestParam String name, Model model) {
        fileExtensionBlockService.saveCustomExtension(name);
        updateModelWithAllExtensionsAndCount(model);
        return "index :: custom-list";
    }

    @PostMapping("/custom/delete")
    public String deleteCustomExtension(@RequestParam String name, Model model) {
        fileExtensionBlockService.deleteCustomExtension(name);
        updateModelWithAllExtensionsAndCount(model);
        return "index :: custom-list";
    }

    private void updateModelWithAllExtensionsAndCount(Model model) {
        List<FileExtensionBlockDto> allExtensions = fileExtensionBlockService.getAllExtensions();
        long customCount = allExtensions.stream().filter(ext -> !ext.isFixed()).count();
        model.addAttribute("extensions", allExtensions);
        model.addAttribute("customCount", customCount);
    }
}
