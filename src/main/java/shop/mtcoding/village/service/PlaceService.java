package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.category.request.CategorySaveDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.dto.place.response.PlaceUpdateResponse;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.category.CategoryRepository;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.facilityInfo.FacilityInfoRepository;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.hashtag.HashtagRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.review.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final DateRepository dateRepository;

    private final HashtagRepository hashtagRepository;

    private final FacilityInfoRepository facilityInfoRepository;

    private final ReviewRepository reviewRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public PlaceSaveResponse 공간등록하기(PlaceSaveRequest placeRequest) {

        // 카테고리 insert
        Category saveCategory = categoryRepository.save(placeRequest.getCategory().toDEntity());

        // 요일 날짜 insert
        Dates saveDates = dateRepository.save(placeRequest.getDayOfWeek().toEntity());

        // 해시태그 insert
        Hashtag saveHashtag = hashtagRepository.save(placeRequest.getHashtag().toEntity());

        // 편의 시설 insert
        FacilityInfo saveFacilityInfo = facilityInfoRepository.save(placeRequest.getFacilityInfo().toEntity());

        // 공간 insert
        Place savePlace = placeRepository.save(placeRequest.toEntity());


        return savePlace.toResponse(saveDates , saveHashtag, saveFacilityInfo, saveCategory);

    }

    @Transactional
    public Optional<Place> getPlace(Long id) {
        return placeRepository.findById(id);
    }

    @Transactional
    public void 공간삭제하기(Place place) {
        placeRepository.delete(place);
    }

    public PlaceUpdateResponse 공간수정하기(PlaceUpdateRequest placeUpdateRequest) {
        // 카테고리 update
        Category updateCategory = categoryRepository.save(placeUpdateRequest.getCategory().toDEntity());

        // 요일 날짜 update
        Dates updateDates = dateRepository.save(placeUpdateRequest.getDayOfWeek().toEntity());

        // 해시태그 update
        Hashtag updateHashtag = hashtagRepository.save(placeUpdateRequest.getHashtag().toEntity());

        // 편의 시설 update
        FacilityInfo updateFacilityInfo = facilityInfoRepository.save(placeUpdateRequest.getFacilityInfo().toEntity());

        // 공간 update
        Place updatePlace = placeRepository.save(placeUpdateRequest.toEntity());


        return updatePlace.toUpdateResponse(updateDates , updateHashtag, updateFacilityInfo, updateCategory);
    }

    public Page<Place> getPage(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }
}
