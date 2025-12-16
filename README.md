# Software Engineering Project Starter Code

This repo will start you off with an initial configuration that you'll modify as part of Checkpoint 1. As part of the modifications, you'll eventually delete the contents of this README and replace it with documentation for your project.

### Computation: Finding all prime numbers less than the input

This system will take a single positive integer as input, and it will find all the prime numbers that are smaller than that number. The program will repeatedly check each number from 2 up to (input - 1) to determine if it is prime, and collect all the primes into a list.

**Example Input:**
30

**Example Output:**
2, 3, 5, 7, 11, 13, 17, 19, 23, 29

<img width="755" height="280" alt="Screenshot 2025-12-04 at 11 22 07 AM" src="https://github.com/user-attachments/assets/abcc8336-e4fd-47ca-b7ab-7fdf347a8b1d" />

I selected an upper bound of 4 threads for the multithreaded compute engine.
This is enough parallelism to show concurrency while avoiding unnecessary
thread overhead for small workloads.

## Final Project (New addition): ReviewDog Checkstyle Integration
This project extends the CI infrastructure by integrating ReviewDog with Checkstyle.
Checkstyle violations are reported directly as GitHub pull request review comments,
improving developer feedback and code review clarity.
