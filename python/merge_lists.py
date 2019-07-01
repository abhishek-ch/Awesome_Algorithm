class LinkedList:	
	def __init__(self):
		self.head = None

	# Inserts a new Node at front of the list.
	def push(self,new_data):
		new_node = self.Node(new_data)
		new_node.next = self.head
		self.head = new_node

	def printlist(self):
		temp = self.head
		while temp != None:
			print(temp.data, end=', ')
			temp = temp.next
		print()
			
	# Linked list Node 
	class Node:
		def __init__(self, d):
			self.data = d
			self.next = None


	def merge(self, other):
		print('I am startibg Merge')
		pointer1_curr = self.head
		pointer2_curr = other.head
		# pointer1 <- list1.head 
		# pointer2 <- list2.head
		# while pointer1 is not none, continue the loop and pointer2 is not none
		while pointer1_curr is not None and pointer2_curr is not None:
			# increment pointer1 to next
			pointer1_next = pointer1_curr.next
			pointer2_next = pointer2_curr.next

			pointer1_curr.next =  pointer2_curr
			pointer2_curr.next = pointer1_next	
		other.head = pointer2_curr


	def reverse_in_group(self, curr_list, threshold):
		# set previous as current list head
		current = curr_list
		prev = None
		nextelem = None
		count = 0


		# while curr is not None
		while current is not None and count < threshold:
			nextelem = current.next
			current.next = prev
			prev = current
			current = nextelem
			count += 1

		if nextelem is not None:
			curr_list.next = self.reverse_in_group(nextelem,threshold)
	
		return prev





# list1 = LinkedList()
# list1.push(3)
# list1.push(2)
# list1.push(1)

# list1.printlist()

# list2 = LinkedList()
# list2.push(8)
# list2.push(7)
# list2.push(6)
# list2.push(5)
# list2.push(4)

# list2.printlist()


# list1.merge(list2)
# list1.printlist()

# list2.printlist()

llist = LinkedList()
llist.push(9) 
llist.push(8) 
llist.push(7) 
llist.push(6) 
llist.push(5) 
llist.push(4) 
llist.push(3) 
llist.push(2) 
llist.push(1) 

llist.head = llist.reverse_in_group(llist.head,3)
llist.printlist()

