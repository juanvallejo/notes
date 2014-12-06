# Mips assignment 2. Sorts given array of n numbers.
# Does bubble sort swapping from the end of the array.
# mips2_Vallejo.asm

		.data
Array: 		.word	14,12,13,5,9,11,3,6,7,10,2,4,8,1

		.text
		.globl	main
	
main:
		li	$a0, 14			# parameter n = # values to sort
		sll	$a0, $a0, 2		# number of bytes in Array A
		li 	$t2, 1			# flag holds 0 if swap occurs, 1 if no swap
		li 	$t0, 0			# stores current index of iterator (j)
		
sub_routine1:
		beq 	$a0, 4, reset		# checks to see if we are at end of array, starts at beginning again if not 
		lw 	$t4, Array + 4($t0)	# store A[j+1] in $t4
		lw 	$t5, Array($t0)		# store A[j] in $t5
		bgt 	$t4, $t5, no_swap	# if A[j+1] is greater than A[j], then there is no need to swap
		blt 	$t4, $t5, swap		# if next item in array is less than current item, swap the items

no_swap:					
		addi	$a0, $a0, -4		# subtracts four from $a0, to account for array items parsed
		addi 	$t0, $t0, 4		# iterate to the next item in our array (add 4 to current index)
		b 	sub_routine1		# goes back to the sub routine
			
swap:
		sw 	$t4, Array($t0)		# stores value previously stored in $t5 (current index) in $t4
		sw 	$t5, Array + 4($t0)	# stores value previously stored in $t4 (current index + 1) in $t5
		addi 	$a0, $a0, -4		# subtracts 4 from $a0 to account for array items parsed
		addi	$t0, $t0, 4		# iterate to the next item in our array (add 4 to current index)
		li 	$t2, 0			# sets $t2 flag = 0 to indicate that a swap occurs
		b 	sub_routine1		# goes back to the sub routine
	
					
reset:
		beq 	$t2, 0, main		# if a swap has happened, start over at beginning of array
