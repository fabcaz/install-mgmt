package com.example.installmgmt.controllers;

// to make testing and updates easier
public class ControllerUris {

  // variable naming convention:
  // contoller::[Http_method]::[single|multiple]objects::handler_purpose

  public static final String API_V1 = "/api/v1";

  // PersonController
  public static final String PERSON_REQUESTMAPPING = API_V1 + "/person";
  public static final String PERSON_GET_SING_FIND_BY_ID = "/profile/{id}";
  public static final String PERSON_POST_SING_CREATE_OR_UPDATE = "/new";

  // InstallRequestController
  public static final String INSTALLREQUEST_REQUESTMAPPING = API_V1 + "/installrequest";
  public static final String INSTALLREQUEST_GET_SING_FIND_BY_ID = "/{installrequestid}";
  public static final String INSTALLREQUEST_GET_MULT_FIND_BY_ID = "";
  public static final String INSTALLREQUEST_POST_SING_CREATE = "/new";
  public static final String INSTALLREQUEST_POST_SING_UPDATE = "/update/{installrequestid}";



}
