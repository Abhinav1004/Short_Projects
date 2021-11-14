# A simple Python program to introduce a linked list

# Pair class
class Pair:

	# Function to initialise the Pair object
	def __init__(self, data):
		self.data = data # Assign data
		self.next = None # Initialize next as null


# Linked List class contains a Pair object
class LinkedList:

	# Function to initialize head
	def __init__(self):
	    self.head = None

	def empty_list(self):
	    empty_node = Pair(None)
	    return empty_node

	def length(self):
		temp = self.head # Initialise temp
		count = 0 # Initialise count
		# Loop while end of linked list is not reached
		while (temp):
		    count += 1
		    temp = temp.next
		return count
	# function to create and return a Node
	def getNode(data):
	    # allocating space
	    newNode = Node(data)
	    return newNode;

	def add(self, index, data):
		if index == 1:
			new_node = Pair(data)
			new_node.next = self.head
			self.head = new_node
		i = 1
		n = self.head
		while i < index-1 and n is not None:
		    n = n.next
		    i = i+1
		if n is None:
		    print("Index out of bound")
		else:
		    new_node = Pair(data)
		    new_node.next = n.next
		    n.next = new_node
	# Returns data at given index in linked list
	def get(self, index):
	    current = self.head  # Initialise temp
	    head = current
	    count = 0  # Index of current node
	    if index<0:
	        raise IndexError("index is less than 0")
	    # Loop while end of linked list is not reached
	    while (current):
	        if (count == index):
	            return current.data
	        count += 1
	        current = current.next
	    if count<index:
	        raise IndexError("index is more than size of list")
	    # return head

	# Set data at given index in linked list
	def setitem(self, index,value):
	    current = self.head  # Initialise temp
	    head = current
	    count = 0  # Index of current node
	    if index<0:
	        raise IndexError("index is less than 0")
	    # Loop while end of linked list is not reached
	    while (current):
	        if (count == index):
	            current.data = value
	            break
	        count += 1
	        current = current.next
	    if count<index:
	        raise IndexError("index is more than size of list")

	# Given a reference to the head of a list
    # and a position, delete the node at a given position
	def remove(self, position):
	    # If linked list is empty
	    if self.head == None:
	        return
	    # Store head node
	    temp = self.head
	    if position<0:
	        raise IndexError("position is less than 0")
	    # If head needs to be removed
	    if position == 0:
	        self.head = temp.next
	        temp = None
	        return

	    # Find previous node of the node to be deleted
	    for i in range(position -1 ):
	        temp = temp.next
	        if temp is None:
	            break
	    # If position is more than number of nodes
	    if temp is None:
	        return
	    if temp.next is None:
	        return

	    # Node temp.next is the node to be deleted
	    # store pointer to the next of node to be deleted
	    next = temp.next.next

	    # Unlink the node from linked list
	    temp.next = None

	    temp.next = next
