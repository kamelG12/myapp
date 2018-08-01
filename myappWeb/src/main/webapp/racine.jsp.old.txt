<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>racine carrée que j'aime</title>
</head>
<body>
<f:view>
    <h:messages /> <!-- h:messages affiche des messages d'erreur si ils existent -->
    <h:form>
       x: <h:inputText  value="#{calculBean.x}"></h:inputText> <br/>
       <h:commandButton value="calculer racine carrée" 
                        action="#{calculBean.calculerRacine}" />
    </h:form>
    res: <h:outputText value="#{calculBean.res }"></h:outputText>
</f:view>
</body>
</html>