# Pull requests and peer-reviews in GitHub

Pull requests (or PRs) are a powerful way of reviewing code, or work tracked with Git in general. A PR is requesting permission to merge one Git branch into another. In this course we're following a practice called [trunk based development](https://trunkbaseddevelopment.com/), commonly used when developing applications that are released regularly (rather than libraries or applications released at most once a sprint). The 'base' branch is therefore almost always going to be the `master` branch.

## Raising pull requests

GitHub will suggest that you create a pull request if you've recently pushed a branch (within the last couple of hours). This is going to be the main way you open PRs, but the GitHub UI is hopefully friendly enough that you should be able to figure out how to raise PRs even after this time has passed.

- [ ] **Do a self-review**. The diff of your changes should be visible before you press the button to open the PR. Assume that the reviewer's time is more precious than yours. This is generally the case, not because the reviewer is senior to you -- seniority between reviewer and creator is irrelevant -- but because you have a lot more insight into this change and can spot most mistakes much faster than the reviewer can.
- [ ] Check if the changes done in this PR should really have been done in multiple PRs. This is generally the case when one branch contains multiple pieces of work that could have been done independently of each other.
- [ ] Ensure the title of the PR makes sense at a glance. "Sprint-19 Task-53" conveys very little information whereas "Upgrade JSON library version" is good.
- [ ] Ensure the description of the PR contains any *important* information about the PR. Anything non-obvious about the change should go here, as well as any concerns you might have. The description is not a good place for questions. If you've got questions relating to work in this course just ask in Slack. If you've got questions about an open-source project on GitHub, raise an issue instead of a PR. For really trivial changes a title-only (no description) PR is OK (e.g. fixing a typo).
- [ ] Assign a reviewer. It is likely that nobody even knows about the PR unless you assign a reviewer. Choosing an 'assignee' is not necessary.

It is fine to raise an early-PR, i.e. raising a PR of work that you know is incomplete, when you might want guidance or confirmation of an approach you're taking. These PRs should be clearly labelled as such.

## Reviewing PRs

- [ ] *Be nice*. PRs are a form of communication between your teammates, just like emails, except they are inherently about critiquing someone's work. A good relationship with your teammate is always more important than perfect code.
- [ ] Treat reviews of your teammate's work as high priority tasks. A team that fully incorporates this value is rarely blocked, and is more productive as a result.
- [ ] Be clear. Just like we refactor our code to be clearer and more precise, we should do the same when doing review comments.
- [ ] Be constructive. If you're going to leave a comment make sure you suggest how to improve it. Just pointing out what's wrong is often not enough to identify a better approach. Unless it is obvious, try and explain the 'why' in addition to the 'what'. For example "This method doesn't always do what you think it does." is a terrible review comment whereas "This method doesn't work when the list we pass in is null, as this line will throw a `NullPointerException`. Let's make sure we add a test case for this." is better.
- [ ] Don't lose track of the big picture. It's tempting to just read the PR in one go and leave comments wherever you find something you'd do differently. Take a step back and remember to look at the approach as a whole. Is there a simpler way to do all of this? E.g. if there is a lot of advanced formatting involved, it's best to use an existing library instead of trying to implement dozens of lines of code to do the same thing.
- [ ] Use inline comments when possible. Only use 'global' comments to talk about the general approach of the change.
- [ ] Don't use 'Request changes' for trivial changes. See: 'Be nice'. This is just a waste of time. Comment, approve the PR, and trust that your teammate is going to make the trivial fixes.
- [ ] Allow different people to have different coding styles. Nit-picking on minor style issues (e.g. missing `final` on local variables, or turning `for` loops into `forEach` using streams etc.) should be avoided, unless for teaching purposes. These types of comments require a lot of time for very little value, and can feel like it's done as a power play. See: 'Be nice'.
- [ ] Unless the change is trivial, check out the branch on your own machine and play around with it to verify that the change does what it should do.

## Receiving reviews

A PR is a living entity. If you push more commits to the existing branch then the PR will reflect these changes too. There is therefore no need to close a PR if there are changes requested. Just address them by pushing more commits to the branch, and re-request the review.

There is no hard and fast rule on who merges the PR. It largely depends on the team you're working in and what kind of application you're writing.

- [ ] Regardless of who merges, do not merge unless the pull request has been reviewed and approved.
- [ ] Address your review comments in a manner such that it's easy to review. One way to guarantee that it's easy to review is to address each review comment in a separate commit.
- [ ] If you disagree with a review comment you may argue against your case as a reply to the comment itself. That being said, even if your case has merit it's likely that the code it was left on is difficult to understand, and will require some form of change anyway.
- [ ] To help yourself identify which comments you've addressed and which comments are still outstanding you can use the "Resolve conversation" button. Never delete review comments left by someone else.
- [ ] Note that comments that are left on a line of code that has been changed since it was written will show up as outdated. To ensure you make use of this automation that GitHub provides, ensure that considerable refactors and renaming files are made last, and preferably in a separate commit.
- [ ] Ensure all the comments have been addressed before merging/re-requesting reviews. Double checked the 'Outdated' comments.
- [ ] If you have set up a form of continuous integration ensure the build passes before merging. Ensure the build passes on master after it has been merged as well. If it does not pass, relax, figure out the issue, and raise another PR to resolve it.
- [ ] One branch == one feature, so delete the branch after you're done.
- [ ] Only close a PR without merging if you don't intend to continue this feature, or if the approach taken is not the way to go.

## Examples

Here is [a good example](https://github.com/JStonham/budjen/pull/10) of what a reasonably sized pull request looks like.

## Advanced: `pull_request_template.md`

If you like the idea of automating your workflow you may wish to set up a [pull request template](https://help.github.com/articles/creating-a-pull-request-template-for-your-repository/) in your repo. This markdown file will automatically be pasted in the description of a PR whenever anyone raises a PR in that repo. You may wish to grab some bits from the checklists above and create your own template. The markdown source file for this document can be [found here](https://github.com/kinbiko/teaching-resources/blob/master/pull-requests.md).

That being said, in mature teams incorporating this type of process around PRs ends up wasting more time than it saves. It is therefore recommended to add this only to projects where contributors have vastly different experience with the project. E.g. open source projects with many outside contributors.
