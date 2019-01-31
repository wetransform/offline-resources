offline-resources
=================

Repository collecting cached versions of online resources.

It includes a Gradle build that is configured with downloading, packaging and publishing online resources for offline use in different contexts.

Currently the actual resources are not added to the repository, though this may change at a later point if we think it makes sense.

The goal is to use this as a base for using these resources for offline use in different applications, e.g. hale studio, deegree, etc.

Resource JARs
-------------

The build includes tasks to create and publish JARs containing the offline resources.
The JARs are published into the wetransform artifactory.

For versioning the artifacts, three different patterns are used:

- Date of the download/creation as version, e.g. `2019.1.20` for the 20th of January 2019
- A snapshot based on the current year, e.g. `2019-SNAPSHOT`
- A snapshot that always represents that last state (`CURRENT-SNAPSHOT`)

If applicable, all these versions are built and published.

TODOs / Improvements
--------------------

- produce JARs compatible to hale resources mechanism
- add configuration for existing hale resource bundles currently built in hale-platform repository
- allow suppressing the creation/publishing of `CURRENT-SNAPSHOT` versions in case old versions are built
- possibly add downloaded resources to version tracking to be able to track changes
