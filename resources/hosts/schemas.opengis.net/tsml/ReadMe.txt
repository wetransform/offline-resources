OGC(r) TimeseriesML (TSML) schema - ReadMe.txt
=======================================================================

TimeseriesML defines an XML encoding that implements the OGC Timeseries Profile
of Observations and Measurements with the intent of allowing the exchange of
such data sets across information systems.

More information may be found at
 http://www.opengeospatial.org/standards/tsml

The most current schema are available at http://schemas.opengis.net/tsml/ .

-----------------------------------------------------------------------

2021-03-04  Paul Hershberg
  + v1.3: Published TSML 1.3.0 as tsml/1.3 from OGC 15-042r6.

    Contained in v1.3 is the allowance for a specific count of the
    number of time steps in a timeseries, or each homogenous (regularly
    spaced) segment of an irregularly spaced whole timeseries.

    It is an optional TimeseriesMetadata property, called numberTimeSteps,
    of type Integer for both the TVP and Domain-Range encodings.

    TimeseriesMetadata is of type "tsml:TimeseriesMetadataType". The
    additional property numberTimeSteps was added as a child element to
    this type in the schema timeseriesMetadata.xsd.

2019-09-19  James Tomkins, Dominic Lowe, Paul Hershberg
  + v1.2: Published TSML 1.2.0 as tsml/1.2 from OGC 15-042r5.

    v1.2 Amendment 1)

    In order to accommodate metadata applicable to an irregularly
    spaced whole timeseries for Domain-Range encoding, an amendment to
    increase the cardinality of timeseriesMetadata from "1" to "unbounded"
    has been added to timeseriesDR.xsd in v1.2.

    The amendment allows for dividing the entire irregularly spaced
    timeseries with different time spacings into segments that do contain
    regularly spaced time steps. Metadata can then be used to describe
    each of these individual segments. The amendment also necessitates an
    update to the documentation of both timeseriesMetadata in
    timeseriesDR.xsd and its' child element TimeseriesMetadata in
    timeseriesMetadata.xsd to denote that metadata can be applied to the
    whole timeseries or individual regularly spaced segments of the whole
    irregularly spaced timeseries.

    v1.2 Amendment 2)

    Previously, the TimeseriesML Domain-Range schema only allowed for
    a list of instantaneous time positions to be encoded under the
    gml:domainSet element (TimePositionList).

    An amendment to the timeseriesDR.xsd allows the encoding of a list
    of Time Periods, which contain both a beginning dateTime and an ending
    dateTime. This has been added primarily to denote an observation's
    valid time that spans a range in time. For example, a maximum
    temperature that is valid from 12Z to 00Z needs a valid time that is
    denoted by both a beginning and ending time. v1.2 contains this
    update.

2016-08-19  James Tomkins, Dominic Lowe
  * v1.0: Published TSML 1.0.0 as tsml/1.0 from OGC 15-042r3.

 Note: Check each OGC numbered document for detailed changes.

-----------------------------------------------------------------------

Policies, Procedures, Terms, and Conditions of OGC(r) are available
  http://www.opengeospatial.org/ogc/legal/ .

OGC and OpenGIS are registered trademarks of Open Geospatial Consortium.

Copyright (c) 2015, 2016, 2019, 2021 Open Geospatial Consortium.

-----------------------------------------------------------------------
