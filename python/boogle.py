# define the input

# expected list of output

'''
{{'G', 'I', 'Z'},
{'U', 'E', 'K'},
{'Q', 'S', 'E'}}


{"GEEKS", "FOR", "QUIZ", "GO"}

https://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/

'''

graph = [['G', 'I', 'Z'],
		 ['U', 'E', 'K'],
		 ['Q', 'S', 'E']]

input_list = ["GEEKS", "FOR", "QUIZ", "GO"]

result_set = set()


def main():
# for each of the index [x][y] iterate through neighbours and do DFS
	# for x in graph:
	visited = [[False for x in range(len(graph)+1)] for y in range(len(graph[0])+1)]

	for x in range(len(graph)):
		#    for y in col graph
		for y in range(len(graph[0])):
# 		define visited as size of array (X*X)
			dfs_utl(x,y,visited,'')				
 	# dfs_utl(0,0,visited,'')		
 	print('Final Matching Values are %s'%list(result_set))




def find_word_in_str(str):
	for each in input_list:
		if each in str:
			return (True,each)

	return (False,'')


# define dfs util function -> visited, x,y, res
def dfs_utl(i,j,visited,res):
# 	if x >= len(X) or y >= len(X)
	if i > len(graph)-1 or j > len(graph[0])-1 or i < 0 or j < 0 :
		return
	# 	visited -> True
	visited[i][j] = True

	# 	res += graph[x][y]
	
	res += graph[i][j]

	# print('Res is %s'%res)
	# 	check res contains any of the word in the list, if YES -> PRINT
	find_word_tuple = find_word_in_str(res)
	if find_word_tuple[0]:
		# print('Word Found is %s with String %s'%(find_word_tuple[1],res))
		result_set.add(find_word_tuple[1])

	# Looping through entire set


	# 8 steps of going/looping
# 	dfs util (x+1,y)
	if not visited[i+1][j]:
		dfs_utl(i+1,j,visited,res)
# 	dfs util (x-1,y)
	if not visited[i-1][j]:
		dfs_utl(i-1,j,visited,res)
# 	dfs util (x,y+1)
	if not visited[i][j+1]:
		dfs_utl(i,j+1,visited,res)
# 	dfs util (x,y-1)
	if not visited[i][j-1]:
		dfs_utl(i,j-1,visited,res)
	if not visited[i+1][j+1]:
		dfs_utl(i+1,j+1,visited,res)
	if not visited[i-1][j-1]:
		dfs_utl(i-1,j-1,visited,res)
	if not visited[i+1][j-1]:
		dfs_utl(i+1,j-1,visited,res)
	if not visited[i-1][j+1]:
		dfs_utl(i-1,j+1,visited,res)

# 	visited[x][y] = False
	visited[i][j] = False
# 	res = res[0:len(res)-1]
	res = res[0:len(res)-1]


main()
