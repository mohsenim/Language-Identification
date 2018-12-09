import langIdent.LanguageIdentModelManager;
import langIdent.LanguageIdentifier;
import langIdent.LanguagesIdentModel;

/**
 * Created by Mahdi on 7/13/2016.
 */
public class Process {

    public static void main(String[] args) {
        try {
            LanguageIdentModelManager languageIdentModelManager = new LanguageIdentModelManager();
            languageIdentModelManager.createLanguaugeIdenModel();
            LanguagesIdentModel languagesIdentModel = languageIdentModelManager.readLanguageIdentModel();
            LanguageIdentifier languageIdentifier = new LanguageIdentifier(languagesIdentModel);

            String[] texts = new String[4];
            texts[0] = "ألف ليلة وليلة هو كتاب يتضمن مجموعة من القصص";
            texts[1] = "Miranda was directly involved in the French Revolution";
            texts[2] = "Les débats et remarques sur les articles sont bienvenus";
            texts[3] = "قوم مایا پدیدآورندهٔ یکی از تمدن های بسیار پیشرفته بوده";
            for (int i = 0; i < texts.length; ++i) {
                System.out.println("Language of text " + (i + 1) + ":" + languageIdentifier.detectLanguage(texts[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
