# SOFTWARE ENGINEERING IN JAVA

## Session 20 (18/12/2018)

- Project creation for API application
- Rebase / merge explanation
- Reseting commits
- See the amount of branches and the differences between the master
- Resolving merge conflicts in API Application

### Notes

#### Project creation

We created a new [project for the API application][1] in order to track the tasks using [kanban method][9].

#### Calculator Frontend

Roger was explaining to me how to use `rebase` and the difference with `merge` for the [calculator-frontend repository][2].

He was also explaining to me why he used an initial commit called `Initial codebase` on a branch called `delete-me-after-review` as the begining of the history. He did that in order to he could raise this [Pull Request (PR) from master against that branch][3] and to compare seeing all changes I did in master, being able to do comments, but with the caveat to couldn't make reviews because was himself who created that branch and raised the PR as he states:

>I had to create an empty commit at the beginning of the git history in order to be able to do a complete PR against the entire codebase.
>
>If you're interested here's what I did to enable this:
>
>```bash
>git checkout --orphan delete-me-after-review
>git commit --allow-empty -m "Empty commit for reviewing purposes"
>git push -u origin delete-me-after-review
>git checkout master
>git rebase delete-me-after-review
>git push -f
>```

At the end, `delete-me-after-review` branch wasn't neccesary since I applied all changes asked in that branch's PR in another branch I had created called `frontend-bs4`, so I [merged that branch into master][4] (master was only different to the empty branch for 1 commit - the merge one). And the empty branch could be deleted:

```bash
[jaar@port-staff calculator-frontend]$ git status
On branch frontend-bs4
Your branch is up to date with 'origin/frontend-bs4'.
...
        modified:   index.html
        modified:   public/scripts/calculator.js
        modified:   public/stylesheets/calculator.css

[jaar@port-staff calculator-frontend]$ git branch -a
* frontend-bs4
  master
  remotes/origin/HEAD -> origin/master
  remotes/origin/delete-me-after-review
  remotes/origin/frontend-bs4
  remotes/origin/master

[jaar@port-staff calculator-frontend]$ git add . ; git commit -m 'Apply sugesstions in PR #2 #3'; git push
[frontend-bs4 182e403] Apply sugesstions in PR #2 #3
 3 files changed, 10 insertions(+), 56 deletions(-)
...
To github.com:javarb/calculator-frontend.git
   081423d..182e403  frontend-bs4 -> frontend-bs4
```

#### API

Several operation were made in order to apply the last changes in the most sequential and ordered way into Git history

First we looked in the commits history:

```bash
[jaar@port-staff api]$ git log
commit 2ebf8f787a2bae16f20e97ff4c663fbb6eecdc09 (HEAD -> bugsnag-integration, origin/bugsnag-integration)
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Mon Dec 3 16:47:42 2018 -0500

    Removing BugSnag test

commit c00c06b022cff888cd320e19e322597e8bb116bd
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Sun Dec 2 13:59:46 2018 -0500

    Bugsnag integration
...
```

And check for the state of the repo for pending changes for commit:

```bash
[jaar@port-staff api]$ git status
On branch bugsnag-integration
Your branch is up to date with 'origin/bugsnag-integration'.
...
        modified:   build.gradle
        modified:   src/main/java/co/org/osso/api/CalculatorController.java
        modified:   src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
```

In order to decide what to commit, we look in the differences between our current content and the last commit. So as is showed next, a least 3 different subjects are mixed into our differences to commit: Bugsnag integration, application logics and testing HTTP endpoints.

Then, even if we could just make a commit and get out quickly with this, the best solution should be to commit selectively depending on the subject:

