# Temperature converter
# program that prompts a user to choose to "a. Convert Fahrenheit to Celsius, or b. Convert Celsius to Fahrenheit."
#
# Juan Vallejo

	.text
	.globl main

main:
	li 	$v0, 4				# load syscall for output type string
	la 	$a0, askConvertMode		# load address of initial string
	syscall					# print input string
	li	$v0, 5				# load syscall for string input
	syscall					# receive input temperature
	beq	$v0, 1, toCelcius		# if a 1 is entered, call toCelcius method
	beq	$v0, 2, toFarenheit		# if a 1 is entered, call toFarenheit method
	b 	main				# loop to beginning to ask for more tempteratures
	
toCelcius:
	li 	$v0, 4				# load syscall for type string
	la 	$a0, askTemperatureFarenheit	# load address of temperature input string
	syscall					# ask user to enter temperature
	li 	$v0, 6				# load syscall for input type float
	syscall					# get float input and store in $f0
	l.s	$f1, farenheitConvertFactor1	# load floating point conversion into $f1 (single precision)
	l.s	$f2, farenheitConvertFactor2	# load floating point conversion into $f2 (single precision)
	l.s	$f4, farenheitConvertFactor3	# load floating point conversion into $f4 (single precision)
	add.s	$f3, $f0, $f1			# add user input and first conversion
	mul.s	$f3, $f3, $f2			# multiply $f3 value by 100
	div.s	$f3, $f3, $f4			# divide our new value in $f3 by 180
	mov.s	$f12, $f3			# move result in celcius to $12 to allow for its output
	li	$v0, 2				# load syscall for type float
	syscall					# output floating point value stored in $f12
	
	# print Celcius unit
	li	$v0, 4				# syscall for type string
	la	$a0, responseUnitC		# print string with unit for celcius
	syscall
	
	# see if temperature is cold
	l.s	$f1, celciusColdCutoff		# load floating point conversion into $f1 (single precision)
	c.lt.s	$f3, $f1			# see if current tempterature is less than constant for cold temperature
	bc1f	isCelciusHot			# if computed celcius temperature is not less than cold temp cutoff, see if it's hot
	li 	$v0, 4				# load syscall for type string
	la 	$a0, responseCold		# load string into $a0 for printing
	syscall					# since branch command did not branch, assume temperature is indeed colder than -17.78

toFarenheit:
	li 	$v0, 4				# load syscall for type string
	la 	$a0, askTemperatureCelcius	# load address of temperature input string
	syscall					# ask user to enter temperature
	li 	$v0, 6				# load syscall for input type float
	syscall					# get float input and store in $f0
	l.s	$f1, celciusConvertFactor1	# load floating point conversion into $f1 (single precision)
	l.s	$f2, celciusConvertFactor2	# load floating point conversion into $f2 (single precision)
	l.s	$f4, celciusConvertFactor3	# load floating point conversion into $f4 (single precision)
	mul.s	$f3, $f3, $f4			# multiply $f3 value by 180
	div.s	$f3, $f3, $f2			# divide our new value in $f3 by 100
	add.s	$f3, $f0, $f1			# add user input and 32
	mov.s	$f12, $f3			# move result in celcius to $12 to allow for its output
	li	$v0, 2				# load syscall for type float
	syscall					# output floating point value stored in $f12
	
	# print Farenheit unit
	li	$v0, 4				# syscall for type string
	la	$a0, responseUnitF		# output farenheit unit
	syscall
	
	# see if temperature is cold
	l.s	$f1, farenheitColdCutoff	# load floating point conversion into $f1 (single precision)
	c.lt.s	$f3, $f1			# see if current tempterature is less than constant for cold temperature
	bc1f	isFarenheitHot			# if computed celcius temperature is not less than cold temp cutoff, see if it's hot
	li 	$v0, 4				# load syscall for type string
	la 	$a0, responseCold		# load string into $a0 for printing
	syscall					# since branch command did not branch, assume temperature is indeed colder than 0.0
	
isCelciusHot:
	l.s 	$f1, celciusHotCutoff		# load floating point conversion into $f1 (single precision); hot temp cutoff
	c.lt.s	$f3, $f1			# see if computed temperature is less than 48.89
	bc1f	outputTemperatureIsHot		# if computed celcius temperature is not less than hot temp cutoff, print
	b 	exit

isFarenheitHot:
	l.s	$f1, farenheitHotCutoff		# load farenheit max hot constant into $f1
	c.lt.s	$f3, $f1			# see if computed tempterature in farenheit is less than $f1
	bc1f	outputTemperatureIsHot		# if computed temperature is not less than hot ceiling temp, output that it's hot
	b 	exit

exit:
	li	$v0, 10				# load
	syscall
	

outputTemperatureIsHot:
	li	$v0, 4				# load syscall for type string
	la	$a0, responseHot		# output string for when temperature exceeds hot limit
	syscall					# print string
	b 	exit
	
.data
	#input requests
	askConvertMode:			.asciiz "Enter 1 to convert Fahrenheit to Celsius, or 2 to convert Celsius to Fahrenheit. "
	askTemperatureCelcius:		.asciiz "Enter a temperature in Celcius. "
	askTemperatureFarenheit:	.asciiz "Enter a temperature in Farenheit. "
	
	#responses
	responseCold:			.asciiz "\nThat\'s Cold!"
	responseHot:			.asciiz "\nThat\'s Hot!"
	responseCelsius:		.asciiz "The temperature in Celsius is "
	responseFarenheit:		.asciiz "The temperature in Farenheit is "
	responseUnitF:			.asciiz " F"
	responseUnitC:			.asciiz " C"
	
	#conversion factors - toFarenheit method
	celciusConvertFactor1:		.float 32	# degrees to subtract from farenheit temperature
	celciusConvertFactor2:		.float 100	# conversion factor 2
	celciusConvertFactor3: 		.float 180	# conversion factor 3
	
	#conversion factors - toCelcius method
	farenheitConvertFactor1:	.float -32	# degrees to subtract from farenheit temperature
	farenheitConvertFactor2:	.float 100	# we then multiply by 100 as part of our equation
	farenheitConvertFactor3:	.float 180	# finally, divide by this number to get result in celcius
	
	# temperature extreme cutoffs - celcius
	celciusColdCutoff:		.float -17.78	# constant holding floor of temperate temperature
	celciusHotCutoff:		.float 48.89	# constant holding roof of temperate temperature

	# temperature extreme cutoffs - farenheit
	farenheitColdCutoff:		.float 0.0	# constant holding floor of temperate temperature
	farenheitHotCutoff:		.float 120	# constant holding roof of temperate temperature