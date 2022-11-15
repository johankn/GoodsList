module app.restserver {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires spring.core;
  requires spring.context;
  requires spring.data.commons;
  requires spring.web;
  requires spring.webmvc;

  requires app.core;
  //requires com.fasterxml.jackson.databind;

  //opens app.restserver to spring.beans, spring.context, spring.web, spring.core;
  //exports app.restserver.properties to spring.beans, spring.boot;
}
