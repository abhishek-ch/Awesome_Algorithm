# Find the middle element of the main array
# middle = (left+right)/2
	# Divide the array by the above
	# mergesort(arr,left, middle)
	# mergesort(arr,middle+1,right)
	# for each of the array, keep on dividing it untill it reaches 1
		# once 1, compare and sort the value and then start merging

# define mergesort <- arr
def mergesort(arr):
	if len(arr) > 1:
		# get middle by diving with length of array <- mid
		mid = len(arr)//2
		# extract left array arr[:mid]
		left = arr[:mid]
		# extract right arrat arr[mid:]
		right = arr[mid:]

		# re-call mergesort by left array
		mergesort(left)
		# re-call mergesort by right array
		mergesort(right)

		# Merge Starts From here
		# define i,j, k = 0
		i = j = k = 0
		# while i is less than len of left and j is less than len of right
		while i < len(left) and j < len(right):
			# if left <- i is less than right<-j
			if left[i] < right[j]:
				# arr[k] = left <- i
				arr[k] = left[i]
				i += 1
				# i += 1
			# else 
			else:
				# arr[k] = right <- j
				arr[k] = right[j]
				j += 1
				# j += 1
			# k += 1
			k += 1

		# while i is less than len<- left
		while i < len(left):
			# arr[k] = left[i]
			arr[k] = left[i]
			i += 1
			k += 1

		while j < len(right):
			# arr[k] = left[i]
			arr[k] = right[j]
			j += 1
			k += 1


arr = [12, 11, 13, 5, 6, 7]
mergesort(arr)
print(arr)