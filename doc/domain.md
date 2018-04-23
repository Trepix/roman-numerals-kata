# Understanding the domain

If you reached this point, you may think you have solved the problem. But you probably are not a roman numerals expert.

Can you guarantee that the number **IIII** is valid? Or **IV**? And this **IM**? Have you ever heard about *Subtractive notation*?
If you can't answer this questions confidently, you are not a roman numerals expert. You have delivered code related with this domain that you may not fully understand.

Please take a look at these readings

- https://www.roman-numerals.org/: Basic but useful to check valid subtractive notation combinations.
    
- https://en.wikipedia.org/wiki/Roman_numerals: Provides context and different casuistries


Given that more than one notation is valid, to homogenize the solutions this kata has to be resolved based on **subtractive notation**.
This new requirement generates new questions: What names will you give to your classes? Are you going to add *subtractive* adjective to your classes?

Expanding the examples:


| Roman Number  | Computation                                    | Value      
 
| ------------- | ---------------------------------------------- | ----------
 
| MMCCLVI       |   1000 + 1000 + 100 + 100 + 50 + 5 + 1         | 2256 
 
| MCMXLIV       |   1000 + (1000 - 100) + (50 - 10) + (5 - 1)    | 1944    
