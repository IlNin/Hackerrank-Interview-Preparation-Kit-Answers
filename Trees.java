/* TREE: HEIGHT OF A BINARY TREE:
The height of a binary tree is the number of edges between the tree's root and its furthest leaf. 
Complete the getHeight or height function in the editor. It must return the height of a binary tree as an integer. */
public static int height(Node root) {
    int heightLeft = -1;
    int heightRight = -1;
        
    // Searches the length of the left side of the current node
    if (root.left != null) {
        heightLeft = height(root.left);
    }
        
    // Searches the length of the right side of the current node
    if (root.right != null) {
        heightRight = height(root.right);
    }
        
    // Returns the height of the deepest side added by 1
    return Math.max(heightLeft, heightRight) + 1;
}

/* BINARY SEARCH TREE: LOWEST COMMON ANCESTOR:
You are given pointer to the root of the binary search tree and two values v1 and v2. 
You need to return the lowest common ancestor (LCA) of v1 and v2 in the binary search tree. */
public static Node lca(Node root, int v1, int v2) {
    // Checks if the current node is either v1 or v2
    if (root.data == v1 || root.data == v2) {
        return root;
    }
        
    Node containsLeft = new Node(0);
    Node containsRight = new Node(0);
        
    // Checks if both sides of root contain either v1 and v2
    if (root.left != null) {
        containsLeft = lca(root.left, v1, v2);   
    }
    if (root.right != null) {
        containsRight = lca(root.right, v1, v2);
    }
        
    // Checks if both values were found
    if (containsLeft.data != 0 && containsRight.data != 0) {
        return root;
    }
        
    // Checks if only one value was found
    if (containsLeft.data != 0) {
        return containsLeft;
    } 
    if (containsRight.data != 0) {
        return containsRight;
    }
        
    // If we are here, nothing was found
    return new Node(0);
}

/* TREES: IS THIS A BINARY SEARCH TREE?
Complete the function checkBST in the editor below. It must return a boolean denoting whether or not the binary tree is a binary search tree. */
boolean recursiveBST(Node root, int min, int max) {
    if (root == null) {
        return true;
    }
        
    // Checks if the current value is inside the constraints
    if (root.data <= min || root.data >= max) {
        return false;
    }
    
    // Checks in both sides of the current Node: no value on the left side can be bigger than current, while no value on the right side can be smaller
    return recursiveBST(root.left, min, root.data) && recursiveBST(root.right, root.data, max);
}


boolean checkBST(Node* root) {
    return recursiveBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);	
}

/* TREES: HUFFMAN DECODING:
Huffman coding assigns variable length codewords to fixed length input characters based on their frequencies.
More frequent characters are assigned shorter codewords and less frequent characters are assigned longer codewords. 
All edges along the path to a character contain a code digit. 
If they are on the left side of the tree, they will be a 0 (zero). If on the right, they'll be a 1 (one). 
Only the leaves will contain a letter and its frequency count. 
All other nodes will contain a null instead of a character, and the count of the frequency of all of it and its descendant characters.
Complete the function decode_huff in the editor below. It must return the decoded string. */
void decode(String s, Node root) {
    String result = "";
    Node current = root;
        
    // We cycle through all chars of s
    for (int i = 0; i < s.length(); i++) {
        char direction = s.charAt(i);
            
        // We travel in tree according to the direction requested by the current char
        if (direction == '0') {
            current = current.left;
        }
        if (direction == '1') {
            current = current.right;
        }
            
        // If the node contains a valid value, we have reached a leaf: the value is added to the string, and we start back from the root.
        if (current.data != '\0') {
            result = result + current.data;
            current = root;
            continue;
        }
    }
            
    System.out.println(result);
}
