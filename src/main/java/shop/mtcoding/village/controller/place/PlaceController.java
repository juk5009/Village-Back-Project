package shop.mtcoding.village.controller.place;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.google.auth.oauth2.JwtProvider;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.notFoundConst.RoleConst;
import shop.mtcoding.village.service.PlaceService;

@RestController
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

    @GetMapping("/place")
    public ResponseEntity<ResponseDTO<List<Place>>> getPlace() {
        List<Place> allPlace = placeJpaRepository.findAll();
        System.out.println("등록 페이지 전체 보기 : " + allPlace);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 전체 보기", allPlace), HttpStatus.OK);
    }

    @GetMapping("/place/{id}")
    public ResponseEntity detailPlace(
            @PathVariable Long id
    ) {
        Optional<Place> detailPlace = placeService.공간상세보기(id);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 상세 보기",detailPlace), HttpStatus.OK);
    }

    // @GetMapping
    // public ResponseEntity<Page<PlaceSaveResponse>> getPage(Pageable pageable) {
    // var page = placeService.getPage(pageable);
    // var content = page.getContent()
    // .stream()
    // .map(Place::toDTO)
    // .toList();
    //
    // return ResponseEntity.ok(
    // new PageImpl<>(content, pageable, page.getTotalElements())
    // );
    // }

    @PostMapping("/host/place")
    public @ResponseBody ResponseEntity<ResponseDTO> savePlace(
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

    @PutMapping("/host/place")
    public ResponseEntity<ResponseDTO> updatePlace(
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
        
        @DeleteMapping("/host/place/{id}")
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
