#ifndef PROJECT2_H
#define PROJECT2_H

/**
 * Basic c++ program for parsing file data.
 * Attempts to open a pre-defined file, on fail,
 * asks user for custom filename. If found, all words
 * are read from the file, counted, saved as a list
 * in a file 'OutputArray.txt', alphabetized, and saved
 * to a second file 'OutputResults.txt'
 *
 * @author juanvallejo
 */

#include <iostream>
#include <fstream>
#include <sstream>
#include <stdio.h>
#include <algorithm>

const std::string DEFAULT_FILENAME 		= "TestData.txt";
const std::string OUTPUTARRAY_FILENAME 	= "OutputArray.txt";
const std::string OUTPUT_FILENAME 		= "Output.txt";

struct Word {
	std::string word;
	int count;
};

/**
 * Asks user for custom filename, reads file
 */
bool check_user_file();

/**
 * Default file to be parsed by the program
 */
bool check_default_file();

/**
 * Parses file using fsream
 */
bool parse_file(const std::string& filename);

/**
 * Split a string by a specified delimeter and only
 * return the amount of words found.
 */
int str_split(std::string& string, const char& delim);
int str_split_count(std::string& string, const char& delim);

bool parse_word(std::string& word);

/**
 * Return a lowercase copy of a string
 */
std::string toLower(std::string string);

/**
 * Sort an array alphabetically
 */
void sort_array();

#endif