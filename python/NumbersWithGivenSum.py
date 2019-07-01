__author__ = 'achoudhary'

input = [1,2,4,7,11,15]
val = 26
pointer1 = 0
pointer2 = len(input)-1

found = False
while (not found):
    print(pointer1,pointer2)
    if pointer1 >= pointer2:
        break
    elif input[pointer1]+input[pointer2] < val:
        pointer1 += 1
    elif input[pointer1]+input[pointer2] > val:
        pointer2 -= 1
    elif input[pointer1]+input[pointer2] == val:
        found = True
        break



if found:
    print("Output is : ",input[pointer1]," & ",input[pointer2])
else:
    print("Not Possible")
