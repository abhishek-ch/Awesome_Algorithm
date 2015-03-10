__author__ = 'abhishekchoudhary'

# consider any N input
#example N = 30

#name O(n) = N add a dictionary with all values as Prime
N = 130
i =j= 2 #first prime number
dict = {}
while (i < N):
    dict[i] = True
    i += 1


#iterate through each of the elements and find the unmarked element
while j < N:

    if dict[j]:
        k = j
        while k+j < N:
            k += j
            dict[k] = False
    if dict[j]:
        #Displays the Prime Number
        print("Prime Number ",j)
    j += 1





