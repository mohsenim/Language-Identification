package langIdent;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Creates language identification model to files and reads it from files
 * Created by Mahdi on 7/13/2016.
 */
public class LanguageIdentModelManager {
    private Properties properties;

    public LanguageIdentModelManager() throws IOException {
        properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("properties.prop"));
    }

    public LanguageIdentModelManager(Properties properties) {
        this.properties = properties;
    }

    public void createLanguaugeIdenModel() {
        int max_ngram = Integer.parseInt(properties.getProperty("max_ngram_length"));
        File lang_models_path = new File(properties.getProperty("lang_models_path"));
        File input = new File(properties.getProperty("input"));
        File[] files = input.listFiles();
        NgramProcess ngramProcess = new NgramProcess();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isFile()) {
                String lang_name = files[i].getName();
                Map<String, Integer> ngrams = ngramProcess.getNgram(IO.readLinesFromFile(files[i].getPath()), max_ngram);
                String[] ngram_freq = new String[ngrams.size()];
                int j = 0;
                Iterator<String> it = ngrams.keySet().iterator();
                while (it.hasNext()) {
                    String ngram = it.next();
                    int freq = ngrams.get(ngram);
                    ngram_freq[j++] = ngram + "\t" + freq;
                }
                IO.writeLinesToFile(lang_models_path.getPath() + "\\" + lang_name, ngram_freq);
            }
        }
    }

    public LanguagesIdentModel readLanguageIdentModel() {
        LanguagesIdentModel languagesIdentModel = new LanguagesIdentModel();
        File lang_model_path = new File(properties.getProperty("lang_models_path"));
        File[] files = lang_model_path.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isFile()) {
                String[] lines = IO.readLinesFromFile(files[i].getPath());
                LanguageModel languageModel = new LanguageModel(files[i].getName());
                for (String line : lines) {
                    String[] lineSplit = line.split("\t");
                    String ngram = lineSplit[0];
                    int freq = Integer.parseInt(lineSplit[1]);
                    languageModel.getNgrams().put(lineSplit[0], freq);
                    if (languagesIdentModel.getAll_ngrams().containsKey(ngram)) {
                        freq += languagesIdentModel.getAll_ngrams().get(ngram);
                    }
                    languagesIdentModel.getAll_ngrams().put(ngram, freq);
                }
                languagesIdentModel.getLanguageModels().add(languageModel);
            }
        }
        return languagesIdentModel;

    }

    public static void main(String[] args) {
        try {
            LanguageIdentModelManager languageIdentModelManager = new LanguageIdentModelManager();
            languageIdentModelManager.createLanguaugeIdenModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
