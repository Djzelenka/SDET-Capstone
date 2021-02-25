package mvctodo.tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


import org.junit.After;
import org.junit.Test;
import testObject.Account;
import testObject.Todo;

public class RESTAssuredTodos extends FunctionalTest {

  @Test
  public void postSignup() {
    Account account = new Account();
    account.setEmail("email@test.com");
    account.setPassword("password");

    given().contentType("application/json").body(account).when().post("/signup").then()
        .body("email", equalTo("email@test.com"))
        .statusCode(201);
  }

  @Test
  public void postSignupEmailInUse() {
    Account account = new Account();
    account.setEmail("email@test.com");
    account.setPassword("password");

    given().contentType("application/json").body(account).when().post("/signup");

    given().contentType("application/json").body(account).when().post("/signup").then()
        .statusCode(409);
  }

  @Test
  public void postSignupNoEmail() {
    Account account = new Account();
    account.setPassword("password");

    given().contentType("application/json").body(account).when().post("/signup").then()
        .statusCode(401);
  }

  @Test
  public void postSignupNoPassword() {
    Account account = new Account();
    account.setEmail("email@test.com");

    given().contentType("application/json").body(account).when().post("/signup").then()
        .statusCode(401);
  }


  @Test
  public void postLogin() {
    Account account = new Account();
    account.setEmail("email@test.com");
    account.setPassword("password");

    given().contentType("application/json").body(account).when().post("/signup");

    given().contentType("application/json").body(account).when().post("/login")
        .then()
        .body("message", equalTo("User is logged in"))
        .body("token", equalTo("true"))
        .statusCode(200);
  }

  @Test
  public void getTodos() {
    given().when().get("/todos").then().statusCode(200);
  }

  @Test
  public void getTodosWrongURI() {
    given().when().get("/todoos").then().statusCode(404);
  }

  @Test
  public void postNewTodo() {
    Todo todo = new Todo();
    todo.setTitle("Finish tests");
    todo.setCompleted(false);
    todo.setId(1);

    given()
        .contentType("application/json")
        .body(todo)
        .when().post("/todos").then()
        .body("completed", equalTo(false))
        .body("title", equalTo("Finish tests"));

  }

  @Test
  public void deleteTodo() {
    Todo todo = new Todo();
    todo.setTitle("Write the test");
    todo.setCompleted(false);
    todo.setId(5);

    Integer todoId = given()
        .contentType("application/json")
        .body(todo)
        .when().post("/todos").then()
        .body("title", equalTo("Write the test"))
        .extract().path("id");

    given().pathParam("id", todoId)
        .when().delete("/todos/{id}").then()
        .statusCode(200);
  }

  @Test
  public void deleteAllTodos() {
    given().when().delete("/todos").then()
        .statusCode(204);
  }

  @After
  public void close() {
    given().when().post("/reset");
  }
}
