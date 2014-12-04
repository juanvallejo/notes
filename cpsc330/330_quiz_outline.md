###360 Quiz outline
#####10/27/14

- Access offset
- $t1 comes from read data 1

- ALUSrc (1 if offset, 0 if not [add, sub...])

####Example 1
**Pretend instruction is** `lw`

- Select 1 MemRead
- Select 1 MemWrite

We have to add offset to base address, therefore, ALUOp = 1 (for add)

- Select 1 RegWrite
- Select 1 MemToReg
- 
Select 0 RegDst

Since it's I-type instruction, RegDst is 0

R-type all have rd (1). I-type all have rt (0).

