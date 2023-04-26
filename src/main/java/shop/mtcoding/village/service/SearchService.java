package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.model.search.SearchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<SearchList> searchPlacesByKeyword(String keyword) {
        try {
            List<SearchList> searchLists = searchRepository.searchPlacesByKeyword(keyword);
            return searchLists;
        }catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }

    }
}
