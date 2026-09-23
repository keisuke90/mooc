# Java Dev Environment

Dockerized Java 25 (LTS) environment for learning fundamentals — no host JDK install needed.

## Usage

Start a shell in the container (source code lives in `src/`, mounted from the host):

```
docker compose run --rm java
```

Inside the container:

```
javac Main.java
java Main
```

Edit files on your host with any editor; changes are reflected immediately in the container via the mounted `src/` volume.

To rebuild the image after changing the `Dockerfile` (e.g. to bump the Java version):

```
docker compose build
```
