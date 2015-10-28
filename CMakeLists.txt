cmake_minimum_required (VERSION 2.8)

# Title
set(PROJNAME "firststep")
project (${PROJNAME})

# Native code

#	N1. Titles
set(LIBNAME ${PROJNAME})
find_package(JNI)

#	N2. Options and flags
if(MINGW)
	SET(CMAKE_SHARED_LIBRARY_PREFIX "")
	SET(CMAKE_SHARED_MODULE_PREFIX "")
endif(MINGW)

IF(APPLE)
	SET(CMAKE_C_FLAGS "-std=gnu99 -fPIC")
	SET(CMAKE_CXX_FLAGS "-fPIC")
	SET(MACOSX_RPATH FALSE)
	SET(EXTRA_LIBS "-framework Cocoa -framework CoreAudio -framework AudioToolbox -framework AudioUnit -framework CoreVideo -framework IOKit")
	SET(CMAKE_EXE_LINKER_FLAGS "-std=gnu99")
	add_definitions( -DUSE_GLEW_GL3 )
ELSEIF(WIN32)
	SET(CMAKE_C_FLAGS "-std=gnu99")
	SET(CMAKE_CXX_FLAGS "")
	SET(EXTRA_LIBS "-lglu32 -lopengl32 -lwinmm")
	SET(CMAKE_EXE_LINKER_FLAGS "-std=gnu99 -static -static-libgcc -static-libstdc++ -mwindows")
	SET(CMAKE_SHARED_LINKER_FLAGS "-std=gnu99 -static -static-libgcc -static-libstdc++ -mwindows")
	add_definitions( -DUSE_GLEW_GL3 )
ELSE()
	SET(CMAKE_C_FLAGS "-std=gnu99 -fPIC")
	SET(CMAKE_CXX_FLAGS "-fPIC")
	SET(GLFW_USE_EGL ON)
	SET(GLFW_CLIENT_LIBRARY "glesv2")
	SET(EXTRA_LIBS "-lasound -lXrandr -lXi -lXinerama -lXcursor -lEGL")
	add_definitions( -DUSE_EGL_GLES2 )
ENDIF()

#	N3. Sources
file(GLOB SOURCES_C "${CMAKE_CURRENT_SOURCE_DIR}/src/native/*.c")
file(GLOB SOURCES_CPP "${CMAKE_CURRENT_SOURCE_DIR}/src/native/*.cpp")
file(GLOB SOURCES_PA_JNI_C "${CMAKE_CURRENT_SOURCE_DIR}/src/native/portaudio/*.c")
set(SOURCES
	${SOURCES_C} 
	${SOURCES_CPP}
	${SOURCES_PA_JNI_C}
	"${CMAKE_CURRENT_SOURCE_DIR}/nanovg/src/nanovg.c" 
)

#	N4. Includes
include_directories(
	${CMAKE_CURRENT_SOURCE_DIR}/src 
	${CMAKE_CURRENT_SOURCE_DIR}/include
	${CMAKE_CURRENT_SOURCE_DIR}/nanovg/src
	${CMAKE_CURRENT_SOURCE_DIR}/glfw/include
	${CMAKE_CURRENT_SOURCE_DIR}/portaudio/include
	${CMAKE_CURRENT_SOURCE_DIR}/glew-1.13.0/include
	${JAVA_INCLUDE_PATH}
	${JAVA_INCLUDE_PATH2}
)

#	N5. Linking
add_library(
	${LIBNAME}
	SHARED 
	${SOURCES}
)
IF(APPLE)
	target_link_libraries(${LIBNAME} glew_s glfw portaudio_static ${GLFW_LIBRARIES} ${EXTRA_LIBS})
ELSEIF(WIN32)
	target_link_libraries(${LIBNAME} glew_s glfw portaudio_static ${GLFW_LIBRARIES} ${EXTRA_LIBS})
ELSE()
	target_link_libraries(${LIBNAME} glfw portaudio_static ${GLFW_LIBRARIES} ${EXTRA_LIBS})
	# No GLEW with GLES 
ENDIF()

# Java code

# 	J1. Titles
set(JARNAME "${PROJNAME}-java")
find_package(Java REQUIRED)
include(cmake_patches/UseJavaCustom.cmake)

# 	J2. Compiler options
set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.7" "-target" "1.7")

# 	J3. Sources and java resources
set(CMAKE_CURRENT_SOURCE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/src/java")
file(GLOB_RECURSE SOURCES_JAVA "*.java")

# 	J4. Linking
add_jar(${JARNAME} ${SOURCES_JAVA})

#	J5 (optional). Printing out the paths
get_target_property(_jarFile ${JARNAME} JAR_FILE)
get_target_property(_classDir ${JARNAME} CLASSDIR)
message(STATUS "[${PROJNAME}] Jar file: ${_jarFile}")
message(STATUS "[${PROJNAME}] Classes compiled to: ${_classDir}")

# Dependencies

#	D1. Subprojects (with flags)
SET(GLFW_BUILD_DOCS OFF CACHE BOOL "build docs")
SET(GLFW_BUILD_EXAMPLES OFF CACHE BOOL "build examples")
SET(GLFW_BUILD_TESTS OFF CACHE BOOL "build tests")
add_subdirectory("glfw")

IF(WIN32)
	SET(PA_USE_WASAPI OFF CACHE BOOL "wasapi")
	SET(PA_USE_WDMKS OFF CACHE BOOL  "wdmks")
ENDIF()

IF(APPLE)
	add_subdirectory(glew-1.13.0/build/cmake)
ELSEIF(WIN32)
	add_subdirectory(glew-1.13.0/build/cmake)
ELSE()
	# No GLEW with GLES 
ENDIF()
add_subdirectory("portaudio")

#	D2. Target dependencies
add_dependencies(${PROJNAME} ${JARNAME})
add_dependencies(${PROJNAME} ${LIBNAME})
IF(APPLE)
	add_dependencies(${PROJNAME} glew_s)
ELSEIF(WIN32)
	add_dependencies(${PROJNAME} glew_s)
ELSE()
	# No GLEW with GLES 
ENDIF()

