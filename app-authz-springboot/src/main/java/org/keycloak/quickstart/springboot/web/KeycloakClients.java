/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.keycloak.quickstart.springboot.web;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.authorization.PolicyEnforcementMode;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.keycloak.representations.idm.authorization.ResourceServerRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Controller
public class KeycloakClients {

    public static void main(String[] args) {
        // get token
        Keycloak keycloak = token();
        //get clients
//        get(keycloak);
        //add clients
        add(keycloak);
    }

    public static Keycloak token() {
        String url = "http://localhost:8080/auth/";
        String realm = "spring-boot-quickstart";
        String userName = "alice";
        String password = "password";
        String clientId = "app-authz-springboot";
        String clientSecret = "46cf15ff-48ca-429c-926d-0707fdf62cb0";
        Keycloak keycloak = Keycloak.getInstance(url, realm, userName, password, clientId, clientSecret);
        return keycloak;
    }

    public static void add(Keycloak keycloak) {
        ClientRepresentation clientRepresentation = new ClientRepresentation();
        //basic information
        clientRepresentation.setClientId("test-client");
        clientRepresentation.setEnabled(false);
        clientRepresentation.setBaseUrl("http://localhost:8180");
        clientRepresentation.setAdminUrl("http://localhost:8180");
        clientRepresentation.setBearerOnly(false);
        List<String> redirectUri = new ArrayList<String>();
        redirectUri.add("http://localhost:8180/*");
        clientRepresentation.setRedirectUris(redirectUri);
        clientRepresentation.setSecret("secret");
        clientRepresentation.setAuthorizationServicesEnabled(false);
        ResourceServerRepresentation resourceServerRepresentation = new ResourceServerRepresentation();
        resourceServerRepresentation.setAllowRemoteResourceManagement(false);
        resourceServerRepresentation.setPolicyEnforcementMode(PolicyEnforcementMode.ENFORCING);
        //resources
        List<ResourceRepresentation> resources = new ArrayList<ResourceRepresentation>();
        ResourceRepresentation resourceRepresentation = new ResourceRepresentation();
        resourceRepresentation.setName("Test Resource");
        resourceRepresentation.setOwnerManagedAccess(false);
        Set<String> set = new HashSet<String>();
        set.add("/protected/login");
        resourceRepresentation.setUris(set);
        resources.add(resourceRepresentation);
        clientRepresentation.setAuthorizationSettings(resourceServerRepresentation);
        Response response = keycloak.realm("spring-boot-quickstart").clients()
                .create(clientRepresentation);
        System.out.println("****" + response.getStatus());
    }

    public static void get(Keycloak keycloak) {
        ClientRepresentation clientRepresentation = keycloak.realm("spring-boot-quickstart").clients()
                .get("a79df12b-6aa1-46de-9d28-65813475cfd3").toRepresentation();
        System.out.println("****" + clientRepresentation.getClientId());
    }

    public static void search(Keycloak keycloak) {
        //find user
        List<UserRepresentation> userRepresentationList = keycloak.realm("spring-boot-quickstart").users().search("alice");
        System.out.println("****" + userRepresentationList.get(0).getUsername());
    }
}
