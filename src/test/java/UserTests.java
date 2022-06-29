import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.MapCreater;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class UserTests {

    private final RequestSpecification REQUEST_SPECIFICATION_FOR_USER =
            new RequestSpecBuilder()
                    .setBaseUri("https://petstore.swagger.io/v2")
                    .setBasePath("/user")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

    @Test
    public void createUser() {
        User rq = User.builder()
                .id("123")
                .username("Mary")
                .firstName("Mary")
                .lastName("Bru")
                .password("12345")
                .email("bru@mail.ru")
                .phone("1354654")
                .userStatus(1)
                .build();

        ApiResponse rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_USER)
                .body(rq)
                .when().post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(ApiResponse.class);

        assertNotNull(rs);
        assertTrue(rs.getMessage().contains(rq.getId()));
    }

    @Test
    public void loggInUser() {

        ApiResponse response = given()
                .spec(REQUEST_SPECIFICATION_FOR_USER)
                .basePath("/user/login")
                .queryParams("username", "dima", "password", "dima")
                .when().get()
                .then()
                .assertThat().statusCode(200)
                .extract().as(ApiResponse.class);

    }

    @Test
    public void updateUser() {
        User rq = User.builder()
                .id("23")
                .username("Dima")
                .firstName("Dima")
                .lastName("Bru")
                .password("123gfgf45")
                .email("bru@mail.ru")
                .phone("135465gfgfgg4")
                .userStatus(16)
                .build();

        ApiResponse rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_USER)
                .basePath("/user")
                .body(rq)
                .when().put("dima")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(ApiResponse.class);

        assertNotNull(rs);
        assertEquals(rq.getId(), rs.getMessage());
    }

    @Test
    public void loggOutUser() {
        ApiResponse response = given()
                .spec(REQUEST_SPECIFICATION_FOR_USER)
                .basePath("/user/logout")
                .when().get()
                .then()
                .assertThat().statusCode(200)
                .extract().as(ApiResponse.class);
        Assert.assertTrue(response.getMessage().contains("ok"));
    }

    @Test
    public void createWithArray() {
        User dima = User.builder()
                .id("23")
                .username("Dima")
                .firstName("Dima")
                .lastName("Bru")
                .password("123gfgf45")
                .email("bru@mail.ru")
                .phone("135465gfgfgg4")
                .userStatus(16)
                .build();

        User vika = User.builder()
                .id("23")
                .username("Vika")
                .firstName("Vika")
                .lastName("V")
                .password("145")
                .email("bruvvv@mail.ru")
                .phone("135465gfgfgg4")
                .userStatus(34)
                .build();


        ApiResponse rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_USER)
                .basePath("/user/createWithArray")
                .params(String.valueOf(MapCreater.mapping("23", "dima", "dima", "bru",
                        "tgj@mail.ru", "454646", "76855", 56)), MapCreater.mapping("23", "vik",
                        "vika", "brudd", "tgj@mail.ru", "11111", "734555",
                        356))
                .when().post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(ApiResponse.class);
        Assert.assertTrue(rs.getMessage().contains("ok"));
    }
}
