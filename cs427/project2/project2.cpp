/**
 * Project2 - Computer Science 427
 * Juan Vallejo
 */

#define _CRT_SECURE_NO_DEPRECATE
#include "project2.h"

Word WORDS[1000];
Word SORTED_WORDS[1000];

int words_added = 0;
int words_remaining = 0;

// ask user for filename and parse
bool check_user_file() {

	std::cout << "Please enter a filename: ";

	std::string filename = "";
	std::cin >> filename;

	// determine if user input is valid
	if(filename == "X") {
		exit(0);
	} 

	FILE *file = fopen(filename.c_str(), "r");

	if(filename == "" || !file) {
		return check_user_file();
	} else if(filename == "X") {
		exit(0);
	}

	if(file) {
		fclose(file);
	}

	const std::string FILENAME = filename;

	// assume input was valid, attempt to parse file
	return parse_file(FILENAME);

}

bool check_default_file() {

	// open default file for reading
	FILE *file = fopen(DEFAULT_FILENAME.c_str(), "r");

	// determine if default file exists
	if(!file) {
		return check_user_file();
	}

	// assume file exists beyond this line
	// read file contents line by line
	if(file) {
		fclose(file);
	}

	return parse_file(DEFAULT_FILENAME);

}

// called once file exists
bool parse_file(const std::string& filename) {

	int wcount = 0;

	std::string line;
	std::fstream file(filename);

	for(;std::getline(file, line);) {
		if(line != "") {
			wcount += str_split(line, ' ');
		}
	}

	// open file for writing
	std::ofstream fOutputArray(OUTPUTARRAY_FILENAME);
	int fcount = 0;

	// write contents of our array to file
	for(fcount = 0; fcount < words_added; fcount++) {
		fOutputArray << WORDS[fcount].word << ": " << WORDS[fcount].count << std::endl;
	}

	words_remaining = words_added;

	// sort array alphabetically
	sort_array();

	return true;
}

// split string
int str_split(std::string& string, const char& delim) {

	int wcount = 0;

	std::string word;
	std::stringstream stream(string);

	for(;std::getline(stream, word, delim);) {
		parse_word(word);
		wcount++;
	}

	return wcount;
}

// count each word
bool parse_word(std::string& word) {

	int count = 0;
	int arr_size = sizeof(WORDS) / sizeof(WORDS[0]);

	bool init = false;

	for(;count < arr_size; count++) {
		if(WORDS[count].word == word) {
			WORDS[count].count++;
			init = true;
		}
	}

	if(!init) {
		WORDS[words_added].word = word;
		WORDS[words_added++].count = 1;
	}

	return true;

}

std::string toLower(std::string string) {
	std::transform(string.begin(), string.end(), string.begin(), ::tolower);
	return string;
}

void sort_array() {

	int count = 0;

	int wordamt = 0;
	std::string lowest = "";

	for(count = 0; count < words_added; count++) {

		bool word_exists = false;

		for(int i = 0; i < words_added - words_remaining && !word_exists; i++) {
			if(toLower(SORTED_WORDS[i].word) == toLower(WORDS[count].word)) {
				word_exists = true;
			}
		}

		if((lowest == "" || toLower(WORDS[count].word) < toLower(lowest)) && !word_exists) {
			lowest = WORDS[count].word;
			wordamt = WORDS[count].count;
		}
	}

	SORTED_WORDS[words_added - words_remaining].word = lowest;
	SORTED_WORDS[words_added - words_remaining].count = wordamt;
	words_remaining--;

	if(words_remaining > 0) {
		sort_array();
	} else {

		// done, save sorted array to file
		std::ofstream fOutput(OUTPUT_FILENAME);

		for(int c = 0; c < words_added; c++) {
			if(SORTED_WORDS[c].word != "") {
				fOutput << SORTED_WORDS[c].word << ": " << SORTED_WORDS[c].count << std::endl;
			}
		}
	}
}

int main() {
	check_default_file();
	return 0;
}