```bash
[jaar@port-staff api]$ git diff
diff --git a/build.gradle b/build.gradle
index 87c18ed..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,9 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }

diff --git a/src/main/java/co/org/osso/api/CalculatorController.java b/src/main/java/co/org/osso/api/CalculatorController.java
index 6c53162..a2e2218 100644
--- a/src/main/java/co/org/osso/api/CalculatorController.java
+++ b/src/main/java/co/org/osso/api/CalculatorController.java
@@ -28,6 +28,7 @@ public class CalculatorController {
     }

     @GetMapping("/factorial/{number}")
+    // JSON that contains a biginteger
     public BigInteger getFactorial(@PathVariable("number") int number){
         return calculator.getFactorial(number);
     }
+++ b/src/main/java/co/org/osso/api/CalculatorController.java
@@ -28,6 +28,7 @@ public class CalculatorController {
     }

     @GetMapping("/factorial/{number}")
+    // JSON that contains a biginteger
     public BigInteger getFactorial(@PathVariable("number") int number){
         return calculator.getFactorial(number);
     }

diff --git a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
index fa172d1..a0fdd36 100644
--- a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
+++ b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
@@ -1,16 +1,41 @@
 package co.org.osso.api;

+import com.fasterxml.jackson.core.JsonProcessingException;
+import com.fasterxml.jackson.databind.DeserializationFeature;
+import com.fasterxml.jackson.databind.ObjectMapper;
+import org.apache.http.HttpResponse;
+import org.apache.http.auth.AuthScope;
+import org.apache.http.auth.UsernamePasswordCredentials;
+import org.apache.http.client.CredentialsProvider;
+import org.apache.http.client.methods.HttpPost;
+import org.apache.http.client.methods.HttpUriRequest;
+import org.apache.http.entity.StringEntity;
+import org.apache.http.impl.client.BasicCredentialsProvider;
+import org.apache.http.impl.client.CloseableHttpClient;
+import org.apache.http.impl.client.HttpClientBuilder;
+import org.apache.http.util.EntityUtils;
 import org.junit.Assert;
 import org.junit.Test;
+import org.junit.runner.RunWith;
+import org.springframework.boot.test.context.SpringBootTest;
+import org.springframework.boot.web.server.LocalServerPort;
+import org.springframework.test.context.junit4.SpringRunner;

+import java.io.IOException;
+import java.io.UnsupportedEncodingException;
 import java.math.BigInteger;
 import java.util.Arrays;
 import java.util.List;

+@RunWith(SpringRunner.class)
+@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 public class ApiApplicationCalculatorTests {

     Calculator target = new Calculator();

+    @LocalServerPort
+    int port;
+
     @Test
     public void checkCalculatorFibonacci(){
         List<Integer> expected = Arrays.asList(1,1,2,3,5);
@@ -59,4 +84,62 @@ public class ApiApplicationCalculatorTests {
             Assert.assertTrue(re.getMessage().contains("Error:"));
         }
     }
+
+    // look at databasic, this is the same mapper
+    private ObjectMapper mapper() {
+        final ObjectMapper mapper = new ObjectMapper();
+        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
+        return mapper;
+    }
+
+    private String serialise(final Object object) throws JsonProcessingException {
+        return mapper().writeValueAsString(object);
+    }
+
+    // HttpUriRequest is an interface parent of HttpPost
+    // If I inline http method, what is there, comes to here
+    HttpResponse send(final HttpUriRequest req) throws IOException {
+        return http().execute(req);
+    }
+
+    HttpPost post(final String path, Object payload) throws JsonProcessingException, UnsupportedEncodingException {
+        String uri = uri(path);
+        final HttpPost req = new HttpPost(uri);
+        req.setEntity(new StringEntity(serialise(payload)));
+        req.setHeader("Accept", "application/json");
+        req.setHeader("Content-type", "application/json");
+        return req;
+    }
+
+    private CloseableHttpClient http() {
+        return HttpClientBuilder.create()
+                .build();
+    }
+
+    private String uri(final String path) {
+        return baseUrl() + path;
+    }
+
+    private String baseUrl() {
+        return "http://localhost:" + port + "/";
+    }
+
+    @Test
+    public void testPost() throws Exception{
+        Book book = new Book();
+        book.setTitle("one");
+        book.setYear("2018");
+        HttpPost req = post("/api/book", book);
+        HttpResponse res = send(req);
+        Book bookResponse = deserialise(res, Book.class);
+        Assert.assertEquals("one", bookResponse.getTitle());
+        Assert.assertNotNull(bookResponse.getBookID());
+    }
+
+    // read about generic (try to understand this can be a little advanced)
+    <T> T deserialise(final HttpResponse res, final Class<T> type) throws IOException {
+        return mapper().readValue(EntityUtils.toString(res.getEntity()), type);
+    }
+
+
}
```

