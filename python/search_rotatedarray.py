# Take the onput as values {5, 6, 7, 8, 9, 10, 1, 2, 3};
# check for key 3

# try to find the pivot value
'''
	3, 4, 5, 6, 1, 2 it returns 3 
'''
# def find_pivot <- left , right
	# find the middle of the array , above starts with 0 and size of arr - 1
	# if right is less than right return left -1
	# if right === left ...

	# calculate mid

	# if mid > left and arr[mid] < arr[mid-1]
		# return mid-1
	# if middle is less than right and arr[mid] > arr[mid+1]
		# return mid
	# if arr of low is greater than/= arr of mid
		# call find pivot <- left, mid-1
	# find pivot <- mid+1, right

