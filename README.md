# Utility - Azure Blob Storage with Azure APIs

## Description
This spring based java project has been written to create a task to test the utilities written for Azure Blob Storage manipulation.

Using these utilities, one can:
- Upload file
- Download file
- Delete file
- Create container
- Generate SAS URL
- List file/folder from specified path

and so on.


## Use Case
These utilities can be used to manage the files and containers on Azure Blob Storage with Java APIs in easily manner.

In this utility project there are three utility class used:
1. M1AzureStorageUtils - This utility class is using the "com.microsoft.azure:azure-storage:8.6.4" library provided by Microsoft Azure. It provides rich set of APIs to manage Storage account from the Java code

It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator

2. M2AzureStorageUtils - This utility class is using the "com.microsoft.azure:azure-storage-blob:11.0.0" library provided by Microsoft Azure. It provide APIs backed by HTTP protocol and has observed comparatively slow than the azure-storage APIs.

It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator

3. M3AzureStorageUtils - This utility class usage the same azure library as M1AzureStorageUtils but it has been enhanced to support local file system as well along with AzureStorage.

It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator, metadata association with files, generate SAS token for container, local upload, download, delete


## Technology Stack
- Java 8 or later
- Spring boot 2.x
- Maven as build tool
- Azure Blob Storage


## Build
Maven has been used as build tool for this project with default Spring boot build plugin.
Hence, we can use below maven code to build the runnable jar as microservice:

```
mvn clean package
```

## Deployment 
These utilities can be used as library in the domain projects wherever Azure Blob Storage requred. 

## Configuraiton
Below are the configuration to be used with Azure Blob Storage:

```
ConnectionString
```
When Azure Blob Storage created, a connection string can be obtained from the Azure Portal (https://portal.azure.com). This connection string contains the user, security key, protocl and endpont suffix information.

In case using M3AzureStorageUtils, and want to use local directory instead of azure.
There are three part of this connection string:
LOCAL - Indicate local directory will be used instead of azure
Path - Path of local directory where file will be uploaded/download/deleted in case of local
URL - SAS url to load resource in case of local



```
Container Name
```
Containers are the buckets provided by the Azure to segregate the files into Azure Blob Storage. 


```
Path
```
Path of the file or directory which contains the data.

