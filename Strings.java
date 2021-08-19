/* STRINGS: MAKING ANAGRAMS:
Given two strings, a and b, that may or may not be of the same length, determine the minimum number of character deletions 
required to make a and b anagrams. 
Any characters can be deleted from either of the strings. */
public static int makeAnagram(String a, String b) {
    int deletions = 0;
        
    // Gets length of strings
    int sizeA = a.length();
    int sizeB = b.length();
        
    // HashMap that saves characters from both strings and how many times they appear
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
    // Populate the map with characters from the first string
    for (int i = 0; i < a.length(); i++) {
        char charA = a.charAt(i);
        if (map.containsKey(charA)) {
            map.put(charA, map.get(charA)+1);
        }
        else {
            map.put(charA, 1);
        }
    }
        
    // Populate the map with characters from the second string
    // NOTE: This time we'll use negative values to keep track their appearances
    for (int i = 0; i < b.length(); i++) {
        char charB = b.charAt(i);
        if (map.containsKey(charB)) {
            map.put(charB, map.get(charB)-1);
        }
        else {
            map.put(charB, -1);
        }
    }
        
    // We calculate the number of deletions
    /* NOTE: 
    - Keys with value of 0 means both strings have the same number of that character
    - Keys with positive value means that string a has more times that charater then string b by that amount
    - Keys with negative value means the opposite of above */
    for (char key : map.keySet()) {
        deletions += Math.abs(map.get(key)); 
    }
        
    return deletions;
}

/* ALTERNATING CHARACTERS:
You are given a string containing characters A and B only. 
Your task is to change it into a string such that there are no matching adjacent characters. 
To do this, you are allowed to delete zero or more characters in the string.
Your task is to find the minimum number of required deletions. */
public static int alternatingCharacters(String s) {
    int deletions = 0;
    char lastChar = '\n';
        
    // Gets the length of
    int size = s.length();
        
    // Cycles through all characters of s
    for (int i = 0; i < size; i++) {
        char currentChar = s.charAt(i);
            
        // First char: we initialize the last distinct char encountered
        if (i == 0) {
            lastChar = currentChar;
            continue;
        }
            
        // We have changed character: we update the last character
        if (currentChar != lastChar) {
            lastChar = currentChar;
            continue;
        }
            
        // This char is the same as the previous: it is a repetion!
        deletions += 1;
    }
        
    return deletions;
}

/* SHERLOCK AND THE VALID STRING:
Sherlock considers a string to be valid if all characters of the string appear the same number of times. 
It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters will occur the same number of times. 
Given a string , determine if it is valid. If so, return YES, otherwise return NO. */
public static String isValid(String s) {
    int firstCharOccurrences = 0;
    boolean deletionFlag = false;
    boolean firstChar = false;
    boolean testFailed = false;
        
    // Gets length of s
    int size = s.length();
        
    // Hashmap that saves each character and how many times they appear
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
    // List of characters that appear exactly one time
    List<Character> oneTimeChars = new ArrayList<Character>();
            
    // Populates the HashMap with all characters
    for (int i = 0; i < size; i++) {
        char currentChar = s.charAt(i);
        if (map.containsKey(currentChar)) {
            map.put(currentChar, map.get(currentChar)+1);
        }
        else {
            map.put(currentChar, 1);
        }
    }
        
    // First test for the majority of cases
    for (char key : map.keySet()) {
        int occurrences = map.get(key);
            
        // If this is the first char, we initialize the comparison value
        if (firstChar == false) {
            firstCharOccurrences = occurrences;
            firstChar = true;
            continue;
        }
            
        // We add this element to the list since it appears only one time
        if (occurrences == 1) {
            oneTimeChars.add(key);
        }
            
        // All good!
        if (occurrences == firstCharOccurrences) {
            continue;
        }
            
        // The current char appears one time more than it should: we remove one instance if possible
        if (occurrences == (firstCharOccurrences+1) && deletionFlag == false) {
            deletionFlag = true;
            continue;
        }
            
        // The starting char appears one time more than it should: we remove one instance if possible
        if (occurrences == (firstCharOccurrences-1) && deletionFlag == false) {
            firstCharOccurrences += -1;
            deletionFlag = true;
            continue;
        }
                
        // If we are here, the first test has failed!
        testFailed = true;
        break;
    }
        
    // All good!
    if (!testFailed) {
        return "YES";
    }
        
    // We try a second test in which for each iteration we remove a char that appears only once
    else {
        int listSize = oneTimeChars.size();
            
        for (int i = 0; i < listSize; i++) {
            char element = oneTimeChars.get(i);
            testFailed = false;
            firstChar = false;
                
            for (char key : map.keySet()) {
                if (key == element) {
                    continue;
                }
                    
                int occurrences = map.get(key);
                if (firstChar == false) {
                    firstCharOccurrences = occurrences;
                    firstChar = true;
                    continue;
                }
                    
                if (occurrences != firstCharOccurrences) {
                    testFailed = true;
                    break;
                }
            }
                
            if (!testFailed) {
                return "YES";
            }
        }
            
        return "NO";
    }
}