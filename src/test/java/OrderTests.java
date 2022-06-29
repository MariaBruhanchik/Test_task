import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Category;
import models.Order;
import models.OrderResponse;
import models.Pet;
import org.testng.annotations.Test;
import util.OrderStatus;
import util.PetStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class OrderTests {

    private static final RequestSpecification REQUEST_SPECIFICATION_FOR_ORDER =
            new RequestSpecBuilder()
                    .setBaseUri("https://petstore.swagger.io/v2")
                    .setBasePath("/store/order")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

    @Test
    public void createAnOrder() {
        Order rq = Order.builder()
                .id(3)
                .petId(3)
                .quantity(345)
                .shipDate("2022-06-29T13:37:27.066Z")
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();

        Order rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_ORDER)
                .body(rq)
                .when().post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(Order.class);

        assertEquals(rq.getPetId(), rs.getPetId());
    }

    @Test
    public void findById() {
        List<Order> orders =
                given()
                        .spec(REQUEST_SPECIFICATION_FOR_ORDER)
                        .basePath("/store/order/")
                        .when().get("3")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().jsonPath().get("id");

    }

    @Test
    public void returnsPetInventories() {
        OrderResponse orders =
                given().spec(REQUEST_SPECIFICATION_FOR_ORDER)
                        .basePath("/store/inventory")
                        .when().get()
                        .then().assertThat().statusCode(200)
                        .extract().as(OrderResponse.class);

    }
}
