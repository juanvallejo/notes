330 Exam Practice
=============

####Definitions
- `NUMA` - Non-uniform memory access
    - How parallel processors share data
    - Single address space (NUMA v. SMP)
        - Message passing
            - Communicate by passing messages over LAN
- `Spacial Locality` - Memory locations are used multiple times; tendency to reference data that is close to other data
- Finite State Machine - A machine with a fixed number of states; meaning its output depends on current state
- Data Hazard in pipelining - occurs when the value of a register is used before such value has actually been assigned.
- EPC - Exception program counter - keeps track of the number of instruction that caused an exception
- Pipelining in the processor - using one full clock cycle to execute more than one instruction stage.


####MIPS
- `lw $s1, 100($2)` -> $s1 = Memory[$s2 + 100 / 4] 
- `slt $t0, $s0, $s1 -> if ($s0 < $s1) $t0 = 1
- `jr` $t1 -> jump via register to address specified by $s1
- `div` -> `mflo` gets quotient while `mfhi` gets remainder

####ALU Diagram -> See notes



####Tailoring ALU to the MIPS
- Need to support the set-on-less-than instruction (slt)
	- rember: slt is an arithmetic instrcution
	- slt $t0, $t1, $t2
	- produces a `1` if $t1 < $t2 and `0` otherwise
	- use subtraction: (a - b) < 0 implies a < b
	- remember subtraction: a + (b' + 1)
- Need to support test for equality
	- `beq` $t1, $t2, branch targte address
	- use subtraction: `(a - b) = 0` implies `a = b`
	
- If the ALU performs `a - b` (select 3 in the MUX)

####Study this
- Study stuck on `0` or `1` fault table
- ALUSrcA needs to be fed the PC number in order to decrease program count to figure out the program at fault

####True / False
- In MIPS Assembly language `addi` is an R-Type instrction
