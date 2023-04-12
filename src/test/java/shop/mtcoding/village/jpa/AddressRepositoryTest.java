package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.address.AddressRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE reservation_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
        setUp("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");
    }

    @Test
    @Transactional
    void selectAll() {
        List<Address> addresses = addressRepository.findAll();
        Assertions.assertNotEquals(addresses.size(), 0);

        Address address = addresses.get(0);
        Assertions.assertEquals(address.getRoadFullAddr(), "부산 부산진구 중앙대로 688 한준빌딩 3층");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalAddress = this.addressRepository.findById(4L);

        if(optionalAddress.isPresent()) {
            var result = optionalAddress.get();
            Assertions.assertEquals(result.getSggNm(), "부산진구1");

            var zipNo = "12345";
            result.setZipNo(zipNo);
            Address merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getZipNo(),"12345");
        } else {
            Assertions.assertNotNull(optionalAddress.get());
        }
    }

    @Test
//    @Transactional
    void insertAndDelete() {
        Address address = setUp("부산 부산진구 중앙대로 688 한준빌딩 4층", "부산진구2", "43396", "11", "11");
        Optional<Address> findAddress = this.addressRepository.findById(address.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getLng(), "11");
            entityManager.remove(address);
            Optional<Address> deleteAddress = this.addressRepository.findById(address.getId());
            if (deleteAddress.isPresent()) {
                Assertions.assertNull(deleteAddress.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
        }
    }

    private Address setUp(String roadFullAddr, String sggNm, String zipNo, String lat, String lng) {
        var address = new Address();
        address.setRoadFullAddr(roadFullAddr);
        address.setSggNm(sggNm);
        address.setZipNo(zipNo);
        address.setLat(lat);
        address.setLng(lng);
        return this.entityManager.persist(address);
    }
}
