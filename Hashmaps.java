/* HASH TABLES: RANSOM NOTE:
Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him through his handwriting.
He found a magazine and wants to know if he can cut out whole words from it and use them to create an untraceable replica of his ransom note. 
The words in his note are case-sensitive and he must use only whole words available in the magazine. 
He cannot use substrings or concatenation to create the words he needs.
Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his ransom note exactly using whole words from the magazine; 
otherwise, print No. */
static void checkMagazine(String[] magazine, String[] note) {
    // Creates an Hashmap that saves all the words in the magazine and how many times they appear
    HashMap<String, Integer> magazineWords = new HashMap<String, Integer>();
    
    // Cycles through all the words in the magazine and saves them in the Hashmap
    for (int i = 0; i < magazine.length; i++) {
        String word = magazine[i];
        if (!magazineWords.containsKey(word)) {  // First time a word has been encountered: initializes counter at 1
            magazineWords.put(word, 1);
        }
        else { // This word was already present: refreshes counter by adding 1 
            magazineWords.put(word, magazineWords.get(word) + 1);
        }
    }
    
    // Cycles through the words in the ransom note to see if each one appears in the Hashmap
    for (int i = 0; i < note.length; i++) {
        String word = note[i];
        if (magazineWords.containsKey(word)) { // The word appears: the counter must be decreased by 1
            int counter = magazineWords.get(word);
            if (counter <= 0) {
                System.out.println("No");
                return;
            }
            counter += -1;
            magazineWords.put(word, counter);
        }
        else {
            System.out.println("No");
            return;
        }
    }
    
    System.out.println("Yes");
}

/* TWO STRINGS:
Given two strings, determine if they share a common substring. A substring may be as small as one character. */
static String twoStrings(String s1, String s2) {
    // Initializes Hashmap where we add each letter in string s1
    HashMap <Character, Integer> letters = new HashMap<Character, Integer>();
    
    // Cycles through all letters in s1 and adds them to the Hashmap
    for (int i = 0; i < s1.length(); i++) {
        letters.put(s1.charAt(i), 1);
    }
    
    // Cycles through all letters in s2 and see if there's one that also appears in s1 by using the Hashmap
    for (int i = 0; i < s2.length(); i++) {
        if (letters.containsKey(s2.charAt(i))) {
            return "YES";
        }
    }
    
    return "NO";
}

/* SHERLOCK AND ANAGRAMS:
Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string. 
Given a string, find the number of pairs of substrings of the string that are anagrams of each other. */
static int sherlockAndAnagrams(String s) {
    int result = 0;
        
    // Hashmap where we saves all substring of s for quick access
    HashMap<String, Integer> substrings = new HashMap<String, Integer>();
    
    // We get the length of s
    int length = s.length();
    
    // We cycle through all substrinbs of s
    for (int i = 0; i < length; i++) {
       for (int j = i+1; j < length+1; j++) {
            String substring = s.substring(i, j);
            
            char[] chars = substring.toCharArray(); // We convert the substring to an array of chars...
            Arrays.sort(chars); // ... that then we sort! In this way two strings that are anagrams will be represented by the same ordered array of chars!
            substring = new String(chars); // Reverts back the array of chars to a string 
            
            // If this substring already appears in the Hashmap, then we have an anagram and the counter is refreshed
            if (substrings.containsKey(substring)) {
                int value = substrings.get(substring);
                result += value;
                value += 1;
                substrings.put(substring, value);
            }
            else {
                substrings.put(substring, 1);
            }
        }
    }
    
    return result;
}

/* FREQUENCY QUERIES:
You are given q queries. Each query is of the form two integers described below:
- [1,x] : Insert x in your data structure.
- [2,y] : Delete one occurence of y from your data structure, if present.
- [3,z] : Check if any integer is present whose frequency is exactly z. If yes, print 1 else 0.
The queries are given in the form of a 2-D array queries of size q where queries[i][0] contains the operation, and 
queries[i][1] contains the data element.
Write a function that returns all answers of queries of type 3. */
static List<Integer> freqQuery(List<List<Integer>> queries) {
    List<Integer> results = new ArrayList<Integer>();

    // Gets the size of queries
    int size = queries.size();
        
    // Data structure that contains all the values and how many times they appear
    HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
        
    // Cycles through the queries
    for (int i = 0; i < size; i++) {
        List<Integer> query = queries.get(i);
        int operator = query.get(0);
        int value = query.get(1);
            
        // We need to insert a new element
        if (operator == 1) {
            if (data.containsKey(value)) {
                data.put(value, data.get(value)+1);
            }
            else {
                data.put(value, 1);
            } 
        }
      
        // We need to delete an element
        else if (operator == 2) {
            if (data.containsKey(value)) {
                int occurrences = data.get(value)-1;
                if (occurrences == 0) {
                    data.remove(value);
                }
                else {
                    data.put(value, occurrences);
                }
            }
        }
            
        // We need to check if an element appears 'value' times
        else if (operator == 3) {
            boolean flag = false;
            for (int key : data.keySet()) {
                if (data.get(key) == value) {
                    results.add(1);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                results.add(0);
            }
        }
    }

    return results;
}