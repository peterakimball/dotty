## Dotty

A platform for solving the interview question: given a board of size 2^n x 2^n, with one square occupied by a 'dot' (*), fill all of the remaining squares using L shapes (L, ⅃, ˥, Г)  that occupy three squares.

A solver implementing the approach given during the interview is included - it works for n<3, but succeeds only about 76% of the time for n=3. Obviously not 'the' solution.

### Building

This project is written in Java and uses some Java 8 features. A Java 8 JDK is therefore required.

Maven is required in order to build the demo app.

The demo app is run as part of the test suite. Use 'mvn test'.