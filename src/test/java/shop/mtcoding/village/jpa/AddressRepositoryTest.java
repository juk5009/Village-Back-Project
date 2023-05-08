 package shop.mtcoding.village.jpa;

 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.transaction.annotation.Transactional;
 import shop.mtcoding.village.model.address.Address;
 import shop.mtcoding.village.model.address.PlaceAddressRepository;
 import shop.mtcoding.village.model.place.PlaceAddress;

 import javax.persistence.EntityManager;
 import java.util.List;
 import java.util.Optional;

 import static org.junit.jupiter.api.Assertions.*;

 @DataJpaTest
 @ExtendWith(SpringExtension.class)
 @DisplayName("주소 JPA 테스트")
 public class AddressRepositoryTest {

     @Autowired
     private PlaceAddressRepository addressRepository;

     @Autowired
     private TestEntityManager entityManager;

     @Autowired
     private EntityManager em;

     @BeforeEach
     public void init() {
//         setUp("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "1층", "121", "151");
     }

     @Test
     @Transactional
     @DisplayName("주소 조회 테스트")
     void selectAll() {
         List<PlaceAddress> addresses = addressRepository.findAll();
         Assertions.assertNotEquals(addresses.size(), 0);

         PlaceAddress address = addresses.get(0);
         assertEquals(address.getAddress().toLowerCase(), "부산 부산진구 중앙대로 688 한준빌딩 2층".toLowerCase());
     }

     @Test
     @Transactional
     @DisplayName("주소 조회 및 수정 테스트")
     void selectAndUpdate() {
         var optionalAddress = this.addressRepository.findById(1L);

         if(optionalAddress.isPresent()) {
             var result = optionalAddress.get();
             assertEquals(result.getSigungu().toLowerCase(), "부산 부산진구".toLowerCase());

             var zipNo = "12345";
             result.setZonecode(zipNo);
             PlaceAddress merge = entityManager.merge(result);

             Assertions.assertEquals(merge.getZonecode(),"12345");
         } else {
             Assertions.assertNotNull(optionalAddress.get());
         }
     }

     @Test
     @Transactional
     @DisplayName("주소 삽입 및 삭제 테스트")
     void insertAndDelete() {
         PlaceAddress address = setUp("부산 부산진구 중앙대로 688 한준빌딩 4층", "부산진구2", "43396", "1층","11", "11");
         Optional<PlaceAddress> findAddress = this.addressRepository.findById(address.getId());

         if(findAddress.isPresent()) {
             var result = findAddress.get();
             Assertions.assertEquals(result.getY(), "11");
             entityManager.remove(address);
             Optional<PlaceAddress> deleteAddress = this.addressRepository.findById(address.getId());
             if (deleteAddress.isPresent()) {
                 Assertions.assertNull(deleteAddress.get());
             }
         } else {
             Assertions.assertNotNull(findAddress.get());
         }
     }

     private PlaceAddress setUp(String address, String sigungu, String zonecode, String detailAddress, String x, String y) {
         var placeaddress = new PlaceAddress();
         placeaddress.setAddress(address);
         placeaddress.setSigungu(sigungu);
         placeaddress.setZonecode(zonecode);
         placeaddress.setDetailAddress(detailAddress);
         placeaddress.setX(x);
         placeaddress.setY(y);
         return this.entityManager.persist(placeaddress);
     }
 }
