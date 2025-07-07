package org.nofre.api.base.feature.localization.translation;

import org.nofre.api.base.common.controller.ApiRestController;
import org.nofre.api.base.common.controller.model.CommonRs;
import org.nofre.api.base.common.crud.controller.CommonCrudControllerImp;
import org.nofre.api.base.feature.localization.language.exception.UnExistLanguageCode;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@ApiRestController("translations")
public class TranslationController extends CommonCrudControllerImp<TranslationDto, TranslationService> {
    public TranslationController(TranslationService service) {
        super(service, "TRANS");
    }


    /**
     * Recupera las traducciones para un idioma específico.
     *
     * @param lang el código del idioma para el cual se deben recuperar las traducciones
     * @return un {@code ResponseEntity} que contiene un {@code CommonRs<Map<String, String>>}, donde las claves y valores
     * del mapa representan las claves de traducción y sus valores correspondientes en el idioma especificado
     */
    @GetMapping("lang/{lang}")
    public ResponseEntity<CommonRs<Map<String, String>>> getTranslationsByLang(@PathVariable String lang) throws UnExistLanguageCode {
        return postResponse(service.getTranslationsByLang(lang));
    }

}
