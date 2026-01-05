# unCrackable

## what is unCrackable

unCrackable is a tool for encrypting files using the caesar or viginere cipher

## how does it work

First of all the set password has to be convertet from a string to a Array of ASCII values. 
Then each character of the input file is parsed through one by one. Each char is converted into its ASCII value. Then the corresponding password char is calculated. 

`encryptKey[(currentOutputCharIndex % encryptKey.length)]`

with encryptKey being the array of ASCII values from the passwords and currentOutputCharIndex being the outputfile index of the char which is currently encrypted. 

Before the character can be encrypted it has to be noted that the first printable ASCII value is 32 (space). so in order to get the correct encryption a excess of 32 has to be subtracted from both the password ASCII value and the input ASCII value. Then both values can be added. This value can be bigger than the largest printable ASCII value (126 = 32 + 95) which is why % 95 is used with 95 being the amount of printable chars. Finally the excess 32 is added back. 

`((inputValue - 32 + passwordValue -32) % 95 ) + 32`

| <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; |     |     |     |     |     |     |     |     |     |     |     |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| input char                                                                                                                                                                                                                                                                                                                                                                                     | H   | e   | l   | l   | o   |     | W   | o   | r   | l   | d   |
| input ASCII                                                                                                                                                                                                                                                                                                                                                                                    | 72  | 101 | 108 | 108 | 111 | 32  | 87  | 111 | 114 | 108 | 100 |
| password char                                                                                                                                                                                                                                                                                                                                                                                  | 1   | 2   | 3   | 4   | 1   | 2   | 3   | 4   | 1   | 2   | 3   |
| password ASCII                                                                                                                                                                                                                                                                                                                                                                                 | 49  | 50  | 51  | 52  | 49  | 50  | 51  | 52  | 49  | 50  | 51  |
| password - 32 + input -32                                                                                                                                                                                                                                                                                                                                                                      | 57  | 83  | 95  | 96  | 96  | 18  | 74  | 99  | 99  | 94  | 87  |
| ((password - 32 + input -32)%95)+32                                                                                                                                                                                                                                                                                                                                                            | 89  | 115 | 32  | 33  | 33  | 50  | 106 | 36  | 36  | 126 | 119 |
| encrypted char                                                                                                                                                                                                                                                                                                                                                                                 | Y   | s   |     | !   | !   | 2   | j   | $   | $   | ~   | w   |



The table above is an example how the encrypted value is calculated.

input char = "Hello World"

password char = "1234"

encrypted char = "Ys !!2j\$\$~w"



decryption works the same way but in die other direction.

## installation

In order to install uncrackable download the folder conainting the `uncrackable.bat` and `unCRACKABLE.jar` files to the directory where you want to save them. Then edit `uncrackable.bat` and change the path to the path of `unCRACKABLE.jar`. The tool is now ready to be used. But if you want to use it from any terminal and not only inside the folder you have to add the path of the folder to the PATH enviorment variable. Then you can use uncrackable from anywhere in your system by writing uncrackable in the console.

## usage

This section will cover the syntax and usage of uncrackable.

the uncrackable command syntax is following:

`uncrackable <mode> <password> <inputfilePath> <outputfilePath>`

with examples being:

`uncrackable -e -p myPassword -i myInputFile.txt -o myOutputFile.txt`

`uncrackable -e -p 1234 -i C:\Users\myPC\Desktop\input.txt`

`uncrackable -encrypt -password love`

`uncrackable -d -p 123456789 -o C:\Users\myPC\Desktop\output.txt`

`uncrackable -decrypt -password qwerty -output results.txt`

`uncrackable -decrypt -p qwerty -input myInput.txt -o results.txt`

`uncrackable -help`


explanation of parameters:
1. \<mode\>
   
   can either be set to encrypt or decrypt
   
	  -e or -encrypt: specifies that the inputFile has to be encrypted
   
	  -d or -decrypt: spedifies that the inputFile has to be decryptet
   
	One of these paramters has to be set. The programm won't work if neither or both are set.
	
3. \<password\>
   
   -p or -password: specifies the password used to encrypt/decrypt the inputFile
   
   Parameter has to be set in order for the programm to work.
   
4. <\inputfilePath\>
   
   -i or -input: specifies the file to be encrypted/decrypted
   
   If file is in current active directory just the name suffices if the file is located outside of the AD the absolute path has to be given.
   
   If not set the file `input.txt` in the AD is used.
   
5. <\outputfilePath\>
   
   -o or -output: specifies the file to which the output is written.
   
    If file is in current active directory the name suffices if the file is located outside of the AD the absolute path has to be given.
   
    Will create the file with the given path if it doesn't exist.
   
	If not set the file `output.txt` in the AD is used or will be created.
	
 7. <\help\>
    
    -h or -help: will print the helppage
    
    this parameter overwrites all other parameters set
    
    helppage will also open if no parameter is set




