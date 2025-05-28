package ru.vysokikh.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vysokikh.project.dto.CategoryDto;
import ru.vysokikh.project.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryChildController {

    private final CategoryService categoryService;

    @GetMapping("/api/catchild/{catid}")
    public List<CategoryDto> getAllCatChild(@PathVariable("catid") long catid) {
        return categoryService.findChildByCatId(catid);
    }
}
