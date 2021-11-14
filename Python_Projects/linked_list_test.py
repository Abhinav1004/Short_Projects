from linked_list import LinkedList,Pair

# Start with the empty list
llist = LinkedList()

llist.head = Pair(1)
second = Pair(2)
third = Pair(3)

llist.head.next = second; # Link first Pair with second
second.next = third; # Link second Pair with the third Pair

print(llist.length())
# print(type(llist.add(llist,1,5)))
llist.add(1,5)

# llist = llist.add(1,5)

print(llist.length())

print(llist.get(0))
