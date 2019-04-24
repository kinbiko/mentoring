# SOFTWARE ENGINEERING IN JAVA

## Session 8 (24/07/2018)
- Defining work flow in Github's `notes` repo pull requests.
- Fix a pull request with a lot of files and different commits.
- Cleaning a little Databasic.


### Notes:

** Work flow for GitHub's `notes` repo Pull Requests (PR) **

There was a GitHub's PR for session 5, but as the time was passing over, changes in the session 6 and 7 were added. Thus, this PR was having each time more and more commits.

Have a Pull Request with so many files isn't a good practice, since the reviewer will have to inspect a lot of code each time. So, its considered as a good rule of thumb that a PR have less than 500 changed files per solicitude (PR). In that way we'll be spliting our work in several pieces that are easier to review.

So in ahead, a PR will be made for each particular session.

In order to achieve this, a branch must be created for each particular session, after changes be pushed to that the session branch, a PR must be generated in order to merge to master once PR be approved.

**Fixing our PR solicitude**

Once the flow was defined, in order to fix the issue of have several commits in the same pull request, we had to create 2 branches. The first branch should to contain what was reviewed still that moment by Roger (Session 5 and 6), and the other branch to keep the Session 7 content.

As a parenthesis, the idea, as mentioned before, is to make a pull request for each session content. This means, that for example for this Session 8, a branch `session-8` should be generated, a PR opened in order to finally merge this branch into master once the PR be accepted.

Returning with our issue, the following changes were made in order to achieve our desired state.

The first thing was to get the desired commit id:

```bash
$ git log --oneline
dcbb20a (HEAD -> master, origin/master, origin/HEAD) Merge corrections
e7b5097 Merge corrections
eac52c5 Added Session 7
fea3185 Updated TODOS
e892ef0 some updates
d8dc181 Added session 6 content
413c4f5 Updates to session 5
d7549fd Created Session 6 file
05d4ba1 Updated Session 5
3efe033 Updates to session 5
49a5301 Updated session 5
3fcea34 Changes in Session 5
d4e5719 Updated requested changes PR Session 4
...
```

The selected commit was `3fcea34 Changes in Session 5` because that was the first commit after the PR for Session 4. In order to make `HEAD` to point to that commit we used the following command:

```bash
$ git checkout 3fcea34
Note: checking out '3fcea34'.

You are in 'detached HEAD' state. You can look around, make experimental changes and commit them, and you can discard any commits you make in this state without impacting any branches by performing another checkout.

If you want to create a new branch to retain commits you create, you may do so (now or later) by using -b with the checkout command again. Example:

  git checkout -b <new-branch-name>

HEAD is now at 3fcea34... Changes in Session 5
```
Now, from that point in history, we created a new branch in order to put there the content that Roger had reviewed still that moment (Sessions 5 and 6):

```bash
$ git checkout -b session-5-and-6
Switched to a new branch 'session-5-and-6'
```

As we tried to do `git push` we found an error because in GitHub that branch doesn't existed yet.

```bash
$ git push
fatal: The current branch session-5-and-6 has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin session-5-and-6
```

In order of create that remote branch ans sync with the local we executed:

```bash
$ git push --set-upstream origin session-5-and-6
Total 0 (delta 0), reused 0 (delta 0)
To github.com:javarb/notes.git
 * [new branch]      session-5-and-6 -> session-5-and-6
Branch session-5-and-6 set up to track remote branch session-5-and-6 from origin.
```

At this point was neccesary to copy the raw content for Sessions 5 and 6 from the commit in the `master` branch of my repo in GitHub, in order to paste them into the corresponding files in the local branch `session-5-and-6 `. That, in order of recreate those changes as be made in the corresponding branches and not in `master`.

So files were updated, commited and pushed to the remote branch:

```bash
$ git add sessions/2018/july/Session6.md ; git commit -m 'Session 6 added'; git push

$ git add sessions/2018/june/Session_5.md ; git commit -m 'Session 5 added'; git push
```

Now, we can see how two commits more:

```bash
$ git log --oneline
e80a04d (HEAD -> session-5-and-6, origin/session-5-and-6) Session 5 added
126ab46 Session 6 added
3fcea34 Changes in Session 5
...
```

And we made the same for the content of the session 7 by creating a new branch from the branch we are currently on `session-5-and-6`:

```bash
$ git checkout -b session-7
Switched to a new branch 'session-7'
```

We also setted and push the upstream branch:

```bash
$ git push --set-upstream origin session-7
```

At this point, there a typo of the session 6 file this was called `Session6.md` but the name should have been `Session_6.md` :

