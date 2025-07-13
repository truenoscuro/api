package org.nofre.api.base.feature.frontendresource;

import jakarta.annotation.security.PermitAll;
import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.frontendresource.model.FrontendResourceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@ApiRestController("frontend-resources")
public class FrontendResourceController extends CommonCrudControllerImp<FrontendResourceDto, FrontendResourceService> {

    public FrontendResourceController(FrontendResourceService service) {
        super(service, "FRONTEND_RESOURCE");
    }


    @PermitAll
    @GetMapping("me")
    public ResponseEntity<CommonRs<List<FrontendResourceDto>>> findAllByMe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getResponse(service.findAllByMe(email));
    }


}
