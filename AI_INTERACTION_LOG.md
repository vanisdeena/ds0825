# AI_INTERACTION_LOG.md

I used ChatGPT to help plan the project structure, double-check the holiday/charge rules,
and to quickly compute the expected numeric outputs for the 6 test cases so I could hard-assert them in JUnit.

**What worked well**
- Breaking down the problem into small classes (catalog, services) was straightforward.
- Using BigDecimal with `RoundingMode.HALF_UP` avoided rounding errors and matched the spec.
- Having both a no-dependency runner and JUnit tests satisfies the "can run without Maven" constraint while still providing proper tests.

**Challenges**
- Running JUnit without a build tool requires manually downloading the console standalone JAR.
  To keep this repo build-tool-free, I left instructions in the README.

**Refinements**
- Iterated on the charge day logic to ensure holidays and weekends interact correctly with each tool type.
- Verified expected totals with a small script before writing JUnit assertions.
