####################################################################################################
===========
Java I/O Performance Tests
http://www.redgreencode.com/why-is-java-io-slow/
===========

----------------------------------------------------------------------------------------------------
**********
Running the Tests
**********

-- Read tests --

Example:
javac ReadTest1.java
java ReadTest < text.txt

* The first output line is the runtime in nanoseconds.
* The second output line (starting with 't') verifies that the input was read correctly.
  It should be the same for every run of every set of tests (different value for read vs. write tests).
* Run the test 5 times. The difference between the slowest and fastest time should be
  within 15% or so of the median time.
* Results go in Results.xlsx.
* Repeat for the other read tests.

-- Write tests --

Example:
javac WriteTest1.java
java WriteTest > output.txt

* Otherwise, the process works the same as the read test. WriteTest1 is very slow
  (several minutes of runtime) so I only run it once.

----------------------------------------------------------------------------------------------------
####################################################################################################
