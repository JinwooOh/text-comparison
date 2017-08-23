# Text-comparison
This is the program that can compare two text files and produces the result. This program is especially useful for court reporters and stenographers who want to compare a typed text file and an original text file.  

## Usage

Download and run text-comparison.jar

<img align="center" src="/imagesforReadMe/program.png"  height="300" weight="420">

![Alt text](/imagesforReadMe/result.jpg?raw=true "result txt file")

Original File: your text file

Answer File: text file that you want to compare

Run: produce the output in the same directory of the program 

## Result Message example
Original File: t1.txt.
Your File: t2.txt.
Inserted: 5.0
Deleted: 5.0
Changed: 0.0
Accuracy: 90.0%

## How I approach
My first approach was comparing two text files character by character. However, it produced the problem that once it found a different character, it marked the rest of characters are wrong. 
The second approach was using Longest Common Sequence theory. I needed to study before I jumped into writing code. 
Applying LCS theory was the right choice and it produces the accurate result. 

## Links and references that helped me to finish the project 
1. LCS table: https://www.youtube.com/watch?v=P-mMvhfJhu8
2. LCS theory: https://en.wikipedia.org/wiki/Longest_common_subsequence_problem

