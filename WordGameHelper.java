/**
 *  WordGameHelper.java
 *  Nicole Binder & Margaret Anne Puzak, Carleton College, 2019-03-17
 *
 *  Given two words of identical length, return the shortest word ladder to go from one word to the other.
 *  Given a string of letters find all the real-word anagrams.
 *
 */

import java.util.*;
import java.io.*;

public class WordGameHelper {

    public static Set<String> makeDictionary(String fileName){
        Set<String> dictionary = new HashSet<String>();
        Scanner scanner = null;
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public static Set<String> makeMiniDictionary(Set<String> dictionary, String string){
        Set<String> sameLengthDict = new HashSet<String>();
        for (String word : dictionary){
            if (word.length() == string.length()){
                sameLengthDict.add(word);
            }
        }
        return sameLengthDict;
    }

    public static ArrayList<String> wordLadder(String startWord, String endWord, Set<String> dictionary) {
        Set<String> sameLengthDict = makeMiniDictionary(dictionary, startWord);
        ArrayList<String> wordLadder = new ArrayList<String>();
        ArrayList<String> visited = new ArrayList<String>();
        Map<String, String> edges = new TreeMap<String, String>();
        Deque<String> queue = new LinkedList<String>();
        ArrayList<String> finalWordLadder = new ArrayList<String>();
        queue.add(startWord);
        visited.add(startWord);
        while (!queue.isEmpty()){
            String V = queue.removeFirst();
            ArrayList<String> neighbours = getNeighbours(sameLengthDict, V);
            for (String word : neighbours){
                if (word.compareTo(endWord) == 0){
                    edges.put(word, V);
                    finalWordLadder = makeWordLadder(edges, wordLadder, startWord, endWord);
                    finalWordLadder.add(startWord);
                    return finalWordLadder;
                }
                boolean isVisited = visited.contains(word);
                if (!isVisited){
                    visited.add(word);
                    queue.add(word);
                    edges.put(word, V);
                }
            }
        }
        return null;
    }

    public static ArrayList<String> makeWordLadder(Map<String, String> edges, ArrayList<String> wordLadder, String startWord, String endWord){
        String word = endWord;
        while (word != startWord){
            wordLadder.add(word);
            word = edges.get(word);
        }
        return wordLadder;
    }

    public static Map<String, String> makeAnagramMiniDictionary(Set<String> dictionary, String string){
        Map<String, String> sameLengthDict = new HashMap<String, String>();
        for (String word : dictionary){
            char[] wordArray = word.toCharArray();
            Arrays.sort(wordArray);
            String sorted = new String(wordArray);
            if (word.length() == string.length()){
                sameLengthDict.put(word, sorted);
            }
        }
        return sameLengthDict;
    }

    public static ArrayList<String> anagram(String string, Set<String> dictionary){

        ArrayList<String> anagrams = new ArrayList<String>();
        char[] wordArray = string.toCharArray();
        Arrays.sort(wordArray);
        String sortedString = new String(wordArray);
        Map<String, String> sameLengthDict = makeAnagramMiniDictionary(dictionary, sortedString);

        for (Map.Entry<String, String> pair : sameLengthDict.entrySet()){
            if (pair.getValue().compareTo(sortedString) == 0){
                anagrams.add(pair.getKey());
            }
        }
        if (anagrams.size() == 1){
            return null;
        }
        return anagrams;
    }

    public static boolean checkDictionary(String string, Set<String> dictionary){
        for (String word : dictionary) {
            if (word.compareTo(string) == 0){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getNeighbours(Set<String> miniDict, String string){
        ArrayList<String> neighbours = new ArrayList<String>();
        for (String word : miniDict) {
            int differentCharacters = 0;
            for (int i = 0; i < string.length(); i++) {
                if (word.charAt(i) != string.charAt(i)){
                    differentCharacters++;
                }
            }
            if (differentCharacters == 1){
                neighbours.add(word);
            }
        }
        return neighbours;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java WordGameHelper dictionaryFile.txt ladder startWord endWord OR java WordGameHelper dictionaryFile.txt anagram word");
            System.exit(1);
        }
        String fileName = args[0];
        Set<String> dictionary = makeDictionary(fileName);

        if (args[1].compareTo("ladder") == 0) {
            if (args[2].length() == args[3].length()){
                if (checkDictionary(args[2], dictionary) == true && checkDictionary(args[3], dictionary) == true){
                    ArrayList<String> wordLadder = wordLadder(args[2], args[3], dictionary);
                    if (wordLadder != null){
                        Collections.reverse(wordLadder);
                        for (String word : wordLadder){
                            System.out.println(word);
                        }
                    } else {
                        System.out.println("Word Ladder does not exist within the given dictionary");
                    }
                } else {
                    System.err.println("Usage: One or more words not in dictionary");
                }
            } else {
                System.err.println("Usage: Words must be the same length");
            }
        } else if (args[1].compareTo("anagram") == 0) {
            if (checkDictionary(args[2], dictionary) == true){
                ArrayList<String> anagrams = anagram(args[2], dictionary);
                if (anagrams != null){
                    for (String word : anagrams){
                        System.out.println(word);
                    }
                } else {
                    System.out.println("Anagrams do not exist within the given dictionary");
                }
            } else {
                System.err.println("Usage: Word not in given dictionary");
            }
        } else {
            System.err.println("Usage: java WordGameHelper dictionaryFile.txt ladder startWord endWord OR java WordGameHelper dictionaryFile.txt anagram word");
            System.exit(1);
        }

    }
}
