package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    void testNotificationManager() {
        System.out.println("========== NotificationManager 테스트 시작 ==========");

        // NotificationManager 주입 확인
        assertNotNull(notificationManager);
        System.out.println("notificationManager 주입 성공: " + notificationManager);

        // 이메일 서비스 검증
        assertNotNull(notificationManager.getEmailService());
        System.out.println("emailService 주입 성공: " + notificationManager.getEmailService());

        EmailNotificationService emailService =
                (EmailNotificationService) notificationManager.getEmailService();

        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        System.out.println("SMTP 서버 확인: " + emailService.getSmtpServer());
        System.out.println("SMTP 포트 확인: " + emailService.getPort());

        // SMS 서비스 검증
        assertNotNull(notificationManager.getSmsService());
        System.out.println("smsService 주입 성공: " + notificationManager.getSmsService());

        SmsNotificationService smsService =
                (SmsNotificationService) notificationManager.getSmsService();

        assertEquals("SKT", smsService.getProvider());
        System.out.println("SMS 제공업체 확인: " + smsService.getProvider());

        // NotificationManager 메서드 실행
        System.out.println("---- 이메일 알림 전송 테스트 ----");
        notificationManager.sendNotificationByEmail("테스트 이메일");

        System.out.println("---- SMS 알림 전송 테스트 ----");
        notificationManager.sendNotificationBySms("테스트 SMS");

        System.out.println("========== NotificationManager 테스트 종료 ==========");
    }
}