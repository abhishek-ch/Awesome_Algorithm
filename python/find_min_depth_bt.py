class Node:
	def __init__(self, value):
		self.data = value
		self.left = None
		self.right = None

def minimum_depth(root):
	# it has 3 properties 
	# if the root is None return 0
	if root is None:
		return 0

	# if left and right both are None then return 1
	if root.left is None and root.right is None:
		return 1

	# if root left is None, the recursion with root.right +1
	if root.left is None:
		return minimum_depth(root.right) + 1
	# if root right is None, the recursion with root.left +1
	if root.right is None:
		return minimum_depth(root.left) + 1

	# check the minimum of root<-left or root<-right and add +1
	return min(minimum_depth(root.left),minimum_depth(root.right)) + 1


def minimum_depth_level_order(root):
	pass
	# Level Order Traversing
	# define a queue and add root as first element and keep sum as 1
	result =1
	final_result = 0
	queue = []
	# define subqueue
	subqueue = []
	queue.append(root)
	sum_dict = {}
	sum_dict[result] = [root.data]
	
	# while queue has element
	while len(queue) > 0:
		curr = queue.pop()
	
		# print(curr.left, curr.right )
	# pop the element from queue and check it has children , add children(s) to subqueue
		if curr.left is not None:
			subqueue.append(curr.left)

		if curr.right is not None:
			subqueue.append(curr.right)


	# if any of the elem is missing either left/right child, you hit the depth, return the sum
		if curr.left is None or curr.right is None:
			print('Find the Depth First in %s'%result)
		# else:
		# 	result += 1

		if len(queue) <= 0:
			result += 1
			sum_dict[result] = [each.data for each in subqueue]
			queue.extend(subqueue)
			subqueue = []

	# if queue is empty, then append the subqueue to queue anc clear subqueue and increment the sum += 1
	return sum_dict


def uncovered_iterate_left(node, result):
	# if None return sum
	if node is None:
		return result
	
	# recursive iterate through left and keep on adding it
	return uncovered_iterate_left(node.left,result+node.data)

def uncovered_iterate_right(node, result):
	# if None return sum
	if node is None:
		return result
	
	# recursive iterate through left and keep on adding it
	return uncovered_iterate_left(node.right,result+node.data)

# def covered_iterate_left(node):
# 	if node is None:
# 		return result

# 	return node.data +  

# def covered_iterate(node):
# 	pass

def all_sum(node):
	if node is None:
		return 0

	return node.data + all_sum(node.left) + all_sum(node.right)

def covered_uncovered(node):
	total_uncovered_sum = node.data + uncovered_iterate_left(node.left,0) + uncovered_iterate_right(node.right,0)
	total_tree_sum = all_sum(node)

	print('Uncovered Sum %s Tree Sum %s '%(total_uncovered_sum,total_tree_sum))
	covered_sum = total_tree_sum - total_uncovered_sum

	return total_uncovered_sum == covered_sum




root = Node(1)
root.left = Node(2) 
root.right = Node(3) 
root.left.left = Node(4) 
root.left.right = Node(5)

# print(minimum_depth(root))
# print('The Minimum Depth level Order %s '%minimum_depth_level_order(root))



root = Node(8)  
root.left = Node(3)  

root.left.left = Node(1)  
root.left.right = Node(6)  
root.left.right.left = Node(4)  
root.left.right.right = Node(7)  

root.right = Node(10)  
root.right.right = Node(14)  
root.right.right.left = Node(13)

print(covered_uncovered(root))

