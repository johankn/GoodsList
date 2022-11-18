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

  opens rest.goodslist to spring.beans, spring.context, spring.web, spring.core;
}
