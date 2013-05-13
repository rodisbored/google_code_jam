//============================================================================
// Name        : AlienLanguage.cpp
// Author      : Roderick Buenviaje
// Version     :
// Copyright   : RB2013
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <iterator>
#include <sstream>
#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <string.h>

#include <regex>
#include <regex.h>

using namespace std;

int main(int argc, char *argv[])
{
	ifstream inFile;
	ofstream outFile;
	string sFileName;
	string sCurLine;

	int iWordCount = 0;
	int iCaseNum = 1;

	vector<string> vsNumValues;
	vector<string> vsKnownWords;

	if (argc == 1)
	{
		sFileName = "A-small.in";
	}
	else
	{
		sFileName = argv[1];
	}

	inFile.open(sFileName.c_str());

	if (inFile.fail())
	{
		cout << "File could not be opened. Exiting." << endl;
		return 1;
	}

	sFileName.replace(sFileName.find_last_of('.'), 4, ".out");
	outFile.open(sFileName.c_str());

	getline(inFile, sCurLine);

    istringstream iss(sCurLine);

    while (iss >> sCurLine)
    {
    	vsNumValues.push_back(sCurLine);
    }

    int iWordLength = atoi(vsNumValues.at(0).c_str());
    int iKnownWords = atoi(vsNumValues.at(1).c_str());
    int iTestCases = atoi(vsNumValues.at(2).c_str());

	for (int i = 0; i < iKnownWords; i++)
	{
		getline(inFile, sCurLine);
		vsKnownWords.push_back(sCurLine);
	}

	while ((!inFile.eof()) &&
			(iCaseNum <= iTestCases))
	{
		getline(inFile, sCurLine);

		if (sCurLine.find('(') != std::string::npos)
		{
			replace(sCurLine.begin(), sCurLine.end(), '(', '[');
			replace(sCurLine.begin(), sCurLine.end(), ')', ']');
		}

		regex rgx(sCurLine.c_str(), std::regex_constants::extended);

		regex_t p;
		int rcomp_err;
		regmatch_t pmatch[2];

		rcomp_err = regcomp(&p, sCurLine.c_str(), REG_EXTENDED);

		for (int i = 0; i < vsKnownWords.size(); i++)
		{

			if (regexec(&p, vsKnownWords[i].c_str(), 1, pmatch, 0) != REG_NOMATCH)
			{
				iWordCount++;
			}
		}

		outFile << "Case #" << iCaseNum << ": " << iWordCount << endl;
		iCaseNum++;
		iWordCount = 0;
	}


	inFile.close();
	outFile.close();

//	copy(vsKnownWords.begin(), vsKnownWords.end(), ostream_iterator<string>(cout, " "));

	return 0;
}
