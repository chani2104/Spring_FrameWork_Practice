package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    void testShoppingCart() {
        System.out.println("========== ShoppingCart 테스트 시작 ==========");

        // A. shoppingCart 객체가 Null 이 아닌지
        assertNotNull(shoppingCart);
        System.out.println("shoppingCart 객체 주입 성공 : " + shoppingCart);

        // B. shoppingCart.getProducts().size()
        assertNotNull(shoppingCart.getProducts());
        assertEquals(2, shoppingCart.getProducts().size());
        System.out.println("상품 개수 : " + shoppingCart.getProducts().size());

        // 상품 목록 출력
        System.out.println("장바구니 상품 목록");
        for (Product product : shoppingCart.getProducts()) {
            System.out.println(product);
        }

        // C. 첫 번째 상품 이름이 "노트북"
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName());
        System.out.println("첫 번째 상품명 검증 완료 : " + shoppingCart.getProducts().get(0).getName());

        // D. 두 번째 상품 이름이 "스마트폰"
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName());
        System.out.println("두 번째 상품명 검증 완료 : " + shoppingCart.getProducts().get(1).getName());

        // 총 가격 출력
        System.out.println("장바구니 총 금액 : " + shoppingCart.getTotalPrice());

        System.out.println("========== ShoppingCart 테스트 종료 ==========\n");
    }

    @Test
    void testOrderService() {
        System.out.println("========== OrderService 테스트 시작 ==========");

        // OrderService가 주입됐는지
        assertNotNull(orderService);
        System.out.println("orderService 객체 주입 성공 : " + orderService);

        // OrderService 안에 ShoppingCart도 주입됐는지
        assertNotNull(orderService.getShoppingCart());
        System.out.println("orderService 안의 shoppingCart 주입 성공 : " + orderService.getShoppingCart());

        // 총액 검증: 150000 + 800000 = 950000
        double total = orderService.calculateOrderTotal();
        assertEquals(950000.0, total, 0.0001);
        System.out.println("주문 총 금액 계산 결과 : " + total);

        System.out.println("========== OrderService 테스트 종료 ==========\n");
    }
}