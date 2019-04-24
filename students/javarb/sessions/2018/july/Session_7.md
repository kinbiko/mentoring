# SOFTWARE ENGINEERING IN JAVA

## Session 7 (17/07/2018)

- Answering some of my questions about exceptions and using JSON in web development
- Introduction to Docker usage

### Notes:

**About Java new versions**

Java code release every 6 months, but from decads it's still in version 1, that means Java 8 is in really Java 1.8. So, as Java has been with the same major version it's expected stability.

What new Java versions are bringing in general is new functionalities or characteristics to make easier to use Java for certain purposes, but is very possible that same tasks can be done in older versions using the corresponding methods or libraries. Even if there is a total new functionality, as for example [*Streams*][9] and [*Functional Programming*][10], the same functionality could be achieved using third party software. Thus, for a company that have already a well defined workflow, the new characteristics in subsequent Java versions must be important enought, since this means to adapt/rewrite code and/or work flows to the pointed Java release.

**Test with finally keyword**

`Finally` block is used at the end of `try-catch` blocks and the purpose of this block is to do healthy post-operations such as close databases connections, close opened files, etc.

In the following example, the `println` statement inside the `try` block is executed, then `finally` block and after the `return` statement inside `try` block:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("We are= " + new Main().finallyTests());

    }

    private String finallyTests() {
        try {
            System.out.println("Before return from try");
            return "Inside try";

        } catch (RuntimeException e) {
            return "Inside catch";

        } finally {
            System.out.println("Inside finally");

        }
    }

}
```

`finally` block is executed just before of the return in the `try` block. Also notice this code isn't even reaching the catch block at all since a exception hasn't been triggered. So the exit of the code above is:

```
Before return from try
Inside finally
We are= Inside try
```

The next example is similar to the previous one, but this time a `RuntimeException` is caused so `catch` block is executed:

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("We are= " + new Main().finallyTests());

    }

    private String finallyTests() {
        try {
            System.out.println("Inside try");
            throw new RuntimeException();

        } catch (RuntimeException e) {
            System.out.println("Before return in catch");
            return "Inside catch";

        } finally {
            System.out.println("Inside finally");

        }

    }

}
```

Here, `catch` block is executed after is throwed inside the `try` block but `finally` block is executed before `return` from `catch` block. The exit of this code is:

```
Inside try
Before return in catch
Inside finally
We are= Inside catch
```

By last, in the following code the execution is finished inside the `try` clause and thus `finally` block isn't executed:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("We are= " + new Main().finallyTests());

    }

    private String finallyTests() {
        try {
            System.exit(2);

        } catch (RuntimeException e) {
            return "Inside catch";

        } finally {
            System.out.println("Inside finally");

        }

        System.out.println("Before return");
        return "Outside Try block";
    }

}
```

Because the `System.exit(2)` statement the execution ends without even execute `finally` block and returning the corresponding exit message:

`Process finished with exit code 2`

`finally` keyword was mainly used in Java versions prior to 7 in order to ensure all closeable resources should be adequately closed, but in later Java versions, was introduced the `java.io.Closeable` class, arising a new approach to handle the closing of streams that is called the [try-with-resources Statement][8], of course in classes which implements the class closeable (`java.io.Closeable`). Anyway, it's prefereable to know how to work and deal with old code, since in a real work situation it's most common find oneself reviewing/patching old code than generating new code. So is good to know about new characteristics but is better have very good bases over these kind of things that has been widely used in past.


**Working with Docker**

In order to work with Docker we had to face some nuances. First the service was stopped:

```bash

$ sudo docker ps
Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?
```

The service was started using the following command:

```bash
$ sudo systemctl start docker
```

But then, my user could not use docker but only using root privileges which is undesired ([see the solution here](#dockernosudo)):

```bash
$ docker ps
Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get http://%2Fvar%2Frun%2Fdocker.sock/v1.30/containers/json: dial unix /var/run/docker.sock: connect: permission denied

$ sudo docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

In this first session we ran the `hello-world` docker image, from [Docker Hub][4]:

```bash
$ sudo docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
9db2ca6ccae0: Pull complete
Digest: sha256:4b8ff392a12ed9ea17784bd3c9a8b1fa3299cac44aca35a85c90c5e3c7afacdc
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/
```

TO make another example, we created a `Dockerfile` to deploy the [Alpine Linux][5] and then install [MySQL][6] over it:

```bash
$ cat Dockerfile
FROM alpine:3.7
RUN apk add --no-cache mysql-client
ENTRYPOINT ["mysql"]
```

And build it passing the path where the `Dockerfile` is located:

