# Software Engineering Project Starter Code

The System will find the largest prime number in n (input)
EX: 10:7 (as 7 is the largest prime in 10)

(https://github.com/CPS353-Suny-New-Paltz/project-starter-code-ShahidHKhan/blob/main/CPS353_checkpoint2_APIs.png?raw=true)

For Checkpoint6, i implemented a multi-threaded version of my @NetworkAPI using Executors.newFixedThreadPool with upper bound of 4 threads; ensuring that no more than 4 tasks run at onces.

For Checkpoint8, Bottleneck (Step 1)
InterComputeAPIImpl was slowed down by LargestPrime.largestPrimeLeq(n), which scanned downward and used an O(√n) prime check. This became the main CPU cost for larger inputs.

Fix (Step 2)
Created FastInterComputeAPIImpl, which uses a cached Sieve of Eratosthenes.
The sieve builds once and is reused, making repeated lookups much faster.

Benchmark Results (Step 3)
(500 iterations, n = 100,000)

Version	Time (ns)
Original	2,659,000
Fast	2,310,400
~13.1% faster (meets the ≥10% requirement)

Benchmark Test
test/project/intercompute/BenchmarkInterComputeTest.java
