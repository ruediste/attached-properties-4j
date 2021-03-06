[![Build Status](https://travis-ci.org/ruediste/attached-properties-4j.svg?branch=master)](https://travis-ci.org/ruediste/attached-properties-4j)

# attached-properties-4j
Java library to attach properties to objects.

## Setup
To use the latest release, add 

    <dependency>
        <groupId>com.github.ruediste</groupId>
        <artifactId>attached-properties-4j</artifactId>
        <version>0.0.1</version>
    </dependency>

to your `pom.xml`. To use the latest snapshot version, add

	<repositories>
		<repository>
			<id>oss.sonatype.org-snapshot</id>
			<url>http://oss.sonatype.org/content/repositories/snapshots</url>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
	     	</snapshots>
		</repository>
	</repositories>
	...
	<dependencies>
		<dependency>
			<groupId>com.github.ruediste</groupId>
			<artifactId>attached-properties-4j</artifactId>
			<version>0.0.2-SNAPSHOT</version>
		</dependency>
	</dependencies>

to your `pom.xml`.

## Usage
To attach properties to an object, the class of the object has to implement **AttachedPropertyBearer**. Then instantiate **AttachedProperties** and typically store them in static final fields. Afterwards, you can set the property on an object.

    	class TestBearer extends AttachedPropertyBearerBase {}
	final static AttachedProperty<TestBearer, String> test = new AttachedProperty<>("test");
	...
	test.isSet(bearer); // check if property is set on an instance
	test.set(bearer, "Hello"); // set the property
	test.get(bearer); // get the property

The name of the property constructor is used only to generate a nice toString() representation; 

## Creating Releases
During development, the version is always set to the next version with the -SNAPSHOT suffix.

To build a release, first the `~/.m2/settings.xml` file has to be set up using the Sonatype Jira credentials:

	<?xml version="1.0" encoding="UTF-8"?>
	<settings>
		<servers>
			<server>
				<id>ossrh</id>
				<username>your-jira-id</username>
				<password>your-jira-pwd</password>
			</server>
		</servers>
	</settings>
	
Then a release can be perfomed with

	mvn release:clean release:prepare
	
by answering the prompts for versions and tags, followed by
	
	mvn release:perform

Finally, put the release to the central repository by

	...
	cd target/checkout
	mvn nexus-staging:release -P release

Last but not least, do not forget to bump the versions in the examples in this file.

### Deploying Snapshots
When the `~/.m2/settings.xml` is present, a simple

    mvn clean deploy -Prelease

will deploy a snapshot release to the sontatype snaphshot repository.
