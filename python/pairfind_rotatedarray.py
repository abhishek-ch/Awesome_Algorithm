# Input

# Find the sum

# 11, 15, 6, 7, 9, 10
# Try to find the pivot for the above
arr = [4, 5, 6, 7, 8, 9, 1, 2, 3]

# def find_pivot <- left, right
def find_pivot(left,right):
	# if left == high then return left
	if left == right:
		return left

	# mid = left+high/2
	mid = (left+right)/2
	# if mid < high and arr_mid > arr_mid+1
	if mid < right and arr[mid] > arr[mid+1]:
		# return mid
		return mid
		# elif mid > low and arr_mid < arr_mid-1
	elif mid > left and arr[mid] < arr[mid-1]:
		# return mid-1
		return mid-1

	# if arr_low >= arr_mid
	if arr[left] >= arr[mid]:
		# find_pivot <- left, mid-1
		return find_pivot(left,mid-1)
	# find_pivot <- mid+1, right
	return find_pivot(mid+1,right)


# def find count of matching sum
def find_matching_count(expected):
	count = 0
	# pivot = find_pivot
	pivot = find_pivot(0,len(arr)-1)
	# left = pivot
	left = pivot
	# right = pivot+1
	right = pivot+1

	# while left is not equal to right
	while left != right:
		# value = arr of left + right
		value = arr[left] + arr[right]

		# if value is less than expected move right by 1 in rotation mode
		if value == expected:
			count += 1
			print('Found => %s , %s'%(left,right))
			if right == len(arr) - 1:
				# right = 0
				right = 0
			# else 
			else:
				# right += 1
				right += 1
		elif value < expected:
			# if right == len(arr)-1:
			if right == len(arr) - 1:
				# right = 0
				right = 0
			# else 
			else:
				# right += 1
				right += 1
		else:
			if left == 0:
				left = len(arr)-1
			else:
				left -= 1

	return count



# print(find_pivot(0,len(arr)-1))
print(find_matching_count(6))