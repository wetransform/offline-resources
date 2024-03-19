offline-resources
=================

Repository collecting cached versions of online resources.

It includes a Gradle build that is configured with downloading, packaging and publishing online resources for offline use in different contexts.

The resources are added to the repository to be able to track changes and keep maintaining the resources in case the original source is no longer available or maintained.

The goal is to use this as a base for using these resources for offline use in different applications, e.g. hale studio, deegree, etc.

The resources can be accessed as follows:

- Maven artifacts are published to Artifactory
- An update site is published to S3 and available [here](http://build-artifacts.wetransform.to/p2/offline-resources/site)

 **Please note:**

Eclipse caches the content of an update site from a specific location, so for Eclipse to actually be able to load the current update site content you may need to invalidate the respective cache.

*Option 1:* You can find and delete the cache folder in your Eclipse workspace in the subfolder `.metadata/.plugins/org.eclipse.pde.core/.p2/org.eclipse.equinox.p2.repository/cache`.

*Option 2:* It seems to be possible to invalidate the cache via the UI (see [these instructions](https://stackoverflow.com/a/70291188/982265)).

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

### Hale Studio specific resource JARs

The repository includes the build for a number of resource JARs that are specifically intended to be used with hale studio.
They are in part duplicates to the generic resource JARs, e.g. with OGC and INSPIRE schemas but use a different structure (files are not placed in subfolders) and add additional information.
They can be recognized by the prefix `eu.esdihumboldt.util.resource` in the artifact name and the bundle symbolic name.

TODOs / Improvements
--------------------

- in hale studio support resource JARs that add resources in subfolders to avoid potential clashes in the classloader when not using OSGi
- allow suppressing the creation/publishing of `CURRENT-SNAPSHOT` versions in case old versions are built
