import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyserWithMap {


    private Map<String, ArrayList<String>> chatMap = new HashMap<>();
    private final ArrayList<String> fullChat = new ArrayList<>();
    private final ArrayList<Pattern> bannedWordsPatterns = new ArrayList<>();
    private final ArrayList<String> bannedWords = new ArrayList<>();
    public FileAnalyserWithMap() {
        chatMap.put("Alex", new ArrayList<>());
        chatMap.put("Στεφανάρας", new ArrayList<>());
        chatMap.put("Vasilis Davios", new ArrayList<>());
        chatMap.put("Τζόρνταν", new ArrayList<>());
        chatMap.put("Χριστόδουλος", new ArrayList<>());
        chatMap.put("Βασίλης Κορτσιμελίδης", new ArrayList<>());
        chatMap.put("Τάσος Παγκαλίδης", new ArrayList<>());
        chatMap.put("Θεοδόσης Καλογερόπουλος", new ArrayList<>());
        chatMap.put("Ορφέας Μποτέας", new ArrayList<>());
        bannedWordsPatterns.add(Pattern.compile("\\bmalakas\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bmalaka\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bmlk\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bmalakes\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bmlks\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλάκας\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλακα\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλάκα\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλακας\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλακες\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμαλάκες\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμλκς\\b"));
        bannedWordsPatterns.add(Pattern.compile("\\bμλκσ\\b"));
        bannedWords.add("malakas");
        bannedWords.add("malaka");
        bannedWords.add("mlk");
        bannedWords.add("malakes");
        bannedWords.add("mlks");
        bannedWords.add("μαλάκας");
        bannedWords.add("μαλάκα");
        bannedWords.add("μαλακα");
        bannedWords.add("μαλακας");
        bannedWords.add("μαλακες");
        bannedWords.add("μαλάκες");
        bannedWords.add("μλκς");
        bannedWords.add("μλκσ");


    }

    private static final Logger logger = LogManager.getLogger(FileAnalyserWithMap.class);
    public void fileReader(String fileName) {
        logger.info("Reading from file");
        try(FileInputStream inputStream = new FileInputStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                for(Map.Entry<String, ArrayList<String>> entry: chatMap.entrySet()) {
                    if(line.contains(entry.getKey())) { //Checking if the line from our file contains the key(String name) of our map
                        entry.getValue().add(line); //adding the line to the corresponding Arraylist of the key
                    }
                }
                fullChat.add(line);

            }
        } catch (IOException e) {

            logger.error("Cannot read from file");
            e.printStackTrace();
        }
    }

    public int bannedWordsUsedInChat() {

        System.out.println(fullChat.size());
        return bannedWordsUsed(fullChat);

    }

   public Map<Integer, String> bannedWordsCounterPerUser() {
        Map<Integer, String> results = new TreeMap<>();
        chatMap.forEach((username, lines) -> results.put(bannedWordsUsed(lines), username));
        return results;
   }

   private int bannedWordsUsed(ArrayList<String> input) {

       return input.stream().map(line -> {
            int counter=0;
            for(Pattern pattern: bannedWordsPatterns) {
               Matcher matcher = pattern.matcher(line.toLowerCase());
               while(matcher.find()){
                   counter++;
               }
            }
            return counter;
        }).reduce(0, Integer::sum);
   }

   private int bannedWordsUsedMethodThatWorks(ArrayList<String> input) {
        return input.stream()
                .filter(line -> {
                    for(String word: bannedWords) {
                        if(line.toLowerCase().contains(word)) {
                            return true;
                        }
                    }
                    return false;
                }).toList().size();
   }


   public void results(HashMap<String, Integer> input) {

   }
}
