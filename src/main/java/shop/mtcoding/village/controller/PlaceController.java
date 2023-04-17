package shop.mtcoding.village.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.date.DateSaveResponse;
import shop.mtcoding.village.dto.place.request.PlaceSaveRequest;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.service.PlaceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceSaveResponse> savePlace(
            @Valid @RequestBody PlaceSaveRequest placeSaveDto, BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        var placeSave = placeService.공간등록하기(placeSaveDto);

        return ResponseEntity.ok(placeSave.toResponse());
    }

    @PutMapping
    public ResponseEntity<?> updatePlace(

    ){
        return ResponseEntity.ok("");
    }

}
