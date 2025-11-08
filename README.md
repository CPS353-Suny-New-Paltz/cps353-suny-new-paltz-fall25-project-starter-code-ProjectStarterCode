# Software Engineering Project Starter Code

This repo will start you off with an initial configuration that you'll modify as part of Checkpoint 1. As part of the modifications, you'll eventually delete the contents of this README and replace it with documentation for your project.

### API Diagram

![API Diagram](https://raw.githubusercontent.com/CPS353-Suny-New-Paltz/project-starter-code-koleada/refs/heads/main/APIDiagram.jpg)


### Compute Engine

For the computation engine, I chose to perform a simplified version of the PBKDF2 hashing algorithm. This hashing function is designed to be computationally expensive to make the resulting hashes harder to crack. 

We use a nested loop with a set number of iterations where on the first iteration we take the SHA-256 hash of the original user input and update a value. On subsequent iterations we continue to take the hash of the updated value until the loop completes. 

After all iterations are completed, we take the first four bytes of the final hash and use modulo to convert it into a positive integer (0 < hash < Interger.MAX_VALUE). To ensure these constraints we use 'Integer.floorMod() which ensures a nonnegative result. This essentially takes the floor of resultHash mod Integer.MAX_VALUE-1 then adding one to that. 

Example:

Input: 12345
Iterations: 1000 outer, 10 inner = 10,000 total (this can be changed to make it more or less computationally intense)
Output: 1890392330

### Network API

This API is multithreaded to allow multiple users to initiate computation requests. I chose a maximum of 8 threads as my CPU has 6 physical cores and 12 threads in total. 8 is a 
good middle ground to maximize efficiency from multithreading without overwhelming the CPU and reducing efficiency. I also made the number of threads slightly dynamic, so my code will choose the max number of threads by looking at the number of processors on the client machine and taking the minimum of that and 8. 

### Performance Improvement

In working on this project, I found a massive performance improvement within the Pbkdf2 computation. This computation essentially does repeated SHA-256 hashing of an input value. To do the hashing, I used the java.security.MessageDigest library. I used a nested loop to do this kind of for no reason but thats just what I choose to do. Within the inner loop I had these 3 lines of code:  

```java
md.reset();
md.update(value);
value = md.digest();
```

Creating this computation was the first time I used the MessageDigest library ever, so I was not familiar with it at all. When I began to look for places I could improve performance I quickly noticed the computation was taking the vast majority of the total execution time so naturally I started looking there. I then decided to dive into the MessageDigest class and really learn what each method call does. I dove into the documentation and saw the following within the MessageDigest docs: "After digest has been called, the MessageDigest object is reset to its initialized state." So the digest() method includes a call to reset() within it or in some way resets the object. This means the line "md.reset()" was entirely redundant and unnecessary. 

I removed this line completely which was originally located within the inner most loop body which greatly improved performance. I first ran a benchmark test using Junit which passes if the performance difference between the old and improved version is faster more than 10%, meaning 10% improvement. I used a custom class TimingLogger to measure various sections of the compute method, so I was able to just isolate and measure the computation itself to remove any other data. The test immediately passed, but then I was curious just how much of an improvement was made. I made another class: PerformanceComparison which again measured the differences between the old and new computations and printed the differences along with the percentage improvement. I ran this many times and every single time the improvement percentage was above 50%. The most I saw was about 80% improvement! So just this one small removal from the inner loop body has a massive impact on the overall performance of my code.

