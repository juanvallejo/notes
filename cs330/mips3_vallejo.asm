#Simple Calculator in MIPS Assembly:
# Using MARS, write and debug a program that requests the user to input two numbers, The program then computes and prints their sum, difference, product, and quotient #(with remainder).  Use the SPIM system calls read_int (5, $a0).
# for input and output.
#
# Juan Vallejo

    .text
    .globl    main
        
main:  
     li    	$v0, 4			# Load syscall code for print_str.
     la    	$a0, askstr1		# Load address of string.
     syscall                		# Print string.
     
     li    	$v0, 5			# Load syscall code for read.
     syscall				# Get argument 1
     
     sw		$v0, firstNumber	# Store the first number in memory
     lw		$t0, firstNumber	# load first number from memory and store in temp register 0
     
     li 	$v0, 4			# tell syscall output will be a string
     la		$a0, askstr2		# load address of ask string
     syscall				# output ask string two
     
     li 	$v0, 5			# tell syscall to grab input
     syscall				# get user input, second number
     
     sw		$v0, secondNumber	# store input as secondNumber
     lw		$t1, secondNumber	# move secondNumber to temp register 1
     
     li		$v0, 4			# tell syscall we're outputting a string
     la		$a0, sumstr		# load address of ask string
     syscall				# output ask string
     
     li		$v0, 1			# tell syscall we're printing a number
     add 	$a0, $t0, $t1		# add two inputs and store in $a0 for outputting with syscall
     syscall				# output sum of two numbers
     
     li		$v0, 4			# tell syscall we're printing a string
     la		$a0, diffstr		# load difference string to $a0 for printing
     syscall				# print diffstr
     
     li		$v0, 1			# tell syscall we're printing a number
     sub	$a0, $t0, $t1		# subtract both inputs and store in $a0
     syscall				# print subtraction result
     
     li		$v0, 4			# tell syscall we're printing a string
     la		$a0, prodstr		# load product string to $a0 for printing
     syscall				# print product string
     
     li		$v0, 1			# tell syscall we're printing a number
     mult	$t0, $t1		# subtract both inputs and store in $a0
     mflo	$a0			# move product to $a0
     syscall				# print multiplication result
     
     li		$v0, 4			# tell syscall we're printing a string
     la		$a0, quotientstr	# load quotient string to $a0 for printing
     syscall				# print quotient string
     
     li		$v0, 1			# tell syscall we're printing a number
     div	$t0, $t1		# divide both inputs and store in $a0
     mflo	$a0			# move quotient to $a0 (no remainder)
     syscall				# print division result (no remainder)
     
     li		$v0, 4			# tell syscall we're printing a string
     la		$a0, period		# loads period into $a0
     syscall				# print a period

     li		$v0, 1			# tell syscall we're printing a number     
     mfhi 	$a0			# fetch division remainder and store in $a0
     syscall				# output remainder
     
     
     .data
	askstr1:	.asciiz "Enter an integer."
	askstr2:	.asciiz "Enter another integer."
	sumstr:		.asciiz "\nTheir sum is : "
	diffstr:	.asciiz "\nTheir difference is : "
	prodstr:	.asciiz "\nTheir product is : "
	quotientstr:	.asciiz "\nTheir quotient is : "
	
	period:		.asciiz "."
	
	firstNumber:	.word 0
	secondNumber:	.word 0
