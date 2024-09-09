[<img src="./images/logo.png" width="400" height="200"/>](./images/logo.png)

# Entities
[![Build](https://github.com/eclipse-ecsp/entities/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/eclipse-ecsp/entities/actions/workflows/maven-publish.yml)
[![License Compliance](https://github.com/eclipse-ecsp/entities/actions/workflows/license-compliance.yml/badge.svg)](https://github.com/eclipse-ecsp/entities/actions/workflows/license-compliance.yml)

The `entities` project provides the following functionalities to a service. 

-  Event data representation as `AbstractEventData` in event specification 
-  Customized `EventData` types:
    - Exception event data.
    - Blob event data with GPB/JSON encodings.
    - Acknowledgement data, Alert data, Device connection data, device message failure data, device status data, generic data, generic third party data and vehicle notification event data, etc. as different event data types.
-  `EventDataDeSerializer` as a Jackson Deserializer for `EventData` event data 
- Base representation of a database entity in event specification 
- Automatic auditing of the entities with timestamp 
- Abstract representation of a database event data
- Deserializer to de-serialize the event key data
- `EventMapping` of event id & version to event data for deserialization purpose. 
- Device Message representation `DeviceMessage` and Device Message header `DeviceMessageHeader` representation entities. 
- Retries of a device message `RetryRecord` along with tracking the number of retries. 
- Maintaining Vehicle ID to Device ID mapping `VehicleIdDeviceIdMapping` and connection status `VehicleIdDeviceIdStatus`
- Added capability to add `UserContext` on event 
- Scheduling events and create and delete operations on a schedule event along with `Firing` information at time the schedule was fired

# Table of Contents
* [Getting Started](#getting-started)
* [Usage](#usage)
* [How to contribute](#how-to-contribute)
* [Built with Dependencies](#built-with-dependencies)
* [Code of Conduct](#code-of-conduct)
* [Authors](#authors)
* [Security Contact Information](#security-contact-information)
* [Support](#support)
* [Troubleshooting](#troubleshooting)
* [License](#license)
* [Announcements](#announcements)


## Getting Started

To build the project in the local working directory after the project has been cloned/forked, run:

```mvn clean install```

from the command line interface.


### Prerequisites

1. Maven
2. Java 17

### Installation

[How to set up maven](https://maven.apache.org/install.html)

[Install Java](https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows)

### Running the tests

```mvn test```

Or run a specific test

```mvn test -Dtest="TheFirstUnitTest"```

To run a method from within a test

```mvn test -Dtest="TheSecondUnitTest#whenTestCase2_thenPrintTest2_1"```

### Deployment

The `entities` project serves as a library for the services. It is not meant to be deployed as a service in a Cloud environment.

## Usage
Add the following dependency in the target project
```
<dependency>
  <groupId>com.eclipse.ecsp</groupId>
  <artifactId>entities</artifactId>
  <version>1.X.X</version>
</dependency>

```
### Customizing event data

#### Exception event data

To represent an exception event data, the services needs to implement `AbstractIgniteExceptionData` type.

Example:

```java
@EventMapping(id = EventID.IGNITE_EXCEPTION_EVENT, version = Version.V1_0)
public class IgniteExceptionDataV1_0 extends AbstractIgniteExceptionData {}
```

#### Blob event data

To create a blob event data, the services need to implement `AbstractBlobEventData` type along with specifying the encoding

Example:

```java
@EventMapping(id = EventID.BLOBDATA, version = Version.V1_0)
public class BlobDataV1_0 extends AbstractBlobEventData {
    
    public BlobDataV1_0() {
         this.encoding = Encoding.GPB;
    }

}
```

#### Alert event data

To implement an alert event representation, the services need to implement `AlertEventData` type having trip id information.

Example:

```java
@EventMapping(id = EventID.DONGLE_STATUS, version = Version.V1_0)
public class DongleStatusV1_0 extends AlertEventData {}
```

#### Device Message failure event data.

`DeviceMessageFailureEventDataV1_0`class captures the key parameters that can be the cause for `DeviceMessage` failures such as exceeding shoulder tap attempts or retry attempts.
It can also be used to send intermediate status such as retrying a message or retrying shoulder tap.

Structure of device message failure event data:

```java
@EventMapping(id = EventID.DEVICEMESSAGEFAILURE, version = Version.V1_0)
public class DeviceMessageFailureEventDataV1_0 extends AbstractEventData {
    private IgniteEventImpl failedIgniteEvent;
    private int retryAttempts;
    private int shoudlerTapRetryAttempts;
    private boolean deviceDeliveryCutoffExceeded;
    private boolean deviceStatusInactive;
    private DeviceMessageErrorCode errorCode;
}
```

#### Schedule event data

To create scheduled events with the information - schedule id, recurrence type, `Firing` information, the received needs to be of the type `CreateScheduleEventData`

To delete scheduled events, the received needs to be of the type `DeleteScheduleEventData`, which is provided in the schedule ID of the schedule to delete.


#### Custom event data representation

To create a custom event data representation, the services need to implement `AbstractEventData` and create mapping using `EventMapping` interface.
The event mapping is needed to deserialize the event into an `AbstractEventData` type on the basis of the combination of event id and version.

Example:

```java
@EventMapping(id = EventID.VEHICLE_PROFILE_CHANGED_NOTIFICATION_EVENT, version = Version.V1_1)
public class VehicleProfileNotificationEventDataV1_1 extends AbstractEventData {}
```

#### Deserialization

- Event Data deserialization
  
  Deserialization for event data is done on the basis of the combination of event id and version specified on the `EventData` classes by `EventMapping`.
  `EventDataDeSerializer` is introduced as a jackson standard deserializer `StdDeserializer<EventData>` for this purpose.
  It maintains the mapping of the eventId + version combination to the actual deserializing class.
- Ignite Key Deserialization

  `IgniteKey` is deserialized into `IgniteStringKey` using an extended jackson deserializer `IgniteKeySerDe`.

### Customizing database entities

#### Creating a custom entity

To create an entity, services need to extend `AbstractIgniteEntity`. 
If no name is provided to the `Entity` annotation, then the class name becomes the entity name in the database.

Example:

```java
@Entity("scheduleEntries")
public class ScheduleEntry extends AbstractIgniteEntity {
    @Id
    private String id = UUID.randomUUID().toString();
}
```

#### Creating an entity for Ignite Event

An `IgniteEvent` needs to be of the type `AbstractIgniteEvent` to be stored in the database as per the Ignite event specification. While the `EventData` represents the event's structure for Jackson serialization/deserialization, `AbstractIgniteEvent` is the one with attributes for the database.

The following are the attributes stored in the database for an `AbstractIgniteEvent` event: - timezone - dff qualifier - deviceRoutable - user context UserContext info - mqtt topic - device message global topic - event ID - event data - vehicle ID - request ID, message ID, correlation ID, bizTransaction ID

Example:

```java
@Entity
public class IgniteEventImpl extends AbstractIgniteEvent {}
```

#### Creating a device message entity

Device Message is a routable entity wrapper around `IgniteKey` and `IgniteEvent`. It also encapsulates attributes like deviceRoutable and igniteKey.

Some of the attributes of a Device Message include:
   - message - a byte array representation of the actual device message.
   - Ignite Event - using this the byteArray message was generated. This is needed in scenarios where we have to send a feedback on DM failures.
   - feedBackTopic - The kafka topic for sending feedback.
   - DeviceMessageHeader - Other metadata related to the device message, such as messageid, correlation id, request id, vehicle id, and so on.

#### Creating a retry record for device message failure

Device message failures can be retried and the current metadata related to the retries is stored in the database.
To create a `RetryRecord`, services need to initialize it with a `DeviceMessage` and an `IgniteKey`
It also maintains the metadata about how many times the retry has already been attempted.

Example:

```java
    import com.harman.ignite.entities.dma.DeviceMessage;
    import com.harman.ignite.entities.dma.RetryRecord;
    import com.harman.ignite.key.IgniteStringKey;
    
    class Test {
        IgniteKey igniteKey = new IgniteStringKey();
        RetryRecord retryRecord = new RetryRecord(new IgniteStringKey("test"), new DeviceMessage(), 100);
    }
```

#### Auditing entities

Auditing an entity means keeping track of the time when the entity was last updated.
To create an Auditable entity, services must have the following:

```java
public class AuditableEntityTest implements IgniteEntity, AuditableIgniteEntity {

    @Override
    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    @Override
    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
```

#### Creating scheduled entities

Scheduled entities are created by creating objects of `ScheduleEntry` and associating below required attributes:

- schedule id
- vehicle id
- `IgniteEvent` event
- firedCount - the number of times the schedule has been fired
- A list of `Firing` metadata
- delete status - if the schedule has been deleted or not
- task id - 'taskId' of the Kafka Stream thread that processed the CreateScheduleEvent for this ScheduleEntry.
- lastMissedFiringNotificationDts - Represents the last time the caller service is notified about a missed firing of this schedule.

## Built With Dependencies

|                                                  Dependency                                                   | Purpose                                                                                                                                                 |
|:-------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
|                                      [Logback](https://logback.qos.ch/)                                       | Provides logging support                                                                                                                                |
|                       [Commons Lang3](https://commons.apache.org/proper/commons-lang/)                        | Provides a host of helper utilities for the java.lang API                                                                                               |
|                          [Morphia Core](https://morphia.dev/morphia/2.1/index.html)                           | ODM for MongoDB                                                                                                                                         |
|                [Mongo DB Driver Sync](https://www.mongodb.com/docs/drivers/java/sync/current/)                | MongoDB Java Driver                                                                                                                                     |
|                              [Mongo DB Driver legacy](https://maven.apache.org/)                              | MongoDB Support for the Legacy API                                                                                                                      |
|                            [Hibernate Validator](https://hibernate.org/validator/)                            | Express and validate application constraints                                                                                                            |
|                                [Jackson](https://github.com/FasterXML/jackson)                                | serialization and deserialization support                                                                                                               |
|                              [Guava](https://guava.dev/releases/18.0/api/docs/)                               | Google Core Libraries for Java                                                                                                                          |
|                               [Open POJO](https://github.com/OpenPojo/openpojo)                               | POJO validator                                                                                                                                          |
|           [Javax.el](https://docs.oracle.com/javaee%2F7%2Fapi%2F%2F/javax/el/package-summary.html)            | Expression Language support                                                                                                                             |
|                                      [Junit](https://junit.org/junit5/)                                       | Testing framework                                                                                                                                       |
|                                     [Mockito](https://site.mockito.org/)                                      | Test Mocking framework                                                                                                                                  |
| [Reflections](https://www.javadoc.io/doc/org.reflections/reflections/0.9.10/org/reflections/Reflections.html) | Scans the classpath, indexes the metadata, allows to query it on runtime and may save and collect that information for many modules within the project. |
|     [Jetty Util](https://eclipse.dev/jetty/javadoc/jetty-12/org/eclipse/jetty/util/package-summary.html)      | Utility for Eclipse Jetty server                                                                                                                        |

## How to contribute

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on our contribution guidelines, and the process for submitting pull requests to us.

## Code of Conduct

Please read [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md) for details on our code of conduct.

## Authors

* **Kaushal Arora** - *Initial work* 
* **Ashish Kumar Singh** - *Coding guidelines*

See also the list of [contributors](https://github.com/eclipse-ecsp/entities/contributors) who participated in this project.

## Security Contact Information

Please read [SECURITY.md](./SECURITY.md) to raise any security related issues.

## Support

Contact the project developers via the project's "dev" list - https://accounts.eclipse.org/mailing-list/ecsp-dev

## Troubleshooting

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) for details on how to raise an issue and submit a pull request to us.

## License

This project is licensed under the Apache-2.0 License - see the [LICENSE](./LICENSE) file for details.

## Announcements

All updates to this library are documented in our [Release notes](./release_notes.txt) and [releases](https://github.com/eclipse-ecsp/entities/releases)
For the versions available, see the [tags on this repository](https://github.com/eclipse-ecsp/entities/tags).
