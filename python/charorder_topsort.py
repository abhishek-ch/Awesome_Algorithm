# Inputs are words
# Expected in sequence if characters in ascending Order

# b a a
# a b c d
# a b c a
# c a b
# c a d
from collections import defaultdict
words = ["caa", "aaa", "aab"]
V = 3

# define a 2D graph with rows and columns equal to the size of first word : graph
graph = defaultdict(list)


# topoligical_sort_util(vertex,visisted,stack):
def topological_sort_util(vertex,visited,stack):
	# visited[vertex] = True
	visited[vertex] = True
	# for each in graph[vertex]:
	for idx in graph[vertex]:
		# if not visited[each]:
		if not visited[idx]:
			# topoligical_sort_util(each,visited,stack)
			topological_sort_util(idx,visited,stack)
	# stack.insert(0,vertex)
	stack.insert(0,vertex)




# topilogical Sort
# def top_sort():
def topological_sort():
	# visited = [False for each in range(V)]
	visited = [False for idx in range(V)]
	# stack = []
	stack = []
	# for idx in range(V):
	for idx in range(V):
		# if not visited[idx]:
		if not visited[idx]:
			# topoligical_sort_util(idx,visisted,stack)
			topological_sort_util(idx,visited,stack)

	print(stack)
	# for each in range(len(stack)):
	# 	print(chr(int(each)+97))
	


# def function to create elements in graph
def build_graph():
	# for each pair of words in sequence
	for idx in range(len(words)-1):
		word1 = words[idx]
		word2 = words[idx+1]
		# if word1[i] != word2[i]:
		for each in range(min(len(word1),len(word2))):
			if word1[each] != word2[each]:
				# graph[Firs Word Index][ord(word1[i])-ord('a')] = [ord(word2[i])-ord('a')]
				graph[ord(word1[each])-ord('a')].append(ord(word2[each])-ord('a'))
	topological_sort()

build_graph()




