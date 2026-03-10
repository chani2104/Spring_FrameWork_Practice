package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testUserService() {
        System.out.println("========== UserService 테스트 시작 ==========");

        // UserService 주입 확인
        assertNotNull(userService);
        System.out.println("userService 주입 성공: " + userService);

        // UserRepository 주입 확인
        assertNotNull(userService.getUserRepository());
        System.out.println("userRepository 주입 성공: " + userService.getUserRepository());

        // dbType 값 확인
        assertEquals("MySQL", userService.getUserRepository().getDbType());
        System.out.println("dbType 확인: " + userService.getUserRepository().getDbType());

        // SecurityService 주입 확인
        assertNotNull(userService.getSecurityService());
        System.out.println("securityService 주입 성공: " + userService.getSecurityService());

        // registerUser() 기능 확인
        boolean result = userService.registerUser("hong01", "홍길동", "1234");
        assertTrue(result);
        System.out.println("registerUser() 결과: " + result);

        System.out.println("========== UserService 테스트 종료 ==========");
    }
}