For select among the code to commit, we add to partially using `add -p` git flag which suggest code chunks to add (we can split more or select manually if we want). We begin adding code related to Bugsnag integration so we have to be located in the corresponding branch:

```bash
[jaar@port-staff api]$ git branch
  api-basics
  api-calculator
  api-calculator-fix-bug
* bugsnag-integration
  master

[jaar@port-staff api]$ git add -p
diff --git a/build.gradle b/build.gradle
index 87c18ed..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,9 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
Stage this hunk [y,n,q,a,d,s,e,?]? y

diff --git a/src/main/java/co/org/osso/api/CalculatorController.java b/src/main/java/co/org/osso/api/CalculatorController.java
index 6c53162..a2e2218 100644
--- a/src/main/java/co/org/osso/api/CalculatorController.java
+++ b/src/main/java/co/org/osso/api/CalculatorController.java
@@ -28,6 +28,7 @@ public class CalculatorController {
     }

     @GetMapping("/factorial/{number}")
+    // JSON that contains a biginteger
     public BigInteger getFactorial(@PathVariable("number") int number){
         return calculator.getFactorial(number);
     }
Stage this hunk [y,n,q,a,d,e,?]? n

diff --git a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
index fa172d1..a0fdd36 100644
--- a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
+++ b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
@@ -1,16 +1,41 @@
 package co.org.osso.api;

+import com.fasterxml.jackson.core.JsonProcessingException;
+import com.fasterxml.jackson.databind.DeserializationFeature;
+import com.fasterxml.jackson.databind.ObjectMapper;
+import org.apache.http.HttpResponse;
+import org.apache.http.auth.AuthScope;
+import org.apache.http.auth.UsernamePasswordCredentials;
+import org.apache.http.client.CredentialsProvider;
+import org.apache.http.client.methods.HttpPost;
+import org.apache.http.client.methods.HttpUriRequest;
+import org.apache.http.entity.StringEntity;
+import org.apache.http.impl.client.BasicCredentialsProvider;
+import org.apache.http.impl.client.CloseableHttpClient;
+import org.apache.http.impl.client.HttpClientBuilder;
+import org.apache.http.util.EntityUtils;
 import org.junit.Assert;
 import org.junit.Test;
+import org.junit.runner.RunWith;
+import org.springframework.boot.test.context.SpringBootTest;
+import org.springframework.boot.web.server.LocalServerPort;
+import org.springframework.test.context.junit4.SpringRunner;

+import java.io.IOException;
+import java.io.UnsupportedEncodingException;
 import java.math.BigInteger;
 import java.util.Arrays;
 import java.util.List;

+@RunWith(SpringRunner.class)
+@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 public class ApiApplicationCalculatorTests {

     Calculator target = new Calculator();

+    @LocalServerPort
+    int port;
+
     @Test
     public void checkCalculatorFibonacci(){
         List<Integer> expected = Arrays.asList(1,1,2,3,5);
Stage this hunk [y,n,q,a,d,j,J,g,/,s,e,?]? n
@@ -59,4 +84,62 @@ public class ApiApplicationCalculatorTests {
             Assert.assertTrue(re.getMessage().contains("Error:"));
         }
     }
+
+    // look at databasic, this is the same mapper
+    private ObjectMapper mapper() {
+        final ObjectMapper mapper = new ObjectMapper();
+        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
+        return mapper;
+    }
+
+    private String serialise(final Object object) throws JsonProcessingException {
+        return mapper().writeValueAsString(object);
+    }
+
+    // HttpUriRequest is an interface parent of HttpPost
+    // If I inline http method, what is there, comes to here
+    HttpResponse send(final HttpUriRequest req) throws IOException {
+        return http().execute(req);
+    }
+
+    HttpPost post(final String path, Object payload) throws JsonProcessingException, UnsupportedEncodingException {
+        String uri = uri(path);
+        final HttpPost req = new HttpPost(uri);
+        req.setEntity(new StringEntity(serialise(payload)));
+        req.setHeader("Accept", "application/json");
+        req.setHeader("Content-type", "application/json");
+        return req;
+    }
+
+    private CloseableHttpClient http() {
+        return HttpClientBuilder.create()
+                .build();
+    }
+
+    private String uri(final String path) {
+        return baseUrl() + path;
+    }
+
+    private String baseUrl() {
+        return "http://localhost:" + port + "/";
+    }
+
+    @Test
+    public void testPost() throws Exception{
+        Book book = new Book();
+        book.setTitle("one");
+        book.setYear("2018");
+        HttpPost req = post("/api/book", book);
+        HttpResponse res = send(req);
+        Book bookResponse = deserialise(res, Book.class);
+        Assert.assertEquals("one", bookResponse.getTitle());
+        Assert.assertNotNull(bookResponse.getBookID());
+    }
+
+    // read about generic (try to understand this can be a little advanced)
+    <T> T deserialise(final HttpResponse res, final Class<T> type) throws IOException {
+        return mapper().readValue(EntityUtils.toString(res.getEntity()), type);
+    }
+
+
 }
Stage this hunk [y,n,q,a,d,K,g,/,e,?]? n
```

