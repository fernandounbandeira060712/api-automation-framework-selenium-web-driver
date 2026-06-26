package utils;

import io.qameta.allure.Allure;

public final class AllureUtils {

    private AllureUtils() {
    }

    public static void step(String description) {
        Allure.step(description);
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }

    public static void attachText(String name, String text) {
        Allure.addAttachment(name, "text/plain", text);
    }

    public static void attachRequest(String request) {
        attachJson("Request", request);
    }

    public static void attachResponse(String response) {
        attachJson("Response", response);
    }
}