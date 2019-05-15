# Docker

> Docker runs processes in isolated containers. A container is a process which runs on a host. The host may be local or remote. When an operator executes docker run, the container process that runs is isolated in that it has its own file system, its own networking, and its own isolated process tree separate from the host. - [Docker](https://docs.docker.com/)

- What is a container? at its simplest it's a sandbox for a process - allowing the process to have its own namespace, restricting certain capabilities and imposing resource limits as required.
- Container technology allows multiple isolated user space instances to be run on a single host.
- Containers are generally considered a lightweight or lean technology because they require limited overhead, as each process can be run on very small Linux distributions.
- Docker encourages microservices architecture.
- Images are the building blocks of the Docker world. You launch your containers from images.
- `image` vs `container`: an image is akin to an OOP class (the blueprint), and the container is akin to an OOP object (the instance).
- Docker stores the images you build in registries. There are two types of registries: public and private, and can be found on (Docker Hub)[https://hub.docker.com], which contains over 10,000 images that other people have built and shared.
- A Docker container comprises:
    - An image format.
    - A set of standard operations.
    - An execution environment.

### Overview of common commands

```sh
$ docker pull mongo
$ docker run -d mongo
$ docker ps
$ docker rm (remove a container)
$ docker images (ps, but for images)
$ docker rmi (remove image)
$ docker stats
$ docker exec -it my_container_name mongo
```

### Commands: in depth
- Firstly, the docker daemon needs to be running running, I use the `Docker Desktop` app for this.
- Try `$ docker run hello-world` to run a very simple hello-world image.
- `$ docker pull <name>`: To make sure you have the relevant files for the project, `docker pull <name>` will download a particular image, or set of images (i.e., a repository) from the Docker Hub registry. You can also use the docker `push` command, which pushes the image to its configured registry.
- `$ docker run --name some-mongo -d mongo:tag`: Start a mongo server instance, where `some-mongo` is the name you want to assign to your container and tag is the tag specifying the MongoDB version you want. The `-d` option detaches the container, allowing it to run in the background.
- `$ docker run -d MongoDB` will return the container ID.
- `$ docker ps`: What processes are currently running? Notice the unique `container ID`, it's used in commands to specify the container:
```
CONTAINER ID        IMAGE                        COMMAND                CREATED              STATUS              PORTS               NAMES
b2530588b5c0        mongo                        "docker-entrypoint.s…" 40 seconds ago       Up 39 seconds       27017/tcp           reverent_mcclintock
4c01db0b339c        ubuntu:12.04                 bash                   55 seconds ago       Up 154 seconds      3300-3310/tcp       webapp
d7886598dbe2        tronald/redis:latest         /redis-server --dir    33 minutes ago       Up 33 minutes
```
- `$ docker images`: lists the available images:
```
REPOSITORY  TAG        IMAGE ID        CREATED         VIRTUAL SIZE
mongo       latest     d98005b752b4    8 days ago      411MB
ubuntu      latest     5506de2b643b    3 weeks ago     199.3 MB
ubuntu      16.04      0b310e6bf058    5 months ago    127.9 MB
```
- `$ docker rmi 0b310e6bf058`: Removes the image with the specified ID.
- `$ docker stats`: Returns a live data stream for running containers:
```
CONTAINER ID    NAME                                    CPU %    MEM USAGE / LIMIT     MEM %     NET I/O        BLOCK I/O      PIDS
b2530588b5c0    wizardly_euclid.                        0.47%    40.87MiB / 1.952GiB   2.04%     1.18kB / 0B    4.1kB / 655kB  27
b95a83497c91    awesome_brattain                        0.28%    5.629MiB / 1.952GiB   0.28%     916B / 0B      147kB / 0B     9
67b2525d8ad1    foobar                                  0.00%    1.727MiB / 1.952GiB   0.09%     2.48kB / 0B    4.11MB / 0B    2
e5c383697914    test-1951.1.kay7x1lh1twk9c0oig50sd5tr   0.00%    196KiB / 1.952GiB     0.01%     71.2kB / 0B    770kB / 0B     1
4bda148efbc0    random.1.vnc8on831idyr42slu578u3cr      0.00%    1.672MiB / 1.952GiB   0.08%     110kB / 0B     578kB / 0B     2
```
- `$ docker exec -it wizardly_euclid mongo`:
    - `exec` runs a command in a running container.
    - `-i` indicates that the container will run interactively and the `-t` flag creates a virtual tty terminal, which can be detached with ^P^Q.
    - The container is called `wizardly_euclid` (these names are auto-generated and can be found when running `$ docker ps`) and it runs `mongo`.
    - Alternatively, the first few characters of the container ID can be used instead of the full name: `$ docker exec -it b25 mongo`s.
- `$ docker stop my_container` can be used to stop the container, this is useful if you forgot to add the -it flag on the command.

### What is an image?

- An image is a binary representation of files/state.
- Images are arranged in a parent-child tree hierarchy of snapshots:
```
app                 <- a small application
  \  /     /
  sshd   java1.8    <- dependencies
    \    /
    ubuntu          <- bare-bones Linux OS
      \     /
      scratch       <- empty file-system
```
- This allows for a modular design where images can be shared by other process that may be running and can be replaced very easily if images need updating.

### Dockerfile

- A Dockerfile is a text document that contains all the commands a user could call on the command line to assemble an image.
- Using docker build users can create an automated build that executes several command-line instructions in succession.
- Common Dockerfile instructions start with `RUN`, `ENV`, `FROM`, `MAINTAINER`, `ADD`, and `CMD`, among others. An example looks like this:
```
# Dockerfile
FROM demo/maven:3.3-jdk-8
MAINTAINER Author <autor@email.com>
RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget pwgen ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
ENV TOMCAT_MAJOR_VERSION 8
ENV TOMCAT_MINOR_VERSION 8.0.11
ENV CATALINA_HOME /tomcat
```
- This dockerfile will need to be built with: `$ docker build -f Dockerfile -t demo/spring:maven-3.3-jdk-8`
