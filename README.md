I wrote a java program that decides ADFA.
***
ADFA is a decidable language in theory of computation.
ADFA = { <D,w> | D is a DFA that accepts the string w }.
in this program we define DFA in a text file whose name is input.txt.
for example look at this image. 

![Untitled](https://github.com/user-attachments/assets/f43e1483-7242-46e1-947b-78d7f7fcf5a0)

This is how we define the DFA in the photo in the input.txt file:

states: q0,q1 
alphabet: 0,1 
start: q0 
accept: q0 
transitions: 
(q0,0,q0) 
(q0,1,q1) 
(q1,0,q1) 
(q1,1,q0) 

attention : The DFA must be defined in the input.txt file in this way, and if it is not, an error will occur.
Note that I prevented this error using try catch and it does not cause the program to crash.
I also use java.util.logging.Logger to show the error and this package shows the errors in red. Do not take them as errors
