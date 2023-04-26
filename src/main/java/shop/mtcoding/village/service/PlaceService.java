package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.category.request.CategorySaveDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
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

import java.util.ArrayList;
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
    public Place 공간등록하기(PlaceSaveRequest placeRequest) {
        try {
            Optional<Place> byId = placeRepository.findById(2L);
            Place place1 = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            System.out.println("디버그 : " + placeRequest.getHashtag().getHashtagDto());

            for (HashtagSaveDTO.HashtagDto hash : placeRequest.getHashtag().getHashtagDto()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place1));

                hashtagList.add(save1);
            }

            // 카테고리 insert
            CategorySaveDTO.CategoryDTO categoryDTO = placeRequest.getCategory().getCategoryName();
            categoryRepository.save(categoryDTO.toEntity(categoryDTO.getCategoryName(), place1));

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DatesDto date : placeRequest.getDayOfWeek().getDayOfWeekName()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place1));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoDTO facilityInfo : placeRequest.getFacilityInfo().getFacilityName()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place1));

                facilityInfoList.add(savefacilityInfo);
            }

            // 공간 insert
            Place savePlace = placeRepository.save(placeRequest.toEntity());


            return savePlace;
        } catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }


    }

    @Transactional
    public Optional<Place> getPlace(Long id) {
        return placeRepository.findById(id);
    }

    @Transactional
    public void 공간삭제하기(Place place) {
        try {
            placeRepository.delete(place);
        } catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }

    }

    public Place 공간수정하기(PlaceUpdateRequest placeUpdateRequest) {

        try {
            Optional<Place> byId = placeRepository.findById(2L);
            Place place1 = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            for (HashtagSaveDTO.HashtagDto hash : placeUpdateRequest.getHashtag().getHashtagDto()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place1));

                hashtagList.add(save1);
            }

            // 카테고리 insert
            CategorySaveDTO.CategoryDTO categoryDTO = placeUpdateRequest.getCategory().getCategoryName();
            categoryRepository.save(categoryDTO.toEntity(categoryDTO.getCategoryName(), place1));

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DatesDto date : placeUpdateRequest.getDayOfWeek().getDayOfWeekName()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place1));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoDTO facilityInfo : placeUpdateRequest.getFacilityInfo().getFacilityName()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place1));

                facilityInfoList.add(savefacilityInfo);
            }
            // 공간 update
            Place updatePlace = placeRepository.save(placeUpdateRequest.toEntity());


            return updatePlace;
        } catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }


    }

    public Page<Place> getPage(Pageable pageable) {
        return placeRepository.findAll(pageable);
    }
}
