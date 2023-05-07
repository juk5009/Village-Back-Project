package shop.mtcoding.village.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.AdminCustomException;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
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
import shop.mtcoding.village.util.status.HostStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AdminController {

    private final UserRepository userRepository;

    private final PlaceJpaRepository placeRepository;

    private final ReservationRepository reservationRepository;

    private final PaymentRepository paymentRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final HttpSession session;

    private final HostRepository hostRepository;


    public AdminController(UserRepository userRepository, PlaceJpaRepository placeRepository, ReservationRepository reservationRepository, PaymentRepository paymentRepository, BCryptPasswordEncoder passwordEncoder, HttpSession session, HostRepository hostRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
        this.reservationRepository = reservationRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
        this.session = session;
        this.hostRepository = hostRepository;
    }

    @GetMapping("/loginForm/admin")
    public String loginForm(){
        return "/admin/loginForm";
    }

    @GetMapping("/admin/main")
    public String view(Model model) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new AdminCustomException("회원 인증이 되지 않았습니다. 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
        }

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

    @GetMapping("/admin/host")
    public String host(Model model) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new AdminCustomException("회원 인증이 되지 않았습니다. 로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
        }

        List<Host> hosts = hostRepository.findByStatus(HostStatus.WAIT);
        model.addAttribute("hosts", hosts);
        return "admin/host";
    }

    @PostMapping("/admin/login")
    public String login(UserRequest.LoginDTO loginDTO) {

        ArrayList<String> loginViewList = new ArrayList<>();

        Optional<User> userOP = userRepository.findByEmail(loginDTO.getEmail());
        if (userOP.isPresent()) {
            User userPS = userOP.get();

            if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
                String jwt = MyJwtProvider.create(userPS);
                loginViewList.add(jwt);
                loginViewList.add(String.valueOf(userPS.getId()));
                loginViewList.add(userPS.getName());
                loginViewList.add(userPS.getEmail());

                User login = userRepository.findByEmailAndPassword(userPS.getEmail(), userPS.getPassword());
                if (login == null) {
                    throw new AdminCustomException("email 혹은 password가 잘못 입력되었습니다");
                }

                if (!login.getRole().equals("ADMIN")) {
                    throw new AdminCustomException("관리자만 로그인 할 수 있습니다.");
                }
                session.setAttribute("principal", login);
            }
        }

        return "redirect:/admin/main";
    }

    @GetMapping("/logout/admin")
    public String logout() {
        session.invalidate();
        return "redirect:/loginForm/admin";
    }
}
