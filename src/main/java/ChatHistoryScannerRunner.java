import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatHistoryScannerRunner {

    public static void main(String[] args) {

        FileAnalyserWithMap fileAnalyser = new FileAnalyserWithMap();
        fileAnalyser.fileReader("/Users/vasileiosdavios/Documents/My Games/personal shit/_chat.txt");
        Map<Integer, String> resultsPerUser = fileAnalyser.bannedWordsCounterPerUser();
        System.out.println(+fileAnalyser.bannedWordsUsedInChat() +" banned words were used in chat");

        resultsPerUser.entrySet().forEach(entry  -> System.out.println(entry.getValue() + " " + entry.getKey()));
        System.out.println("*****************************************");

        FileAnalyser fileAnalyser1 = new FileAnalyser();
        fileAnalyser1.fileReader("/Users/vasileiosdavios/Documents/My Games/personal shit/_chat.txt");
        System.out.println( fileAnalyser1.bannedWordsUsedByChat());



        System.out.println("*****************Testing************************");


        String line = "This is a line with multiple words list, gist fist, fish, kiss. Is it ok?";
        String wordToCount = "is";
        Pattern pattern = Pattern.compile("\\b" + wordToCount + "\\b");
        Matcher matcher = pattern.matcher(line.toLowerCase());
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println("The word '" + wordToCount + "' appears " + count + " times in the line.");






    }
}
