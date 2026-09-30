# CLAUDE.md

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
