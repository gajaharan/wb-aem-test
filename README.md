# Whitbread AEM Test


The solution package is done in AEM 6.2 (Using HTL and TouchUI) and have met the following criteria:

1. Create a Page template with with header, footer and a body area with two Iparsys
2. Create a custom Multi-Field component that contains: Title, Rich Text Area, Image
3. Create a custom component that consume an OSGi bundle

However encountered issues with AEM 6.2 and assumptions.
* I believe the Touch UI Mult-field (using ASC-common) has an issue with using image (granite fileupload) as sometimes you will see drop area and sometimes you will see button to upload a file.
* Touch UI Dialog shows the fields out of alignment due to dialog size. The Dialog size can only be changed via javascript.
* File upload does not work properly in multi field. This is because the feature is not supported. please see link below
https://github.com/Adobe-Consulting-Services/acs-aem-commons/issues/1035
*Will try to use Image component when back from holiday.
* Also ASC saves the file name property to an string array instead of the file reference.
* I have not done class ui dialog for the components as AEM is moving towards Touch UI.

There is already a test page setup and you will be able to create a new template based on requirement 1 above.
http://localhost:4502/editor.html/content/wb-aem-test-app/wb-test.html


## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, ie on the AEM server. To test, execute:

    mvn clean integration-test -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

    in the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.


## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
