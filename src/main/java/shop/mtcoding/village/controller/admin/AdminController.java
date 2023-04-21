package shop.mtcoding.village.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.AdminCustomException;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.notFoundConst.RoleConst;
import shop.mtcoding.village.util.status.HostStatus;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AdminController {

    private final UserRepository userRepository;

    private final PlaceJpaRepository placeRepository;

    private final ReservationRepository reservationRepository;

    private final PaymentRepository paymentRepository;

    private final HttpSession session;

    private final HostRepository hostRepository;


    public AdminController(UserRepository userRepository, PlaceJpaRepository placeRepository, ReservationRepository reservationRepository, PaymentRepository paymentRepository, HttpSession session, HostRepository hostRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.reservationRepository = reservationRepository;
        this.paymentRepository = paymentRepository;
        this.session = session;
        this.hostRepository = hostRepository;
    }

    @GetMapping("/z/admin/loginForm")
    public String loginForm(){
        return "/admin/loginForm";
    }

    @GetMapping("/z/admin/main")
    public String view(Model model) {

        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);

        List<Place> placeList = placeRepository.findAll();
        model.addAttribute("placeList", placeList);

        List<Reservation> reservationList = reservationRepository.findAll();
        model.addAttribute("reservationList", reservationList);

        List<Payment> paymentList = paymentRepository.findAll();
        model.addAttribute("paymentList", paymentList);

        return "admin/main";
    }

    @GetMapping("/z/admin/host")
    public String host(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
//
//        Long id = myUserDetails.getUser().getId();
//        System.out.println("디버그 : " + id);
//        User principal = (User) session.getAttribute("principal");

        List<User> userList = userRepository.findAll();


        // TODO 유저 아이디 받아 와야함 우째 받지
        Optional<User> byId = userRepository.findById(1L);
        User user = byId.get();

        Host host = hostRepository.findByUser_Id(1L);
        if (host.getStatus().equals(HostStatus.WAIT)) {
            model.addAttribute("user", user);
        }

        return "admin/host";
    }

    @PostMapping("/z/admin/login")
    public String login(UserRequest.LoginDTO loginDTO) {

        User login = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (login == null) {
            throw new AdminCustomException("email 혹은 password가 잘못 입력되었습니다");
        }

        String role = login.getRole();
        if (!role.equals("ADMIN")) {
            throw new MyConstException(RoleConst.notFound);
        }

        session.setAttribute("principal", login);

        return "redirect:/z/admin/main";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/z/admin/main";
    }
}
