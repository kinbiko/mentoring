# Logging

### What is a log?

- A log message is a register of an event generated when some kind of activity happens in a software that runs on a computer or device.
- Logs are used to derive meaning in the following domains:
    - troubleshooting/resolving issues
    - computer system resource management
    - user and application management
    - auditing
    - security
- For example, a web server will often log whenever someone accesses a resource (image, file, etc.) on a web page. If the user accessing the page had to authenticate herself, the log message would contain the user’s name. This is an example of a log message: you can use the username to determine who accessed a resource.
- There is unfortunately no standard format for how data is represented in a log message. The exact way a log message is represented depends on how the source of the log message has implemented its log data system.
- A log like `May 21 14:38:25 error` would not be very meaningful, so we should consider improving the quality of the logs that our applications generate, including as much useful information for our logs to help with deriving meaning.
- The typical basic contents for a log message are the following:
    - Timestamp
    - Source
    - Data
- In general, logs should tell you:
    - What happened (with appropriate detail; “Something happened” is not usually particularly useful).
    - When did it happen (and when did it start and end, if relevant).
    - Where did it happen (on what host, what file system, which network interface, etc.).
    - Who was involved (usually in the form of a user ID).
    - Where he, she or it came from (if dealing with OS/Security logs, or the source IP address).
- The logging ecosystem (or logging infrastructure), are all the components and piece parts that come together to allow for the generation, filtering, normalization, analysis, and long-term storage of log data.
    Once a device or computer system is configured to generate log messages, the next step is to filter and normalize the messages.
    - `Filtering` deals with including or excluding log messages based on the content in the log message.
    - `Normalization` is the act of taking disparately formatted log messages and converting them to a common format through the use of parsing, which entails scanning the log message from start to finish to pull out information we are interested in and place them into normalized fields.
    - `Log message analysis` deals with collating, correlating and analyzing log data to derive meaning from it.
    - `Long-term data store` is typically accomplished by using a database.
        - This will usually only store the last months, days, hours, dependant on the size of the HD and the size/detail/amount of logs. Preserving a vast amount of logging history can be achieved by storing on backup media (DVD, tapes, external HDD, [NAS](https://en.wikipedia.org/wiki/Network-attached_storage), etc.) or sending that logs to a cloud storage engine such as [GCP Coldline Storage](https://cloud.google.com/storage/archival/) or [AWS S3](https://aws.amazon.com/s3/).
        - It is pretty common to store your logs in Elasticsearch (a database), and use something called the ELK stack (Elasticsearch, Logstash, Kibana) to centralise your logs.
- Logger software must be running on the base system or into the software itself in order to catch all generated events and write them to a log file that resides in some location on the storage device.
- In order that a log can be writen, the software that generates it must have write permission into that specific disk location and file. In \*nix systems log files default location is `/var/log`. Thus each piece of software running \*nix services should be able to write to `/var/log` directory.
- In Linux, for example, the `syslogd` service is on charge of log all system services related activities depending on the log level with which was configured (all kind of registries, or just errors, or debug logs, or info, etc.)
- Java also has logger libraries that allows register events, and will store that information physically on a specified location of the baseystem.

### Log levels

- Log messages can have different `log levels`, which are used to determine the severity of the code's process, some events are benign while others might be fatal.
- If you set the logging level to be too verbose, there may be too much noise in the logs, and finding useful information may be difficult. On the other hand, if you set logging levels to be too slack, the logs may not be of much value in understanding what an application is doing.

- Typical log levels:
    - `INFORMATIONAL`: Messages of this type are designed to let users and administrators know that something benign has occurred.
    - `DEBUG`: Debug messages are generally generated from software systems in order to aid software developers troubleshoot and identify problems with running application code.
    - `WARNING`: Warning messages are concerned with situations where things may be missing or needed for a system, but the absence of which will not impact system operation. For example, if a program isn’t given the proper number of command line arguments, but yet it can run without them, is something the program might log just as a warning to the user or operator.
    - `ERROR`: Error log messages are used to relay errors that occur at various levels in a computer system. Unfortunately, many error messages only give you a starting point as to why they occurred.
    - `ALERT`: An alert is meant to indicate that something interesting has happened.

- In Syslog, the logging framework for OS and Networking, the log levels are (source [Wiki](https://en.wikipedia.org/wiki/Syslog)):

| Severity      | Description                       | Condition                                                                              |
|---------------|-----------------------------------|----------------------------------------------------------------------------------------|
| Emergency     | System is unusable                | A panic condition.                                                                     |
| Alert         | Action must be taken immediately  | A condition that should be corrected immediately, such as a corrupted system database. |
| Critical      | Critical conditions               | Hard device errors.                                                                    |
| Error         | Error conditions                  |                                                                                        |
| Warning       | Warning conditions                |                                                                                        |
| Notice        | Normal but significant conditions | Conditions that are not error conditions, but that may require special handling.       |
| Informational | Informational messages            |                                                                                        |
| Debug         | Debug-level messages              | Messages that contain information normally of use only when debugging a program.       |


- In Spring Boot Logging Levels are: `OFF`, `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`.
- By default, Spring Boot configures logging via [Logback](http://logback.qos.ch) to write to the console at an INFO level.

### Common log libraries in Java
- `SLF4J` (Simple Logging Facade for Java, a facade is an object that serves as a front-facing interface masking more complex underlying or structural code. Here's a `HelloWorld` using `SLF4J`:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```
This would result in the following log message: `0 [main] INFO HelloWorld - Hello World`

- `Log4j` is another library for logging in Java. Here's a log example setting a log message in three different log levels using `Log4J`:

```java
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4jExample {

    private static Logger logger = LogManager.getLogger(Log4jExample.class);

    public static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }
}
```
This would result in the following log message:
```
15:11:22.022 [main] DEBUG Log4jExample - Debug log message
15:11:22.027 [main] INFO Log4jExample - Info log message
15:11:22.027 [main] ERROR Log4jExample - Error log message
```

For full control over the logging configuration, you can create a `logback.xml` file in `src/main/resources`. Here’s an example of a simple `log-back.xml` file you might use (this logback configuration is more or less equivalent to the default you’ll get if you have no `logback.xml` file.):
```xml
<configuration>
  <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>
        %d{HH:mm:ss.SSS} [%p]: %t %ex - %m
      </pattern>
    </encoder>
  </appender>
  <logger name="root" level="INFO"/>
  <root level="INFO">
    <appender-ref ref="STDOUT" />
  </root>
</configuration>
```
- The `appender` determines where the log message will be routed, this could be a console, a file, socket, etc.
- The `pattern` determines the layout of the log message using string formatting with argument specifiers such as `%d` (digits), `%p` (level), `%t` (thread), `%ex` (exception), and `%m` (message).
