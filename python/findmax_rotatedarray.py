# Input array
arr = [5, 6, 1, 2, 3, 4] 
# Expected Max

# binary search approach to find the output.

# def a function <- left , right
def find_maximum(left, right):
	# if left is greater than right then return -1 (should not happen)
	if left > right:
		return -1
	# if left is equal to right then return the index of left
	if arr[left] == arr[right]:
		return arr[left]
	# calculate middle element, left + (high - left) // 2
	mid = left + (right - left) // 2

	# if mid > left and arr[mid - 1] > arr[mid]
	if mid > left and arr[mid - 1] > arr[mid]:
		# found the maximum element , return that mid -1
		return arr[mid-1]	
	# elif mid < right and arr[mid + 1] < arr[mid]	
	elif mid < right and arr[mid + 1] < arr[mid]:
		# found the maximum element mid
		return arr[mid]

	# if arr[mid] > arr[left]
	if arr[mid] > arr[left]:
 		 # call function < mid+1, right
 		 return find_maximum(mid+1,right)
	# else
	else:
		# call function <- low, mid -1 
		return find_maximum(left,mid-1)


print(find_maximum(0,len(arr)-1))