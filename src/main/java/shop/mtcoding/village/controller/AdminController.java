package shop.mtcoding.village.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/z/admin")
public class AdminController {

    private final UserRepository userRepository;

    private final PlaceJpaRepository placeRepository;

    private final ReservationRepository reservationRepository;

    private final PaymentRepository paymentRepository;

    public AdminController(UserRepository userRepository, PlaceJpaRepository placeRepository, ReservationRepository reservationRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.reservationRepository = reservationRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public String view(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);

        List<Place> placeList = placeRepository.findAll();
        model.addAttribute("placeList", placeList);

        List<Reservation> reservationList = reservationRepository.findAll();
        model.addAttribute("reservationList", reservationList);

        List<Payment> paymentList = paymentRepository.findAll();
        model.addAttribute("paymentList", paymentList);

//        TotalPrice.calculateTotalPrice(reservationList);

        return "admin/main";
    }

    @DeleteMapping
    public String delete(@PathVariable Long id) {

//        userRepository.delete();

        return "admin/main";
    }


}
