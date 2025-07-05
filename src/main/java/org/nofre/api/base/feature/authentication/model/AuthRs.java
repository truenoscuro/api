package org.nofre.api.base.feature.authentication.model;

import java.util.List;

public record AuthRs(String token, List<String> roles) {

}
