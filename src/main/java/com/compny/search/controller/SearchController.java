package com.compny.search.controller;

import com.compny.search.dto.SearchResDto;
import com.compny.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    @Qualifier("search-service")
    private final SearchService searchService;


    @GetMapping("/{query}")
    public ResponseEntity<List<SearchResDto>> search(@PathVariable(value = "query") String query) {
        return ResponseEntity.ok(searchService.search(query));
    }
}
