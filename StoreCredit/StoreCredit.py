'''
 Author: Roderick Buenviaje
 Date: April 1, 2013
 Problem: Store Credit

 Problem
 You receive a credit C at a local store and would like to buy two items. 
 You first walk through the store and create a list L of all available items. 
 From this list you would like to buy two items that add up to the entire value of the 
 credit. The solution you provide will consist of the two integers indicating the 
 positions of the items in your list (smaller number first).
 
 Input
 The first line of input gives the number of cases, N. N test cases follow. 
 For each test case there will be:

 One line containing the value C, the amount of credit you have at the store.
 One line containing the value I, the number of items in the store.
 One line containing a space separated list of I integers. Each integer P indicates the price of an item in the store.
 Each test case will have exactly one solution.

 Output
 For each test case, output one line containing "Case #x: " followed by the indices 
 of the two items whose price adds up to the store credit. The lower index should 
 be output first.
 
 https://code.google.com/codejam/contest/351101/dashboard#s=p0
'''

import sys

class Basket:
    array = []

def recurseFunction(iCredits, aPrices, aPart):
    s = sum(aPart)
    # Check if the aPart sum is equals to iCredits
    if s == iCredits:
        if not Basket.array:
            # Save final answer
            Basket.array = aPart
        return
    elif s > iCredits:
        # Answer already obtained
        return

    for i in range(len(aPrices)):
        n = aPrices[i]
        rem = aPrices[i+1:]
        recurseFunction(iCredits, rem, aPart + [n])


if __name__ == '__main__':
    
    input = 'A-large'
    
    inputFile = open(input + ".in", 'r')
    outputFile = open(input + ".out", 'w')
    
    iInputN = int(inputFile.readline())
    for x in range(0, iInputN):
        # Clear array
        Basket.array = []
        iCredits = int(inputFile.readline())
        
        # This is not needed in python
        iNumItems = int(inputFile.readline())
        
        # Takes string and converts into an int array
        aInputOrig = map(int, inputFile.readline().split())
        
        # Grabs all the integers that are less than the total credit available
        # Sort valid prices to take fewest number of items
        aValidPrices = filter(lambda y:y<iCredits, aInputOrig)

        sCaseAnswer = "Case #%d:" % (x + 1)
        
        aValidPrices.sort(cmp=None, key=None, reverse=True)
        
        recurseFunction(iCredits, aValidPrices, list())
        
#        print FinalAnswer.array;
        for y in range(0, len(Basket.array)):
            iCurIndex = aInputOrig.index(Basket.array[y],)
            
            sCaseAnswer += " %d" % (iCurIndex + 1)
            # Clear value from list to make sure duplicate values choose different indexes
            aInputOrig[iCurIndex] = 0
            
        outputFile.write(sCaseAnswer + "\n")
        outputFile.flush()
    
    inputFile.close()
    outputFile.close()
    pass