```bash
$ sudo docker build .
Sending build context to Docker daemon   4.96MB
Step 1/3 : FROM alpine:3.7
3.7: Pulling from library/alpine
911c6d0c7995: Pull complete
Digest: sha256:56e2f91ef15847a2b02a5a03cbfa483949d67a242c37e33ea178e3e7e01e0dfd
Status: Downloaded newer image for alpine:3.7
 ---> 791c3e2ebfcb
Step 2/3 : RUN apk add --no-cache mysql-client
 ---> Running in 2d5e9f0629ae
fetch http://dl-cdn.alpinelinux.org/alpine/v3.7/main/x86_64/APKINDEX.tar.gz
fetch http://dl-cdn.alpinelinux.org/alpine/v3.7/community/x86_64/APKINDEX.tar.gz
(1/6) Installing mariadb-common (10.1.32-r0)
(2/6) Installing ncurses-terminfo-base (6.0_p20171125-r0)
(3/6) Installing ncurses-terminfo (6.0_p20171125-r0)
(4/6) Installing ncurses-libs (6.0_p20171125-r0)
(5/6) Installing mariadb-client (10.1.32-r0)
(6/6) Installing mysql-client (10.1.32-r0)
Executing busybox-1.27.2-r11.trigger
OK: 41 MiB in 19 packages
 ---> 601b785aa5cf
Removing intermediate container 2d5e9f0629ae
Step 3/3 : ENTRYPOINT mysql
 ---> Running in c0e591dcafdf
 ---> 1e64f2327071
Removing intermediate container c0e591dcafdf
Successfully built 1e64f2327071
```

Now, we can see how our new image was installed as `alpine` but also our local deployed version of it hasn't a `REPOSITORY` nor `TAG` names:

```bash
$ sudo docker images
REPOSITORY                                           TAG                 IMAGE ID            CREATED             SIZE
<none>                                               <none>              1e64f2327071        6 minutes ago       36.8MB
hello-world                                          latest              2cb0d9787c4d        6 days ago          1.85kB
alpine                                               3.7                 791c3e2ebfcb        11 days ago         4.2MB
gcr.io/qwiklabs-gcp-864869a1205a5f3c/py-web-server   v1                  b85efe8254e0        6 months ago        97.4MB
```

So we need to tag it in order have the name we want and placing a version after colon `:`. We must also give the path where our Dockerfile is located:

```bash
$ sudo docker build -t alpine-mysql:1.0 .
Sending build context to Docker daemon   4.96MB
Step 1/3 : FROM alpine:3.7
 ---> 791c3e2ebfcb
Step 2/3 : RUN apk add --no-cache mysql-client
 ---> Using cache
 ---> 601b785aa5cf
Step 3/3 : ENTRYPOINT mysql
 ---> Using cache
 ---> 1e64f2327071
Successfully built 1e64f2327071
Successfully tagged alpine-mysql:1.0
```

Then we see the repository name has been updated and also the `TAG` version:

```bash
$ sudo docker images
REPOSITORY                                           TAG                 IMAGE ID            CREATED             SIZE
alpine-mysql                                         1.0                 1e64f2327071        13 minutes ago      36.8MB
hello-world                                          latest              2cb0d9787c4d        6 days ago          1.85kB
alpine                                               3.7                 791c3e2ebfcb        11 days ago         4.2MB
gcr.io/qwiklabs-gcp-864869a1205a5f3c/py-web-server   v1                  b85efe8254e0        6 months ago        97.4MB
```

After we run (seems the service is stopped) in iteractive mode to avoid our terminal stick to the docker server:

```bash
$ sudo docker run -it 1e64f2327071
ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/run/mysqld/mysqld.sock' (2 "No such file or directory")
```

If we see the status of the process (We should list all with `-a` flag), we can see how it's exited:

```bash
$ sudo docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES

$ sudo docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                      PORTS               NAMES
d932a49efe50        1e64f2327071        "mysql"             44 seconds ago      Exited (1) 42 seconds ago                       epic_spence
ffeb22397240        hello-world         "/hello"            37 minutes ago      Exited (0) 37 minutes ago                       blissful_knuth
```

This also can be sawn in the docker logs (we can use the id or as a shorcut some first letters of it):

```bash
$ sudo docker logs d932a49efe50
ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/run/mysqld/mysqld.sock' (2 "No such file or directory")
```

```bash
$ sudo docker logs d93
ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/run/mysqld/mysqld.sock' (2 "No such file or directory")
```

### Resources:
- [Heroku - Cloud for startups][1]
- [Play with Docker][2]
- [Play with Docker Classroom][3]
- [Docker Hub][4]
- [Alpine Linux image][5]
- [MySQL image][6]
- [How to link to a named anchor in Multimarkdown?][7]
- [The try-with-resources Statement][8]
- [Java Streams][9]
- [Functional programming in Java][9]

[1]: https://www.heroku.com/
[2]: https://labs.play-with-docker.com/
[3]: https://training.play-with-docker.com/
[4]: https://hub.docker.com/
[5]: https://hub.docker.com/_/alpine/
[6]: https://hub.docker.com/_/mysql/
[7]: https://stackoverflow.com/a/7015050
[8]: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
[9]: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
[10]: https://hackernoon.com/finally-functional-programming-in-java-ad4d388fb92e

### TODO:

- [x] Run docker without sudo

<a name="dockernosudo"></a>In order to run docker without sudo
```bash
$ sudo usermod -aG docker $USER
$ newgrp docker
$ docker ps -a
```
