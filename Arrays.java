/* 2D ARRAY - DS:
Given a 6x6 2D Array arr, an hourglass is a subset of values with indices falling in this pattern in arr's graphical representation:
a b c
  d
e f g
There are 16 hourglasses in arr. An hourglass sum is the sum of an hourglass' values. Calculate the hourglass sum for every hourglass in arr, 
then print the maximum hourglass sum. The array will always be 6x6. */
static int hourglassSum(int[][] arr) {
    boolean firstResult = true; 
    int maxResult = 0;
    int actualResult = 0;
        
    // Cycles through all the possible hourglasses
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            // Finds the sum of the current hourglass
            actualResult = arr[i][j] + arr[i][j+1] + arr[i][j+2];
            actualResult += arr[i+1][j+1];
            actualResult += arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
                
            // If this is the first sum, it initializes first max result
            if (firstResult) {
                maxResult = actualResult;
                firstResult = false;
            }
                
            // Otherwise it checks if this is biggest result yet
            else if (actualResult > maxResult) {
                maxResult = actualResult;
            }
        }
    }
    return maxResult;
}

/* ARRAYS: LEFT ROTATION:
Given an array a of n integers and a number, d, perform d left rotations on the array. 
Return the updated array to be printed as a single line of space-separated integers. */
static int[] rotLeft(int[] a, int d) {
    // Gets length of input array
    int size = a.length;
    
    // Creates output array
    int[] result = new int[size];
    
    // We start cycling through the input array with index d instead of the very beginning
    int index = d;
    
    // Cycles through the input array
    for (int i = 0; i < size; i++) {
        // If the index is bigger than the size of the array, it starts again from 0 in order to not overflow
        if (index >= size) {
            index = 0;
        }
        result[i] = a[index]; // We save the current value in result
        index += 1; // We refresh the index
    }
    
    return result;
}

/* NEW YEAR CHAOS:
It is New Year's Day and people are in line for the Wonderland rollercoaster ride. 
Each person wears a sticker indicating their initial position in the queue from 1 to n. 
Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker. 
One person can bribe at most two others.
Determine the minimum number of bribes that took place to get to a given queue order. 
Print the number of bribes, or, if anyone has bribed more than two people, print 'Too chaotic'. */
static void minimumBribes(int[] q) {
    int bribes = 0;
    boolean valid = true;
    int index = 1;
    
    // We get the length of the input array
    int size = q.length; 
    
    // We cycle through the input array
    for (int i = 0; i < size; i++) {
        int actual = q[i];
        
        // We check if the current person has surely swapped position more than two times since it appears before they are supposed to
        if (actual > index) {
            int difference = actual - index;
            if (difference > 2) {
                valid = false;
                break;
            }
        }
        
        // We check the number of bribes by counting how many persons with bigger IDs have already swapped place with the current one
        // NOTE: We can safely begin to check from position (actual-2) since higher numbers of swaps would have been caught by the previous if condition
        for (int j = actual-2; j < index; j++) {
            if (j < 0) {
                continue;
            }
            if (actual < q[j]) {
                bribes += 1;
            }
        }
            
        index += 1;
    }
    
    // Checks if the result is valid, and if so, it prints it
    if (valid == false) {
       System.out.println("Too chaotic");
    }
    else {
       System.out.println(bribes);
    }
}

/* MINIMUM SWAPS 2:
You are given an unordered array consisting of consecutive integers [1, 2, 3, ..., n] without any duplicates. 
You are allowed to swap any two elements. Find the minimum number of swaps required to sort the array in ascending order. */
static int minimumSwaps(int[] arr) {
    int result = 0;
    
    // Gets the length of the input array
    int size = arr.length;
    
    // Cycles through the input array
    for (int i = 0; i < size; i++) {
        // Checks if the element is in the right position
        if (arr[i] != i+1) {
            result += 1; // The element ISN'T in the right position. A new swap is needed!
            
            // Cycles through the remaining input array
            for (int j = i+1; j < size; j++) {
                if (arr[j] == i+1) { // Gets the element that should be in the place of arr[i] and swaps it!
                    int swap = arr[j];
                    arr[j] = arr[i];
                    arr[i] = swap;
                    break;
                }
            }   
        }
    }
    
    return result;
}

/* ARRAY MANIPULATION:
Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array element between two given indices,
inclusive. Once all operations have been performed, return the maximum value in the array. */
public static long arrayManipulation(int n, List<List<Integer>> queries) {
    // Create an array of n+1 elements, all set to 0
    long resultArray[] = new long[n+1];
        
    // Gets number of input queries
    int size = queries.size(); 
        
    // Value of the max value
    long maxValue = 0;
        
    // Cycles through all queries
    for (int i = 0; i < size; i++) {
        List<Integer> query = queries.get(i); // Gets current query
        int value = query.get(2); // Gets number to be added to resultArray
            
        /* Adds number to the right indexes of resultArray: TAKES TOO MUCH TIME!
        for (int j = query.get(0)-1; j <= query.get(1)-1; j++) {
            resultArray[j] += value;
                
            // Checks if this is the biggest value
            if (resultArray[j] > maxValue) {
                maxValue = resultArray[j];
            }
        } */
            
        // Adds number to only two indexes of resultArray:
        resultArray[query.get(0)-1] += value; // We signal the beginning of the 'positive curve', which spans until query.get(1).
        resultArray[query.get(1)] -= value; // We signal the ending of the 'positive curve'.
    }
        
    // Gets the max value
    long sum = 0;
    for (int i = 0; i < n; i++) {
       sum += resultArray[i];
       if (sum > maxValue) {
           maxValue = sum;
       }
    }
        
    return maxValue;
}