class Node:
	def __init__(self,data):
		self.data = data
		self.left = None
		self.right = None
		


class Solution:

	"""
	1. Node Only
	2. Max of Left Child + Node
	3. Max of Right Child + Node
	4. Max of left Child + Node + Max of Right Child
	"""


	def max_path_sum(self,node):
		if node is None:
			return 0

		left_val = self.max_path_sum(node.left)
		right_val = self.max_path_sum(node.right)

		parent_max = max(max(left_val,right_val)+node.data,node.data)
		self.ans = max(self.ans, max(parent_max, node.data+left_val+right_val))

		return parent_max

def is_full_path(node):
	# check if the node is None return its True
	if node is None:
		return True

	if node.left is None and node.right is None:
		return True

	# if node has left and node.right
	if node.left is not None and node.right is not None:
		return (is_full_path(node.left) and is_full_path(node.right))
		# recursion with node.left
		# recursion with node.right
	return False


def top_view_util(node,curr_value,max_left,max_right):
	if node is None:
		return

	# if curr_value < max_left or curr_value > max_right:
	# 	print('Max_right %s data %s curr_value %s'%(max_right,node.data,curr_value))
	# 	print(node.data)


	# print('Max_right %s data %s curr_value %s'%(max_right,node.data,curr_value))
	if curr_value < max_left:
		print(node.data)
		print('left inside %s with current %s'%(max_left,curr_value))
		max_left = min(max_left,curr_value)				
	elif curr_value > max_right:
		print(node.data)
		print('Max_right inside %s with current %s'%(max_right,curr_value))
		max_right = max(max_right,curr_value)
	

	if node.left is not None:
		top_view_util(node.left,curr_value-1,max_left,max_right)

	if node.right is not None:
		top_view_util(node.right,curr_value+1,max_left,max_right)



def top_view_tree(node):
	
	if node is None:
		return
	# define a queue and add the root node with current_value as 0 and max_left & max_right with all 0
	queue = [node]
	print(node.data, end = ' ')
	# define all the constants
	current_value = [0]
	max_left = 0
	max_right = 0

	# while queue is not empty
	while len(queue) > 0:
		# pop first element from queue
		element = queue.pop()
		this_value = current_value.pop()

		# if node has left element
		if element.left is not None:
			# print('Element Left %s'%(element.left))
			# if current_value -= 1 is les than max_left print and set the max_left to current_value
			left_value = this_value - 1
			if left_value < max_left:
				print(element.left.data, end = ' ')
				max_left = left_value

			# q.append node <- left
			queue.append(element.left)
			current_value.append(this_value-1)


		# if node has right element
		if element.right is not None:
			# if current_value -= 1 is les than max_left print and set the max_left to current_value
			right_value = this_value + 1
			if right_value > max_right:
				print(element.right.data, end = ' ')
				max_right = right_value

			# q.append node <- left
			queue.append(element.right)
			current_value.append(this_value+1)


def remove_nodes_with_length_util(node,level,k):
	if node is None:
		return None

	node.left = remove_nodes_with_length_util(node.left,level+1,k)
	node.right = remove_nodes_with_length_util(node.right,level+1,k)

	if node.left is None and node.right is None and level < k:
		return None

	return  node


def remove_nodes_with_length_less_than(node,k):
	return remove_nodes_with_length_util(node,1,k)


def inorder(node):
	if node is not None:
		inorder(node.left)
		print(node.data, end = ' ')
		inorder(node.right)


def sum_root_leaf_path_util(node,result):
	
	# if node is none return none
	if node is None:
		return 0

	# if node is not None, then add the 
	'''
	6 -> 60 -> 600 
	6 -> (3+60) -> (2 + 630)

	6 -> (3+60) -> (5+630) -> (4+6350)
	'''
	result = node.data + result*10

	# if node <- left and <- right is None, thats where path ends return the val
	# found the leaf child so return
	if node.left is None and node.right is None:
		return result
	
	# call left and right with sum 
	return sum_root_leaf_path_util(node.left,result) + sum_root_leaf_path_util(node.right,result) 


def sum_root_leaf_path(node):
	return sum_root_leaf_path_util(node,0)


# Driver program  
root = Node(10) 
root.left = Node(2) 
root.right   = Node(10)
root.left.left  = Node(20)
root.left.right = Node(1) 
root.right.right = Node(-25)
root.right.right.left   = Node(3)
root.right.right.right  = Node(4)
root.right.right.right.left  = Node(40)
root.right.right.right.left.left  = Node(41)

sol = Solution()
sol.ans = -float("inf")
# print(sol.ans)



# Driver Program 
root = Node(10); 
root.left = Node(20); 
root.right = Node(30); 
  
root.left.right = Node(40); 
root.left.left = Node(50); 
root.right.left = Node(60); 
root.right.right = Node(70); 
  
root.left.left.left = Node(80); 
root.left.left.right = Node(90); 
root.left.right.left = Node(80); 
root.left.right.right = Node(90); 
root.right.left.left = Node(80); 
root.right.left.right = Node(90); 
root.right.right.left = Node(80); 
root.right.right.right = Node(90); 

# print(is_full_path(root))
root = Node(1)
root.left = Node(2)
root.right = Node(3)
root.left.right = Node(4)
root.left.right.right = Node(5)
root.left.right.right.right = Node(6)

# top_view_tree(root)

root = Node(1)  
root.left = Node(2)  
root.right = Node(3)  
root.left.left = Node(4)  
root.left.right = Node(5)  
root.left.left.left = Node(7)  
root.right.right = Node(6)  
root.right.right.left = Node(8)  
# print("Inorder Traversal of Original tree" )  
# inorder(root) 
# result_node = remove_nodes_with_length_less_than(root,4)
# print()
# print("Inorder Traversal of Final tree" )  
# inorder(result_node)

root = Node(6) 
root.left = Node(3) 
root.right = Node(5) 
root.left.left = Node(2) 
root.left.right = Node(5) 
root.right.right = Node(4) 
root.left.right.left = Node(7) 
root.left.right.right = Node(4) 
print('Sum of all paths is %s'%(sum_root_leaf_path(root)))




