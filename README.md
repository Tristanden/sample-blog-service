# sample-blog-service

## Description

This project is a sample "microservice-like" architecture using Springboot and maven. It contains two sub-projects:

* iam is a fake service providing user identification and authentication features
* blog-service is a fake service providing articles store and retrieve features

Both services expose REST API and blog-service is a client of iam API.

## Goal of this project

The goal of this project is to demonstrate how Maven and Swagger-codegen can be used to 

* publish iam API Swagger description as a maven artefact
* use maven and swagger-codegen to automatically generate Java code from this artefact
* embed the generated code into a Springboot project

## More details

A blog article describing this project a bit further can be found here (in french): FIXME

