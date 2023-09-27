package com.compny.search.service;

import com.compny.search.dto.SearchResDto;

import java.util.List;

public interface SearchService {

    List<SearchResDto> search(String query);

}
