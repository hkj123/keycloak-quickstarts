package org.keycloak.quickstart.springboot.web;

        import org.keycloak.admin.client.Keycloak;

public class KeycloakToken {
    public static void main(String[] args) {
        String url = "http://localhost:8080/auth/";
        String realm = "spring-boot-quickstart";
        String userName = "alice";
        String password = "password";
        String clientId = "app-authz-springboot";
        String clientSecret = "46cf15ff-48ca-429c-926d-0707fdf62cb0";
        Keycloak keycloak = Keycloak.getInstance(url, realm, userName, password, clientId, clientSecret);
        System.out.println("**********"+keycloak.tokenManager().getAccessTokenString());
    }
}
