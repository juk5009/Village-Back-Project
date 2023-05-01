package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.api.s3.S3Service;
import shop.mtcoding.village.dto.category.request.CategorySaveDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.file.dto.FileSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.category.CategoryRepository;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.facilityInfo.FacilityInfoRepository;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileRepository;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.hashtag.HashtagRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.review.ReviewRepository;
import shop.mtcoding.village.util.Base64Decoded;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final PlaceJpaRepository placeJpaRepository;

    private final DateRepository dateRepository;

    private final HashtagRepository hashtagRepository;

    private final FacilityInfoRepository facilityInfoRepository;

    private final ReviewRepository reviewRepository;

    private final CategoryRepository categoryRepository;

    private final FileService fileService;

    private final FileRepository fileRepository;

    private final S3Service s3Service;

    @Transactional
    public List<PlaceList> 공간리스트() {

        try {
            return placeRepository.PlaceList();
        }catch (Exception500 e) {
            throw new Exception500("공간리스트 오류" + e.getMessage());
        }
    }

    @Transactional
    public Place 공간등록하기(PlaceSaveRequest placeRequest) {
        try {

            // 공간 insert
            Place savePlace = placeJpaRepository.save(placeRequest.toEntity());

            Optional<Place> byId = placeJpaRepository.findById(savePlace.getId());

            Place place = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            System.out.println("디버그 : " + placeRequest.getHashtag());

            for (HashtagSaveDTO.HashtagSaveDto hash : placeRequest.getHashtag()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place));

                hashtagList.add(save1);
            }

            // file s3에 저장
            List<File> fileList = new ArrayList<>();

            for (FileSaveDTO.FileSaveDto files : placeRequest.getImage()) {
                String imgPath = s3Service.upload(files.getFileName(), Base64Decoded.convertBase64ToMultipartFile(files.getData()));
                files.setFileUrl(imgPath);
                File save = fileRepository.save(files.toEntity(files.getName(), files.getFileUrl()));
//                fileList.add(save);

                fileService.save(placeRequest.getImage().get(0));
            }

            // 카테고리 insert
            Category category = new Category();
            category.setCategoryName(placeRequest.getCategoryName());
            categoryRepository.save(category);

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DateSaveDto date : placeRequest.getDayOfWeek()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoSaveDto facilityInfo : placeRequest.getFacilityInfo()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place));

                facilityInfoList.add(savefacilityInfo);
            }

            return savePlace;
        } catch (Exception500 e) {
            throw new Exception500("공간등록 오류" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public Optional<Place> getPlace(Long id) {
        return placeJpaRepository.findById(id);
    }

    @Transactional
    public void 공간삭제하기(Place place) {
        try {
            placeJpaRepository.delete(place);
        } catch (Exception500 e) {
            throw new Exception500("공간삭제 오류" + e.getMessage());
        }

    }

    public Place 공간수정하기(PlaceUpdateRequest placeUpdateRequest) {
        try {

            // 공간 insert
            Place savePlace = placeJpaRepository.save(placeUpdateRequest.toEntity());

            Optional<Place> byId = placeJpaRepository.findById(savePlace.getId());

            Place place = byId.get();

            // 해시태그 insert
            List<Hashtag> hashtagList = new ArrayList<Hashtag>();

            System.out.println("디버그 : " + placeUpdateRequest.getHashtag());

            for (HashtagSaveDTO.HashtagSaveDto hash : placeUpdateRequest.getHashtag()) {
                Hashtag save1 = hashtagRepository.save(hash.toEntity(hash.getHashtagName(), place));

                hashtagList.add(save1);
            }

            // file s3에 저장
            List<File> fileList = new ArrayList<>();

            for (FileSaveDTO.FileSaveDto file : placeUpdateRequest.getImage()) {
                String imgPath = s3Service.upload(file.getName(), Base64Decoded.convertBase64ToMultipartFile(file.getData()));
                file.setFileUrl(imgPath);
                File save = fileRepository.save(file.toEntity(file.getName(), file.getFileUrl()));
                fileList.add(save);

                fileService.save(placeUpdateRequest.getImage().get(0));
            }

            // 카테고리 insert
            Category category = new Category();
            category.setCategoryName(placeUpdateRequest.getCategoryName());
            categoryRepository.save(category);

            // 요일 날짜 insert
            List<Dates> dateList = new ArrayList<Dates>();

            for (DateSaveDTO.DateSaveDto date : placeUpdateRequest.getDayOfWeek()) {
                Dates saveDate = dateRepository.save(date.toEntity(date.getDayOfWeekName(), place));

                dateList.add(saveDate);
            }

            // 편의 시설 insert
            List<FacilityInfo> facilityInfoList = new ArrayList<FacilityInfo>();

            for (FacilityInfoSaveDTO.FacilityInfoSaveDto facilityInfo : placeUpdateRequest.getFacilityInfo()) {
                FacilityInfo savefacilityInfo = facilityInfoRepository.save(facilityInfo.toEntity(facilityInfo.getFacilityName(), place));

                facilityInfoList.add(savefacilityInfo);
            }

            return savePlace;
        } catch (Exception500 e) {
            throw new Exception500("공간등록 오류" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public Page<Place> getPage(Pageable pageable) {
        return placeJpaRepository.findAll(pageable);
    }

    public Optional<Place> 공간상세보기(Long id) {
        return placeJpaRepository.findById(id);
    }

    public List<Place> 공간메인보기() {
        return placeJpaRepository.findAll();
    }
}
