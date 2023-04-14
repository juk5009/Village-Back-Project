package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.place.PlaceSaveDto;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final DateRepository dateRepository;


    public List<Object[]> 공간등록하기(PlaceSaveDto placeSaveDto) {

        Place save = placeRepository.save(placeSaveDto.toEntity());

        for (Dates date : placeSaveDto.getDayOfWeek()) {
            date.setDayOfWeekName(date.getDayOfWeekName());
            dateRepository.save(date);
        }

        return placeRepository.findAllWithDate(save.getId());
//        return save;

    }
}
