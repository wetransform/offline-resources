#!/bin/sh

#
# Note: Requires the slides to run on localhost (see run.sh)
#

docker run --rm -t --net=host -v "`pwd`":/slides astefanutti/decktape http://localhost:8000 slides.pdf
