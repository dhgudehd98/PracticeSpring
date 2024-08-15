package com.sh.app;

import com.sh.app.chroling.repository.DataRepository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootTest
class ChrolingWithJpaApplicationTests {
    private static EntityManagerFactory entityManagerFactory;
    @Autowired
    private DataRepository dataRepository;
    //request-scope : 웹 요청마다 1개씩 생성 (non-thread-safe) : 요청 사이 간의 공유하면 안됨.
    private EntityManager entityManager;

    @BeforeAll
    static void beforeAll() {
        //jpa 설정 정보를 읽어서 EntityManagerFactory를 생성
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @AfterAll
    static void afterAll() {
        entityManagerFactory.close();
    }

    @BeforeEach
    void setUp() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        this.entityManager.close();
    }

    @Test
    @DisplayName("chrolingData 테이블 생성 ")
    void test1() {
//        create table tbl_data (
//                id bigint not null auto_increment,
//                date varchar(255),
//                detailLink varchar(255),
//                imageLink varchar(255),
//                price varchar(255),
//                startDate varchar(255),
//                title varchar(255),
//                primary key (id)
//    ) engine=InnoDB

    }

//    @Test
//    @DisplayName("값 삽입하기 ")
//    void test2() {
//        ChrolingData data = ChrolingData.builder()
//                .sourceSite("네이버 여행")
//                .travelInformation(new TravelInformation("재밌는 제주도 여행", "2박 3일", "2024/08/21", "400000", "https://imageLink", "https://detailLink"))
//                .build();
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//
//        try {
//            entityManager.persist(data);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//        }
//        ChrolingData data2 = entityManager.find(ChrolingData.class, data.getId());
//
//        System.out.println(data);
//        System.out.println(data2);
//    }
//
//    @Test
//    @DisplayName("Repository를 통해서 저장 ")
//    void test3() {
//        ChrolingData data = ChrolingData.builder()
//                .travelInformation(new TravelInformation("재밌는 제주도 여행", "2박 3일", "2024/08/21", "400000", "https://imageLink", "https://detailLink"))
//                .build();
//        System.out.println(data);
//
//
//    }
}



