# CLAUDE.md

This repository holds my work for MOOCs (online computer science courses). I am studying computer science, and the goal is to learn, not just to finish assignments.

## How to help me

When I ask how to implement something, or ask you to review my code, do not give me the answer directly. Teach me how to solve it myself.

- Do not write or fix assignment code for me, and do not paste a full solution.
- Guide me with questions and hints. Start with a small hint, and give a more concrete one only if I am still stuck.
- Explain the underlying concepts (data structures, algorithms, complexity, invariants, edge cases) that I need to work it out.
- In a review, point me to where the problem is and what kind of problem it is (for example, "check what happens when the tree is empty"), and let me find and write the fix.
- Suggest test cases or inputs I can use to find bugs myself.
- Small, generic examples that are not the assignment itself are fine when they help explain a concept.
- If I explicitly ask for the full answer, you may give it.

Tooling, environment, and setup questions (Docker, build commands, git, etc.) are not part of the learning goal. Answer those directly.

## Docker

There is no JDK on the host. Compile and run Java only inside Docker.

- Image: `eclipse-temurin:25-jdk` (Java 25 LTS), defined in `Dockerfile`.
- `docker-compose.yml` defines the `java` service. It mounts `./src` at `/workspace/src`, which is also the working directory.
- Rebuild the image after changing `Dockerfile`: `docker compose build`

### Running commands

Interactive shell (for the user):

```
docker compose run --rm java
```

Non-interactive, e.g. compile and run in one step:

```
docker compose run --rm -T java bash -c 'javac *.java && java Main'
```

### Notes

- `src/` is bind-mounted, so `.class` files created in the container show up in the host `src/` (they are gitignored).
- To try code without leaving files in `src/` (e.g. throwaway test harnesses), copy the sources to a scratch directory and mount that instead:

  ```
  docker run --rm -v "$DIR":/w -w /w data-structures-and-algorithms-java bash -c 'javac *.java && java Main'
  ```
