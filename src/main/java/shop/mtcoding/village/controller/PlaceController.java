package shop.mtcoding.village.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.place.request.PlaceUpdateRequest;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.service.PlaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final PlaceRepository placeRepository;
    @GetMapping
    public ResponseEntity<List<Place>> getPlace() {
        List<Place> allPlace = placeRepository.findAll();
        return ResponseEntity.ok(allPlace);
    }

    @PostMapping
    public ResponseEntity<PlaceSaveResponse> savePlace(
            @Valid @RequestBody PlaceUpdateRequest placeSaveDto, BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        var savePlace = placeService.공간등록하기(placeSaveDto);

        return ResponseEntity.ok(savePlace.toResponse());
    }

    @PutMapping
    public ResponseEntity<String> updatePlace(
            @Valid @RequestBody PlaceUpdateRequest placeUpdateRequest, BindingResult result
        ){

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        placeService.공간수정하기(placeUpdateRequest);
        return ResponseEntity.ok("수정이 완료 되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlace(
            @PathVariable Long id
    ){
        var optionalPlace = placeService.getPlace(id);
        if (optionalPlace.isEmpty()) {
            throw new Exception400(PlaceConst.notFound);
        }

        placeService.공간삭제하기(optionalPlace.get());

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }

}
