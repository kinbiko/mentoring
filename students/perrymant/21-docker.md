# Docker

> Docker runs processes in isolated containers. A container is a process which runs on a host. The host may be local or remote. When an operator executes docker run, the container process that runs is isolated in that it has its own file system, its own networking, and its own isolated process tree separate from the host. - [Docker](https://docs.docker.com/)

- What is a container? At its simplest it's a sandbox for a process - allowing the process to have its own namespace, restricting certain capabilities and imposing resource limits as required.
- Container technology allows multiple isolated user space instances to be run on a single host.

```
+---++---++---++---++---+   \
| A || A || A || A || A |   |
| p || p || p || p || p |   |
| p || p || p || p || p |   |--- Containers running on Docker
| 1 || 2 || 3 || 4 || 5 |   |
+---++---++---++---++---+   /
+-----------------------+
|         Docker        |
+-----------------------+
+-----------------------+
| Host Operating System |
+-----------------------+
+-----------------------+
|     Infrastructure    |
+-----------------------+
```
- Containers are generally considered a lightweight or lean technology because they require limited overhead, as each process can be run on very small Linux distributions.
- Docker encourages microservices architecture.
- Images are the building blocks of the Docker world. You launch your containers from images at runtime.
- `image` vs `container`: An image is akin to an OOP class (the blueprint), and the container is akin to an OOP object (the instance). Containers can only be created when an image exists.
- Docker stores the images you build in registries. These registries can be deployed as a local registry or through a Container Registry platform, the most common being [Docker Hub](https://hub.docker.com), which contains over 10,000 images that other people have built and shared. These registries can be public or private (payment plans are applicable for these).
- You push/pull images to/from a registry in a similar way as we do with Git repositories.
- There are many other Container Registry platforms, for example:
  - https://github.com/features/package-registry
  - https://cloud.google.com/container-registry/
  - https://aws.amazon.com/es/ecr/
  - https://azure.microsoft.com/en-us/services/container-registry/
  - https://www.ibm.com/mx-es/cloud/container-registry
- You can deploy images as a local registry, the following command can start the registry container:
```sh
$ docker run -d -p 5000:5000 --restart=always --name registry registry:2
```

### What is an image?

- An image is a binary representation of files/state.
- Images are arranged in a parent-child tree hierarchy of snapshots:
```
    scratch       <- empty file-system
      /
    ubuntu        <- bare-bones Linux OS
    /    \
  sshd  java1.8   <- dependencies
  /  \     \
app               <- a small application
```
- This allows for a modular design where images can be shared by other process that may be running and can be replaced very easily if images need updating.

### Dockerfile

- A Dockerfile is a text document that contains all the commands a user could call on the command-line to assemble an image.
- Using `docker build` users can create an automated build that executes several command-line instructions in succession.
- The Dockerfile is a text file that contains all commands, in order, needed to build a given image. It's a DSL that has a handful of instructions:
  - `RUN`: Executes a command and save the result as a new layer
  - `ENV`: Sets an environment variable in the new container
  - `FROM`: The base image to use in the build. This is mandatory and must be the first command in the file.
  - `MAINTAINER`: An optional value for the maintainer of the script
  - `ADD`: Copies a file from the host system onto the container
  - `CMD`: The command that runs when the container starts
  - There are many more, see [Dockerfile best practices](https://docs.docker.com/develop/develop-images/dockerfile_best-practices/) for more info.
- An example looks like this:
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

### Overview of common commands

```sh
$ docker pull <image_name>
$ docker run -d <image_name>
$ docker run --name <container_name> -d <image_name>:<tag>
$ docker ps
$ docker rm <container_id>
$ docker images
$ docker rmi <image_id>
$ docker stats
$ docker exec -it <container_name> <command>
```

### Commands: in depth
- Firstly, the docker daemon needs to be running running, I use the `Docker Desktop` app for this.
- Try `$ docker run hello-world` to run a very simple hello-world image.
- `$ docker pull <image_name>`: To make sure you have the relevant files for the project, `docker pull <image_name>` will download a particular image, or set of images (i.e., a repository) from the Docker Hub registry. You can also use `docker push` command, which pushes the image to its configured registry.
- `$ docker run --name <container_name> -d <image_name>:<tag>`: Start a Mongo server instance, where `<container_name>` is the name you want to assign to your container and tag is the tag specifying the version you want. The `-d` option detaches the container, allowing it to run in the background.
- `$ docker run -d <image_name>`: a shortcut of the above command, this will run the container in background and return the container ID.
- `$ docker ps`: What processes are currently running? Notice the unique `container ID`, it's used in commands to specify the container:
```
CONTAINER ID        IMAGE                        COMMAND                CREATED              STATUS              PORTS               NAMES
b2530588b5c0        mongo                        "docker-entrypoint.s…" 40 seconds ago       Up 39 seconds       27017/tcp           reverent_mcclintock
4c01db0b339c        ubuntu:12.04                 bash                   55 seconds ago       Up 154 seconds      3300-3310/tcp       webapp
d7886598dbe2        tronald/redis:latest         /redis-server --dir    33 minutes ago       Up 33 minutes
```
- `$ docker images`: lists the available images (ps, but for images):
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
    - `-i` indicates that the container will run interactively and the `-t` flag creates a virtual tty terminal, which can be detached with `^P^Q`.
    - Here, the container called `wizardly_euclid` (these names are auto-generated and can be found when running `$ docker ps`) will run `mongo` command.
    - Alternatively, the first few characters of the container ID can be used instead of the full name. For example, to execute `mongo` command in the container with ID `b2530588b5c0`: `$ docker exec -it b25 mongo`.
- `$ docker stop <container_name>` can be used to stop the container, this is useful if you forgot to add the `-it` flag on the command.
- `$ docker build [OPTIONS] PATH | URL | -`: Build an image from a Dockerfile. In the example dockerfile above, the command to build would be: `$ docker build -t demo/spring:maven-3.3-jdk-8 .`, the `-t` flag adds a tag to the image so that it gets a nice repository name and tag and the final `.` indicates that the Dockerfile in the current directory should be used.
