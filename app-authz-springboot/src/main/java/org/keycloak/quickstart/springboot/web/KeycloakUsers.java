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

import io.swagger.annotations.Api;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Controller
@RequestMapping("")
@Api("KeycloakAdminController")
public class KeycloakUsers {

    private static final Logger log = LoggerFactory.getLogger(KeycloakUsers.class);
    private static final String serverUrl = "http://localhost:8080/auth";

    public static void main(String[] args) {
        // get token
        Keycloak keycloak = token();
        //add user
//        add(keycloak);
        // find user
        search(keycloak);
    }
    public static Keycloak token() {
        String url = "http://localhost:8080/auth/";
        String realm = "spring-boot-quickstart";
        String userName = "alice";
        String password = "password";
        String clientId = "app-authz-springboot";
        String clientSecret ="46cf15ff-48ca-429c-926d-0707fdf62cb0";
        Keycloak keycloak = Keycloak.getInstance(url, realm, userName, password, clientId,clientSecret);
        return keycloak;
    }
    public static void add(Keycloak keycloak) {
        UserRepresentation user = new UserRepresentation();
        // Set up account information
        user.setUsername("edwing");
        user.setFirstName("hu");
        user.setLastName("kj");
        user.setEnabled(true);
        // Set up password
        List<CredentialRepresentation> credentials = new ArrayList<CredentialRepresentation>();
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setType(CredentialRepresentation.PASSWORD);
        cr.setValue("123456");
        cr.setTemporary(false);
        credentials.add(cr);
        user.setCredentials(credentials);

        Response response = keycloak.realm("spring-boot-quickstart").users().create(user);
        int status = response.getStatus();
        System.out.println("****"+status);
    }
    public static void search(Keycloak keycloak) {
        //find user
        List<UserRepresentation>  userRepresentationList= keycloak.realm("spring-boot-quickstart").users().search("alice");
        System.out.println("****"+userRepresentationList.get(0).getUsername());
    }
}
