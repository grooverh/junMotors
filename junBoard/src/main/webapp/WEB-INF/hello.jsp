<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "tiles" uri = "http://tiles.apache.org/tags-tiles" %>

<% request.setAttribute("greeting", "Hi..greetiing"); %>

<tiles:insertDefinition name = "hello" />
 

