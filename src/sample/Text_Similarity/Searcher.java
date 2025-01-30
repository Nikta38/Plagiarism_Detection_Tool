package sample.Text_Similarity;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import static sample.Text_Similarity.CosineSimilarity.cosineSimilarity;

public class Searcher {
    public Text_Reader File;
    public String[] Similarity_with_Sentences;
    public ArrayList<String> Similarity_with_files;
    public int num_of_similar_Words;

    // Constructor to initialize the Searcher with a Text_Reader file
    public Searcher(Text_Reader file) {
        File = file;
        this.num_of_similar_Words = 0;
    }

    public Text_Reader getFile() {
        return File;
    }

    public void setFile(Text_Reader file) {
        File = file;
    }

    public String[] getSimilarity_with_Sentences() {
        return Similarity_with_Sentences;
    }

    public void setSimilarity_with_Sentences(String[] similarity_with_Sentences) {
        Similarity_with_Sentences = similarity_with_Sentences;
    }

    public int getNum_of_similar_Words() {
        return num_of_similar_Words;
    }

    public void setNum_of_similar_Words(int num_of_similar_Words) {
        this.num_of_similar_Words = num_of_similar_Words;
    }

    public ArrayList<String> getSimilarity_with_files() {
        return Similarity_with_files;
    }

    public void setSimilarity_with_files(ArrayList<String> similarity_with_files) {
        Similarity_with_files = similarity_with_files;
    }

    // Preprocess sentences by converting to lowercase and removing non-alphanumeric characters
    private List<String> preprocessSentences(List<String> sentences) {
        List<String> preprocessed = new ArrayList<>();
        for (String sentence : sentences) {
            preprocessed.add(sentence.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", ""));
        }
        return preprocessed;
    }

    // Search method to compare sentences from the provided files
    public String Search(java.io.File[] files) {
        StringBuilder res = new StringBuilder();
        double totalSimilarityPercentage = 0.0;
        int totalSentences = 0;
        Map<String, Double> fileSimilarities = new HashMap<>();

        // Preprocess the input text
        List<String> preprocessedSentences = preprocessSentences(getFile().sentences_list);

        // Process each file
        for (File file : files) {
            Text_Reader textReader = new Text_Reader(file.getPath());
            String fileName = file.getName(); // Get the file name

            // Preprocess the target file's sentences
            List<String> targetSentences = preprocessSentences(textReader.sentences_list);

            double fileSimilarityPercentage = 0.0; // Similarity percentage for each file
            int validSentences = 0; // Number of sentences with similarity
            int totalTargetSentences = targetSentences.size();

            // Compare the sentences and calculate cosine similarity
            for (String sentence : preprocessedSentences) {
                if (sentence == null) continue;

                for (String targetSentence : targetSentences) {
                    if (targetSentence == null) continue;

                    // Compute the cosine similarity between the two sentences
                    double similarity = CosineSimilarity.cosineSimilarity(sentence, targetSentence);

                    // Record only similarities greater than 0.7 (threshold)
                    if (similarity > 0.7) {
                        validSentences++;
                        fileSimilarityPercentage += similarity * 100;

                        // Display the similar sentence and its similarity percentage
                        res.append(sentence).append("\nwith ")
                                .append(String.format("%.2f", similarity * 100))
                                .append("% similarity found in file ").append(fileName).append("\n");
                    }
                }
            }

            // Calculate the overall similarity for the file
            double fileOverallSimilarity = 0.0;
            if (totalTargetSentences > 0) {
                fileOverallSimilarity = (validSentences * 100.0) / totalTargetSentences;
            }
            fileSimilarities.put(fileName, fileOverallSimilarity);
        }

        // Display similarity percentage for each file
        res.append("_____________________________________________________________________________________________________________________________\n");
        for (Map.Entry<String, Double> entry : fileSimilarities.entrySet()) {
            // Ensure the similarity percentage is greater than zero before displaying
            if (entry.getValue() > 0) {
                res.append("percentage of similarity to file ").append(entry.getKey())
                        .append(" is ").append(String.format("%.2f", entry.getValue())).append("%\n");
            }
        }
        res.append("_____________________________________________________________________________________________________________________________\n");

        // Calculate overall similarity across all files
        if (!fileSimilarities.isEmpty()) {
            double overallSimilarity = 0.0;
            for (Map.Entry<String, Double> entry : fileSimilarities.entrySet()) {
                overallSimilarity += entry.getValue();
            }
            overallSimilarity = overallSimilarity / fileSimilarities.size();
            res.append("Overall similarity percentage across all files is ").append(String.format("%.2f", overallSimilarity)).append("%\n");
        }

        return res.toString();
    }

    // Convert sentence to a feature vector (Example using Bag of Words or TF-IDF)
    private double[] convertToVector(String sentence) {
        String[] words = sentence.split(" ");
        double[] vector = new double[words.length];
        for (int i = 0; i < words.length; i++) {
            vector[i] = 1;  // Assuming each word is a feature
        }
        return vector;
    }

    // Calculate the cosine similarity between two vectors
    public double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;

        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            magnitudeA += Math.pow(vectorA[i], 2);
            magnitudeB += Math.pow(vectorB[i], 2);
        }

        magnitudeA = Math.sqrt(magnitudeA);
        magnitudeB = Math.sqrt(magnitudeB);

        if (magnitudeA == 0 || magnitudeB == 0) return 0.0;

        return dotProduct / (magnitudeA * magnitudeB);
    }

    // Check word similarity between a sentence and a list of source sentences
    public static int Check_word_similarity(ArrayList<String> source, String sentence) {
        String[] s2 = sentence.split(" ");
        int max1 = 0;

        for (int i = 0; i < source.size(); i++) {
            String[] s1 = source.get(i).split(" ");
            boolean flag = true;
            int s_words = 0;
            Set<String> set = new HashSet<>(Arrays.asList(s1));
            for (int t = 0; t < s2.length; t++) {
                flag = set.add(s2[t]);
                if (flag == false) {
                    s_words++;
                }
            }
            if (max1 < s_words) {
                max1 = s_words;
            }
        }
        return max1;
    }

    // Find the sentences that match a specific file
    public static ArrayList<Integer> similarity_with_file(String[] similar_sentences, String file_name) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < similar_sentences.length; i++) {
            String[] temp = similar_sentences[i].split("find in file ");
            String name = temp[temp.length - 1].trim();
            if (name.equals(file_name)) {
                res.add(i);
            }
        }
        return res;
    }
}
