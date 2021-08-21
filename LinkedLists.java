/* INSERT A NODE IN A SPECIFIC POSITION IN A LINKED LIST:
Given the pointer to the head node of a linked list and an integer to insert at a certain position, 
create a new node with the given integer as its data attribute, insert this node at the desired position and return the head node.
A position of 0 indicates head, a position of 1 indicates one node away from the head and so on. 
The head pointer given may be null meaning that the initial list is empty. */
public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
    // We create the node which we will insert into the list
    SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        
    // Case in which we need to add a new head element
    if (position == 0) {
        node.next = llist;
        return node;
    }
        
    // Case in which w need to add the element inside the list
    int counter = 0;
    SinglyLinkedListNode current = llist;
    while (current.next != null) { // We cycle until we find the right spot where to insert the new node
        current = current.next;
        counter += 1;
        if (counter == position-1) { // We insert the node while keeping the previous order
            node.next = current.next;
            current.next = node;
            return llist;
        }
    }

    return llist;
}

/* INSERT A NODE INTO A SORTED DOUBLY LINDED LIST:
Given a reference to the head of a doubly-linked list and an integer, 'data', create a new DoublyLinkedListNode object 
having data value 'data' and insert it at the proper location to maintain the sort. */
public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist, int data) {
    // We create the new node that must be added to the list
    DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        
    // Case where this is the first element of the list
    if (data <= llist.data) {
        node.next = llist;
        llist.prev = node;
        return node;
    }
        
    // We cycle through llist until we find the right spot (all the elements must be in order)
    DoublyLinkedListNode current = llist;
    while (current.next != null) {
        current = current.next;
        if (data <= current.data) {
            DoublyLinkedListNode prev = current.prev;
            prev.next = node;
            node.prev = prev;
            node.next = current;
            current.prev = node;
            return llist;
        }
    }
        
    // If we are here, then we must add node as the last element
    current.next = node;
    return llist;
}

/* REVERSE A DOUBLY LINKED LIST:
Given the pointer to the head node of a doubly linked list, reverse the order of the nodes in place.
That is, change the next and prev pointers of the nodes so that the direction of the list is reversed. 
Return a reference to the head node of the reversed list. */
public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {       
    // We reach the last element of llist
    DoublyLinkedListNode current = llist;
    while (current.next != null) {
        current = current.next;
    }
        
    // We create the reverse of llist
    DoublyLinkedListNode reverse = new DoublyLinkedListNode(current.data);
        
    // Current pointer of reverse
    DoublyLinkedListNode reverseCurrent = reverse;
        
    // We populate reverse by going backwards through llist using current
    while (current.prev != null) {
        current = current.prev;
        DoublyLinkedListNode node = new DoublyLinkedListNode(current.data);
        node.prev = reverseCurrent;
        reverseCurrent.next = node;
        reverseCurrent = reverseCurrent.next;
    }
        
    return reverse;
}

/* FIND MERGE POINT OF TWO LISTS:
Given pointers to the head nodes of 2 linked lists that merge together at some point, find the node where the two lists merge. 
The merge point is where both lists point to the same node, i.e. they reference the same memory location. 
It is guaranteed that the two head nodes will be different, and neither will be NULL. 
If the lists share a common node, return that node's data value. */
static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    SinglyLinkedListNode counter1 = head1;
    SinglyLinkedListNode counter2 = head2;
        
    // We cycle through all elements of list 1
    while (counter1.next != null) {
            
        // For each element of l1, we cycle through all elements of l2
        counter2 = head2;
        while (counter2.next != null) {   
            // Both lists have the same next element
            if (counter1.next == counter2.next) {
                return counter1.next.data;
            }
            counter2 = counter2.next;
        }
            
        counter1 = counter1.next;
    }
        
    return head1.data;
}

/* LINKED LISTS: DETECT A CYCLE:
A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
Complete the function has_cycle in the editor below. It must return a boolean true if the graph contains a cycle, or false. */
boolean hasCycle(Node head) {
// We use this list to keep track of visited nodes
List<Node> visitedNodes = new ArrayList<Node>();
    
// If the head isn't null, we initialize the visited list
if (head != null) {
    visitedNodes.add(head);
        
    // We traverse through the list and we keep adding new nodes to the visited list until we find a cycle
    while (head.next != null) {
        head = head.next;
        if (visitedNodes.contains(head)) {
            return true;
        }
        visitedNodes.add(head);
    }
}
    
// If we are here, no cycle was found!
return false;
}