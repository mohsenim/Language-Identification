package langIdent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all language models and the statistics of ngrams in all languages
 * Created by Mahdi on 7/13/2016.
 */
public class LanguagesIdentModel {
    private List<LanguageModel> languageModels;
    private Map<String, Integer> all_ngrams;

    public LanguagesIdentModel() {
        languageModels = new ArrayList<LanguageModel>();
        all_ngrams = new HashMap<String, Integer>();
    }

    public List<LanguageModel> getLanguageModels() {
        return languageModels;
    }

    public Map<String, Integer> getAll_ngrams() {
        return all_ngrams;
    }
}
