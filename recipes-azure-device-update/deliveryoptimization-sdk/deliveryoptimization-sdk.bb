# Build and install the DO Client CPP SDK.

# Environment variables that can be used to configure the behaviour of this recipe.
# BUILD_TYPE            Changes the type of build produced by this recipe.
#                       Valid values are Debug, Release, RelWithDebInfo, and MinRelSize.
#                       These values are the same as the CMAKE_BUILD_TYPE variable.

LICENSE = "CLOSED"

DO_GIT_BRANCH ?= "main"

DO_SRC_URI ?= "gitsm://github.com/microsoft/do-client"
SRC_URI = "${DO_SRC_URI};branch=${DO_GIT_BRANCH}"

DO_GIT_COMMIT ?= "b61de2d347c8032562056b18f90ec710e531baf8"
SRCREV = "${DO_GIT_COMMIT}"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git" 

DEPENDS = "boost curl libproxy msft-gsl"

inherit cmake

BUILD_TYPE ?= "Debug"
EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=${BUILD_TYPE}"
# Specify build is for the sdk lib
EXTRA_OECMAKE += "-DDO_INCLUDE_SDK=ON"
# Don't build DO tests.
EXTRA_OECMAKE += "-DDO_BUILD_TESTS=OFF"

# cpprest installs its config.cmake file in a non-standard location.
# Tell cmake where to find it.
EXTRA_OECMAKE += "-Dcpprestsdk_DIR=${WORKDIR}/recipe-sysroot/usr/lib/cmake"

BBCLASSEXTEND = "native nativesdk"
