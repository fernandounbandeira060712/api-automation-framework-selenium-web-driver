package testes;

import bases.BaseTest;
import org.testng.annotations.Test;

import static enums.EndPointEnum.ENDPOINT_PATCH_UPDATE;
import static enums.UsuarioEnum.USUARIO_VALIDO_UPDATE;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static services.Services.patchComLoginNoBody;
import static utils.Common.requestBodyNameJob;

public class PatchUpdateTest extends BaseTest {

    @Test(groups = {"regressivo"})
    public void validarStatusCode200EResponseBody() {

        patchComLoginNoBody(
                ENDPOINT_PATCH_UPDATE.getEndPoint(),
                requestBodyNameJob(
                        USUARIO_VALIDO_UPDATE.getName(),
                        USUARIO_VALIDO_UPDATE.getJob()))
                .statusCode(SC_OK)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt",
                        matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
    }

    @Test(groups = {"contrato"})
    public void validarSchema() {

        patchComLoginNoBody(
                ENDPOINT_PATCH_UPDATE.getEndPoint(),
                requestBodyNameJob(
                        USUARIO_VALIDO_UPDATE.getName(),
                        USUARIO_VALIDO_UPDATE.getJob()))
                .statusCode(SC_OK)
                .body(matchesJsonSchemaInClasspath(
                        "arquivos/schemas/PatchUpdateSchema.json"));
    }
}