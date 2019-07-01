# Define Graph
# define V (length of all elements)
'''
graph = [ [0, 2, 0, 6, 0], 
        [2, 0, 3, 8, 5], 
        [0, 3, 0, 0, 7], 
        [6, 8, 0, 0, 9], 
        [0, 5, 7, 9, 0]] 
'''

import sys
graph =[[0, 2, 0, 6, 0], 
        [2, 0, 3, 8, 5], 
        [0, 3, 0, 0, 7], 
        [6, 8, 0, 0, 9], 
        [0, 5, 7, 9, 0]]
V = 5
INF = 99999
minset = [False for each in range(V)]
key = [INF for each in range(V)]
parent = [INF for each in range(V)]

# find minimum value not part of min set
# def min_value(minset, keyset)
def min_value(minset,keyset):
	# curr_min = sys.maxsize
	curr_min = sys.maxsize
	# curr_idx = sys.maxsize
	curr_idx = sys.maxsize
	# loop through range (V)
	for idx in range(V):
		# if not minSet[idx]  and keyset[idx] < curr_min:
		if not minset[idx] and keyset[idx] < curr_min:
			# curr_idx
			curr_min = keyset[idx]
			curr_idx = idx
	# return curr_idx
	return curr_idx

# Define minSet which contains all visited vertices with empty {}
# define parent,key = len V
# set key[0] = 0 and rest INF(99999)
key[0] = 0
parent[0] = 0
# while len of minSet != V:
while not all(minset):
	# X = min_value():
	x = min_value(minset,key)
	print('Current Minimum %s'%(x))
	# minSet[X] = True
	minset[x] = True
	# for idx of the vertices
	for idx in range(V):
		# if each != idx and value of idx != 0
		curr_value = graph[x][idx]
		if key[idx] > curr_value and curr_value > 0:
			# if curr_value < key[idx]:
				# key[idx] = graph[each][idx]
			key[idx] = curr_value
			# parent[idx] = each
			parent[idx] = idx

print(key)
print(parent)
