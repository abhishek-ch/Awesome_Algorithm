__author__ = 'achoudhary'

inputs = ["Abhishekzrpoiepooliooopabcdefghijklmn","abcabaabccfdsaewer","Abhishek","CharliedaadaabcdefghijklmnmbrijeshoAlphaBeta","AABCdefghhijkl"]


for input in inputs:
    index = 0
    charFreqIndex = {}
    currIndex = 0
    maxsize = 0

    pointA = 0
    pointB = 0
    #iterate through each character
    for char in input:

        if not charFreqIndex.has_key(char):
            values = [index]
            charFreqIndex[char] = values
           # print("NA ",char,' = ',index)

        else:
            #fetch all the values of the current available list of particular key
            subvalues = charFreqIndex.get(char)
            lastAvailableIndex = subvalues[-1] #get the last value
            #print(char,subvalues)

            #if last index for a character in the list is more than the previous sstarting point of pointer
        #that means we have found the repeating character
            if lastAvailableIndex >= currIndex:
                size = (index-1) - currIndex
                #change the A and B point to get the new pointers
                if maxsize < size:
                    pointA = currIndex
                    pointB = index-1
                maxsize = max(maxsize,size)

                currIndex = index

            subvalues.append(index)
            charFreqIndex[char] = subvalues

        #increase counter
        index += 1

        #fail safe condition if last element in the list is non repeating ,
        #logic doesn't found any break point to stop
        size = index - currIndex
        if maxsize < size:
            pointA = currIndex
            pointB = index
        maxsize = max(maxsize,size)


    print("Input ",input," MAXSIZE ",maxsize, "Output ",input[pointA:pointB])