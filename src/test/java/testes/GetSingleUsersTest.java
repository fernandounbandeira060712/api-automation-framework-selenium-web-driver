package testes;

import bases.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_GET_SINGLE_USER;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static services.Services.getSemAutenticacao;

public class GetSingleUsersTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {
        getSemAutenticacao(ENDPOINT_GET_SINGLE_USER.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", notNullValue());
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {
        getSemAutenticacao(ENDPOINT_GET_SINGLE_USER.getEndPoint())
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("arquivos/schemas/GetSingleUsersSchema.json"));
    }
}