Now we're commiting and pushing to that branch

```bash
[jaar@port-staff api]$ git commit -m 'Minor change'; git push
[bugsnag-integration 3e3245d] Minor change
 ...
   2ebf8f7..3e3245d  bugsnag-integration -> bugsnag-integration
```

Following we use `git show` to see details about our last commit:

```bash
[jaar@port-staff api]$ git show
commit 3e3245de5df8c3bf21805709e5ff80be255e3344 (HEAD -> bugsnag-integration, origin/bugsnag-integration)
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Tue Dec 18 11:54:18 2018 -0500

    Minor change

diff --git a/build.gradle b/build.gradle
index 87c18ed..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,9 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
```

Here, we made a mistake by mixing some content about the HTTP integration tests which will be solved in next steps.

At this point we also changed to the HTTP integration tests corresponding branch and pushed that branch to sync the remote tree:

```bash
[jaar@port-staff api]$ git checkout -b http-integration-tests
M       src/main/java/co/org/osso/api/CalculatorController.java
M       src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
Switched to a new branch 'http-integration-tests'

[jaar@port-staff api]$ git push
fatal: The current branch http-integration-tests has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin http-integration-tests

[jaar@port-staff api]$     git push --set-upstream origin http-integration-tests
Total 0 (delta 0), reused 0 (delta 0)
remote:
remote: Create a pull request for 'http-integration-tests' on GitHub by visiting:
remote:      https://github.com/javarb/api/pull/new/http-integration-tests
remote:
To github.com:javarb/api.git
 * [new branch]      http-integration-tests -> http-integration-tests
Branch 'http-integration-tests' set up to track remote branch 'http-integration-tests' from 'origin'.
```

Now, in order to fix our mistake, we came back to the previous branch (`bugsnag-integration`):

```bash
[jaar@port-staff api]$ git checkout -
M       src/main/java/co/org/osso/api/CalculatorController.java
M       src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
Switched to branch 'bugsnag-integration'
Your branch is up to date with 'origin/bugsnag-integration'.

[jaar@port-staff api]$ git show
commit 3e3245de5df8c3bf21805709e5ff80be255e3344 (HEAD -> bugsnag-integration, origin/http-integration-tests, origin/bugsnag-integration, http-integration-tests)
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Tue Dec 18 11:54:18 2018 -0500

    Minor change
...
```

Also, we could see in that branch some of the changes were pending (the other subject changes):

```bash
[jaar@port-staff api]$ git status
On branch bugsnag-integration
Your branch is up to date with 'origin/bugsnag-integration'.

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   src/main/java/co/org/osso/api/CalculatorController.java
        modified:   src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java

no changes added to commit (use "git add" and/or "git commit -a")
```

As we want to clean our tree but keep the record of the changes made, we use [`git stash`][6]:

```bash
[jaar@port-staff api]$ git stash
Saved working directory and index state WIP on bugsnag-integration: 3e3245d Minor change

[jaar@port-staff api]$ git status
On branch bugsnag-integration
Your branch is up to date with 'origin/bugsnag-integration'.

nothing to commit, working tree clean

```

Now as we wanted just to add the content related with Bugsnag integration, we reset our previous commit because as was said, we made a mistake adding the whole chunk with something about testing HTTP endpoints:

```bash
[jaar@port-staff api]$ git reset HEAD~
Unstaged changes after reset:
M       build.gradle

[jaar@port-staff api]$ git status
On branch bugsnag-integration
Your branch is behind 'origin/bugsnag-integration' by 1 commit, and can be fast-forwarded.
  (use "git pull" to update your local branch)

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   build.gradle

no changes added to commit (use "git add" and/or "git commit -a")
```

