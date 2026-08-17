# LLM Prompt Iterations & Refinement Log

## Iteration 1 (Basic Prompt)
- **Prompt:** "Reconstruct the greenDAO query module in Java maintaining equivalent functionality."
- **Observation:** Generated a monolithic class structure with tight coupling between QueryBuilder and concrete queries. High SOLID violations.

## Iteration 2 (Adding Architectural Constraints)
- **Prompt:** "Reconstruct the greenDAO query module in Java. Separate responsibilities into distinct classes for SQL Generation, Parameter Binding, and Query Execution."
- **Observation:** Single Responsibility improved, but Dependency Inversion was still violated as QueryBuilder instantiated concrete Query types directly.

## Iteration 3 (Strict SOLID Enforcement - Final Prompt)
- **Prompt:** "Reconstruct the greenDAO query package in Java adhering strictly to SOLID principles:
1. Single Responsibility (SRP): Separate SQL building, query parameter setting, and execution lifecycle.
2. Open/Closed (OCP) & Dependency Inversion (DIP): Introduce a QueryFactory interface so QueryBuilder depends on abstraction rather than concrete Query subclasses.
3. Interface Segregation (ISP): Split query execution interfaces for read-only vs modifying operations."
- **Observation:** Successfully decoupled dependencies using abstractions and factory patterns, significantly improving DIS and reducing SVC.