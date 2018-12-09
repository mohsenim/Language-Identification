package langIdent;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Gets a language identification model and based on that, detects language of a text documents
 * Created by Mahdi on 7/13/2016.
 */
public class LanguageIdentifier {

    private LanguagesIdentModel languagesIdentModel;
    private int max_ngram_length;


    public LanguageIdentifier(LanguagesIdentModel languagesIdentModel, Properties properties) {
        this.max_ngram_length = Integer.parseInt(properties.getProperty("max_ngram_length"));
        this.languagesIdentModel = languagesIdentModel;
    }

    public LanguageIdentifier(LanguagesIdentModel languagesIdentModel) throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties.prop"));
        this.max_ngram_length = Integer.parseInt(properties.getProperty("max_ngram_length"));
        this.languagesIdentModel = languagesIdentModel;
    }

    /***
     * Detects the language of a text.
     *
     * @param text
     * @return
     */
    public String detectLanguage(String text) {
        NgramProcess ngramProcess = new NgramProcess();
        Map<String, Integer> text_ngram = ngramProcess.getNgram(text, max_ngram_length);
        double max_score = -Double.MAX_VALUE;
        String lang = "";
        for (LanguageModel languageModel : languagesIdentModel.getLanguageModels()) {
            double score = computeScore(text_ngram, languageModel, languagesIdentModel.getAll_ngrams(), languagesIdentModel.getLanguageModels().size());
            if (max_score < score) {
                max_score = score;
                lang = languageModel.getName();
            }
        }
        return lang;
    }

    /***
     * Computes p(languageModel | text) using Naive Bayes. Prior probabilities are supposed to be uniform.
     * For smoothing, the Laplace method is used.
     *
     * @param text_ngram
     * @param languageModel
     * @param all_ngrams
     * @param no_languages
     * @return
     */
    private double computeScore(Map<String, Integer> text_ngram, LanguageModel languageModel, Map<String, Integer> all_ngrams, int no_languages) {
        double score = 0;
        Iterator<String> it = text_ngram.keySet().iterator();
        while (it.hasNext()) {
            String ngram = it.next();
            if (all_ngrams.containsKey(ngram)) {
                int freq = text_ngram.get(ngram);
                int total_freq = all_ngrams.get(ngram);
                int lang_freq = 0;
                if (languageModel.getNgrams().containsKey(ngram)) {
                    lang_freq = languageModel.getNgrams().get(ngram);
                }
                score += freq * Math.log((lang_freq + 1.0) / (total_freq + no_languages));
            }
        }
        return score;
    }
}