As we can see following we are as in the beggining (the changes are stashed):

```bash
[jaar@port-staff api]$ git log
commit 2ebf8f787a2bae16f20e97ff4c663fbb6eecdc09 (HEAD -> bugsnag-integration)
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Mon Dec 3 16:47:42 2018 -0500

    Removing BugSnag test

commit c00c06b022cff888cd320e19e322597e8bb116bd
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Sun Dec 2 13:59:46 2018 -0500

    Bugsnag integration
...

[jaar@port-staff api]$ git diff
diff --git a/build.gradle b/build.gradle
index 87c18ed..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,9 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
```

Notice the 2nd `+` sign, this is what we wanted to put in another commit. So we again add partially, but this time spliting again the suggestion to choose only what we want:

```bash
[jaar@port-staff api]$ git add -p
diff --git a/build.gradle b/build.gradle
index 87c18ed..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,9 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
Stage this hunk [y,n,q,a,d,s,e,?]? s
Split into 2 hunks.
@@ -28,6 +28,7 @@
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
Stage this hunk [y,n,q,a,d,j,J,g,/,e,?]? y
@@ -31,6 +32,5 @@
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
Stage this hunk [y,n,q,a,d,K,g,/,e,?]? s^C

```

If we check differences between our last commit and the content added, now we can see only bugsnag content is showed:

```bash
[jaar@port-staff api]$ git diff
diff --git a/build.gradle b/build.gradle
index 87c18ed..59083be 100644
--- a/build.gradle
+++ b/build.gradle
@@ -28,9 +28,8 @@ dependencies {
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('org.springframework.boot:spring-boot-starter-data-mongodb')
        compile('org.springframework.boot:spring-boot-starter-web')
+       compile 'com.bugsnag:bugsnag-spring:3.+'
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
-       compile 'com.bugsnag:bugsnag-spring:3.+'
-
 }

```

We push it but as we had made a push before we are behind the remote branch:

```bash
[jaar@port-staff api]$ git add . ; git commit -m 'minor change'; git push
[bugsnag-integration 9c04af9] minor change
 1 file changed, 1 insertion(+), 2 deletions(-)
To github.com:javarb/api.git
 ! [rejected]        bugsnag-integration -> bugsnag-integration (non-fast-forward)
error: failed to push some refs to 'git@github.com:javarb/api.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

Then, we forced it:

```bash
[jaar@port-staff api]$ git add . ; git commit -m 'minor change'; git push --force
On branch bugsnag-integration
Your branch and 'origin/bugsnag-integration' have diverged,
and have 1 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)

nothing to commit, working tree clean
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using up to 4 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (3/3), 298 bytes | 298.00 KiB/s, done.
Total 3 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To github.com:javarb/api.git
 + 3e3245d...9c04af9 bugsnag-integration -> bugsnag-integration (forced update)
```

Now if we compare the local content with last commit we can see the HTTP tests content pending:

```bash
[jaar@port-staff api]$ git diff
diff --git a/build.gradle b/build.gradle
index 59083be..9d011ed 100644
--- a/build.gradle
+++ b/build.gradle
@@ -32,4 +32,5 @@ dependencies {
        runtime('org.springframework.boot:spring-boot-devtools')
        testCompile('org.springframework.boot:spring-boot-starter-test')
        testCompile('de.flapdoodle.embed:de.flapdoodle.embed.mongo')
+       testCompile('org.apache.httpcomponents:httpclient:4.5.5')
 }
```

We tried to return to previous branch `http-integration-tests`, but couldn't at this time:

```bash
[jaar@port-staff api]$ git checkout -
error: Your local changes to the following files would be overwritten by checkout:
        build.gradle
Please commit your changes or stash them before you switch branches.
Aborting
```

For some reason we needed to revert the last commit and return to the previous (but seems is the same last one):

```bash
[jaar@port-staff api]$ git reset --hard
HEAD is now at 9c04af9 minor change

[jaar@port-staff api]$ git log
commit 9c04af9ed94577a563ab314b8fd26063f74b46a9 (HEAD -> bugsnag-integration, origin/bugsnag-integration)
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Tue Dec 18 15:00:55 2018 -0500

    minor change

