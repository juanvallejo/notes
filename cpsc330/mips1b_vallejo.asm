# Comment giving name of program and description of function
# Template.s
# Bare-bones outline of MIPS assembly language program


		.data
scores:		.word	145, 95, 92, 85, 100, 81, 90, 75, 99, , -124, 82, 79, -1
avgstr:		.asciiz	"The average is "
remstr:		.asciiz " with a remainder of "

		.text					#instructions follow this line
		.globl	main				#init global variables
main:		
__start:	la	$a0, scores			#load base address of scores into the register $a0
		add 	$t0, $zero, $zero		#keep count of sores that have been iterated
		add 	$t1, $zero, $zero		#our total score keeper
		addi	$t4, $zero, -1			#init temp register 4 with value of -1
		
getscr:		lw	$t2, 0($a0)			#assign the current value of the scores array to $t2
		beq	$t2, $t4, nomore		#if $t2 == -1, then we have reached the end of our array
		addi	$a0, $a0, 4			#jump one index ahead in our array
		slti	$t3, $t2, -1			#check to see if our current array value is less than zero
		bne	$t3, $zero, getscr		#if the current value is less than zero, skip the value
		slti	$t3, $t2, 101			#check to see if current array is still zero
		beq	$t3, $zero, getscr		#check that the number is less than 100
		add	$t1, $t1, $t2			#if current value is valid, add it to the score count
		addi	$t0, $t0, 1			#add 1 to the current score count
		j	getscr				#loop through again by jumping to the getscr label
		
nomore:		li	$v0, 4				#set the service to 4 to print strings on syscall
		la	$a0, avgstr			#load the address of the ascii string into $a0
		syscall					#print out what we have so far
		div	$t1, $t0			#calculate the score average
		mflo	$a0				#store the quotient from the average calculation in $a0
		move 	$a1, $a0			#move the quotient from the average calculation to $a1
		li	$v0, 1				#set the service to 1 to print integers on syscall
		syscall					#output what we have so far
		li	$v0, 4				#set the service to 4 to print strings on syscall
		la	$a0, remstr			
		syscall					#output our modifications to $v0
		mfhi	$a0				#get the remainder and store it in $a0
		#move 	$a2, $a0			#move the remainder value from $a0 to $a2
		li	$v0, 1				#set the service to 1 to print integers on syscall
		syscall					#output what we have so far
		j end					#jump to our end label

end:		nop					#end with a null operation

		
		
		
		
		
		
