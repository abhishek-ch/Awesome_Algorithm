# Define graph
# Define V

from collections import defaultdict
# define function for adding graph
'''
1 - 0
0 - 2
2 - 1
0 - 3
3 - 4
'''

V = 5
# add Graph in defaultDict
graph = defaultdict(list)
graph[1].append(0)
graph[0].append(2)
graph[2].append(0)
graph[0].append(3)
graph[3].append(4)

# Because its Acyclic Graph
graph[0].append(1)
graph[2].append(0)
graph[0].append(2)
graph[3].append(0)
graph[4].append(3)

# def dfs util --> idx,visited, parent
def dfs_util(vertex, visited, parent):
	# visited set to True
	visited[vertex] = True
	# for each of the neighbour of the graph idx
	for neighbour in graph[vertex]:
		# if not visited
		if not visited[neighbour]:
			# dfs util
			dfs_util(neighbour,visited,vertex)
		# elif parent != each:
		elif parent != neighbour:
			print('Parent %s neighbour %s '%(parent,neighbour))
			# found Cycle/True 
			return True
	# Returns False
	return False


# def dfs
def dfs():
	# define visited as False of all elements of V
	visited = [False for each in range(V)]
	# call dfs util

	if(dfs_util(0,visited, -1)):
		print('Cycle Found !!!')
	else:
		print('No Cycle Found ')


dfs()
