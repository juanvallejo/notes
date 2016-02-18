#!/usr/bin/env python

def encrypt(plaintext, key):
	enc_strNums = ""
	for i in plaintext:
		enc_strNums += chr((ord(i) ^ key))

	return enc_strNums

print encrypt(encrypt("test", 2), 3)

