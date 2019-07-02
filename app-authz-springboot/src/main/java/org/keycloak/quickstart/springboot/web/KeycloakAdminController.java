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
import io.swagger.annotations.ApiOperation;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@Controller
@RequestMapping("")
@Api("KeycloakAdminController")
public class KeycloakAdminController {

    private static final Logger log = LoggerFactory.getLogger(KeycloakAdminController.class);
    private static final String serverUrl = "http://localhost:8080/auth";
//    @RequestMapping(value = "/example", method = RequestMethod.GET)
//    public String example(Model model) throws Exception {
//        Keycloak keycloak = Keycloak.getInstance(
//                serverUrl,
//                "master",
//                "admin",
//                "admin" ,
//                "admin-cli");
//        RealmRepresentation realm = keycloak.realm("master").toRepresentation();
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setFirstName("test");
//        userRepresentation.setUsername("test");
//        try(true) {
//            keycloak.realm("master").users().create(userRepresentation);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "success";
//    }
//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "登录")
    public AccessTokenResponse getToken(@RequestBody Map<String, Object> credentials) {
        Keycloak kc = Keycloak.getInstance(serverUrl,
                (String)  credentials.get("company"),
                (String) credentials.get("username"),
                (String) credentials.get("password"),
                (String) credentials.get("clientId"));
        int a = 1;
        log.info("ccccccccccccccc");
        AccessTokenResponse accessToken = kc.tokenManager().getAccessToken();
        return accessToken;
    }
}
