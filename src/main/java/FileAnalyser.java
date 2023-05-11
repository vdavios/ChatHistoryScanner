import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileAnalyser {

    private final String alex = "Alex";
    private final String stefanos = "Στεφανάρας";
    private final String davios = "Vasilis Davios";
    private final String tzorntan = "Τζόρνταν";
    private final String christodoulos = "Χριστόδουλος";
    private final String vasKort = "Βασίλης Κορτσιμελίδης";
    private final String tasaras = "Τάσος Παγκαλίδης";
    private final String theo = "Θεοδόσης Καλογερόπουλος";
    private final String orfas = "Ορφέας Μποτέας";

    private ArrayList<String> alexChatMessages = new ArrayList<>();
    private ArrayList<String> stefanosChatMessages = new ArrayList<>();
    private ArrayList<String> daviosChatMessages = new ArrayList<>();
    private ArrayList<String> tzorntanChatMessages = new ArrayList<>();
    private ArrayList<String> christodoulousChatMessages = new ArrayList<>();
    private ArrayList<String> vasKortChatMessages = new ArrayList<>();
    private ArrayList<String> tasarasChatMessages = new ArrayList<>();
    private ArrayList<String> theoChatMessages = new ArrayList<>();
    private ArrayList<String> orfasChatMessages = new ArrayList<>();
    private ArrayList<String> fullChat = new ArrayList<>();

    private final ArrayList<String> bannedWords = new ArrayList<>();

    public FileAnalyser(){
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

    private static final Logger logger = LogManager.getLogger(FileAnalyser.class);
    public void fileReader(String fileName) {
        logger.info("Reading from file");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                populateArrayList(line);
                fullChat.add(line);
            }
        } catch (IOException e) {

            logger.error("Cannot read from file");
            e.printStackTrace();
        }
    }


    public void populateArrayList(String line) {
        if(line.contains(alex)){
            alexChatMessages.add(line);
        }
        if(line.contains(stefanos)){
            stefanosChatMessages.add(line);
        }
        if(line.contains(davios)){
            daviosChatMessages.add(line);
        }
        if(line.contains(tzorntan)){
            tzorntanChatMessages.add(line);
        }
        if(line.contains(christodoulos)){
            christodoulousChatMessages.add(line);
        }
        if(line.contains(vasKort)){
            vasKortChatMessages.add(line);
        }
        if(line.contains(tasaras)){
            tasarasChatMessages.add(line);
        }
        if(line.contains(theo)){
            theoChatMessages.add(line);
        }
        if(line.contains(orfas)){
            orfasChatMessages.add(line);
        }
    }
    public void displayFirstChatMessage() {

        System.out.println(daviosChatMessages.get(0));

    }

    public void bannedWordsUsedByDavios(){

      double size = daviosChatMessages.stream()
              .filter(line -> {
                  for(String str : bannedWords) {
                      if(line.toLowerCase().contains(str)) {
                          return true;
                      }
                  }
                  return false;})
              .toList()
              .size();

      double percentage = (size/bannedWordsUsedByChat()) * 100;
      System.out.println("Davios used "+size+" the word malakas or similar words");

    }

    public int bannedWordsUsedByChat(){
        System.out.println(fullChat.size());
        return  fullChat.stream().filter(line -> {
            for(String str: bannedWords) {
                if(line.toLowerCase().contains(str)) {
                    return true;
                }
            }
        return false; }).toList().size();

    }








}
