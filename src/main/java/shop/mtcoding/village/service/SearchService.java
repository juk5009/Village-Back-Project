//package shop.mtcoding.village.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import shop.mtcoding.village.model.place.Place;
//import shop.mtcoding.village.model.search.SearchRepository;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class SearchService {
//    private final SearchRepository searchRepository;
//
//    public List<Place> search(String keyword) {
//        List<Place> placeList = searchRepository.findByTitleContaining(keyword);
//        return placeList;
//    }
//
//}
