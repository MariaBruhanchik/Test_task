import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.*;
import util.PetStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class PetTests {
    private static final RequestSpecification REQUEST_SPECIFICATION_FOR_PET =
            new RequestSpecBuilder()
                    .setBaseUri("https://petstore.swagger.io/v2")
                    .setBasePath("/pet")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

    @Test
    public void createPet() {
        List listTags = new ArrayList<Tag>();
        listTags.add(Tag.builder().id(0).name("string").build());
        List listPhoto = new ArrayList<PhotoUrls>();
        listPhoto.add(PhotoUrls.builder().name("string").build());

        Pet rq = Pet.builder()
                .name("doggie")
                .id(10)
                .createCategory(Category.builder().id(0).name("string").build())
                .tags(listTags)
                .status(PetStatus.AVAILABLE)
                .build();

        Pet rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_PET)
                .body(rq)
                .when().post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(Pet.class);

        assertNotNull(rs);
        assertEquals(rq.getName(), rs.getName());
    }

    @Test
    public void updatePet() {
        List listTags = new ArrayList<Tag>();
        listTags.add(Tag.builder().id(0).name("string").build());
        List listPhoto = new ArrayList<PhotoUrls>();
        listPhoto.add(PhotoUrls.builder().name("string").build());

        Pet rq = Pet.builder()
                .name("loggie")
                .id(10)
                .createCategory(Category.builder().id(0).name("string").build())
                .tags(listTags)
                .status(PetStatus.AVAILABLE)
                .build();

        Pet rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_PET)
                .body(rq)
                .when().put()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(Pet.class);

        assertNotNull(rs);
        assertEquals(rq.getName(), rs.getName());
    }

    @Test
    public void findByStatusPet() {
        List<Pet> pets =
                given()
                        .spec(REQUEST_SPECIFICATION_FOR_PET)
                        .basePath("/pet/findByStatus")
                        .queryParam(String.valueOf(PetStatus.SOLD))
                        .when().get()
                        .then()
                        .assertThat().statusCode(200)
                        .extract().jsonPath().get("name");

        assertNotNull(pets);
    }

    @Test
    public void uploadImageForPet() {

        ApiResponse rs = given()
                .spec(REQUEST_SPECIFICATION_FOR_PET)
                .contentType(ContentType.MULTIPART)
                .multiPart(new File("R.jpg"))
                .accept("application/json")
                .basePath("/pet/90369791")
                .when().post("/uploadImage")
                .then()
                .assertThat().statusCode(200)
                .extract().as(ApiResponse.class);

        Assert.assertTrue(rs.getMessage().contains("File uploaded to ./R.jpg"));
    }
}

