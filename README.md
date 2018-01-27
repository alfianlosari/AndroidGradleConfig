# Gradle for Android and Java Final Project

In this project, you will create an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## Why this Project

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.

## Instruction before running

### Setup GCE

Make sure to install gcloud sdk locally in your machine first

Start or stop your local server by using the gradle tasks
backend => Tasks => app engine standard environment => appengineStart or appengineStop

Once your local GCE server is started you should see the following at 
[localhost:8080](http://localhost:8080)

<img src="https://raw.githubusercontent.com/GoogleCloudPlatform/gradle-appengine-templates/77e9910911d5412e5efede5fa681ec105a0f02ad/doc/img/devappserver-endpoints.png">


### Functional Testing

Functional testing is located in androidTest directory under the name MainActivityTest
The test uses the idling resource to wait for asynchronous network operation when retrieving joke
from GCE. The test simulates button press, trigger async task, perform intent, and check the textview text
to match the joke string