commit 2ebf8f787a2bae16f20e97ff4c663fbb6eecdc09
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Mon Dec 3 16:47:42 2018 -0500

    Removing BugSnag test

commit c00c06b022cff888cd320e19e322597e8bb116bd
Author: Javier Arboleda <reivaj49@gmail.com>
Date:   Sun Dec 2 13:59:46 2018 -0500

    Bugsnag integration
...
```

Now the work tree was clean and we can change of branch:

```bash
[jaar@port-staff api]$ git status
On branch bugsnag-integration
Your branch is up to date with 'origin/bugsnag-integration'.

nothing to commit, working tree clean


[jaar@port-staff api]$ git checkout -
Switched to branch 'http-integration-tests'
Your branch is up to date with 'origin/http-integration-tests'.
[jaar@port-staff api]$ git status
On branch http-integration-tests
Your branch is up to date with 'origin/http-integration-tests'.

nothing to commit, working tree clean
```

We popped up what we had stashed in a previous step:

```bash
[jaar@port-staff api]$ git stash pop
On branch http-integration-tests
Your branch is up to date with 'origin/http-integration-tests'.

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   src/main/java/co/org/osso/api/CalculatorController.java
        modified:   src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java

no changes added to commit (use "git add" and/or "git commit -a")
Dropped refs/stash@{0} (599d46570d4af1869dc965e21c00c08d06670899)
```

We inspected what was pending:

```bash
[jaar@port-staff api]$ git diff
diff --git a/src/main/java/co/org/osso/api/CalculatorController.java b/src/main/java/co/org/osso/api/CalculatorController.java
index 6c53162..a2e2218 100644
--- a/src/main/java/co/org/osso/api/CalculatorController.java
+++ b/src/main/java/co/org/osso/api/CalculatorController.java
@@ -28,6 +28,7 @@ public class CalculatorController {
diff --git a/src/main/java/co/org/osso/api/CalculatorController.java b/src/main/java/co/org/osso/api/CalculatorController.java
index 6c53162..a2e2218 100644
--- a/src/main/java/co/org/osso/api/CalculatorController.java
+++ b/src/main/java/co/org/osso/api/CalculatorController.java
@@ -28,6 +28,7 @@ public class CalculatorController {
     }

     @GetMapping("/factorial/{number}")
+    // JSON that contains a biginteger
     public BigInteger getFactorial(@PathVariable("number") int number){
         return calculator.getFactorial(number);
     }
diff --git a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
index fa172d1..a0fdd36 100644
--- a/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
+++ b/src/test/java/co/org/osso/api/ApiApplicationCalculatorTests.java
@@ -1,16 +1,41 @@
 package co.org.osso.api;

+import com.fasterxml.jackson.core.JsonProcessingException;
+import com.fasterxml.jackson.databind.DeserializationFeature;
+import com.fasterxml.jackson.databind.ObjectMapper;
+import org.apache.http.HttpResponse;
+import org.apache.http.auth.AuthScope;
+import org.apache.http.auth.UsernamePasswordCredentials;
+import org.apache.http.client.CredentialsProvider;
+import org.apache.http.client.methods.HttpPost;
+import org.apache.http.client.methods.HttpUriRequest;
+import org.apache.http.entity.StringEntity;
+import org.apache.http.impl.client.BasicCredentialsProvider;
+import org.apache.http.impl.client.CloseableHttpClient;
+import org.apache.http.impl.client.HttpClientBuilder;
+import org.apache.http.util.EntityUtils;
```

And then we added:

```bash
[jaar@port-staff api]$ git add . ; git commit -m "Add HTTP verbs Integration test"; git push
[http-integration-tests c6fe812] Add HTTP verbs Integration test
 2 files changed, 84 insertions(+)
Enumerating objects: 31, done.
Counting objects: 100% (31/31), done.
Delta compression using up to 4 threads
Compressing objects: 100% (8/8), done.
Writing objects: 100% (17/17), 2.53 KiB | 863.00 KiB/s, done.
Total 17 (delta 3), reused 0 (delta 0)
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
To github.com:javarb/api.git
   3e3245d..c6fe812  http-integration-tests -> http-integration-tests
```

Fetch to sync local master with remote master and tree of remote braches locally:

```bash
[jaar@port-staff api]$ git fetch
remote: Enumerating objects: 2, done.
remote: Counting objects: 100% (2/2), done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 2 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (2/2), done.
From github.com:javarb/api
   f77c479..37c4a2d  master     -> origin/master

[jaar@port-staff api]$ git branch -a
  api-basics
  api-calculator
  api-calculator-fix-bug
  bugsnag-integration
* http-integration-tests
  master
  remotes/origin/api-basics
  remotes/origin/api-calculator
  remotes/origin/api-calculator-fix-bug
  remotes/origin/bugsnag-integration
  remotes/origin/http-integration-tests
  remotes/origin/master
```

Also we added the flag `-p` (`prune`) to the `fetch` command in order to sync deleting, and so avoid to see locally remote branches that doesn't existed remotely:

```bash
[jaar@port-staff api]$ git fetch -p
From github.com:javarb/api
 - [deleted]         (none)     -> origin/api-basics

[jaar@port-staff api]$ git branch -a
  api-basics
  api-calculator
  api-calculator-fix-bug
  bugsnag-integration
* http-integration-tests
  master
  remotes/origin/api-calculator
  remotes/origin/api-calculator-fix-bug
  remotes/origin/bugsnag-integration
  remotes/origin/http-integration-tests
  remotes/origin/master
```

Now we added and pushed some content about an application specific bug fix to the corresponding branch:

```bash
[jaar@port-staff api]$ git checkout api-calculator-fix-bug
Switched to branch 'api-calculator-fix-bug'
Your branch is up to date with 'origin/api-calculator-fix-bug'.
```

And merge from remote master in order to integrate the last changes merged into master, into our current branch:

```bash
[jaar@port-staff api]$ git merge origin/master
Auto-merging src/main/java/co/org/osso/api/Calculator.java
CONFLICT (content): Merge conflict in src/main/java/co/org/osso/api/Calculator.java
Automatic merge failed; fix conflicts and then commit the result.
```

As Automatic merge cannot be done, some conflicts must be fixed manually:

```bash
[jaar@port-staff api]$ git status
On branch api-calculator-fix-bug
Your branch is up to date with 'origin/api-calculator-fix-bug'.
...
Changes to be committed:

        modified:   build.gradle
        new file:   src/main/java/co/org/osso/api/BugsnagConfig.java
        modified:   src/main/java/co/org/osso/api/CalculatorController.java

Unmerged paths:
  (use "git add <file>..." to mark resolution)

        both modified:   src/main/java/co/org/osso/api/Calculator.java
```

After the conflicts has been fixed, then we add, commit and push:

```bash
[jaar@port-staff api]$ git add . ; git commit;
[api-calculator-fix-bug 9607af3] Merge remote-tracking branch 'origin/master' into api-calculator-fix-bug

[jaar@port-staff api]$ git push
Enumerating objects: 28, done.
Counting objects: 100% (28/28), done.
Delta compression using up to 4 threads
Compressing objects: 100% (6/6), done.
Writing objects: 100% (10/10), 776 bytes | 388.00 KiB/s, done.
Total 10 (delta 3), reused 0 (delta 0)
remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
To github.com:javarb/api.git
   ed9586c..9607af3  api-calculator-fix-bug -> api-calculator-fix-bug
```

Finally that branch is [merged to master][10] and deleted.

The only branch that remain still is `http-integration-tests`.

### Resources

- [Git checkout -][5]
- [Git stash][6]
- [Merging vs Rebasing][7]
- [Kanban definition][9]
- [Domain Specific Languages books][8]

[1]: https://github.com/javarb/api/projects/1
[2]: https://github.com/javarb/calculator-frontend
[3]: https://github.com/javarb/calculator-frontend/pull/2
[4]: https://github.com/javarb/calculator-frontend/pull/3
[5]: http://marcgg.com/blog/2015/10/18/git-checkout-minus/
[6]: https://git-scm.com/docs/git-stash
[7]: https://www.atlassian.com/git/tutorials/merging-vs-rebasing
[8]: https://martinfowler.com/books/dsl.html
[9]: https://en.wikipedia.org/wiki/Kanban
[10]: https://github.com/javarb/api/pull/4