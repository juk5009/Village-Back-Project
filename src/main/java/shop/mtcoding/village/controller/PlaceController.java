package shop.mtcoding.village.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.place.PlaceSaveDto;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.service.PlaceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    private final DateRepository dateRepository;


    @PostMapping
    public ResponseEntity<?> savePlace(@Valid @RequestBody PlaceSaveDto placeSaveDto, BindingResult result) {

//        List<Dates> dayOfWeek = placeSaveDto.getDayOfWeek();
//        for (Dates tttt :placeSaveDto.getDayOfWeek()) {
//        System.out.println("  "+tttt.getDayOfWeekName());
//        }

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        List<Object[]> placeSave = placeService.공간등록하기(placeSaveDto);

        return ResponseEntity.ok().body(placeSave);
    }

}
