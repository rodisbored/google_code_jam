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

'''
# This code is currently not needed since I misread the problem, but will be saved for future use.

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
'''

def chooseItems(iCredits, aPrices):
    # We do not check to see if one item equals the credits since we are told we will be getting
    # two items
    for i in aPrices:
        for j in reversed(aPrices):
            if i + j == iCredits:
                return [i] + [j]
        

if __name__ == '__main__':
    
    inName = 'A-small'
#    inName = 'A-large'
    
    inputFile = open(inName + ".in", 'r')
    outputFile = open(inName + ".out", 'w')
    
    iInputN = int(inputFile.readline())
    for x in range(0, iInputN):
        # Clear array
#        Basket.array = []
        iCredits = int(inputFile.readline())
        
        # This is not needed in python
        iNumItems = int(inputFile.readline())
        
        # Takes string and converts into an int array
        aInputOrig = map(int, inputFile.readline().split())
        
        # Grabs all the integers that are less than the total credit available
        # Sort valid prices to take fewest number of items
        aValidPrices = filter(lambda y:y<iCredits, aInputOrig)
        aValidPrices.sort(cmp=None, key=None, reverse=True)
        
        sCaseAnswer = "Case #%d:" % (x + 1)
    
        chosenItems = chooseItems(iCredits, aValidPrices)
        
        # Ensures we do not have an empty set before referencing the array
        if chosenItems:
            iIndex1 = aInputOrig.index(chosenItems[0])
            # Clear value from list to make sure duplicate values choose different indexes
            aInputOrig[iIndex1] = 0
            
            iIndex2 = aInputOrig.index(chosenItems[1])
            
            if iIndex1 < iIndex2:
                sCaseAnswer += " %d %d" % (iIndex1+1, iIndex2+1)
            else:
                sCaseAnswer += " %d %d" % (iIndex2+1, iIndex1+1)
        
        outputFile.write(sCaseAnswer + "\n")
        outputFile.flush()
    
    inputFile.close()
    outputFile.close()
    pass