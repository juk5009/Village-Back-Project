package shop.mtcoding.village.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.host.HostSaveDto;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.service.HostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
@Slf4j
public class HostController {

    private final HostService hostService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody HostSaveDto hostSaveDto, BindingResult result){

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        Host hostSave = hostService.호스트신청(hostSaveDto);


        return ResponseEntity.ok().body(hostSave.toResponse());
    }


}
