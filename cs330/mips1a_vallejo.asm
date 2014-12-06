# Comment giving name of program and description of function
# Template.s
# Bare-bones outline of MIPS assembly language program


		.text				#instructions follow this line
__start:	la	$t0, 268501056		#load base address of array into register $t0
		li	$t1, 13			#$t1 = 000000d ("load immediate")
		sw	$t1, 4($t0)		#set A[4] to 000000d
		li	$t1, 100		#$t1 = 00000064 ("load immediate")
		sw	$t1, -16($t0)		#set A[10] to $t1
		li	$t1, 15			#$t1 = 000000f ("load immediate")
		sw	$t1, 24($t0)		#set A[18] to $t1 
		li	$t1, 4294967295		#$t1 = 4294967295 ("load immediate")
		sw	$t1, 40($t0)		#set A[8] to $t1

#End of program. Blank line left at the end.
