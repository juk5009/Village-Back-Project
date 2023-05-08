package shop.mtcoding.village.controller.place;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.Exception404;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.DetailPlaceResponse;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.notFoundConst.RoleConst;
import shop.mtcoding.village.service.PlaceService;
import shop.mtcoding.village.util.status.PlaceStatus;


//TODO 경로 바뀐 부분 전달하기
@RestController
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final HostRepository hostRepository;

    private final PlaceJpaRepository placeJpaRepository;

    public PlaceController(PlaceService placeService, HostRepository hostRepository, PlaceJpaRepository placeJpaRepository) {
        this.placeService = placeService;
        this.hostRepository = hostRepository;
        this.placeJpaRepository = placeJpaRepository;
    }


    @GetMapping("/places")
    public ResponseEntity<ResponseDTO<?>> MainList() {
        List<PlaceList> placeLists = placeService.공간리스트();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(placeLists);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/places/allPlace")
    public ResponseEntity<ResponseDTO<List<Place>>> getPlace() {
        List<Place> allPlace = placeJpaRepository.findAll();
        System.out.println("등록 페이지 전체 보기 : " + allPlace);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 전체 보기", allPlace), HttpStatus.OK);
    }

    @GetMapping("/places/{id}")
    public ResponseEntity<ResponseDTO<DetailPlaceResponse>> detailPlace(
           @PathVariable Long id, DetailPlaceResponse detailPlaceResponse
    ) {
        Place placeResponse = placeService.공간상세보기(id, detailPlaceResponse);

        Long id1 = placeResponse.getUser().getId();
        Host host = hostRepository.findByUserId(id1);

        DetailPlaceResponse detailPlaceResponse1 = placeResponse.toDetailResponse(detailPlaceResponse.getFile(), host, detailPlaceResponse.getReview(),
                detailPlaceResponse.getScrap(), detailPlaceResponse.getHashtags(), detailPlaceResponse.getFacilitys(), detailPlaceResponse.getDayOfWeeks()
        ,detailPlaceResponse.getCategoryName());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 상세 보기", detailPlaceResponse1), HttpStatus.OK);
    }

    @PostMapping("/host/places")
    public @ResponseBody ResponseEntity<ResponseDTO<DetailPlaceResponse>> savePlace(
            @Valid @RequestBody PlaceSaveRequest placeSaveRequest, Errors Errors,
            DetailPlaceResponse detailPlaceResponse,  @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        var save = placeService.공간등록하기(placeSaveRequest);

        var savePlace = placeService.등록된공간보기(save.getId(), detailPlaceResponse);

        Long id1 = myUserDetails.getUser().getId();
        Host host = hostRepository.findByUserId(id1);

        DetailPlaceResponse savePlaceResponse = savePlace.toDetailResponse(detailPlaceResponse.getFile(), host, detailPlaceResponse.getReview(),
                detailPlaceResponse.getScrap(), detailPlaceResponse.getHashtags(), detailPlaceResponse.getFacilitys(), detailPlaceResponse.getDayOfWeeks()
                ,detailPlaceResponse.getCategoryName());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 등록 완료", savePlaceResponse), HttpStatus.OK);
    }

    @PutMapping("/host/places")
    public ResponseEntity<ResponseDTO<DetailPlaceResponse>> updatePlace(
            @Valid @RequestBody PlaceUpdateRequest placeUpdateRequest, Errors Errors,
            DetailPlaceResponse detailPlaceResponse, @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {

        String role = myUserDetails.getUser().getRole();

        if (!role.equals("HOST")) {
            throw new MyConstException(RoleConst.notFound);
        }

        var update = placeService.공간수정하기(placeUpdateRequest);

        var savePlace = placeService.등록된공간보기(update.getId(), detailPlaceResponse);

        Long id1 = myUserDetails.getUser().getId();
        Host host = hostRepository.findByUserId(id1);

        DetailPlaceResponse updatePlaceResponse = savePlace.toDetailResponse(detailPlaceResponse.getFile(), host, detailPlaceResponse.getReview(),
                detailPlaceResponse.getScrap(), detailPlaceResponse.getHashtags(), detailPlaceResponse.getFacilitys(), detailPlaceResponse.getDayOfWeeks()
                ,detailPlaceResponse.getCategoryName());


        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 수정 완료", updatePlaceResponse), HttpStatus.OK);
        }
        
        @DeleteMapping("/places/{id}")
        public ResponseEntity<ResponseDTO<PlaceStatus>> deletePlace(
                @PathVariable Long id
        ){
            var optionalPlace = placeService.getPlace(id);
            if (optionalPlace.isEmpty()) {
                throw new Exception400("place" ,PlaceConst.notFound);
            }

            Place inactivePlace = placeService.공간비활성화(optionalPlace.get());

            return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간비활성화 완료", inactivePlace.getStatus()), HttpStatus.OK);

    }

        @PostMapping("/places/active/{id}")
        public ResponseEntity<ResponseDTO<PlaceStatus>> activePlace(
                @PathVariable Long id
        ){
            var optionalPlace = placeService.getPlace(id);
            if (optionalPlace.isEmpty()) {
                throw new MyConstException(PlaceConst.notFound);
            }

            Place activePlace = placeService.공간활성화(optionalPlace.get());

            return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간활성화 완료", activePlace.getStatus()), HttpStatus.OK);

        }
}
