package sample.Text_Similarity;

import java.util.HashMap;
import java.util.Map;

public class CosineSimilarity {

    // این متد دو جمله ورودی را گرفته و شباهت کسینوسی را محاسبه می‌کند
    public static double cosineSimilarity(String sentence1, String sentence2) {
        // گام 1: تبدیل جملات به بردارهای کلمه‌ها (همانند مدل Bag of Words)
        Map<String, Integer> vectorA = getWordFrequencyVector(sentence1);
        Map<String, Integer> vectorB = getWordFrequencyVector(sentence2);

        // گام 2: محاسبه حاصل‌ضرب داخلی دو بردار
        double dotProduct = 0.0;
        for (String word : vectorA.keySet()) {
            if (vectorB.containsKey(word)) {
                dotProduct += vectorA.get(word) * vectorB.get(word);
            }
        }

        // گام 3: محاسبه بزرگی هر بردار
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;
        for (int count : vectorA.values()) {
            magnitudeA += Math.pow(count, 2);
        }
        for (int count : vectorB.values()) {
            magnitudeB += Math.pow(count, 2);
        }

        magnitudeA = Math.sqrt(magnitudeA);
        magnitudeB = Math.sqrt(magnitudeB);

        // گام 4: محاسبه شباهت کسینوسی
        if (magnitudeA == 0 || magnitudeB == 0) {
            return 0.0; // اگر یکی از بردارها صفر باشد، شباهت کسینوسی 0 است
        }

        return dotProduct / (magnitudeA * magnitudeB);
    }

    // این متد تعداد دفعات تکرار هر کلمه در یک جمله را محاسبه می‌کند
    private static Map<String, Integer> getWordFrequencyVector(String sentence) {
        Map<String, Integer> wordFreqMap = new HashMap<>();
        String[] words = sentence.split("\\s+"); // تقسیم جمله به کلمات

        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // پاک‌سازی کلمات
            if (!word.isEmpty()) {
                wordFreqMap.put(word, wordFreqMap.getOrDefault(word, 0) + 1);
            }
        }
        return wordFreqMap;
    }
}
