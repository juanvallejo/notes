x = 0
y = 0
z = 0

def sub1():
	a = 1
	y = 1
	z = 1

	def sub2():
		a = 2
		x = 2
		c = 2

	def sub3():
		a = 3
		b = 3
		z = 3

	sub2()
	sub3()
	print(a,y,z)

sub1()