```bash
$ ls
Session6.md  Session_7.md
```

In order to solve this, we just have to rename:

```bash
$ mv Session6.md Session_6.md
```

If we execute`git status` we saw those changes:

```bash
$ git status
On branch session-7
Your branch is up-to-date with 'origin/session-7'.
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        deleted:    sessions/2018/july/Session6.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        sessions/2018/july/Session_6.md
        sessions/2018/july/Session_7.md

no changes added to commit (use "git add" and/or "git commit -a")
```

Then, when must to add this new file that needs to be commited in the following way so Git is capable to interpret that we only changed the name:

```bash
$ git add sessions/2018/july/Session6.md sessions/2018/july/Session_6.md

$ git status
On branch session-7
Your branch is up-to-date with 'origin/session-7'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        renamed:    sessions/2018/july/Session6.md -> sessions/2018/july/Session_6.md

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        sessions/2018/july/Session_7.md
```

So, as this was solved, we commited those changes:

```bash
$ git commit -m 'rename Session 6'; git push
```

After, we made the correspondent changes into session 7 file using my GitHub master branch raw content:

```bash
$ git add sessions/2018/july/Session_7.md ; git commit  -m 'Added Session 7'; git push
```

Now, as we needed to clean our master, we had to delete it. **This is very dangerous** and only must be done if we are sure what we are doing. In this case was okay because after the two new branches were pushed and two pull requests were made, Roger was approving and merging into his `master` branch. Then, I'm going to create my `master` branch from his `master` branch.

So, in order to do this I place in the previously selected source (talking of our purposes) commit:

```bash
$ git checkout 3fcea34
```

Now we see we are placed again in the commit `3fcea34 (HEAD) Changes in Session 5`:

```bash
$ git log --oneline
3fcea34 (HEAD) Changes in Session 5
d4e5719 Updated requested changes PR Session 4

$ git branch
* (HEAD detached at 3fcea34)
  master
  session-5-and-6
  session-7
```

As can be seen, `HEAD` is `dettached` from any specific branch and so we were able to delete our master branch:

```bash
$ git branch -d master
warning: deleting branch 'master' that has been merged to
         'refs/remotes/origin/master', but not yet merged to HEAD.
Deleted branch master (was dcbb20a).
```

After it, `master` branch is created again:

```bash
$ git checkout -b  master
Switched to a new branch 'master'
```

And the upstream channel is setted up (still not inmediatly):

```bash
$ git push --set-upstream origin master
To github.com:javarb/notes.git
 ! [rejected]        master -> master (non-fast-forward)
error: failed to push some refs to 'git@github.com:javarb/notes.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

As we can see, in the beginning there were errors because master remotely had another content, but as we know what we are doing, we force it anyway:

```bash
$ git push --set-upstream origin master -f
```

Finally, we create the branch for this Session 8. **This has a problem which is explained more ahead (1)**:

```bash
$ git checkout -b session-8
$ git push --set-upstream origin session-8
```

As at this point (commit `3fcea34 (HEAD) Changes in Session 5`) in which we based our new branch, there wasn't a *july* folder created, we've created it:

```bash
$ mkdir sessions/2018/july
$ touch sessions/2018/july/Session_8.md

$ git log --oneline
3fcea34 (HEAD -> session-8, origin/session-8, origin/master, origin/HEAD, master) Changes in Session 5
...

$ git branch
  master
  session-5-and-6
  session-7
* session-8
```

**(1)** But as was previously mentioned, there was an issue with this approach, since this folder should have been created from Session 7 content, not Session 5 (because is outdated) and we be able to include the last merged changes still the moment, else, the changes of session 5,6 and 7 should be losed when we merge to the `master` branch.

In order to fix this situation, the following steps were addressed:

We backed up our `Session_8.md` file to a different location:

```bash
$ cp Session_8.md <backup_dir_location>
```

Changed to the branch we desire to be based on:

```bash
$ git checkout -m session-7

$ git branch
  master
  session-5-and-6
* session-7
  session-8

