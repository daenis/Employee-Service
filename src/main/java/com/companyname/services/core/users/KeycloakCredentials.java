package com.companyname.services.core.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class KeycloakCredentials {

    private String value;
    private boolean temporary;
}
