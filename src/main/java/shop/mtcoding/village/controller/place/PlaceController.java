package shop.mtcoding.village.controller.place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


//TODO 경로 바뀐 부분 전달하기
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final PlaceJpaRepository placeJpaRepository;

    @GetMapping("mainlist")
    public ResponseEntity<List<PlaceList>> MainList() {
        List<PlaceList> placeLists = placeService.공간리스트();
        return ResponseEntity.ok(placeLists);
    }

    @GetMapping
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
    @PreAuthorize("hasRole('HOST')")
    public @ResponseBody ResponseEntity<ResponseDTO<Place>> savePlace(
            @Valid @RequestBody PlaceSaveRequest placeSaveRequest, Errors Errors,
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {

        String role = myUserDetails.getUser().getRole();

        System.out.println("디버그 : " + role);
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
        
    @DeleteMapping("/host/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<?> deletePlace(
            @PathVariable Long id
    ){
        var optionalPlace = placeService.getPlace(id);
        if (optionalPlace.isEmpty()) {
            throw new MyConstException(PlaceConst.notFound);
        }

        placeService.공간삭제하기(optionalPlace.get());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 삭제 완료", null), HttpStatus.OK);
    }

}
