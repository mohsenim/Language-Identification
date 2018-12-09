## Language Identification using Character N-grams

Applied for a visiting position, I was asked to implement a method for language
identification of a document. Though simple, it works well. So, I put it here.

My approach for identifying the language of a text is to utilize character
n-grams. Fortunately, it is easy to collect training data for this task. For each
language we only need a number of documents. Once we have training texts
we can use them and extract features. In my method, features are character
n-grams. N-grams are extracted for each language and their frequencies are
calculated. There are several machine learning methods which can be applied
for language identification. I exploit Näive Bayes, a simple yet very effective
method. Näive Bayes uses the probability of n-grams to identify the language
of a new text.

### Model of Languages

For language identification a model of languages should be created. In the
code, a list of text files, each containing training texts for a language, is read at the begining.
The name of the files indicates languages. To add a language, one can simply
add another text file and put some texts in it. These files are used to create the
language identification model. For each language, a list of character n-grams
and their frequencies are extracted and saved into a file. The default maximum
length of n-grams is set to 3.

### Langauge Identification

In the language identification phase, files containing character n-grams and
their frequencies are read into a Map. As the total frequency of n-grams are
required to calculate probabilities, the total frequencies are also saved into a
Map. To identify the language of an input text, first it is converted to a list
of n-grams. Afterwards, p(Language|T ext) is computed using Näive Bayes.
The prior probabilities of languages are set equal to each other as there is no
information about the domain the code may be used in. I also apply Laplace
method to smooth p(n-gram|Language). The language with the maximum
probability p(Language|T ext) is considered as the language of the given text.

### Parameters

Parameters of the code are the maximum length of n-grams, the path to
the language text files, and the path to the language n-grams files. The default
value of the parameters is set in a property file called properties.prop. The
test code performs on four languages: English, French, Persian and Arabic.

### Licence
MIT

