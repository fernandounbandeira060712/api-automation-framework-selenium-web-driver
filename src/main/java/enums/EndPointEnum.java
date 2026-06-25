package enums;

import utils.ConfigurationManager;

public enum EndPointEnum {

    // Classic API - testes antigos
    ENDPOINT_GET_LIST_USERS("/users?page=2"),
    ENDPOINT_GET_SINGLE_USER("/users/2"),
    ENDPOINT_GET_SINGLE_USER_NOT_FOUND("/users/23"),

    ENDPOINT_GET_LIST_RESOURCE("/unknown"),
    ENDPOINT_GET_SINGLE_RESOURCE("/unknown/2"),
    ENDPOINT_GET_SINGLE_RESOURCE_NOT_FOUND("/unknown/23"),

    ENDPOINT_POST_CREATE("/users"),
    ENDPOINT_PUT_UPDATE("/users/2"),
    ENDPOINT_PATCH_UPDATE("/users/2"),
    ENDPOINT_DELETE("/users/2"),

    ENDPOINT_POST_REGISTER("/register"),
    ENDPOINT_POST_LOGIN("/login"),

    ENDPOINT_GET_DELAYED_RESPONSE("/users?page=2"),

    // Collections API - Products
    ENDPOINT_GET_PRODUCTS("/collections/products/records?project_id=" + ConfigurationManager.get("project.id")),
    ENDPOINT_GET_PRODUCT_BY_ID("/collections/products/records/{id}?project_id=" + ConfigurationManager.get("project.id")),
    ENDPOINT_POST_PRODUCT("/collections/products/records?project_id=" + ConfigurationManager.get("project.id")),
    ENDPOINT_PUT_PRODUCT("/collections/products/records/{id}?project_id=" + ConfigurationManager.get("project.id")),
    ENDPOINT_PATCH_PRODUCT("/collections/products/records/{id}?project_id=" + ConfigurationManager.get("project.id")),
    ENDPOINT_DELETE_PRODUCT("/collections/products/records/{id}?project_id=" + ConfigurationManager.get("project.id"));

    private final String endPoint;

    EndPointEnum(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}