$ git log --oneline
e96b972 (HEAD -> session-7, origin/session-7) Added Session 7
f9d2a06 rename Session 6
e80a04d (origin/session-5-and-6, session-5-and-6) Session 5 added
126ab46 Session 6 added
3fcea34 (origin/session-8, origin/master, origin/HEAD, session-8, master) Changes in Session 5
...
```

At this moment a particular situation arised because the use of the [`git checkout -m` flag][1]. When I switched of branch, the file  `Session_8.md` existed also in `session-7` branch, which I wasn't expecting to happend (this was a typo in first...):

```bash
$ ls
Session_6.md  Session_7.md  Session_8.mdm
```

Returning to the point, as I was in the `session-7` branch, I deleted the `session-8` branch in order to commit first the changes suggested by Roger in [PR #4](https://github.com/kinbiko/notes/pull/4), merge to his and mine `master` branch, and after create the `session-8` branch based on master (with session-7 merged):

```bash
$ git branch -d session-8
Deleted branch session-8 (was 3fcea34).
```

In order to not contaminate the Session 7 merge, with content corresponding to Session 8, I deleted also the ` Session_8.md` file:

```bash
$ rm Session_8.md
```

So I did changes as suggested by Roger for Session 7 PR and merged without problem in his master branch (He conceded to me editor permissions in his repo, and I also inversely).

Now, before to merge into my master, I am seeing this message into my session-7 branch in GitHub (I would like why?):

> *This branch is 3 commits ahead, 3 commits behind master.*

And in the master Roger's branch says:

>  *This branch is 4 commits ahead, 1 commit behind javarb:master.*

To merge my own changes into my master branch in GitHub, I open a [PR](https://github.com/javarb/notes/pull/3) in order to do this.

After in GitHub I deleted branches aren't needed anymore:
- *session-7* (merged)
- *session-5-and-6* (merged)
- *session-8* (will be recreated)

But locally they continue existing:

```bash
$ git branch
  master
  session-5-and-6
* session-7
```

Next, I switch to `master` branch in order to pull the changes made merged in GitHub:

```bash
$ git checkout master
Switched to branch 'master'
Your branch is up to date with 'origin/master'

$ git log --oneline
3fcea34 (HEAD -> master, origin/session-8, origin/master, origin/HEAD) Changes in Session 5
...

$ git pull

$ git log --oneline
df50e8e (HEAD -> master, origin/master, origin/HEAD) Merge pull request #3 from javarb/session-7
e327814 (origin/session-7, session-7) Minor changes
72d74a3 Merge pull request #2 from kinbiko/master
e96b972 Added Session 7
f9d2a06 rename Session 6
f31033e Merge pull request #3 from javarb/session-5-and-6
e80a04d (origin/session-5-and-6, session-5-and-6) Session 5 added
126ab46 Session 6 added
5dde3c7 Merge pull request #1 from javarb/master
3fcea34 (origin/session-8) Changes in Session 5

$ git status
On branch master
Your branch is up to date with 'origin/master'.

$ git branch
* master
  session-5-and-6
  session-7
```

After it, I tried to sync the remote branches with local (in order to delete the local ones) but it din't work:

```bash
$ git pull -p
From github.com:javarb/notes
 - [deleted]         (none)     -> origin/session-5-and-6
 - [deleted]         (none)     -> origin/session-7
 - [deleted]         (none)     -> origin/session-8
Already up to date.

$ git branch
* master
  session-5-and-6
  session-7

$ git fetch origin --prune

$ git branch
* master
  session-5-and-6
  session-7
```

So finally I did it manually:

```bash
$ git branch -d session-5-and-6 session-7
Deleted branch session-5-and-6 (was e80a04d).
Deleted branch session-7 (was e327814).

$ git branch
* master
```

Then I could to create the branch in order to commit the session content (this file) and open a new PR:

```bash
$ git checkout -b session-8
$ git push --set-upstream origin session-8
```

Now that the branch was in sync with GitHub, I copied `Session_8.md` file and made the commit:

```bash
$ cp <backup_dir_location>/Session_8.md sessions/2018/july/

$ git status
On branch session-8
Your branch is up to date with 'origin/session-8'.

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        sessions/2018/july/Session_8.md
...

$ git add sessions/2018/july/Session_8.md ; git commit -m 'Added Session 8'; git push
```

Finally, updates were placed to this file, commited and a new PR was opened in GitHub in order to merge to master once approved.

**Working with Databasic**

Databasic was reviewed to clean code, so for example a method with few lines was deleted and the lines were placed into (inline) the calling method.

In order to convert a method in a `inline` statement we should use `ctrl+atl+n`.

Other significative change was that the query requirement was updated in `README.md` file in order that the program required the string `query` in order to process a query.

### Resources
- [Git Checkout documentation `-m` flag][1]
- [Git Changing history][2]

[1]: https://git-scm.com/docs/git-checkout#git-checkout---merge
[2]: https://stackoverflow.com/a/8981216
###TODO

- [ ] Review code and change where this can be optimized, comments, logic. As good as I can
- [ ] Implements the `query` option
