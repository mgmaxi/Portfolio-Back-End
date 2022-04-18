package com.example.portfolio.service;

import com.example.portfolio.entity.Language;
import java.util.List;

public interface ILanguageService {

    public List<Language> getLanguages();
    
    public Language findByLanguageId(Long language_id);
    
    public List<Language> findByPersonId(Long person_id);

    public Language createLanguage(Language language);

    public Language updateLanguage(Long language_id, Language language);

    public void deleteLanguage(Long language_id);

}
