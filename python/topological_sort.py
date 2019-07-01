from collections import defaultdict 
# Define Graph
graph = defaultdict(list)
graph[5].append(2)
graph[5].append(0)
graph[4].append(0)
graph[4].append(1)
graph[2].append(3)
graph[3].append(1)

# Define length of the Graph
V = 6


# def top_sort_util:
def topoligical_sort_util(vertex,visited,stack):
	# visited[x] = True
	visited[vertex] = True
	# for each in graph (loop thorugh all neighbours)
	for each in graph[vertex]:
		# if not visited:
		if not visited[vertex]:
			# top_sort_util(stack,visited, each)
			top_sort_util(each,visited,stack)

	# stack.insert as first item
	stack.insert(0,vertex)

# def function top_sort()
def topological_sort():
	# set visited to len of V and set it as False
	visited = [False for each in range(V)]
	# define stack = [] (array)
	stack = []
	# loop through V
	for idx in range(V):
		# if not visited[idx]:
		if not visited[idx]:
			# top_sort_util(stack,visited, index)
			topoligical_sort_util(idx,visited,stack)

	print("Sorted Path is %s"%(stack))


topological_sort()


