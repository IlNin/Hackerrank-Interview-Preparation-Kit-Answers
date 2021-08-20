/* MINIMUM ABSOLUTE DIFFERENCE IN AN ARRAY:
Given an array of integers, find the minimum absolute difference between any two elements in the array. */
public static int minimumAbsoluteDifference(List<Integer> arr) {
    int minimum = Integer.MAX_VALUE;
        
    // We get the size of arr
    int size = arr.size();
        
    // We sort the array so that the elements are ordered from smallest to biggest
    Collections.sort(arr);
        
    // We cycle through all consecutive pairs of arr
    for (int i = 1; i < size; i++) {
        // We calculate the absolute difference of the pair, and refresh the smallest minimum if needed
        int difference = Math.abs(arr.get(i-1)-arr.get(i));
        if (difference < minimum) {
            minimum = difference;
        }
    }
        
    return minimum;
}

/* LUCK BALANCE:
Lena is preparing for an important coding competition that is preceded by a number of sequential preliminary contests. 
Initially, her luck balance is 0. She believes in "saving luck", and wants to check her theory. 
Each contest is described by two integers, L[i] and T[i]:
- L[i] is the amount of luck associated with a contest. 
If Lena wins the contest, her luck balance will decrease by L[i]; if she loses it, her luck balance will increase by L[i].
- T[i] denotes the contest's importance rating. It's equal to 1 if the contest is important, and it's equal to 0 if it's unimportant.
If Lena loses no more than k important contests, what is the maximum amount of luck she can have after competing in all the preliminary contests? 
This value may be negative. */
public static int luckBalance(int k, List<List<Integer>> contests) {
    int size = contests.size();
    int importantContests = 0;
    int luckTotal = 0;
        
    // List where we keep only the luck values associated to important contests
    List<Integer> importantContestsValues = new ArrayList<Integer>();
        
    // We cycle through all the contests
    for (int i = 0; i < size; i++) {
        List<Integer> contest = contests.get(i);
            
        // We refresh the total luck sum
        int luck = contest.get(0);
        luckTotal += luck;
            
        int importance = contest.get(1);
            
        // We save this value if it belongs to an important contest
        if (importance == 1) {
            importantContests += 1;
            importantContestsValues.add(luck);
        }
    }
        
    // There are more important tests than the ones we can not take
    if ((importantContests-k) >= 1) {
        // We sort the values, so that tests that damage the luck value the least are the first ones
        Collections.sort(importantContestsValues); 
        for (int i = 0; i < (importantContests-k); i++) {
            luckTotal -= 2*importantContestsValues.get(i);
        }
    } 
        
    return luckTotal;
}

/* GREEDY FLORIST:
A group of friends want to buy a bouquet of flowers. 
The florist wants to maximize his number of new customers and the money he makes. 
To do this, he decides he'll multiply the price of each flower by the number of that customer's previously purchased flowers plus 1. 
The first flower will be original price, (0+1)*originalPrice, the next will be (1+1)*originalPrice and so on.
Given the size k of the group of friends, the number of flowers they want to purchase and the original prices of the flowers, 
determine the minimum cost to purchase all of the flowers. The number of flowers they want equals the length of the c array. */
// Complete the getMinimumCost function below.
static int getMinimumCost(int k, int[] c) {
    int minimumCost = 0;
        
    // We sort the prices of the flowers so that the cheapest are first
    Arrays.sort(c);
        
    // Array that keep track of the purchases of each friend
    int[] purchases = new int[k];
    int currentFriend = 0; // Index of the current friend that is buying a flower
        
    // We get the numbers of flowers that need to be bought
    int flowers = c.length;
        
    /* We cycle in reverse order: the idea is to buy more expensive flowers first while
    the penality for multiple purchases by the same person is at the smallest values */
    for (int i = flowers-1; i >= 0; i--) {
        purchases[currentFriend] += 1;
        minimumCost += c[i] * purchases[currentFriend];
            
        // We switch to the next friend and we start from the first one if needed
        currentFriend += 1;
        if (currentFriend == k) {
            currentFriend = 0;
        }
    }
    
    return minimumCost;
}


/* MAX MIN:
You will be given a list of integers, arr, and a single integer k. 
You must create an array of length k from elements of arr such that its unfairness is minimized. Call that array arr'. 
Unfairness of an array is calculated as: max(arr')-min(arr')
Where:
- max denotes the largest integer in arr'
- min denotes the smallest integer in arr' */
public static int maxMin(int k, List<Integer> arr) {
    int smallestDifference = Integer.MAX_VALUE;
        
    // We sort the list: pairs of ordered numbers give us the smallest differences
    Collections.sort(arr);
        
    // We get the length of arr
    int size = arr.size();
                
    // We cycles all consecutive lists of ordered elements of size k
    for (int i = 0; i < size; i++) {
        // Since the list is ordered, we gain the max of this permutation (i will be the min)
        int max = i+(k-1);
            
        // Check if we are not getting out of the array
        if (max == size) {
            break;
        }
            
        // We calculate the difference and see if it's the smallest
        int difference = arr.get(max) - arr.get(i);
        if (difference < smallestDifference) {
            smallestDifference = difference;
        }
    }

    return smallestDifference;
}
