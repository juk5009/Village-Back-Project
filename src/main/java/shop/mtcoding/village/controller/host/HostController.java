package shop.mtcoding.village.controller.host;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.ResponseDTO;
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
    public @ResponseBody ResponseEntity<ResponseDTO> saveHost(@Valid @RequestBody HostSaveDto hostSaveDto, BindingResult result){

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        Host hostSave = hostService.호스트신청(hostSaveDto);


        return new ResponseEntity<>(new ResponseDTO<>(1, "호스트 신청 완료", hostSave), HttpStatus.OK);
    }


}
