package shop.mtcoding.village.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;



    @Transactional
    public Place 공간등록하기(PlaceSaveRequest placeRequest) {

        Place save = placeRepository.save(placeRequest.toEntity());

        return placeRepository.findAllWithDate(save.getId());

    }

    @Transactional
    public Optional<Place> getPlace(Long id) {
        return placeRepository.findById(id);
    }

    @Transactional
    public void 공간삭제하기(Place place) {
        placeRepository.delete(place);
    }

    public Place 공간수정하기(PlaceUpdateRequest placeUpdateRequest) {
        return placeRepository.save(placeUpdateRequest.toEntity());
    }


}
