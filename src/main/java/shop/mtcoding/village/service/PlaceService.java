package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.date.DateSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;



    public Place 공간등록하기(PlaceSaveRequest placeRequest) {

        Place save = placeRepository.save(placeRequest.toEntity());

        return placeRepository.findAllWithDate(save.getId());

    }
}
