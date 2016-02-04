package textgen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

// The list of words with their next words
//private List<ListNode> wordList;
private HashMap<String, ArrayList<String>> wordList;

// The starting "word"
private String starter;

// The random number generator
private Random rnGenerator;

public MarkovTextGeneratorLoL(Random generator)
{
    wordList = new HashMap<String, ArrayList<String>>();
    starter = "";
    rnGenerator = generator;
}

/** Train the generator by adding the sourceText */
@Override
public void train(String sourceText)
{
    if(sourceText == null || sourceText == "") {
        return;
    }
    String[] words = sourceText.split("[\\s]+");    
    starter = words[0];        
    
    for(int idx = 0; idx < (words.length - 1); ++idx) {
        String currWord = words[idx];
        String nextWord = words[idx+1];
        if(!wordList.containsKey(currWord)) {            
            wordList.put(currWord, new ArrayList<String>());
        }        
        wordList.get(currWord).add(nextWord);    
    }
    
    // Last word's next word should be the first word 
    // in source text
    String lastWord = words[words.length - 1];
    if(!wordList.containsKey(lastWord)) {
        wordList.put(lastWord, new ArrayList<String>());
    }
    wordList.get(lastWord).add(words[0]);
}

/** 
 * Generate the number of words requested.
 */
@Override
public String generateText(int numWords) 
{
    // Untrained
    if(starter == "" || numWords == 0) {
        return "";
    }
    StringBuffer sb = new StringBuffer(starter);
    
    String currWord = starter;
    while(numWords > 1) {
        ArrayList<String> nextWordList = wordList.get(currWord);
        String nextWord = nextWordList.get(rnGenerator.nextInt(nextWordList.size()));
        sb.append(" " + nextWord);
        --numWords;
        currWord = nextWord;        
    }    
    return sb.toString();
}

// Can be helpful for debugging
@Override
public String toString()
{
    String toReturn = "";    
    for(String key : wordList.keySet()){
        System.out.println(key  +" -> "+ wordList.get(key));
        toReturn += wordList.get(key).toString();
    }
    return toReturn;
}

/** Retrain the generator from scratch on the source text */
@Override
public void retrain(String sourceText)
{
    wordList.clear();
    starter = "";
    rnGenerator = new Random();
    train(sourceText);
}

/**
 * This is a minimal set of tests.  Note that it can be difficult
 * to test methods/classes with randomized behavior.   
 * @param args
 */
public static void main(String[] args)
{
    // feed the generator a fixed random value for repeatable behavior
    MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
    String textString = "Hello. Hello there. This is a test. Hello there. Hello Bob. Test again.";
    System.out.println(textString);
    gen.train(textString);
//    System.out.println(gen);
//    System.out.println(gen.generateText(20));
    String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
    gen.retrain(input);
    System.out.println(gen.generateText(500));
//    String textString2 = "You say yes, I say no, "+
//            "You say stop, and I say go, go, go, "+
//            "Oh no. You say goodbye and I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello. "+
//            "I say high, you say low, "+
//            "You say why, and I say I don't know. "+
//            "Oh no. "+
//            "You say goodbye and I say hello, hello, hello. "+
//            "I don't know why you say goodbye, I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello. "+
//            "Why, why, why, why, why, why, "+
//            "Do you say goodbye. "+
//            "Oh no. "+
//            "You say goodbye and I say hello, hello, hello. "+
//            "I don't know why you say goodbye, I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello. "+
//            "You say yes, I say no, "+
//            "You say stop and I say go, go, go. "+
//            "Oh, oh no. "+
//            "You say goodbye and I say hello, hello, hello. "+
//            "I don't know why you say goodbye, I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello, hello, hello, "+
//            "I don't know why you say goodbye, I say hello, hello, hello,";
//    System.out.println(textString2);
//    gen.retrain(textString2);
//    System.out.println(gen);
//    System.out.println(gen.generateText(20));
}

}
