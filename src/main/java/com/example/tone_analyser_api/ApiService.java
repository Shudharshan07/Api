package com.example.tone_analyser_api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class Main {
    private static Map<String, Integer> dataset = new HashMap<>();
    static
    {
        try{
            BufferedReader file = new BufferedReader(new FileReader("C:\\All Files\\Tone_Analyser\\tone_analyser_api\\src\\main\\java\\com\\example\\tone_analyser_api\\sentiment.txt"));
            String line;
            while ((line = file.readLine()) != null)
            {
                String[] words = line.split("\\s*:\\s*");
                dataset.put(words[0],Integer.parseInt(words[1]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String[] stopwords_array = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "nor", "only", "own", "same", "so",
            "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};



    private static Character[] punctution_array = {'.', ',', '!', '?', ';', ':', '\'', '"', '(', ')', '{', '}', '[', ']',
            '<', '>', '-', '_', '/', '\\', '|', '~', '+', '*', '=', '%', '&', '@',
            '#', '$', '€', '£', '¥', '^', '`', '¨', '©', '®', '™', '¶', '§', '∆','’'};
    private static HashSet<String> StopWords = new HashSet<>(Arrays.asList(stopwords_array));
    private static HashSet<Character> punctutions = new HashSet<>(Arrays.asList(punctution_array));

    public static int[] FindSentiment(String input)
    {
        int[] Sentiment = {0,0,0};
        input += " ";
        int len = input.length();
        String words = "";
        for(int i = 0; i < len; i++)
        {
            if(punctutions.contains(input.charAt(i)))
            {
                continue;
            }
            if(input.charAt(i) == ' ')
            {
                words = words.toLowerCase();
                if(!StopWords.contains(words) && words != "" && dataset.containsKey(words))
                {
                    Sentiment[dataset.get(words)]++;
                }
                words = "";
                continue;
            }
            words += input.charAt(i);
        }

        return Sentiment;
    }
}
