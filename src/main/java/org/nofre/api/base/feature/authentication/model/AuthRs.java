package org.nofre.api.base.feature.authentication.model;

import java.util.Set;

public record AuthRs(String token, Set<String> authorities) {

}
