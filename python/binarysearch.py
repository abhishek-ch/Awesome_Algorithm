# Input will be list
# Find the value
input_arr = [ 2, 3, 4, 10, 40 ] 
finder = 2


# def binary_search_util <- arr
def binary_search_util(arr, left, right):
	# find middle element based on size of the array = middle
	
	# check middle is >= 1
	print('left %s right %s '%(left,right))

	if right >= 1: 
		middle = left + (right-1)/2

		print('Middle %s value %s'%(middle,arr[middle]))

		# if middle elem == finder : Return Found
		if arr[middle] == finder:
			return True
		# if middle of sorted array is > finder
		if arr[middle] > finder:
			# return binary_search_util <- arr[middle:len(arr)]
			print('Item is less than middle %s'%(middle-1))
			return binary_search_util(arr,left,middle-1)
		# elif sorted array < finder
		elif arr[middle] < finder:
			# return binary_search_util <- arr[0:middle]
			return binary_search_util(arr,middle+1,right)
		else:
			return False
	else:
		return False

# def function binary_search
def binary_search():
	# define sorted array sort_arr by sorting the input
	sorted_arr = sorted(input_arr)
	# call binary_search_util
	return binary_search_util(sorted_arr,0,len(sorted_arr)-1)

print(binary_search())

