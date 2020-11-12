import io.restassured.RestAssured;
import org.junit.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class BaseTest {
    @Test
    public void test_getAllPosts() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200);
    }

    @Test
    public void test_getPost() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("body", equalTo("quia et suscipit\n" +
                        "suscipit recusandae consequuntur expedita et cum\n" +
                        "reprehenderit molestiae ut ut quas totam\n" +
                        "nostrum rerum est autem sunt rem eveniet architecto"));
    }

    @Test
    public void test_getAllComments() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/posts/1/comments").
                then().
                statusCode(200);
    }

    @Test
    public void test_createNewPost() {
        Post post = new Post();
        post.setUserId(9);
        post.setId(101);
        post.setTitle("ewqeqwe");
        post.setBody("wqeqew");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("userId", equalTo(9))
                .body("id", equalTo(101))
                .body("title", equalTo("ewqeqwe"))
                .body("body", equalTo("wqeqew"));
    }

    @Test
    public void test_createPostWithoutUserId() {
        Post post = new Post();
        post.setId(101);
        post.setTitle("ewqeqwe");
        post.setBody("wqeqew");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("userId", equalTo(null))
                .body("id", equalTo(101))
                .body("title", equalTo("ewqeqwe"))
                .body("body", equalTo("wqeqew"));
    }

    @Test
    public void test_createPostWithoutId() {
        Post post = new Post();
        post.setUserId(9);
        post.setTitle("ewqeqwe");
        post.setBody("wqeqew");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("userId", equalTo(9))
                .body("id", equalTo(101))
                .body("title", equalTo("ewqeqwe"))
                .body("body", equalTo("wqeqew"));
    }

    @Test
    public void test_createPostWithoutTitle() {
        Post post = new Post();
        post.setUserId(9);
        post.setId(101);
        post.setBody("wqeqew");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("userId", equalTo(9))
                .body("id", equalTo(101))
                .body("title", equalTo(null))
                .body("body", equalTo("wqeqew"));
    }

    @Test
    public void test_createPostWithoutBody() {
        Post post = new Post();
        post.setUserId(9);
        post.setId(101);
        post.setTitle("ewqeqwe");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("userId", equalTo(9))
                .body("id", equalTo(101))
                .body("title", equalTo("ewqeqwe"))
                .body("body", equalTo(null));
    }

    @Test
    public void test_changePost() {
        Post post = new Post();
        post.setUserId(2);
        post.setId(1);
        post.setTitle("qwe");
        post.setBody("ewq");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("userId", equalTo(2))
                .body("id", equalTo(1))
                .body("title", equalTo("qwe"))
                .body("body", equalTo("ewq"));
    }


    @Test
    public void test_changePostParameter() {
        Post post = new Post();
        post.setTitle("pio");

        given()
                .contentType("application/json")
                .body(post)
                .when()
                .patch("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo("pio"));
    }

    @Test
    public void test_getPostWithUserId() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/posts?userId=1").
                then().
                statusCode(200)
                .log().body()
                .body("userId", hasItems(1));
    }

    @Test
    public void test_getPostWithId() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/posts?id=1").
                then().
                statusCode(200)
                .log().body()
                .body("id", hasItem(1));
    }

    @Test
    public void test_getPostWithTitle() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/posts?title=sunt aut facere repellat provident occaecati excepturi optio reprehenderit").
                then().
                statusCode(200)
                .log().body()
                .body("title", hasItem("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }


    @Test
    public void test_getPhotos() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/albums/1/photos").
                then().
                statusCode(200);
    }

    @Test
    public void test_getAlbums() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/users/1/albums").
                then().
                statusCode(200);
    }

    @Test
    public void test_getTodos() {
        given()
                .when().get("https://jsonplaceholder.typicode.com/users/1/todos").
                then().
                statusCode(200);
    }

    @Test
    public void test_getPostJsonSchema() {
        RestAssured.given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("JsonSchemaFileGetPost.json"));

    }

    @Test
    public void test_getPostWithIdJsonSchema() {
        RestAssured.given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts?id=1")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("JsonSchemaFileGetPostWithId.json"));

    }

}




  /*  @Test
    public void test_(Integer userId, Integer id, String name, String body ) {
        given()
                .contentType("application/json")
                .body(userId, id, name, body)
                .when()
                .put("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("userId", equalTo(2))
                .body("id", equalTo(1))
                .body("title", equalTo("qwe"))
                .body("body", equalTo("ewq"));
    }





   @Test
   public void test_() {
        Response res =

            String jsonString = res.asString();
   Assert.assertEquals(matchesJsonSchema, true);

  }


   */


