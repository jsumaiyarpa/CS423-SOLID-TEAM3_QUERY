# ARCHITECTURAL COMPLIANCE AND SOLID PRINCIPLES ANALYSIS REPORT

**Course:** CSE423 - Software Architecture  
**Group:** Team 3  
**Repository:** `jsumaiyarpa/CS423-SOLID-TEAM3_QUERY`  
**Target Module:** greenDAO - Query Module  

---

## 1. Executive Summary

This report evaluates the software architecture of the **Query Module** within the open-source ORM framework **greenDAO**. The primary objective is to assess architectural quality, measure compliance with **SOLID principles**, and compare the original human-written codebase ($H$) against an LLM-reconstructed alternative ($L$). Quantitative software metrics including SOLID Violation Count (SVC), Dependency Inversion Score (DIS), and Responsibility Entanglement Index (REI) were evaluated to analyze structural trade-offs in real-world Android library engineering versus AI-guided pure object-oriented design.

---

## 2. Repository Selection & Justification Check

The repository and target module were selected based on the following course criteria:

- **Language Requirement:** Written in **Java**, fulfilling the requirement for statically typed object-oriented systems.
- **Codebase Scale & Maturity:** Possesses over 10,000 Lines of Code (LOC) and more than 1,000 commits, indicating long-term community adoption and development maturity.
- **Historical Development:** Initial commit dates back to 2011, establishing a pre-2020 architectural baseline with over two years of active development.
- **Structural Module Cohesion:** Contains dedicated architectural packages (`query`, `dao`, and `internal`), isolating database query generation and execution logic.
- **System Domain:** Real-world mobile Object-Relational Mapping (ORM) framework designed for high-performance SQLite interactions in Android applications.

---

## 3. Selected Module & File Scope

**Target Module:** `de.greenrobot.dao.query`

### Selected Human Source Files ($H$)
1. `AbstractQuery.java`
2. `Query.java`
3. `QueryBuilder.java`
4. `CountQuery.java`
5. `CursorQuery.java`
6. `DeleteQuery.java`
7. `LazyList.java`

---

## 4. Repository Structure Overview

```text
CS423-SOLID-TEAM3_QUERY/
├── greenDAO/
│   └── query/                 <-- Human Code (H)
│       ├── AbstractQuery.java
│       ├── Query.java
│       ├── QueryBuilder.java
│       ├── CountQuery.java
│       ├── CursorQuery.java
│       ├── DeleteQuery.java
│       ├── LazyList.java
│       └── descriptions.md
├── llm_generated/            <-- LLM Reconstruction (L)
│   ├── AbstractQuery.java
│   ├── CountQuery.java
│   ├── CursorQuery.java
│   ├── DeleteQuery.java
│   ├── ExecutableQuery.java
│   ├── LazyList.java
│   ├── ModifyingQuery.java
│   ├── Query.java
│   ├── QueryBuilder.java
│   ├── QueryFactory.java
│   ├── QueryParameterBindings.java
│   └── ReadOnlyQuery.java
├── prompt_iterations.md       <-- LLM Refinement Log
├── report.md                  <-- Full Analysis Report
└── README.md

```

## 5. Metric Calculations & Comparative Analysis

To quantify architectural compliance, three primary software metrics were evaluated across both implementations:

| Metric | Human Code ($H$) | LLM Code ($L$) | Mathematical Formula & Description |
| :--- | :---: | :---: | :--- |
| **SVC** (SOLID Violation Count) | **4** | **1** | $SVC = SRV + OCPV + LSPV + ISPV + DIPV$ |
| **DIS** (Dependency Inversion Score) | **0.28** | **0.75** | $DIS = \frac{D_a}{D_a + D_c}$ |
| **REI** (Responsibility Entanglement Index) | **3.1** | **1.5** | $REI = \frac{1}{\|M\|} \sum R(m)$ |

---

## 6. Detailed Metric Breakdowns

### 1. Dependency Inversion Score (DIS)
- **Human Code ($H = 0.28$):** The original implementation exhibits low abstractions. Classes like `QueryBuilder` directly instantiate concrete classes (`Query.create()`, `CountQuery.create()`) via static factory methods, leading to high concrete dependencies ($D_c$) and minimal interface abstractions ($D_a$).
- **LLM Reconstruction ($L = 0.75$):** Under strict prompt enforcement, the LLM decoupled query creation from instantiation by introducing interface abstractions (`QueryFactory`, `ExecutableQuery`, `ReadOnlyQuery`), significantly increasing $D_a$.

### 2. Responsibility Entanglement Index (REI)
- **Human Code ($H = 3.1$):** High responsibility entanglement was identified in `QueryBuilder.java`, which simultaneously handles SQL string construction, criteria expression parsing, entity DAO mapping, and query lifecycle management ($R(m) \approx 4$).
- **LLM Reconstruction ($L = 1.5$):** Separated SQL syntax building into dedicated builder entities and decoupled execution lifecycle logic, lowering the entanglement index across methods.

### 3. SOLID Violation Count (SVC)
- **Human Code ($H = 4$):**
  - **SRP Violation:** `QueryBuilder` manages both SQL query building and execution context.
  - **OCP Violation:** Adding new query types requires altering existing instantiation methods inside `QueryBuilder`.
  - **ISP Violation:** `LazyList` implements full `List` interfaces while overriding mutator methods to throw unsupported operation exceptions.
  - **DIP Violation:** Direct instantiation of concrete query implementations throughout the pipeline.
- **LLM Reconstruction ($L = 1$):** Guided prompt constraints successfully eliminated SRP, OCP, and DIP violations, leaving minor encapsulation trade-offs.

---

## 7. Comparative Reflections & Conclusion

- **Real-World Engineering Constraints vs. Pure Architecture:**  
  The human-designed greenDAO framework deliberately made trade-offs against pure SOLID principles to optimize Android runtime memory footprint, minimize object allocation overhead, and deliver low-latency SQLite execution.
- **LLM Capabilities in Refactoring:**  
  When guided by structured architectural prompts, LLMs demonstrate high proficiency in identifying tight coupling, extracting interfaces, applying Factory Patterns, and lowering complexity metrics.