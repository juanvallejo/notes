Section 3
=========

###Analyze Clock Rate as a Benchmark 

####Sources
- http://www.geek.com/chips/why-amd-mhz-dont-equal-intel-mhz-557914/
- http://techchannel.radioshack.com/comparison-speed-amd-intel-2626.html
- http://www.tomshardware.com/forum/278509-28-intel-chips-lower-clock-speeds

####Introduction

Why are there performance differences when comparing AMD and Intel processors that are clocked at the same speed?
Comparison results between AMD and Intel chips show significant performance differences between the two kinds of chips, even when both chips tested have the same clock speed.

The heart of this issue is more easily examined by breaking down each chip into its basic structure and architecture.

A CPU is formed by logic units, caches, registers, and pathways that come together with many other components using differing internal designs. An instruction may be able to be carried out by two processors using different internal designs, but the way in which such instruction is carried out will differ either slightly or greatly in both processors.

When a CPU carries out an instruction, it breaks that instruction down into simpler parts, and those simpler parts into even smaller parts. Depending on the internal structure of the chip we are using, the order in which these smaller parts of the instruction are processed will vary greatly. Some of these smaller parts depend on the result other simple parts will yield within the same instruction. This causes the order at which the broken down parts of this specific instruction are processed to vary.

This is a main factor for differences in performance between an AMD chip and an Intel chip clocked at the same speed. The number of smaller steps that are done out of order, how much an instruction is broken down into simpler steps before being processed piece by piece, and the speed at which each individual part of the instruction is processed all influence the overall performance of each chip.
