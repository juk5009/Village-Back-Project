package shop.mtcoding.village.controller.place;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.service.PlaceService;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final PlaceRepository placeRepository;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Place>>> getPlace() {
        List<Place> allPlace = placeRepository.findAll();
        System.out.println("등록 페이지 전체 보기 : " + allPlace);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 전체 보기", allPlace), HttpStatus.OK);
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

    @PostMapping
    public @ResponseBody ResponseEntity<ResponseDTO> savePlace(
            @Valid @RequestBody PlaceSaveRequest placeSaveRequest, Errors Errors) {

        var save = placeService.공간등록하기(placeSaveRequest);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 등록 완료", save), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updatePlace(
            @Valid @RequestBody PlaceUpdateRequest placeUpdateRequest, Errors Errors) {

        var update = placeService.공간수정하기(placeUpdateRequest);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 수정 완료", update), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(
            @PathVariable Long id) {
        var optionalPlace = placeService.getPlace(id);
        if (optionalPlace.isEmpty()) {
            throw new MyConstException(PlaceConst.notFound);
        }

        placeService.공간삭제하기(optionalPlace.get());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "공간 데이터 삭제 완료", null), HttpStatus.OK);
    }

}
