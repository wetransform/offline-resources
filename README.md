offline-resources
=================

Repository collecting cached versions of online resources.

It includes a Gradle build that is configured with downloading, packaging and publishing online resources for offline use in different contexts.

The resources are added to the repository to be able to track changes and keep maintaining the resources in case the original source is no longer available or maintained.

The goal is to use this as a base for using these resources for offline use in different applications, e.g. hale studio, deegree, etc.

The resources can be accessed as follows:

- Maven artifacts are published to Artifactory
- An update site including only the latest versions is published to S3 and available [here](http://build-artifacts.wetransform.to/p2/offline-resources/current)

Resource JARs
-------------

The build includes tasks to create and publish JARs containing the offline resources.
The JARs are published into the wetransform artifactory.

For versioning the artifacts, three different patterns are used:

- Date of the download/creation as version, e.g. `2019.1.20` for the 20th of January 2019 (if multiple versions are created on the same day, a qualifier is added once the second version for the same day is created)
- A snapshot based on the current year, e.g. `2019-SNAPSHOT`
- A snapshot that always represents that last state (`CURRENT-SNAPSHOT`)

If applicable, all these versions are built and published.

Currently there are the following types of JARs supported:

1. Based on host name and resource paths
2. Downloaded schemas with all dependencies (currently not maintained)


### Host name based resource JARs

Host name based resource JARs are used to load resources that come from a specific host and are available there on a specific path.
These kind of JARs are build from resources downloaded to `resources/hosts/<host-name>`.
The folder structure beneath must match the paths the resources are available at on the original host.

The task that creates the JARs adds these files to the folder `to/wetransform/offline-resources/hosts/<host-name>` and respective subfolders.
This is used for example by kelvin in the respective [implementation for loading the offline resources](https://github.com/wetransform/kelvin/blob/master/deegree-core/deegree-core-commons/src/main/java/org/deegree/commons/offlineresources/OfflineResources.java)


TODOs / Improvements
--------------------

- produce JARs compatible to hale resources mechanism
- add configuration for existing hale resource bundles currently built in hale-platform repository
- allow suppressing the creation/publishing of `CURRENT-SNAPSHOT` versions in case old versions are built
