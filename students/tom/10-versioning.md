# Versioning

## Semantic Versioning

With so much change in the world of software development, each version of the project should have a unique identifier code so as to distinguish one version from another. Semantic Versioning, or `semver` comes to the rescue.

> In the world of software management there exists a dreaded place called “dependency hell.” The bigger your system grows and the more packages you integrate into your software, the more likely you are to find yourself, one day, in this pit of despair. `-https://semver.org/`

Given a version number MAJOR.MINOR.PATCH, increment the:
- MAJOR: incompatible API changes, (aka `Breaking`)
- MINOR: added functionality in a backwards-compatible manner (aka `Feature`)
- PATCH: backwards-compatible bug fixes.

Major version zero (0.y.z) is for initial development, during this phase anything may change at any time, therefor the public API should not be considered stable. Use the "Major version zero" at your own peril.

Once a version deemed for public use is created it will be released with the 1.0.0 designation. After this, the way in which the major/minor/patch values are incremented will be decided by the developers.

> Managing software complexity is a hugely important part of keeping a project efficient, and that’s hard to do if nobody knows how to use your software, or what methods are safe to call. In the long run, Semantic Versioning, and the insistence on a well defined public API can keep everyone and everything running smoothly. `-https://semver.org/`
