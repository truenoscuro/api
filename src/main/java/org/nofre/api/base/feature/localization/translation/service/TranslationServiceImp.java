package org.nofre.api.base.feature.localization.translation.service;

import org.nofre.api.base.common.crud.entity.specification.GenericSpecification;
import org.nofre.api.base.common.crud.service.CommonCrudServiceImp;
import org.nofre.api.base.common.crud.service.CrudService;
import org.nofre.api.base.feature.localization.label.entity.LabelEntity;
import org.nofre.api.base.feature.localization.labelgroup.entity.LabelGroupEntity;
import org.nofre.api.base.feature.localization.language.LanguageService;
import org.nofre.api.base.feature.localization.language.exception.UnExistLanguageCode;
import org.nofre.api.base.feature.localization.translation.TranslationMapper;
import org.nofre.api.base.feature.localization.translation.TranslationService;
import org.nofre.api.base.feature.localization.translation.entity.TranslationEntity;
import org.nofre.api.base.feature.localization.translation.entity.TranslationRepository;
import org.nofre.api.base.feature.localization.translation.model.TranslationDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrudService
public class TranslationServiceImp extends CommonCrudServiceImp<
        TranslationEntity,
        TranslationDto,
        TranslationMapper,
        TranslationRepository
        > implements TranslationService {

    private final LanguageService languageService;

    public TranslationServiceImp(TranslationRepository repository,
                                 TranslationMapper mapper,
                                 GenericSpecification<TranslationEntity> specification, LanguageService languageService) {
        super("translations", repository, mapper, specification);
        this.languageService = languageService;
    }

    /**
     * Recupera un mapa de traducciones para el idioma especificado.
     * Cada clave en el mapa devuelto es una combinación del nombre
     * del grupo de etiquetas y el nombre de la etiqueta en minúsculas
     * unidos por un punto, y cada valor es la traducción correspondiente.
     *
     * @param language el código de idioma para el que se recuperan las traducciones
     * @return un mapa donde las claves son los nombres de grupo y etiquetas combinados,
     * y los valores son las cadenas de traducción para el idioma dado
     */
    @Override
    @Cacheable("translations")
    public Map<String, String> getTranslationsByLang(String language) throws UnExistLanguageCode {
        if (!languageService.existsByCode(language)) {
            throw new UnExistLanguageCode(language);
        }

        List<TranslationEntity> translations = repository.findAllByLanguage_Code(language);
        Map<String, String> map = new HashMap<>();
        for (TranslationEntity translation : translations) {
            LabelEntity label = translation.getLabel();
            LabelGroupEntity labelGroup = label.getLabelGroup();
            String key = (labelGroup.getName() + "." + label.getName()).toLowerCase();
            map.put(key, translation.getValue());
        }
        return map;
    }


}
