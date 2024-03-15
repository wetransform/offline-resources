layout: true

<div class="we-header"><a href="https://www.wetransform.to"><img src="images/we.png"></a> <div>Offline resources overview</div></div>

---
class: center, middle

# Offline resources

.small[2024-03-18]


???

Discussion about use in hale studio, desired confguration:
https://wetransform.slack.com/archives/C8Z981X0W/p1706775949250009?thread_ts=1705917003.471539&cid=C8Z981X0W


---

# What kind of resources?

- Schema files (INSPIRE + dependencies, XPlanung, OGC)
- Information from INSPIRE registry (used in hale-studio)
    - Code lists
    - Feature concepts
    - Schema list for preset generation

---

# Where are they used?

- hale-studio
- hale-cli (via hale-studio dependencies)
- hale Gradle Plugin (via hale-cli dependency)
- hale-connect
    - hale-studio dependencies
        - Schema Service
        - Project Service
        - Transformation
    - Kelvin
    - Service Publisher (via Kelvin dependencies)


---

# Why use offline resources?

- Solve issue with the HTTPS redirects (libraries not able to handle redirect)
- Speeds up loading (especially some of) the resources
- Concrete problems in the past that online resources
    - were not available temporarily
    - were removed
    - were changed in a way was that resulted in problems

**Main problem**

- Stay up-to-date with changes

???

The changes themselves are also increasingly becoming a problem (e.g. breaking changes in schemas which did usually not happen in the past)

---

# How to update offline resources?

**We used to**

- Update seldom, often only if there was an external trigger (e.g. issue raised because of outdated resources)
- Use different approaches for hale-studio and hale-connect:
    - Add resource bundles to hale-platform update site
    - Publish Maven artifacts in offline-resources repository

---

# How to update offline resources?

**Now**

- We get notified if there is a change
    - we can review the changes and
    - automated tests are run (though at the moment they only cover a few small things that could go wrong)
- We have a common build producing artifacts used in both hale-studio and hale-connect


---

# How to update offline resources?

- Resources are [downloaded regularly](https://github.com/wetransform/offline-resources/actions/workflows/update-resources.yml) (currently daily) and checked for changes
- If there are changes
    - Increase version of respective bundle (uses date based versions)
    - Creates a Pull Request ([Example](https://github.com/wetransform/offline-resources/pull/43))
- After merging, publish Maven artifacts and dedicated update site

---

# hale-studio - Integration

**Current approach:**

Update site contains only latest versions of resources.

*Pros:*

- We are forced to update if there are new versions (otherwise the build fails)
- Independent of any changes in hale-platform update site

*Cons:*

- Old commits cannot be built unless the reference to the update site is up-to-date

---

# hale-studio - Integration

**Possible improvements**

- Instead of relying on build failure as trigger for update, actively create a PR to change the target platform
    - This would allow to keep (or merge) old update site versions

---

# hale-studio - What if resources get outdated?

Two strategies we want to follow in parallel

1. Update offline resources on changes, release hale-studio easier and more often
2. Give users control over the behavior

---

# hale-studio - Configurable behavior

- Introduce a setting that allows disabling using offline resources
    - Should be possible to set both in the UI and headless
    - Could be later extended with:
        - Option to only use offline-resources if using online resource fails
        - Specifying a list of URLs to ignore
- Add information *From URL* and *From Preset* for imports if offline resources are enabled, to make users aware of the behavior

---

# Usage as Maven dependencies

Except for hale-studio and Service Publisher, all other components include offline resources as Maven dependencies.

---

# Usage as Maven dependencies

**Approach 1:**

Use `CURRENT-SNAPSHOT` version to always get the latest version ([Example](https://github.com/wetransform/schema-service/blob/3b470f795a5cb3227eb0e90fd9cce02e2e053d1f/build.gradle#L137)).

Pros:

- On build automatically uses the latest version

Cons:

- Dependencies not stable: Resources can change unexpectedly/unintentionally
- Builds need to be triggered for the resources to be updated

---

# Usage as Maven dependencies

**Approach 2:**

Use release versions of Maven artifacts ([Example](https://github.com/halestudio/hale-cli/blob/74ce2de3044d8962934088857921b70b80e4e543/build.gradle#L144)).

Pros:

- Stable dependencies, reproducable results

Cons:

- Requires dependency management tools to keep the resources up-to-date (For [example using Renovate](https://github.com/halestudio/hale-cli/pull/116)).

---

# Integration in Service Publisher

Currently remains the task that requires most effort.

It requires:

- Dependencies in kelvin to be updated first
- Kelvin artifacts to be published
- The service-publisher-platform update site updated with the new Kelvin artifacts
- The target platform definition update with the new update site version
