package shop.mtcoding.village.controller.place;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.notFoundConst.RoleConst;
import shop.mtcoding.village.service.PlaceService;
import shop.mtcoding.village.util.status.PlaceStatus;


//TODO 경로 바뀐 부분 전달하기
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final PlaceJpaRepository placeJpaRepository;

    @GetMapping()
    public ResponseEntity<ResponseDTO<?>> MainList() {
        List<PlaceList> placeLists = placeService.공간리스트();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(placeLists);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/allplace")
    public ResponseEntity<ResponseDTO<List<Place>>> getPlace() {
        List<Place> allPlace = placeJpaRepository.findAll();
        System.out.println("등록 페이지 전체 보기 : " + allPlace);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 전체 보기", allPlace), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Place>> detailPlace(
            @PathVariable Long id
    ) {
        Optional<Place> detailPlace = placeService.공간상세보기(id);
        if (detailPlace.isEmpty()){
            throw new CustomException("공간에 대한 정보가 없습니다.");
        }
        Place place = detailPlace.get();
        log.debug(String.valueOf(detailPlace));
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 상세 보기", place), HttpStatus.OK);
    }

    @PostMapping("/host")
//    @PreAuthorize("hasRole('HOST')")
    public @ResponseBody ResponseEntity<ResponseDTO<Place>> savePlace(
            @Valid @RequestBody PlaceSaveRequest placeSaveRequest, Errors Errors,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {

        String role = myUserDetails.getUser().getRole();
        var save = placeService.공간등록하기(placeSaveRequest);

        if (!role.equals("HOST")) {
            throw new MyConstException(RoleConst.notFound);
        }
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 등록 완료", save), HttpStatus.OK);
    }

    @PutMapping("/host")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseDTO<Place>> updatePlace(
            @Valid @RequestBody PlaceUpdateRequest placeUpdateRequest, Errors Errors,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {

        String role = myUserDetails.getUser().getRole();

        if (!role.equals("HOST")) {
            throw new MyConstException(RoleConst.notFound);
        }

        var update = placeService.공간수정하기(placeUpdateRequest);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 수정 완료", update), HttpStatus.OK);
        }
        
        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('HOST')")
        public ResponseEntity<ResponseDTO<PlaceStatus>> deletePlace(
                @PathVariable Long id
        ){
            var optionalPlace = placeService.getPlace(id);
            if (optionalPlace.isEmpty()) {
                throw new MyConstException(PlaceConst.notFound);
            }

            Place inactivePlace = placeService.공간비활성화(optionalPlace.get());

            return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간비활성화 완료", inactivePlace.getStatus()), HttpStatus.OK);

    }

        @PostMapping("/{id}")
        @PreAuthorize("hasRole('HOST')")
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
