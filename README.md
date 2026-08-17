# CS423 SOLID Team 3 - Query Module

## Project Overview

This repository contains the selected files from the **Query** module of the **greenDAO** open-source project. The files were selected for the CSE423 Software Architecture course assignment to analyze the software structure and identify opportunities for applying the SOLID principles.

## Original Repository

- **Project Name:** greenDAO
- **GitHub Repository:** https://github.com/greenrobot/greenDAO

## Selected Module

- Query Module

## Selected Files

1. AbstractQuery.java
2. Query.java
3. QueryBuilder.java
4. CountQuery.java
5. CursorQuery.java
6. DeleteQuery.java
7. LazyList.java

## Repository Structure

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
│   ├── Query.java
│   ├── QueryBuilder.java
│   ├── CountQuery.java
│   ├── CursorQuery.java
│   ├── DeleteQuery.java
│   └── LazyList.java
├── prompt_iterations.md       <-- LLM Refinement Log
└── README.md

```

## TASK 3: SOLID Principles Compliance Analysis

### 1. Repository Selection & Justification Check
- **Language Requirement:** Java (Satisfies Java/Python/TypeScript rule)
- **Size Requirement:** > 10,000 LOC, > 1,000 Commits (Satisfied)
- **History Requirement:** Initial commit in 2011 (Pre-2020 snapshot, > 2 years active development) (Satisfied)
- **Structure Requirement:** Contains structural core folders including `query`, `dao`, and `internal` subpackages (Satisfied)
- **System Type Requirement:** Real-world ORM Framework / Multi-module Android Database Engine (Satisfied)

---

### 2. Metric Calculations & Comparison

| Metric | Human Code ($H$) | LLM Code ($L$) | Formula & Description |
| :--- | :--- | :--- | :--- |
| **SVC** (SOLID Violation Count) | **4** | **1** | $SVC = SRV + OCPV + LSPV + ISPV + DIPV$ |
| **DIS** (Dependency Inversion Score) | **0.28** | **0.75** | $DIS = \frac{D_a}{D_a + D_c}$ |
| **REI** (Responsibility Entanglement) | **3.1** | **1.5** | $REI = \frac{1}{\|M\|} \sum R(m)$ |

#### Metric Breakdowns:

1. **Dependency Inversion Score (DIS):**
   - **Human ($H$):** `QueryBuilder` instantiates concrete classes directly via static factory calls (`Query.create()`, `CountQuery.create()`), making concrete dependencies ($D_c$) significantly higher than abstract dependencies ($D_a$).
   - **LLM ($L$):** Under strict prompt enforcement, the LLM introduced interface-based abstractions (`QueryFactory` and execution interfaces), raising $D_a$ and boosting the DIS score to 0.75.

2. **Responsibility Entanglement Index (REI):**
   - **Human ($H$):** `QueryBuilder` handles SQL string construction, parameter binding, entity DAO mapping, and query lifecycle management ($R(m) \approx 4$).
   - **LLM ($L$):** Separated SQL building logic from execution management, resulting in lower entanglement ($R(m) \approx 1.5$).

3. **SOLID Violation Count (SVC):**
   - **Human ($H$):** Violates **SRP** (`QueryBuilder`), **OCP** (tight coupling when creating new query types), **ISP** (`LazyList` implementing unmodifiable list behaviors), and **DIP** (direct concrete class dependencies).
   - **LLM ($L$):** Guided prompt constraints successfully minimized violations down to 1.

---

### 3. Comparative Analysis & Reflection

- **Real-World Engineering vs. Pure Architecture:** Real-world pre-LLM frameworks like greenDAO often intentionally violate pure SOLID principles to prioritize memory management, execution speed, and developer ease-of-use on Android devices.
- **LLM Architectural Behavior:** When explicitly guided by prompt constraints, LLMs can enforce clean architecture patterns like DIP and Factory patterns effectively. However, without strict prompt engineering, LLMs tend to generate monolithic code with high coupling.

---

## Team Information
**Course:** CSE423 - Software Architecture  
**Group:** Team 3  
**Repository:** `jsumaiyarpa/CS423-SOLID-TEAM3_QUERY`